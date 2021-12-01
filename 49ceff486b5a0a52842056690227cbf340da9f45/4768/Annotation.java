/*
 * Copyright (C) 2014 RoboVM AB
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
package com.bugvm.rt.annotation;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Abstract base class of annotation implementation classes generated by the
 * compiler {@code AnnotationImplPlugin}.
 */
public abstract class Annotation implements java.lang.annotation.Annotation {
    private enum NoValue {NO_VALUE}

    /**
     * Singleton representing missing element value.
     */
    protected static final Object NO_VALUE = NoValue.NO_VALUE;
    
    private static HashMap<Class<? extends java.lang.annotation.Annotation>, HashMap<String, Object>> 
        defaultValueCache = new HashMap<>();
    
    private final Class<? extends java.lang.annotation.Annotation> annotationType;

    protected Annotation(Class<? extends java.lang.annotation.Annotation> klass) {
        this.annotationType = klass;
    }

    @Override
    public Class<? extends java.lang.annotation.Annotation> annotationType() {
        return annotationType;
    }
    
    protected final Object validate(Object value, String memberName) {
        if (value instanceof Exception) {
            if (value instanceof TypeNotPresentException) {
                TypeNotPresentException e = (TypeNotPresentException) value;
                throw new TypeNotPresentException(e.typeName(), e.getCause());
            } else if (value instanceof EnumConstantNotPresentException) {
                EnumConstantNotPresentException e = (EnumConstantNotPresentException) value;
                throw new EnumConstantNotPresentException(e.enumType(), e.constantName());
            } else if (value instanceof AnnotationTypeMismatchException) {
                AnnotationTypeMismatchException e = (AnnotationTypeMismatchException) value;
                throw new AnnotationTypeMismatchException(e.element(), e.foundType());
            } else if (value instanceof ArrayStoreException) {
                ArrayStoreException e = (ArrayStoreException) value;
                throw new ArrayStoreException(e.getMessage());
            } else {
                // Unknown exception. Wrap and rethrow.
                throw new RuntimeException((Exception) value);
            }
        }
        
        if (value == NO_VALUE) {
            throw new IncompleteAnnotationException(annotationType, memberName);
        }
        
        Class<?> type = value.getClass();
        if (type.isArray()) {
            if (type == int[].class) {
                return ((int[]) value).clone();
            } else if (type == byte[].class) {
                return ((byte[]) value).clone();
            } else if (type == short[].class) {
                return ((short[]) value).clone();
            } else if (type == long[].class) {
                return ((long[]) value).clone();
            } else if (type == char[].class) {
                return ((char[]) value).clone();
            } else if (type == boolean[].class) {
                return ((boolean[]) value).clone();
            } else if (type == float[].class) {
                return ((float[]) value).clone();
            } else if (type == double[].class) {
                return ((double[]) value).clone();
            }
            return ((Object[]) value).clone();
        }
        return value;
    }
    
    protected final Object getDefaultValue(String memberName) {
        synchronized (defaultValueCache) {
            HashMap<String, Object> cache = defaultValueCache.get(annotationType);
            if (cache != null) {
                Object value = cache.get(memberName);
                if (value != null) {
                    return value;
                }
            }
            
            Object value = null;
            try {
                value = annotationType.getMethod(memberName).getDefaultValue();
            } catch (Throwable t) {
                value = t;
            }
            if (value == null) {
                value = NO_VALUE;
            }
            
            if (cache == null) {
                cache = new HashMap<String, Object>();
                defaultValueCache.put(annotationType, cache);
            }
            
            cache.put(memberName, value);
            return value;
        }
    }
    
    protected final int hash(Object value, String memberName) {
        int hash = memberName.hashCode() * 127;
        Class<?> type = value.getClass();
        if (type.isArray()) {
            if (type == int[].class) {
                return hash ^ Arrays.hashCode((int[]) value);
            } else if (type == byte[].class) {
                return hash ^ Arrays.hashCode((byte[]) value);
            } else if (type == short[].class) {
                return hash ^ Arrays.hashCode((short[]) value);
            } else if (type == long[].class) {
                return hash ^ Arrays.hashCode((long[]) value);
            } else if (type == char[].class) {
                return hash ^ Arrays.hashCode((char[]) value);
            } else if (type == boolean[].class) {
                return hash ^ Arrays.hashCode((boolean[]) value);
            } else if (type == float[].class) {
                return hash ^ Arrays.hashCode((float[]) value);
            } else if (type == double[].class) {
                return hash ^ Arrays.hashCode((double[]) value);
            }
            return hash ^ Arrays.hashCode((Object[]) value);
        } else {
            return hash ^ value.hashCode();
        }
    }
    
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return fastEquals(o);
        }
        if (annotationType.isInstance(o)) {
            try {
                return slowEquals(o);
            } catch (Throwable t) {
                return false;
            }
        }
        return false;
    }
    
    protected abstract boolean fastEquals(Object that);

    protected abstract boolean slowEquals(Object that);

    protected final boolean memberEquals(Object ours, Object theirs) {
        if (ours == theirs) {
            return true;
        }
        if (ours instanceof Exception) {
            return false;
        }
        Class<?> type = ours.getClass();
        if (type != theirs.getClass()) {
            return false;
        }
        if (type.isArray()) {
            if (ours instanceof Object[]) {
                return Arrays.equals((Object[]) ours, (Object[]) theirs);
            } else if (type == int[].class) {
                return Arrays.equals((int[]) ours, (int[]) theirs);
            } else if (type == byte[].class) {
                return Arrays.equals((byte[]) ours, (byte[]) theirs);
            } else if (type == short[].class) {
                return Arrays.equals((short[]) ours, (short[]) theirs);
            } else if (type == long[].class) {
                return Arrays.equals((long[]) ours, (long[]) theirs);
            } else if (type == char[].class) {
                return Arrays.equals((char[]) ours, (char[]) theirs);
            } else if (type == boolean[].class) {
                return Arrays.equals((boolean[]) ours, (boolean[]) theirs);
            } else if (type == float[].class) {
                return Arrays.equals((float[]) ours, (float[]) theirs);
            } else if (type == double[].class) {
                return Arrays.equals((double[]) ours, (double[]) theirs);
            }
            return false;
        } else {
            return ours.equals(theirs);
        }
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append('@');
        sb.append(annotationType.getName());
        sb.append('(');
        membersToString(sb);
        sb.append(')');
        return sb.toString();
    }
    
    protected abstract void membersToString(StringBuilder sb);
    
    protected final void memberToString(StringBuilder sb, Object value, String memberName, boolean first) {
        if (!first) {
            sb.append(", ");
        }
        sb.append(memberName);
        sb.append('=');
        sb.append(memberValueToString(value));
    }

    private final String memberValueToString(Object value) {
        Class<?> type = value.getClass();
        if (type.isArray()) {
            if (type == int[].class) {
                return Arrays.toString((int[]) value);
            } else if (type == byte[].class) {
                return Arrays.toString((byte[]) value);
            } else if (type == short[].class) {
                return Arrays.toString((short[]) value);
            } else if (type == long[].class) {
                return Arrays.toString((long[]) value);
            } else if (type == char[].class) {
                return Arrays.toString((char[]) value);
            } else if (type == boolean[].class) {
                return Arrays.toString((boolean[]) value);
            } else if (type == float[].class) {
                return Arrays.toString((float[]) value);
            } else if (type == double[].class) {
                return Arrays.toString((double[]) value);
            }
            return Arrays.toString((Object[]) value);
        }
        return value.toString();
    }

    protected static Boolean box(boolean v) {
        return Boolean.valueOf(v);
    }
    protected static Byte box(byte v) {
        return Byte.valueOf(v);
    }
    protected static Short box(short v) {
        return Short.valueOf(v);
    }
    protected static Character box(char v) {
        return Character.valueOf(v);
    }
    protected static Integer box(int v) {
        return Integer.valueOf(v);
    }
    protected static Long box(long v) {
        return Long.valueOf(v);
    }
    protected static Float box(float v) {
        return Float.valueOf(v);
    }
    protected static Double box(double v) {
        return Double.valueOf(v);
    }
    protected static boolean unbox(Boolean v) {
        return v.booleanValue();
    }
    protected static byte unbox(Byte v) {
        return v.byteValue();
    }
    protected static short unbox(Short v) {
        return v.shortValue();
    }
    protected static char unbox(Character v) {
        return v.charValue();
    }
    protected static int unbox(Integer v) {
        return v.intValue();
    }
    protected static long unbox(Long v) {
        return v.longValue();
    }
    protected static float unbox(Float v) {
        return v.floatValue();
    }
    protected static double unbox(Double v) {
        return v.doubleValue();
    }
    
}
