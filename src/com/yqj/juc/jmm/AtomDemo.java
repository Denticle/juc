package com.yqj.juc.jmm;
/**
 * volatile不保证原子性
 * 将num使用10个线程进行++
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/10/24 15:06
 */
class NumData{
    volatile int num = 0;
    public void numAdd(){
        num++;
    }
}
public class AtomDemo {
    public static void main(String[] args) {
        while (true){
            NumData numData = new NumData();
            //预期值最终num=1000
            for (int i = 0; i < 1000; i++) {
                new Thread(() ->{
                    numData.numAdd();
                },"Thread"+ i).start();
            }
            System.out.println(Thread.currentThread().getName()+"num最终值："+ numData.num);
        }
    }
}
