/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public class MemoryBufferRefOut {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected MemoryBufferRefOut(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MemoryBufferRefOut obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        LLVMJNI.delete_MemoryBufferRefOut(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public MemoryBufferRef getValue() {
    long cPtr = LLVMJNI.MemoryBufferRefOut_value_get(swigCPtr, this);
    return (cPtr == 0) ? null : new MemoryBufferRef(cPtr, false);
  }

  public MemoryBufferRefOut() {
    this(LLVMJNI.new_MemoryBufferRefOut(), true);
  }

}
