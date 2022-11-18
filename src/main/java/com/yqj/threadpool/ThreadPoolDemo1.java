package com.yqj.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池测试
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/11/16 17:06
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        Executor executor = new ThreadPoolExecutor(1,1,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        for (int i = 0; i < 100000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                    System.out.println("当前线程名称："+Thread.currentThread().getName());
                }
            });
        }
        System.out.println("总共用时：" + (System.currentTimeMillis()-start) + "毫秒");
        System.out.println("大小："+list.size());
    }
}
