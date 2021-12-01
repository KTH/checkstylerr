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
package com.bugvm.apple.modelio;

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
import com.bugvm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVoxelIndexExtent/*</name>*/ 
    extends /*<extends>*/Struct<MDLVoxelIndexExtent>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLVoxelIndexExtentPtr extends Ptr<MDLVoxelIndexExtent, MDLVoxelIndexExtentPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLVoxelIndexExtent() {}
    public MDLVoxelIndexExtent(VectorInt4 minimumExtent, VectorInt4 maximumExtent) {
        this.setMinimumExtent(minimumExtent);
        this.setMaximumExtent(maximumExtent);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native VectorInt4 getMinimumExtent();
    @StructMember(0) public native MDLVoxelIndexExtent setMinimumExtent(VectorInt4 minimumExtent);
    @StructMember(1) public native VectorInt4 getMaximumExtent();
    @StructMember(1) public native MDLVoxelIndexExtent setMaximumExtent(VectorInt4 maximumExtent);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
