package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可中断demo
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 21:33
 */
@Slf4j
public class ReentrantLockDemo3 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(()->{
            log.info("thread1线程启动");
            try {
                lock.lockInterruptibly();
                try {
                    log.info("thread1线程获取到锁");
                }finally {
                    lock.unlock();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                log.info("thread1等锁的过程中断了");
            }
        },"thread1");

        lock.lock();
        try {
            log.info("main线程拿到了锁");
            thread1.start();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            thread1.interrupt();
            log.info("thread1线程被中断");
        }finally {
            lock.unlock();
        }
    }
}
