package com.bugvm.bouncycastle.jcajce.provider.asymmetric;

// BEGIN android-removed
// import com.bugvm.bouncycastle.asn1.eac.EACObjectIdentifiers;
// import com.bugvm.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
// END android-removed
import com.bugvm.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.bugvm.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import com.bugvm.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import com.bugvm.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class EC
{
    private static final String PREFIX = "com.bugvm.bouncycastle.jcajce.provider.asymmetric" + ".ec.";

    public static class Mappings
        extends AsymmetricAlgorithmProvider
    {
        public Mappings()
        {
        }

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyAgreement.ECDH", PREFIX + "KeyAgreementSpi$DH");
            // BEGIN android-removed
            // provider.addAlgorithm("KeyAgreement.ECDHC", PREFIX + "KeyAgreementSpi$DHC");
            // provider.addAlgorithm("KeyAgreement.ECMQV", PREFIX + "KeyAgreementSpi$MQV");
            // provider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, PREFIX + "KeyAgreementSpi$DHwithSHA1KDF");
            // provider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, PREFIX + "KeyAgreementSpi$MQVwithSHA1KDF");
            // END android-removed

            registerOid(provider, X9ObjectIdentifiers.id_ecPublicKey, "EC", new KeyFactorySpi.EC());
            // TODO Should this be an alias for ECDH?
            registerOid(provider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC", new KeyFactorySpi.EC());
            // BEGIN android-removed
            // registerOid(provider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
            // END android-removed

            // BEGIN android-removed
            // registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.id_ecPublicKey, "EC");
            // // TODO Should this be an alias for ECDH?
            // registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC");
            // registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "EC");
            // END android-removed

            provider.addAlgorithm("KeyFactory.EC", PREFIX + "KeyFactorySpi$EC");
            // BEGIN android-removed
            // provider.addAlgorithm("KeyFactory.ECDSA", PREFIX + "KeyFactorySpi$ECDSA");
            // provider.addAlgorithm("KeyFactory.ECDH", PREFIX + "KeyFactorySpi$ECDH");
            // provider.addAlgorithm("KeyFactory.ECDHC", PREFIX + "KeyFactorySpi$ECDHC");
            // provider.addAlgorithm("KeyFactory.ECMQV", PREFIX + "KeyFactorySpi$ECMQV");
            // END android-removed

            provider.addAlgorithm("KeyPairGenerator.EC", PREFIX + "KeyPairGeneratorSpi$EC");
            // BEGIN android-removed
            // provider.addAlgorithm("KeyPairGenerator.ECDSA", PREFIX + "KeyPairGeneratorSpi$ECDSA");
            // provider.addAlgorithm("KeyPairGenerator.ECDH", PREFIX + "KeyPairGeneratorSpi$ECDH");
            // provider.addAlgorithm("KeyPairGenerator.ECDHC", PREFIX + "KeyPairGeneratorSpi$ECDHC");
            // provider.addAlgorithm("KeyPairGenerator.ECIES", PREFIX + "KeyPairGeneratorSpi$ECDH");
            // provider.addAlgorithm("KeyPairGenerator.ECMQV", PREFIX + "KeyPairGeneratorSpi$ECMQV");
            //
            // provider.addAlgorithm("Cipher.ECIES", PREFIX + "IESCipher$ECIES");
            // provider.addAlgorithm("Cipher.ECIESwithAES", PREFIX + "IESCipher$ECIESwithAES");
            // provider.addAlgorithm("Cipher.ECIESWITHAES", PREFIX + "IESCipher$ECIESwithAES");
            // provider.addAlgorithm("Cipher.ECIESwithDESEDE", PREFIX + "IESCipher$ECIESwithDESede");
            // provider.addAlgorithm("Cipher.ECIESWITHDESEDE", PREFIX + "IESCipher$ECIESwithDESede");
            // END android-removed

            provider.addAlgorithm("Signature.ECDSA", PREFIX + "SignatureSpi$ecDSA");
            provider.addAlgorithm("Signature.NONEwithECDSA", PREFIX + "SignatureSpi$ecDSAnone");

            provider.addAlgorithm("Alg.Alias.Signature.SHA1withECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAwithSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WITHECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAWITHSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WithECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAWithSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.1.2.840.10045.4.1", "ECDSA");
            // BEGIN android-removed
            // provider.addAlgorithm("Alg.Alias.Signature." + TeleTrusTObjectIdentifiers.ecSignWithSha1, "ECDSA");
            // END android-removed

            // BEGIN android-removed
            // addSignatureAlgorithm(provider, "SHA224", "ECDSA", PREFIX + "SignatureSpi$ecDSA224", X9ObjectIdentifiers.ecdsa_with_SHA224);
            // END android-removed
            addSignatureAlgorithm(provider, "SHA256", "ECDSA", PREFIX + "SignatureSpi$ecDSA256", X9ObjectIdentifiers.ecdsa_with_SHA256);
            addSignatureAlgorithm(provider, "SHA384", "ECDSA", PREFIX + "SignatureSpi$ecDSA384", X9ObjectIdentifiers.ecdsa_with_SHA384);
            addSignatureAlgorithm(provider, "SHA512", "ECDSA", PREFIX + "SignatureSpi$ecDSA512", X9ObjectIdentifiers.ecdsa_with_SHA512);
            // BEGIN android-removed
            // addSignatureAlgorithm(provider, "RIPEMD160", "ECDSA", PREFIX + "SignatureSpi$ecDSARipeMD160",TeleTrusTObjectIdentifiers.ecSignWithRipemd160);
            //
            // provider.addAlgorithm("Signature.SHA1WITHECNR", PREFIX + "SignatureSpi$ecNR");
            // provider.addAlgorithm("Signature.SHA224WITHECNR", PREFIX + "SignatureSpi$ecNR224");
            // provider.addAlgorithm("Signature.SHA256WITHECNR", PREFIX + "SignatureSpi$ecNR256");
            // provider.addAlgorithm("Signature.SHA384WITHECNR", PREFIX + "SignatureSpi$ecNR384");
            // provider.addAlgorithm("Signature.SHA512WITHECNR", PREFIX + "SignatureSpi$ecNR512");
            //
            // addSignatureAlgorithm(provider, "SHA1", "CVC-ECDSA", PREFIX + "SignatureSpi$ecCVCDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
            // addSignatureAlgorithm(provider, "SHA224", "CVC-ECDSA", PREFIX + "SignatureSpi$ecCVCDSA224", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
            // addSignatureAlgorithm(provider, "SHA256", "CVC-ECDSA", PREFIX + "SignatureSpi$ecCVCDSA256", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
            // addSignatureAlgorithm(provider, "SHA384", "CVC-ECDSA", PREFIX + "SignatureSpi$ecCVCDSA384", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
            // addSignatureAlgorithm(provider, "SHA512", "CVC-ECDSA", PREFIX + "SignatureSpi$ecCVCDSA512", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
            // END android-removed
        }
    }
}
