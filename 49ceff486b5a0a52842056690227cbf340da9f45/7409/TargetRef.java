/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.bugvm.llvm.binding;

public class TargetRef {
  private long swigCPtr;

  protected TargetRef(long cPtr, boolean futureUse) {
    swigCPtr = cPtr;
  }

  protected TargetRef() {
    swigCPtr = 0;
  }

  protected static long getCPtr(TargetRef obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public int hashCode() {
    return 31 + (int) (swigCPtr ^ (swigCPtr >>> 32));
  }

  public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    TargetRef other = (TargetRef) obj;
    return swigCPtr == other.swigCPtr;
  }
}

