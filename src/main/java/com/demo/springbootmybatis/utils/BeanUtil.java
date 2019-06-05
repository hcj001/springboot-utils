package com.demo.springbootmybatis.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    //把JavaBean转化为map
    public static Map<String,String> beanToMap(Object bean) throws Exception{
        Map<String,String> map = new HashMap<>();
        //获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
        //获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        //对属性迭代
        for (PropertyDescriptor pd : pds) {
            //属性名称
            String propertyName = pd.getName();
            //属性值,用getter方法获取
            Method m = pd.getReadMethod();
            Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值

            //把属性名-属性值 存到Map中
            map.put(propertyName, properValue == null ? "":properValue.toString());
        }
        return map;
    }

    //把Map转化为JavaBean
    public static <T> T map2bean(Map<String,Object> map,Class<T> clz) throws Exception{
        //创建一个需要转换为的类型的对象
        T obj = clz.newInstance();

        //得到属性的描述器
        BeanInfo b = Introspector.getBeanInfo(clz,Object.class);
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //得到属性的setter方法
            Method setter = pd.getWriteMethod();
            //得到key名字和属性名字相同的value设置给属性
            setter.invoke(obj, map.get(pd.getName()));
        }
        return obj;
    }
}
