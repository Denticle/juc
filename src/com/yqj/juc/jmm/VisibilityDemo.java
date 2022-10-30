package com.yqj.juc.jmm;

/**
 * volatile可见性
 *
 * @author Zhao Yun Long
 * @version V1.0
 * @date 2022/10/24 15:06
 */
class FlagVar{
    volatile boolean flag = true;
    public void changeFlag(){
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:" + flag);
    }
}

public class VisibilityDemo {

    public static void main(String[] args) {
        FlagVar flagVar = new FlagVar();
        // 开启一个线程修改flag的值
        new Thread(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "开始执行");
            flagVar.changeFlag();
        },"AAA").start();
        //主线程判断flag的值，为true时一直死循环
        while (flagVar.flag){
            //
        }
        //flag为false时执行如下代码
        System.out.println(Thread.currentThread().getName() + "执行完成！！！");
    }

}
