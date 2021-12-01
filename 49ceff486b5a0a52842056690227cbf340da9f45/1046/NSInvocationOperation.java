/*
 * Copyright (C) 2013-2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bugvm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import com.bugvm.objc.*;
import com.bugvm.objc.annotation.*;
import com.bugvm.objc.block.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.uikit.*;
import com.bugvm.apple.coretext.*;
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coredata.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coremedia.*;
import com.bugvm.apple.security.*;
import com.bugvm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSInvocationOperation/*</name>*/ 
    extends /*<extends>*/NSOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSInvocationOperationPtr extends Ptr<NSInvocationOperation, NSInvocationOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSInvocationOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSInvocationOperation() {}
    protected NSInvocationOperation(SkipInit skipInit) { super(skipInit); }
    public NSInvocationOperation(NSObject target, Selector sel, NSObject arg) { super((SkipInit) null); initObject(init(target, sel, arg)); }
    public NSInvocationOperation(NSInvocation inv) { super((SkipInit) null); initObject(init(inv)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "invocation")
    public native NSInvocation getInvocation();
    @Property(selector = "result")
    public native NSObject getResult();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTarget:selector:object:")
    protected native @Pointer long init(NSObject target, Selector sel, NSObject arg);
    @Method(selector = "initWithInvocation:")
    protected native @Pointer long init(NSInvocation inv);
    /*</methods>*/
}
