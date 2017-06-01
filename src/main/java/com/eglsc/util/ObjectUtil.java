package com.eglsc.util;

import org.apache.commons.lang3.ObjectUtils;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class ObjectUtil extends ObjectUtils{

    private ObjectUtil() {
    }

    public static boolean equal(Object obj1, Object obj2) {
        return obj1 != null?obj1.equals(obj2):obj2 == null;
    }

    public static int length(Object obj) {
        if(obj == null) {
            return 0;
        } else if(obj instanceof CharSequence) {
            return ((CharSequence)obj).length();
        } else if(obj instanceof Collection) {
            return ((Collection)obj).size();
        } else if(obj instanceof Map) {
            return ((Map)obj).size();
        } else {
            int count;
            if(obj instanceof Iterator) {
                Iterator var3 = (Iterator)obj;
                count = 0;

                while(var3.hasNext()) {
                    ++count;
                    var3.next();
                }

                return count;
            } else if(!(obj instanceof Enumeration)) {
                return obj.getClass().isArray()? Array.getLength(obj):-1;
            } else {
                Enumeration enumeration = (Enumeration)obj;
                count = 0;

                while(enumeration.hasMoreElements()) {
                    ++count;
                    enumeration.nextElement();
                }

                return count;
            }
        }
    }

    public static boolean contains(Object obj, Object element) {
        if(obj == null) {
            return false;
        } else if(obj instanceof String) {
            return element == null?false:((String)obj).contains(element.toString());
        } else if(obj instanceof Collection) {
            return ((Collection)obj).contains(element);
        } else if(obj instanceof Map) {
            return ((Map)obj).values().contains(element);
        } else {
            Object var7;
            if(obj instanceof Iterator) {
                Iterator var6 = (Iterator)obj;

                do {
                    if(!var6.hasNext()) {
                        return false;
                    }

                    var7 = var6.next();
                } while(!equal(var7, element));

                return true;
            } else if(obj instanceof Enumeration) {
                Enumeration var5 = (Enumeration)obj;

                do {
                    if(!var5.hasMoreElements()) {
                        return false;
                    }

                    var7 = var5.nextElement();
                } while(!equal(var7, element));

                return true;
            } else {
                if(obj.getClass().isArray()) {
                    int len = Array.getLength(obj);

                    for(int i = 0; i < len; ++i) {
                        Object o = Array.get(obj, i);
                        if(equal(o, element)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    public static boolean isValidIfNumber(Object obj) {
        if(obj != null && obj instanceof Number) {
            if(obj instanceof Double) {
                if(((Double)obj).isInfinite() || ((Double)obj).isNaN()) {
                    return false;
                }
            } else if(obj instanceof Float && (((Float)obj).isInfinite() || ((Float)obj).isNaN())) {
                return false;
            }
        }

        return true;
    }
}
