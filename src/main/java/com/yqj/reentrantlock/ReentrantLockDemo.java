package com.yqj.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zhao Yun Long
 * @Description: ReentrantLock基本使用
 * @version V1.0
 * @date 2022/11/11 20:27
 */
public class ReentrantLockDemo {

    private static int num = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                lock.lock();
                try {
                    for (int j = 0; j < 100000; j++) {
                        num++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println(num);
    }
}
