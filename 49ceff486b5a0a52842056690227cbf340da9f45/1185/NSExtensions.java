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

import com.bugvm.objc.ObjCClass;
import com.bugvm.objc.ObjCExtensions;
import com.bugvm.objc.ObjCObject;
import com.bugvm.rt.bro.annotation.Marshaler;

/**
 * Base class for all Objective-C extension classes (categories) in the 
 * Cocoa/CocoaTouch bindings. This class adds a {@link Marshaler} for 
 * {@link String}s to/from {@link NSString}. Note that it is not possible to 
 * create new categories in Java which adds methods to Objective-C classes. 
 */
@Marshaler(NSString.AsStringMarshaler.class)
public abstract class NSExtensions extends ObjCExtensions {
    
    protected static <T extends NSObject> T alloc(Class<T> c) {
        long h = NSObject.alloc(ObjCClass.getByType(c));
        return ObjCObject.toObjCObject(c, h, NSObject.FLAG_NO_RETAIN);
    }

}
