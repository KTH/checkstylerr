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
package com.bugvm.apple.security;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecCredential/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecCredential/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecCredential toObject(Class<SecCredential> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecCredential(o);
        }
        @MarshalsPointer
        public static long toNative(SecCredential o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecCredential> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecCredential> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecCredential(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecCredential> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecCredential i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecCredential(CFDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFType key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFType key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getPassword() {
        if (has(Keys.Password())) {
            CFString val = get(Keys.Password(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getServer() {
        if (has(SecAttributes.Keys.Server())) {
            CFString val = get(SecAttributes.Keys.Server(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getAccount() {
        if (has(SecAttributes.Keys.Account())) {
            CFString val = get(SecAttributes.Keys.Account(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public int getPort() {
        if (has(SecAttributes.Keys.Port())) {
            CFNumber val = get(SecAttributes.Keys.Port(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kSecSharedPassword", optional=true)
        public static native CFType Password();
    }
    /*</keys>*/
}
