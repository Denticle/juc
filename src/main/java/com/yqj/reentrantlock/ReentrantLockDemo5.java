package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 22:19
 */
@Slf4j
public class ReentrantLockDemo5 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);

        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }
        // 1s 之后去争抢锁
        Thread.sleep(1000);

        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.debug(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "强行插入" + i).start();
        }
    }
}
