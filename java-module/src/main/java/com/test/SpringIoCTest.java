package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 陈宜康
 * @date 2019/12/13 19:16
 * @forWhat
 */
public class SpringIoCTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object maker2 = context.getBean("juiceMaker2");
        context.close();

    }
}
