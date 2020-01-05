package com.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.Serializable;

/**
 * @author 陈宜康
 * @date 2019/9/10 20:31
 * @forWhat
 */
public class School implements Serializable, BeanNameAware {
    private String id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "School{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("调用了BeanNameAware的setBeanName----------" + this.getClass().getSimpleName());
    }


}
