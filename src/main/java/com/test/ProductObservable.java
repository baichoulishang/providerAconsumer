package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author 陈宜康
 * @date 2019/9/8 9:15
 * @forWhat
 */
public class ProductObservable extends Observable {
    private List<String> list = new ArrayList<>();

    private static ProductObservable productObservable = new ProductObservable();

    // 构造器私有化
    private ProductObservable() {
    }

    // 单例模式
    public static ProductObservable getInstance() {
        return productObservable;
    }

    // 添加观察者
    public void addObserverList(Observer observer) {
        this.addObserver(observer);
    }

    // 添加产品
    public void addProduct(String pro) {
        list.add(pro);
        // 设定状态并且通知观察者们
        this.setChanged();
        // 把新产品当成参数传给观察者们
        this.notifyObservers(pro);
    }
}

