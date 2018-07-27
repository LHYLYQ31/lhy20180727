package com.sinosoft.hms.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * Bean 转换工具
 */

public class BeanUtil {

    /**
     * 获取所有的属性和值，包括父类的
     * 
     * @param bean
     * @return
     */
    public static Map<String, Object> getAllFields(Object bean) {
        return getAllFields(bean, bean.getClass());
    }

    /**
     * 获取所有的属性和值，包括父类的
     * 
     * @param bean
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getAllFields(Object bean, Class clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    // 排除肯定不持久化的部分
                    if (Modifier.isTransient(field.getModifiers()))
                        continue;
                    if (Modifier.isStatic(field.getModifiers()))
                        continue;
                    if (field.getAnnotation(Transient.class) != null)
                        continue;
                    if (field.getAnnotation(Id.class) != null)
                        continue;

                    map.put(field.getName(), PropertyUtils.getProperty(bean,
                            field.getName()));
                }
            }
            Class superClass = clazz.getSuperclass();// 递归获取父类的Field
            Map<String, Object> superMap = getAllFields(bean, superClass);
            if (superMap != null) {
                map.putAll(superMap);
            }
            if (map.size() == 0) {
                return null;
            }
            return map;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取属性的值
    public static Object getProperty(Object bean, String fieldName)
            throws Exception {
        Class<?> fieldType = PropertyUtils.getPropertyType(bean, fieldName
                .trim());
        if (fieldType != null) {
            return PropertyUtils.getProperty(bean, fieldName.trim());
        }
        return null;
    }

    /**
     * 设置property的值
     * 
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    public static void setProperty(Object bean, String fieldName, Object value)
            throws Exception {
        Class<?> fieldType = PropertyUtils.getPropertyType(bean, fieldName);

        if (value == null || "".equals(value)) {
            PropertyUtils.setProperty(bean, fieldName, null);
        }
        else {
            Object tmpValue = null;
            if (fieldType == String.class) {
                tmpValue = value.toString();
            }
            else if (fieldType == Double.class) {
                tmpValue = new Double(value.toString());
            }
            else if (fieldType == Double.TYPE) {
                tmpValue = Double.valueOf(new Double(value.toString())
                        .doubleValue());
            }
            else if (fieldType == Float.class) {
                tmpValue = new Float(value.toString());
            }
            else if (fieldType == Float.TYPE) {
                tmpValue = Float.valueOf(new Float(value.toString())
                        .floatValue());
            }
            else if (fieldType == Integer.class) {
                tmpValue = new Integer(value.toString());
            }
            else if (fieldType == Integer.TYPE) {
                tmpValue = Integer.valueOf(new Integer(value.toString())
                        .intValue());
            }
            else if (fieldType == Long.class) {
                tmpValue = Long.valueOf(value.toString());
            }
            else if (fieldType == Long.TYPE) {
                tmpValue = Long.valueOf(Long.valueOf(value.toString())
                        .longValue());
            }
            else if (fieldType == Boolean.class) {
                tmpValue = Boolean.valueOf(value.toString());
            }
            else if (fieldType == Boolean.TYPE) {
                tmpValue = Boolean.valueOf(Boolean.valueOf(value.toString())
                        .booleanValue());
            }
            else if (fieldType == java.util.Date.class) {
                tmpValue = DateUtil.COMPAT.getTextDate((String) value);
            }
            else {
                tmpValue = null;
            }

            PropertyUtils.setProperty(bean, fieldName, tmpValue);
        }
    }

    /**
     * 获取属性类型
     * 
     * @param bean
     * @param property
     * @return
     * @throws Exception
     */
    public static Class<?> getPropertyType(Object bean, String property)
            throws Exception {
        try {
            Field field = bean.getClass().getDeclaredField(property);
            if (field != null)
                return PropertyUtils.getPropertyType(bean, property);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            return null;
        }
        return null;
    }

    public static void copyPriperties(Object from, Object to) {

        String fileName, str, getName, setName;
        List fields = new ArrayList();
        Method getMethod = null;
        Method setMethod = null;
        try {
            Class c1 = from.getClass();
            Class c2 = to.getClass();

            Field[] fs1 = c1.getDeclaredFields();
            Field[] fs2 = c2.getDeclaredFields();
            // 两个类属性比较剔除不相同的属性，只留下相同的属性
            for (int i = 0; i < fs2.length; i++) {
                for (int j = 0; j < fs1.length; j++) {
                    if (fs1[j].getName().equals(fs2[i].getName())) {
                        fields.add(fs1[j]);
                        break;
                    }
                }
            }
            if (null != fields && fields.size() > 0) {
                for (int i = 0; i < fields.size(); i++) {
                    // 获取属性名称
                    Field f = (Field) fields.get(i);
                    fileName = f.getName();
                    // 属性名第一个字母大写
                    str = fileName.substring(0, 1).toUpperCase();
                    // 拼凑getXXX和setXXX方法名
                    getName = "get" + str + fileName.substring(1);
                    setName = "set" + str + fileName.substring(1);
                    // 获取get、set方法
                    getMethod = c1.getMethod(getName, new Class[] {});
                    setMethod = c2.getMethod(setName,
                            new Class[] { f.getType() });

                    // 获取属性值
                    Object o = getMethod.invoke(from, new Object[] {});
                    //					System.out.println(fileName + " : " + o);
                    // 将属性值放入另一个对象中对应的属性
                    if (null != o) {
                        setMethod.invoke(to, new Object[] { o });
                    }
                }
            }
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
