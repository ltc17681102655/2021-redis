package com.ltc.concurrent.lock;

/**
 * @author : ltc
 * @date : 2020/3/31 20:01
 * @description :需要注意的是等待/通知机制使用的是使用同一个对象锁，如果你两个线程使用的是不同的对象锁，那它们之间是不能用等待/通知机制通信的。
 */

public class WaitAndNotify {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadA: " + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB: " + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}

// 输出：
//        ThreadA: 0
//        ThreadB: 0
//        ThreadA: 1
//        ThreadB: 1
//        ThreadA: 2
//        ThreadB: 2
//        ThreadA: 3
//        ThreadB: 3
//        ThreadA: 4
//        ThreadB: 4

