package com.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface IInterceptor {
	public boolean before(Object proxy, Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;

	public void around(Object proxy, Object target, Method method, Object[] args);

	public void after(Object proxy, Object target, Method method, Object[] args);
}
