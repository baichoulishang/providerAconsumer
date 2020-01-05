package com.utils;


/**
 * 方法静态分派演示
 *
 * @author zzm
 */
public class StaticDispatch {

    public static void main(String[] args) {
        Man human = new Man();
        human.println("dd");

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    static abstract class Human {
    }

    static class Man extends Human {
        private String sale = "man";

        public void println(String shit) {
            System.out.println(shit);
        }
    }

    static class Woman extends Human {
        private String sale = "woman";
    }


}


