package com.service;

import com.api.IInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 陈宜康
 * @date 2019/9/8 9:05
 * @forWhat
 */
public class Interceptor implements IInterceptor {
    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        method.invoke(target, args);
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {

    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {

    }
}
