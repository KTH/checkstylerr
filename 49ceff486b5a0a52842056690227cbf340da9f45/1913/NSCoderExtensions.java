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
package com.bugvm.apple.avfoundation;

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
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.dispatch.*;
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coreaudio.*;
import com.bugvm.apple.coremedia.*;
import com.bugvm.apple.corevideo.*;
import com.bugvm.apple.mediatoolbox.*;
import com.bugvm.apple.audiotoolbox.*;
import com.bugvm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSCoderExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCoderExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSCoderExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTime:forKey:")
    public static native void encodeCMTime(NSCoder thiz, @ByVal CMTime time, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeForKey:")
    public static native @ByVal CMTime decodeCMTime(NSCoder thiz, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTimeRange:forKey:")
    public static native void encodeCMTimeRange(NSCoder thiz, @ByVal CMTimeRange timeRange, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeRangeForKey:")
    public static native @ByVal CMTimeRange decodeCMTimeRange(NSCoder thiz, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTimeMapping:forKey:")
    public static native void encodeCMTimeMapping(NSCoder thiz, @ByVal CMTimeMapping timeMapping, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeMappingForKey:")
    public static native @ByVal CMTimeMapping decodeCMTimeMapping(NSCoder thiz, String key);
    /*</methods>*/
}
