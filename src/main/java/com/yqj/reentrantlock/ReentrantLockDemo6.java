package com.yqj.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件变量
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/11 22:26
 */
@Slf4j
public class ReentrantLockDemo6 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition singleRowCon = lock.newCondition();

    // 玩家数量
    private static int players = 1;

    // 单排
    public void doubleRow(){
        lock.lock();
        try {
            while (players == 1){
                try {
                    log.info("只有一个人，那单排吧");
                    singleRowCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("咦，来人了，一起玩吧");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo6 reentrantLockDemo6 = new ReentrantLockDemo6();
        new Thread(()->{
            reentrantLockDemo6.doubleRow();
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                players = 1;
                //叫人来了，告诉他不要单排了，一起玩
                singleRowCon.signal();
            }finally {
                lock.unlock();
            }
        }).start();
    }


}
