package com.ltc.concurrent;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @author : ltc
 * @date : 2020/3/30 15:50
 * @description :
 */

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("hello runnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        //
        new Thread(() -> {
            System.out.println("hello jdk8");
        }).start();
    }
}

