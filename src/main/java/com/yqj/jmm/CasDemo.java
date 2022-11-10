package com.yqj.jmm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS 原理演示
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/02 15:06
 */
public class CasDemo implements Runnable {

    private volatile int value;
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue){
            value = newValue;
            System.out.println("线程" + Thread.currentThread().getName() + "修改成功");
        }else {
            System.out.println("线程" + Thread.currentThread().getName() + "修改失败");
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
//        CasDemo casDemo = new CasDemo();
//        casDemo.value = 0;
//        Thread t1 = new Thread(casDemo,"t1");
//        Thread t2 = new Thread(casDemo,"t2");
//        t1.start();
//        t2.start();
//        t1.join();
//        t1.join();
        System.out.println(test(0));
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }


    public static  int test(int i){
        try{
            i++;
            return i;
        }finally {
            i++;
        }
    }
}
