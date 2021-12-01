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
package com.bugvm.apple.uikit;

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
import com.bugvm.apple.coreanimation.*;
import com.bugvm.apple.coregraphics.*;
import com.bugvm.apple.coredata.*;
import com.bugvm.apple.coreimage.*;
import com.bugvm.apple.coretext.*;
import com.bugvm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewLayoutInvalidationContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewLayoutInvalidationContextPtr extends Ptr<UICollectionViewLayoutInvalidationContext, UICollectionViewLayoutInvalidationContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewLayoutInvalidationContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewLayoutInvalidationContext() {}
    protected UICollectionViewLayoutInvalidationContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "invalidateEverything")
    public native boolean invalidatesEverything();
    @Property(selector = "invalidateDataSourceCounts")
    public native boolean invalidatesDataSourceCounts();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "invalidatedItemIndexPaths")
    public native NSArray<NSIndexPath> getInvalidatedItemIndexPaths();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "invalidatedSupplementaryIndexPaths")
    public native NSArray<NSIndexPath> getInvalidatedSupplementaryIndexPaths();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "invalidatedDecorationIndexPaths")
    public native NSArray<NSIndexPath> getInvalidatedDecorationIndexPaths();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "contentOffsetAdjustment")
    public native @ByVal CGPoint getContentOffsetAdjustment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setContentOffsetAdjustment:")
    public native void setContentOffsetAdjustment(@ByVal CGPoint v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "contentSizeAdjustment")
    public native @ByVal CGSize getContentSizeAdjustment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setContentSizeAdjustment:")
    public native void setContentSizeAdjustment(@ByVal CGSize v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "previousIndexPathsForInteractivelyMovingItems")
    public native NSArray<NSIndexPath> getPreviousIndexPathsForInteractivelyMovingItems();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "targetIndexPathsForInteractivelyMovingItems")
    public native NSArray<NSIndexPath> getTargetIndexPathsForInteractivelyMovingItems();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "interactiveMovementTarget")
    public native @ByVal CGPoint getInteractiveMovementTarget();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "invalidateItemsAtIndexPaths:")
    public native void invalidateItems(NSArray<NSIndexPath> indexPaths);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "invalidateSupplementaryElementsOfKind:atIndexPaths:")
    public native void invalidateSupplementaryElementsOfKind(String elementKind, NSArray<NSIndexPath> indexPaths);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "invalidateDecorationElementsOfKind:atIndexPaths:")
    public native void invalidateDecorationElementsOfKind(String elementKind, NSArray<NSIndexPath> indexPaths);
    /*</methods>*/
}
