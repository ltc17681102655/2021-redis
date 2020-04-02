package com.ltc.concurrent;

import java.util.stream.IntStream;

/**
 * @author : ltc
 * @date : 2020/3/30 15:50
 * @description :
 */

public class MyThread extends Thread {

    @Override
    public void run() {
//        System.out.println("hello thread");
        System.out.println(String.format("当前执行的线程是：%s，优先级：%d",
                Thread.currentThread().getName(),
                Thread.currentThread().getPriority()));
    }

    public static void main(String[] args) {
        new MyThread().start();
        //
        IntStream.range(1, 10).forEach(c -> {
            Thread thread = new Thread(new MyThread());
            thread.setPriority(c);
            thread.start();
        });
    }
}
