package com.ltc.concurrent;

import java.util.concurrent.*;

/**
 * @author : ltc
 * @date : 2020/3/30 16:18
 * @description :
 */

public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 100;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new MyCallable());
        Integer result = future.get();
        System.out.println(result);
    }
}
