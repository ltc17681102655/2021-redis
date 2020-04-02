package com.ltc.concurrent;

/**
 * @author : ltc
 * @date : 2020/3/30 16:55
 * @description :
 */

public class MyThreadGroup {

    public static void main(String[] args) {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字：" +
                    Thread.currentThread().getName());
        });

        testThread.start();
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());
        //默认优先级是5
        System.out.println(testThread.getPriority());
    }
}
