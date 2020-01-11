package com.utils;

/**
 * @author 陈宜康
 * @date 2019/8/17 9:07
 * @forWhat
 */
public class Test {
    private static int shit = 1;

    static {
        System.out.println("一开始" + shit);
        shit = 2;
        System.out.println("之后" + shit);
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(shit);
        System.out.println(Thread.currentThread());
        Thread thread = new Thread(new child());
        thread.start();
        System.out.println("结束了");
    }

    static class child implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程的方法");
        }
    }

}
