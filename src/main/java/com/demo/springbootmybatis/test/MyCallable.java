package com.demo.springbootmybatis.test;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Integer call() throws Exception {
        int sum    = 0;
        // 执行任务
        for (int i=0; i<100; i++) {
            sum += i;
        }
        return Integer.valueOf(sum);
    }
}
