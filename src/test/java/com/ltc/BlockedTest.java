package com.ltc;

import org.junit.Test;

/**
 * @author : ltc
 * @date : 2020/3/30 18:02
 * @description :
 */

public class BlockedTest {

    //* 在测试方法blockedTest()内还有一个main线程，二是启动线程后执行run方法还是需要消耗一定时间的。不打断点的情况下，上面代码中都应该输出RUNNABLE。
    // * 测试方法的main线程只保证了a，b两个线程调用start()方法（转化为RUNNABLE状态），还没等两个线程真正开始争夺锁，就已经打印此时两个线程的状态（RUNNABLE）了。
    //a:RUNNABLE
    //b:RUNNABLE
    @Test
    public void demo() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    //a:TIMED_WAITING
    //b:BLOCKED
    @Test
    public void demo2() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    @Test
    public void demo3() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");
        a.start();
        a.join();
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出 TERMINATED
        System.out.println(b.getName() + ":" + b.getState());
    }

    // 同步方法争夺锁
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
