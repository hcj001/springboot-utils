package com.demo.springbootmybatis.utils;

import com.demo.springbootmybatis.entity.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public  static void main(String[] args) throws Exception {
    }
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
}
