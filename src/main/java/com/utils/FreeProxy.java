package com.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 陈宜康
 * @date 2019/9/13 10:00
 * @forWhat
 */
public class FreeProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("哈哈哈,进入了动态代理对象");
        return null;
    }
}
