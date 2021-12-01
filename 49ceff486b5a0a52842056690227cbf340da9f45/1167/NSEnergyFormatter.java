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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEnergyFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSEnergyFormatterPtr extends Ptr<NSEnergyFormatter, NSEnergyFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSEnergyFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEnergyFormatter() {}
    protected NSEnergyFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "numberFormatter")
    public native NSNumberFormatter getNumberFormatter();
    @Property(selector = "setNumberFormatter:")
    public native void setNumberFormatter(NSNumberFormatter v);
    @Property(selector = "unitStyle")
    public native NSFormattingUnitStyle getUnitStyle();
    @Property(selector = "setUnitStyle:")
    public native void setUnitStyle(NSFormattingUnitStyle v);
    @Property(selector = "isForFoodEnergyUse")
    public native boolean isForFoodEnergyUse();
    @Property(selector = "setForFoodEnergyUse:")
    public native void setForFoodEnergyUse(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public String formatUnitJoules(double numberInJoules) {
        return formatUnitJoules(numberInJoules, new MachineSizedSIntPtr());
    }
    /*<methods>*/
    @Method(selector = "stringFromValue:unit:")
    public native String format(double value, NSEnergyFormatterUnit unit);
    @Method(selector = "stringFromJoules:")
    public native String formatJoules(double numberInJoules);
    @Method(selector = "unitStringFromValue:unit:")
    public native String formatUnit(double value, NSEnergyFormatterUnit unit);
    @Method(selector = "unitStringFromJoules:usedUnit:")
    protected native String formatUnitJoules(double numberInJoules, MachineSizedSIntPtr unitp);
    /*</methods>*/
}
