package com.test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class MyTest {

    public static void main(String[] args) {
        Base base = new Extend();
        Extend base2 = new Extend();
        System.out.println(base.i);
        System.out.println(base2.i);
    }

    static class Base {
        public int i = 1;

        public Base() {
            g();
        }

        public void f() {
            System.out.println("base f");
        }

        public void g() {
            System.out.println("base f");
        }
    }

    static class Extend extends Base {
        public int i = 2;

        public void f() {
            System.out.println("Extend f");
        }

        public void g() {
            System.out.println("Extend f");
        }
    }

}


