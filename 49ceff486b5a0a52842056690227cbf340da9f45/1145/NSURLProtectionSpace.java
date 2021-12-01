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

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtectionSpace/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLProtectionSpacePtr extends Ptr<NSURLProtectionSpace, NSURLProtectionSpacePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLProtectionSpace.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLProtectionSpace() {}
    protected NSURLProtectionSpace(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "realm")
    public native String getRealm();
    @Property(selector = "receivesCredentialSecurely")
    public native boolean receivesCredentialSecurely();
    @Property(selector = "isProxy")
    public native boolean isProxy();
    @Property(selector = "host")
    public native String getHost();
    @Property(selector = "port")
    public native @MachineSizedSInt long getPort();
    @Property(selector = "proxyType")
    public native NSURLProtectionSpaceProxyType getProxyType();
    @Property(selector = "protocol")
    public native NSURLProtectionSpaceProtocol getProtocol();
    @Property(selector = "authenticationMethod")
    public native NSURLAuthenticationMethod getAuthenticationMethod();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "distinguishedNames")
    public native @com.bugvm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getDistinguishedNames();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @WeaklyLinked
    @Property(selector = "serverTrust")
    public native SecTrust getServerTrust();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static NSURLProtectionSpace create(String host, long port, NSURLProtectionSpaceProtocol protocol, String realm, NSURLAuthenticationMethod authenticationMethod) {
        NSURLProtectionSpace o = new NSURLProtectionSpace((SkipInit) null);
        long handle = o.init(host, port, protocol, realm, authenticationMethod);
        if (handle == 0) {
            return null;
        }
        o.initObject(handle);
        return o;
    }

    public static NSURLProtectionSpace createProxy(String host, long port, NSURLProtectionSpaceProxyType type, String realm, NSURLAuthenticationMethod authenticationMethod) {
        NSURLProtectionSpace o = new NSURLProtectionSpace((SkipInit) null);
        long handle = o.init(host, port, type, realm, authenticationMethod);
        if (handle == 0) {
            return null;
        }
        o.initObject(handle);
        return o;
    }

    /*<methods>*/
    @Method(selector = "initWithHost:port:protocol:realm:authenticationMethod:")
    protected native @Pointer long init(String host, @MachineSizedSInt long port, NSURLProtectionSpaceProtocol protocol, String realm, NSURLAuthenticationMethod authenticationMethod);
    @Method(selector = "initWithProxyHost:port:type:realm:authenticationMethod:")
    protected native @Pointer long init(String host, @MachineSizedSInt long port, NSURLProtectionSpaceProxyType type, String realm, NSURLAuthenticationMethod authenticationMethod);
    /*</methods>*/
}
