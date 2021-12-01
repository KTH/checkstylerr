/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public enum Attribute {
  ZExtAttribute(1 << 0),
  SExtAttribute(1 << 1),
  NoReturnAttribute(1 << 2),
  InRegAttribute(1 << 3),
  StructRetAttribute(1 << 4),
  NoUnwindAttribute(1 << 5),
  NoAliasAttribute(1 << 6),
  ByValAttribute(1 << 7),
  NestAttribute(1 << 8),
  ReadNoneAttribute(1 << 9),
  ReadOnlyAttribute(1 << 10),
  NoInlineAttribute(1 << 11),
  AlwaysInlineAttribute(1 << 12),
  OptimizeForSizeAttribute(1 << 13),
  StackProtectAttribute(1 << 14),
  StackProtectReqAttribute(1 << 15),
  Alignment(31 << 16),
  NoCaptureAttribute(1 << 21),
  NoRedZoneAttribute(1 << 22),
  NoImplicitFloatAttribute(1 << 23),
  NakedAttribute(1 << 24),
  InlineHintAttribute(1 << 25),
  StackAlignment(7 << 26),
  ReturnsTwice(1 << 29),
  UWTable(1 << 30),
  NonLazyBind(1 << 31);

  public final int swigValue() {
    return swigValue;
  }

  public static Attribute swigToEnum(int swigValue) {
    Attribute[] swigValues = Attribute.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (Attribute swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + Attribute.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private Attribute() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private Attribute(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private Attribute(Attribute swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

