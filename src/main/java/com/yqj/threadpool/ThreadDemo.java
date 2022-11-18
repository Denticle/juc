package com.yqj.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 线程测试
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/16 15:06
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    list.add(random.nextInt());
                    System.out.println("当前线程的名称："+Thread.currentThread().getName());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println("总共用时："+(System.currentTimeMillis()-start)/1000 + "秒");
        System.out.println("大小："+list.size());
    }
}
