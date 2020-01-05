package com.service;

import com.api.IMyProxy;

/**
 * @author 陈宜康
 * @date 2019/9/8 8:45
 * @forWhat
 */
public class MyProxy implements IMyProxy {
    @Override
    public void check() {
        System.out.println("真实对象的方法");
    }
}
