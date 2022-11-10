package com.yqj.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncDemo {

    private static int counter = 0;

    public static void increment(){
        counter++;
    }

    public static void decrement() {
        counter--;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                decrement();
            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("counter的值{}",counter);
    }
}
