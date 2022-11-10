package com.yqj.jmm;

import com.yqj.factory.UnsafeFactory;

/**
 * 并发可见性
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/10/24 15:06
 */
public class VisibilityTest {

    private boolean flag = true;
    // volatile storeLoad  JVM内存屏障  ---->  (汇编层面指令)  lock; addl $0,0(%%rsp)
    // lock前缀指令不是内存屏障的指令，但是有内存屏障的效果   缓存失效
//    private volatile boolean flag = true;
    private int count = 0;

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:" + flag);
    }

    public void load(){
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        while (flag){
            count++;
            //可以保证可见性： 释放时间片，上下文切换，下次线程继续执行是从主内存加载，此时加载到的为最新的值
//            Thread.yield();
            //内存屏障
            UnsafeFactory.getUnsafe().storeFence();
        }
        System.out.println(Thread.currentThread().getName() + "跳出循环：count="+count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest visibilityTest = new VisibilityTest();
        Thread threadA = new Thread(() -> visibilityTest.load(),"threadA");
        threadA.start();
        Thread.sleep(1000);
        Thread threadB = new Thread(() -> visibilityTest.refresh(),"threadB");
        threadB.start();
    }

}
