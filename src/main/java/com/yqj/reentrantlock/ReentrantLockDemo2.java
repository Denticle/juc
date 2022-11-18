package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入测试
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 21:01
 */
@Slf4j
public class ReentrantLockDemo2 {

    private static Lock lock = new ReentrantLock();

    public static void reenTrantTest1(){
        lock.lock();
        try {
            log.info("reenTrantTest1 执行");
            reenTrantTest2();
        }finally {
            lock.unlock();
        }
    }

    public static void reenTrantTest2(){
        lock.lock();
        try {
            log.info("reenTrantTest2 执行");
            reenTrantTest3();
        }finally {
            lock.unlock();
        }
    }

    public static void reenTrantTest3(){
        lock.lock();
        try {
            log.info("reenTrantTest3 执行");
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        reenTrantTest1();
    }
}
