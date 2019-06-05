package com.demo.springbootmybatis.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        MyCallable c = new MyCallable();

        Future f = executorService.submit(c);

        System.out.println(f.get());
        executorService.shutdown();
    }
}
