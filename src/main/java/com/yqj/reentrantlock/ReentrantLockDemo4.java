package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁超时
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 22:01
 */
@Slf4j
public class ReentrantLockDemo4 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            log.info("t1线程启动");
            // 注意： 即使是设置的公平锁，此方法也会立即返回获取锁成功或失败，公平策略不生效
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)){
                    log.info("t1获取锁失败，并立即返回false");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.info("t1获取到了锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lockInterruptibly();
        try {
            log.info("main线程获取到了锁");
            t1.start();
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }  finally {
            lock.unlock();
        }
    }
}
