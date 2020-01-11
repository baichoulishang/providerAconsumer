package api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface IInterceptor {
    boolean before(Object proxy, Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;

    void around(Object proxy, Object target, Method method, Object[] args);

    void after(Object proxy, Object target, Method method, Object[] args);
}
