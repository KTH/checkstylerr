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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNetService/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSNetServicePtr extends Ptr<NSNetService, NSNetServicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNetService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNetService() {}
    protected NSNetService(SkipInit skipInit) { super(skipInit); }
    public NSNetService(String domain, String type, String name, int port) { super((SkipInit) null); initObject(init(domain, type, name, port)); }
    public NSNetService(String domain, String type, String name) { super((SkipInit) null); initObject(init(domain, type, name)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSNetServiceDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSNetServiceDelegate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "includesPeerToPeer")
    public native boolean includesPeerToPeer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setIncludesPeerToPeer:")
    public native void setIncludesPeerToPeer(boolean v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "type")
    public native String getType();
    @Property(selector = "domain")
    public native String getDomain();
    @Property(selector = "hostName")
    public native String getHostName();
    @Property(selector = "addresses")
    public native NSArray<NSData> getAddresses();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "port")
    public native @MachineSizedSInt long getPort();
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSInputStream getInputStream() {
        NSInputStream.NSInputStreamPtr ptr = new NSInputStream.NSInputStreamPtr();
        getStreams(ptr, null);
        return ptr.get();
    }
    public NSOutputStream getOutputStream() {
        NSOutputStream.NSOutputStreamPtr ptr = new NSOutputStream.NSOutputStreamPtr();
        getStreams(null, ptr);
        return ptr.get();
    }
    public void scheduleInRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        scheduleInRunLoop(aRunLoop, mode.value().toString());
    }
    public void removeFromRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        removeFromRunLoop(aRunLoop, mode.value().toString());
    }
    /*<methods>*/
    @Method(selector = "initWithDomain:type:name:port:")
    protected native @Pointer long init(String domain, String type, String name, int port);
    @Method(selector = "initWithDomain:type:name:")
    protected native @Pointer long init(String domain, String type, String name);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "publish")
    public native void publish();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "publishWithOptions:")
    public native void publish(NSNetServiceOptions options);
    @Method(selector = "stop")
    public native void stop();
    @Method(selector = "resolveWithTimeout:")
    public native void resolve(double timeout);
    @Method(selector = "getInputStream:outputStream:")
    protected native boolean getStreams(NSInputStream.NSInputStreamPtr inputStream, NSOutputStream.NSOutputStreamPtr outputStream);
    @Method(selector = "setTXTRecordData:")
    public native boolean setTXTRecordData(NSData recordData);
    @Method(selector = "TXTRecordData")
    public native NSData getTXTRecordData();
    @Method(selector = "startMonitoring")
    public native void startMonitoring();
    @Method(selector = "stopMonitoring")
    public native void stopMonitoring();
    @Method(selector = "dictionaryFromTXTRecordData:")
    public static native NSDictionary<NSString, NSData> getDictionaryFromTXTRecordData(NSData txtData);
    @Method(selector = "dataFromTXTRecordDictionary:")
    public static native NSData getDataFromTXTRecordDictionary(NSDictionary<NSString, NSData> txtDictionary);
    /*</methods>*/
}
