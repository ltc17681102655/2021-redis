package com.ltc.concurrent.lock;

/**
 * @author : ltc
 * @date : 2020/3/30 18:44
 * @description :
 */

public class Lock {

    private static Object object = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}
