package com.test;

import java.util.Observable;
import java.util.Observer;

/**
 * @author 陈宜康
 * @date 2019/9/8 9:22
 * @forWhat
 */
public class TaobaoObverser implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // System.out.println("被观察者是" + o.getClass().getName());;
        System.out.println("新的产品是" + arg);
    }
}
