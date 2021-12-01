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
package com.bugvm.apple.gamekit;

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
import com.bugvm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKChallenge/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class GKChallengePtr extends Ptr<GKChallenge, GKChallengePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKChallenge.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKChallenge() {}
    protected GKChallenge(SkipInit skipInit) { super(skipInit); }
    public GKChallenge(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "issuingPlayerID")
    public native String getIssuingPlayerID();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "receivingPlayerID")
    public native String getReceivingPlayerID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "issuingPlayer")
    public native GKPlayer getIssuingPlayer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "receivingPlayer")
    public native GKPlayer getReceivingPlayer();
    @Property(selector = "state")
    public native GKChallengeState getState();
    @Property(selector = "issueDate")
    public native NSDate getIssueDate();
    @Property(selector = "completionDate")
    public native NSDate getCompletionDate();
    @Property(selector = "message")
    public native String getMessage();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "decline")
    public native void decline();
    @Method(selector = "loadReceivedChallengesWithCompletionHandler:")
    public static native void loadReceivedChallenges(@Block VoidBlock2<NSArray<GKChallenge>, NSError> completionHandler);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
