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
package com.bugvm.apple.spritekit;

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
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.dispatch.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.avfoundation.*;
import com.bugvm.apple.glkit.*;
import com.bugvm.apple.scenekit.*;
import com.bugvm.apple.gameplaykit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKAudioNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKAudioNodePtr extends Ptr<SKAudioNode, SKAudioNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKAudioNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKAudioNode() {}
    protected SKAudioNode(SkipInit skipInit) { super(skipInit); }
    public SKAudioNode(AVAudioNode node) { super((SkipInit) null); initObject(init(node)); }
    public SKAudioNode(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    public SKAudioNode(String name) { super((SkipInit) null); initObject(init(name)); }
    public SKAudioNode(NSURL url) { super((SkipInit) null); initObject(init(url)); }
    /*</constructors>*/
    public SKAudioNode(File file) {
        this(file.getAbsolutePath());
    }
    /*<properties>*/
    @Property(selector = "avAudioNode")
    public native AVAudioNode getAVAudioNode();
    @Property(selector = "setAvAudioNode:")
    public native void setAVAudioNode(AVAudioNode v);
    @Property(selector = "autoplayLooped")
    public native boolean isAutoplayLooped();
    @Property(selector = "setAutoplayLooped:")
    public native void setAutoplayLooped(boolean v);
    @Property(selector = "isPositional")
    public native boolean isPositional();
    @Property(selector = "setPositional:")
    public native void setPositional(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAVAudioNode:")
    protected native @Pointer long init(AVAudioNode node);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "initWithFileNamed:")
    protected native @Pointer long init(String name);
    @Method(selector = "initWithURL:")
    protected native @Pointer long init(NSURL url);
    /*</methods>*/
}
