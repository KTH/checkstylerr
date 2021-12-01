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
package com.bugvm.apple.scenekit;

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
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.dispatch.*;
import com.bugvm.apple.glkit.*;
import com.bugvm.apple.spritekit.*;
import com.bugvm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneRendererDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNSceneRendererDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("renderer:updateAtTime:")
    public void update(SCNSceneRenderer aRenderer, double time) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("renderer:didApplyAnimationsAtTime:")
    public void didApplyAnimations(SCNSceneRenderer aRenderer, double time) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("renderer:didSimulatePhysicsAtTime:")
    public void didSimulatePhysics(SCNSceneRenderer aRenderer, double time) {}
    @NotImplemented("renderer:willRenderScene:atTime:")
    public void willRenderScene(SCNSceneRenderer aRenderer, SCNScene scene, double time) {}
    @NotImplemented("renderer:didRenderScene:atTime:")
    public void didRenderScene(SCNSceneRenderer aRenderer, SCNScene scene, double time) {}
    /*</methods>*/
}
