package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件等待队列测试
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 11:18
 */
@Slf4j
public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+"开始处理任务");
                log.info("----当前线程thread1调用了await(),释放当前持有的锁，阻塞当前线程---");
                condition.await();
                log.info(Thread.currentThread().getName()+"重新获取锁结束处理任务");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"thread1").start();

        new Thread(()->{
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+"获取锁开始处理任务");
                Thread.sleep(2000);
                condition.signal();
                log.info(Thread.currentThread().getName()+"任务处理结束");
                log.info("----当前线程thread2调用了signal(),唤醒因调用Condition#await方法而阻塞的thread1---");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"thread2").start();
    }

}
