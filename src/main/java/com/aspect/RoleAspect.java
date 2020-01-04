package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 陈宜康
 * @date 2019/9/15 9:41
 * @forWhat
 */
@Component
@Aspect
public class RoleAspect {
    @Before("execution(* com.service.RoleService.getList(..))")
    public void before() {
        System.out.println("前置方法");
    }

    @After("execution(* com.service.RoleService.getList(..))")
    public void after() {
        System.out.println("后置方法");
    }

    @Around("execution(* com.service.RoleService.getList(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("进入了环绕通知");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("离开了环绕通知");
    }

    @AfterReturning("execution(* com.service.RoleService.getList(..))")
    public void afterReturning() {
        System.out.println("正常执行完的方法");
    }

    @AfterThrowing("execution(* com.service.RoleService.getList(..))")
    public void afterThrowing() {
        System.out.println("异常了的方法");
    }
}