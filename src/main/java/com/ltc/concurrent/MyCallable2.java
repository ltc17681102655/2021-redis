package com.ltc.concurrent;

import java.util.concurrent.*;

/**
 * @author : ltc
 * @date : 2020/3/30 16:18
 * @description :
 */

public class MyCallable2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 100;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        //确保任务只执行一次
        FutureTask futureTask = new FutureTask<>(new MyCallable2());
        service.submit(futureTask);
        Object o = futureTask.get();
        System.out.println(o);
    }
}
