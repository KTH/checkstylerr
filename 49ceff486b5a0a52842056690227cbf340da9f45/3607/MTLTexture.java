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
package com.bugvm.apple.metal;

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
import com.bugvm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Metal") @NativeProtocolProxy/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/MTLTexture/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MTLResource/*</implements>*/ {

    /*<ptr>*/public static class MTLTexturePtr extends Ptr<MTLTexture, MTLTexturePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLTexture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "rootResource")
    public native MTLResource getRootResource();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "parentTexture")
    public native MTLTexture getParentTexture();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "parentRelativeLevel")
    public native @MachineSizedUInt long getParentRelativeLevel();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "parentRelativeSlice")
    public native @MachineSizedUInt long getParentRelativeSlice();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "buffer")
    public native MTLBuffer getBuffer();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "bufferOffset")
    public native @MachineSizedUInt long getBufferOffset();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "bufferBytesPerRow")
    public native @MachineSizedUInt long getBufferBytesPerRow();
    @Property(selector = "textureType")
    public native MTLTextureType getTextureType();
    @Property(selector = "pixelFormat")
    public native MTLPixelFormat getPixelFormat();
    @Property(selector = "width")
    public native @MachineSizedUInt long getWidth();
    @Property(selector = "height")
    public native @MachineSizedUInt long getHeight();
    @Property(selector = "depth")
    public native @MachineSizedUInt long getDepth();
    @Property(selector = "mipmapLevelCount")
    public native @MachineSizedUInt long getMipmapLevelCount();
    @Property(selector = "sampleCount")
    public native @MachineSizedUInt long getSampleCount();
    @Property(selector = "arrayLength")
    public native @MachineSizedUInt long getArrayLength();
    @Property(selector = "usage")
    public native MTLTextureUsage getUsage();
    @Property(selector = "isFramebufferOnly")
    public native boolean isFramebufferOnly();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    @Property(selector = "device")
    public native MTLDevice getDevice();
    @Property(selector = "cpuCacheMode")
    public native MTLCPUCacheMode getCpuCacheMode();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "storageMode")
    public native MTLStorageMode getStorageMode();
    /*</properties>*/
    /*<members>*//*</members>*/
    public byte[] getBytes(int length, @MachineSizedUInt long bytesPerRow, @MachineSizedUInt long bytesPerImage, @ByVal MTLRegion region, @MachineSizedUInt long level, @MachineSizedUInt long slice) {
        byte[] bytes = new byte[length];
        getBytes(VM.getArrayValuesAddress(bytes), bytesPerRow, bytesPerImage, region, level, slice);
        return bytes;
    }
    public void replaceRegion(@ByVal MTLRegion region, @MachineSizedUInt long level, @MachineSizedUInt long slice, byte[] pixelBytes, @MachineSizedUInt long bytesPerRow, @MachineSizedUInt long bytesPerImage) {
        replaceRegion(region, level, slice, VM.getArrayValuesAddress(pixelBytes), bytesPerRow, bytesPerImage);
    }
    public byte[] getBytes(int length, @MachineSizedUInt long bytesPerRow, @ByVal MTLRegion region, @MachineSizedUInt long level) {
        byte[] bytes = new byte[length];
        getBytes(VM.getArrayValuesAddress(bytes), bytesPerRow, region, level);
        return bytes;
    }
    public void replaceRegion(@ByVal MTLRegion region, @MachineSizedUInt long level, byte[] pixelBytes, @MachineSizedUInt long bytesPerRow) {
        replaceRegion(region, level, VM.getArrayValuesAddress(pixelBytes), bytesPerRow);
    }
    /*<methods>*/
    @Method(selector = "getBytes:bytesPerRow:bytesPerImage:fromRegion:mipmapLevel:slice:")
    protected native void getBytes(@Pointer long pixelBytes, @MachineSizedUInt long bytesPerRow, @MachineSizedUInt long bytesPerImage, @ByVal MTLRegion region, @MachineSizedUInt long level, @MachineSizedUInt long slice);
    @Method(selector = "replaceRegion:mipmapLevel:slice:withBytes:bytesPerRow:bytesPerImage:")
    protected native void replaceRegion(@ByVal MTLRegion region, @MachineSizedUInt long level, @MachineSizedUInt long slice, @Pointer long pixelBytes, @MachineSizedUInt long bytesPerRow, @MachineSizedUInt long bytesPerImage);
    @Method(selector = "getBytes:bytesPerRow:fromRegion:mipmapLevel:")
    protected native void getBytes(@Pointer long pixelBytes, @MachineSizedUInt long bytesPerRow, @ByVal MTLRegion region, @MachineSizedUInt long level);
    @Method(selector = "replaceRegion:mipmapLevel:withBytes:bytesPerRow:")
    protected native void replaceRegion(@ByVal MTLRegion region, @MachineSizedUInt long level, @Pointer long pixelBytes, @MachineSizedUInt long bytesPerRow);
    @Method(selector = "newTextureViewWithPixelFormat:")
    public native @com.bugvm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) MTLTexture newTextureView(MTLPixelFormat pixelFormat);
    @Method(selector = "newTextureViewWithPixelFormat:textureType:levels:slices:")
    public native @com.bugvm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) MTLTexture newTextureView(MTLPixelFormat pixelFormat, MTLTextureType textureType, @ByVal NSRange levelRange, @ByVal NSRange sliceRange);
    @Method(selector = "setPurgeableState:")
    public native MTLPurgeableState setPurgeableState(MTLPurgeableState state);
    /*</methods>*/
}
