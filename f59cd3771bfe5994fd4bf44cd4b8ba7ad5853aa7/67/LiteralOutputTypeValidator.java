/**
 *
 * $Id$
 */
package net.opengis.wps10.validation;

import net.opengis.ows11.DomainMetadataType;

import net.opengis.wps10.SupportedUOMsType;

/**
 * A sample validator interface for {@link net.opengis.wps10.LiteralOutputType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface LiteralOutputTypeValidator {
  boolean validate();

  boolean validateDataType(DomainMetadataType value);
  boolean validateUOMs(SupportedUOMsType value);
}
