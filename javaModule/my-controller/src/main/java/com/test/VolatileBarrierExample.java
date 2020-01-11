package com.test;

/**
 * @author 陈宜康
 * @date 2019/10/14 19:59
 * @forWhat
 */
public class VolatileBarrierExample {
    long a;
    volatile long v1 = 1;
    volatile long v2 = 1;

    public static void main(String[] args) {
        final VolatileBarrierExample ex = new VolatileBarrierExample();
        for (int i = 0; i < 50000; i++)
            ex.readAndWrite();
    }

    void readAndWrite() {
        long j = v1;
        long i = v2;
        a = i + j;
        v1 = i + 1;
        long v = v1;
        v2 = j * 2;
    }
}
