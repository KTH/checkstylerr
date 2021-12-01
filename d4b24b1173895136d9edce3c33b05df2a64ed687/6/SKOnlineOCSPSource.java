/* DigiDoc4J library
 *
 * This software is released under either the GNU Library General Public
 * License (see LICENSE.LGPL).
 *
 * Note that the only valid version of the LGPL license as far as this
 * project is concerned is the original GNU Library General Public License
 * Version 2.1, February 1999
 */

package org.digidoc4j.impl;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.OCSPReqBuilder;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.digidoc4j.Configuration;
import org.digidoc4j.Constant;
import org.digidoc4j.exceptions.ConfigurationException;
import org.digidoc4j.exceptions.DigiDoc4JException;
import org.digidoc4j.exceptions.SignatureVerificationException;
import org.digidoc4j.exceptions.TechnicalException;
import org.digidoc4j.impl.asic.SkDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.europa.esig.dss.DSSException;
import eu.europa.esig.dss.DSSRevocationUtils;
import eu.europa.esig.dss.DSSUtils;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.KSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.x509.CertificateToken;
import eu.europa.esig.dss.x509.ocsp.OCSPSource;
import eu.europa.esig.dss.x509.ocsp.OCSPToken;

/**
 * SK OCSP source location.
 */
public abstract class SKOnlineOCSPSource implements OCSPSource {

  public static String OID_OCSP_SIGNING = "1.3.6.1.5.5.7.3.9";
  private static final Logger LOGGER = LoggerFactory.getLogger(SKOnlineOCSPSource.class);

  private SkDataLoader dataLoader;
  private Configuration configuration;

  /**
   * SK Online OCSP Source constructor
   *
   * @param configuration configuration to use for this source
   */
  public SKOnlineOCSPSource(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public OCSPToken getRevocationToken(CertificateToken certificateToken, CertificateToken issuerCertificateToken) {
    LOGGER.debug("Getting OCSP token ...");
    try {
      if (this.dataLoader == null) {
        throw new TechnicalException("Data loader is null");
      }
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Querying by DSS ID <{}>", certificateToken.getDSSIdAsString());
      }
      CertificateID certificateID = DSSRevocationUtils.getOCSPCertificateID(certificateToken, issuerCertificateToken);
      String accessLocation = this.getAccessLocation(certificateToken.getCertificate());
      Extension nonceExtension = this.createNonce(certificateToken.getCertificate());
      BasicOCSPResp response = (BasicOCSPResp) new OCSPResp(this.dataLoader.post(accessLocation,this.buildRequest(certificateID, nonceExtension))).getResponseObject();
      if (response == null) {
        LOGGER.warn("Basic OCSP response is empty");
        return null;
      }
      this.verifyResponse(response);
      this.checkNonce(response, nonceExtension);
      OCSPToken token = new OCSPToken();
      token.setBasicOCSPResp(response);
      token.setCertId(certificateID);
      token.setSourceURL(accessLocation);
      token.extractInfo();
      if (token.getThisUpdate() == null) {
        LOGGER.warn("No best single match of OCSP response found");
        return null;
      }
      return token;
    } catch (DSSException e) {
      throw e;
    } catch (Exception e) {
      throw new DSSException(e);
    }
  }

  /**
   * Returns SK OCSP source location.
   *
   * @param certificate
   * @return OCSP source location
   */
  public String getAccessLocation(X509Certificate certificate) {
    if (getConfiguration() != null) {
      return getConfiguration().getOcspSource();
    }
    return Constant.Test.OCSP_SOURCE;
  }

  /*
   * RESTRICTED METHODS
   */

  protected abstract Extension createNonce(X509Certificate certificate);

  protected void checkNonce(BasicOCSPResp response, Extension expectedNonceExtension) {
    Extension extension = response.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
    DEROctetString expectedNonce = (DEROctetString) expectedNonceExtension.getExtnValue();
    DEROctetString receivedNonce = (DEROctetString) extension.getExtnValue();
    if (!receivedNonce.equals(expectedNonce)) {
      throw new DigiDoc4JException(
              String.format("The OCSP request was the victim of replay attack (nonce sent <%s>, nonce received <%s>)",
                      expectedNonce, receivedNonce));
    }
  }

  protected byte[] buildRequest(final CertificateID certificateID, Extension nonceExtension) throws DSSException {
    try {
      LOGGER.debug("Building OCSP request ...");
      OCSPReqBuilder builder = new OCSPReqBuilder();
      builder.addRequest(certificateID);
      if (nonceExtension != null) {
        builder.setRequestExtensions(new Extensions(nonceExtension));
      }
      if (this.configuration.hasToBeOCSPRequestSigned()) {
        LOGGER.info("Using signed OCSP request ...");
        JcaContentSignerBuilder signerBuilder = new JcaContentSignerBuilder("SHA1withRSA");
        if (!this.configuration.isOCSPSigningConfigurationAvailable()) {
          throw new ConfigurationException("Configuration needed for OCSP request signing is not complete");
        }
        DSSPrivateKeyEntry privateKeyEntry = this.getOCSPAccessCertificatePrivateKey();
        X509Certificate signingCertificate = privateKeyEntry.getCertificate().getCertificate();
        builder.setRequestorName(new GeneralName(new JcaX509CertificateHolder(signingCertificate).getSubject()));
        return builder.build(signerBuilder.build(((KSPrivateKeyEntry) privateKeyEntry).getPrivateKey()),
                new X509CertificateHolder[]{new X509CertificateHolder(signingCertificate.getEncoded())}).getEncoded();
      }
      return builder.build().getEncoded();
    } catch (Exception e) {
      throw new DSSException(e);
    }
  }



  protected void verifyResponse(BasicOCSPResp response) throws IOException {
    List<X509CertificateHolder> holders = Arrays.asList(response.getCerts());
    if (CollectionUtils.isNotEmpty(holders)) {
      for (X509CertificateHolder holder : holders) {
        CertificateToken token = DSSUtils.loadCertificate(holder.getEncoded());
        verifyOcspResponderCertificate(token);
        try {
          ContentVerifierProvider provider = new JcaContentVerifierProviderBuilder().setProvider("BC").build(new X509CertificateHolder(token.getEncoded()));
          if (!response.isSignatureValid(provider)) {
            throw new SignatureVerificationException("OCSP response signature is invalid");
          }
        } catch (SignatureVerificationException e) {
          throw e;
        } catch (Exception e) {
          throw new SignatureVerificationException("Unable to verify response signature", e);
        }
      }
    } else {
      if (!this.configuration.isTest()) {
        LOGGER.warn("OCSP response signature will not be verified. No response certificates has been found");
      }
    }
  }

  protected void verifyOcspResponderCertificate(CertificateToken token) {
    List<CertificateToken> tokens = configuration.getTSL().get(token.getCertificate().getSubjectX500Principal());
    if (CollectionUtils.isEmpty(tokens)) {
      throw new SignatureVerificationException(String.format("OCSP response certificate <%s> match is not found in TSL", token.getDSSIdAsString()));
    }
    try {
      if (!token.getCertificate().getExtendedKeyUsage().contains(OID_OCSP_SIGNING)) {
        throw new SignatureVerificationException(String.format("OCSP response certificate <%s> does not have 'OCSPSigning' extended key usage", token.getDSSIdAsString()));
      }
    } catch (CertificateParsingException e) {
        throw new SignatureVerificationException(String.format("Error on verifying 'OCSPSigning' extended key usage for OCSP response certificate <%s>", token.getDSSIdAsString()), e);
    }
  }

  protected DSSPrivateKeyEntry getOCSPAccessCertificatePrivateKey() throws IOException {
    Pkcs12SignatureToken signatureTokenConnection = new Pkcs12SignatureToken(
            this.configuration.getOCSPAccessCertificateFileName(), new KeyStore.PasswordProtection(this.configuration
            .getOCSPAccessCertificatePassword()));
    return signatureTokenConnection.getKeys().get(0);
  }

  /*
   * ACCESSORS
   */

  /**
   * Gets configuration
   *
   * @return Configuration
   */
  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * Gets data loader
   *
   * @return SkDataLoader
   */
  public SkDataLoader getDataLoader() {
    return dataLoader;
  }

  /**
   * Define data loader.
   *
   * @param dataLoader Data loader object to be used.
   */
  public void setDataLoader(SkDataLoader dataLoader) {
    this.dataLoader = dataLoader;
  }

}