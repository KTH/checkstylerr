/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public enum RelocMode {
  RelocDefault,
  RelocStatic,
  RelocPIC,
  RelocDynamicNoPic;

  public final int swigValue() {
    return swigValue;
  }

  public static RelocMode swigToEnum(int swigValue) {
    RelocMode[] swigValues = RelocMode.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (RelocMode swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + RelocMode.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private RelocMode() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private RelocMode(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private RelocMode(RelocMode swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

