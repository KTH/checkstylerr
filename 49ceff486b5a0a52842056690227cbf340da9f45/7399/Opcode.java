/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public enum Opcode {
  Ret(1),
  Br(2),
  Switch(3),
  IndirectBr(4),
  Invoke(5),
  Unreachable(7),
  Add(8),
  FAdd(9),
  Sub(10),
  FSub(11),
  Mul(12),
  FMul(13),
  UDiv(14),
  SDiv(15),
  FDiv(16),
  URem(17),
  SRem(18),
  FRem(19),
  Shl(20),
  LShr(21),
  AShr(22),
  And(23),
  Or(24),
  Xor(25),
  Alloca(26),
  Load(27),
  Store(28),
  GetElementPtr(29),
  Trunc(30),
  ZExt(31),
  SExt(32),
  FPToUI(33),
  FPToSI(34),
  UIToFP(35),
  SIToFP(36),
  FPTrunc(37),
  FPExt(38),
  PtrToInt(39),
  IntToPtr(40),
  BitCast(41),
  AddrSpaceCast(60),
  ICmp(42),
  FCmp(43),
  PHI(44),
  Call(45),
  Select(46),
  UserOp1(47),
  UserOp2(48),
  VAArg(49),
  ExtractElement(50),
  InsertElement(51),
  ShuffleVector(52),
  ExtractValue(53),
  InsertValue(54),
  Fence(55),
  AtomicCmpXchg(56),
  AtomicRMW(57),
  Resume(58),
  LandingPad(59);

  public final int swigValue() {
    return swigValue;
  }

  public static Opcode swigToEnum(int swigValue) {
    Opcode[] swigValues = Opcode.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (Opcode swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + Opcode.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private Opcode() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private Opcode(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private Opcode(Opcode swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

