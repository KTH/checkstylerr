/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public enum CallConv {
  CCallConv(0),
  FastCallConv(8),
  ColdCallConv(9),
  WebKitJSCallConv(12),
  AnyRegCallConv(13),
  X86StdcallCallConv(64),
  X86FastcallCallConv(65);

  public final int swigValue() {
    return swigValue;
  }

  public static CallConv swigToEnum(int swigValue) {
    CallConv[] swigValues = CallConv.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (CallConv swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + CallConv.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private CallConv() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private CallConv(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private CallConv(CallConv swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

