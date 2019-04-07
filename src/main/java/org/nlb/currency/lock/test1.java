package org.nlb.currency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
//修饰代码块
public class test1 {
    public void test11(){
        synchronized (this) {
            for (int i = 1; i < 20; i++) {
                log.info("test11::::{}", i);
            }
        }
    }
//修饰方法
    public synchronized void test12(){
        for(int i=0;i<20;i++){
            log.info("test12:::::{}",i);
        }
    }
    public static void main(String[] args) {
        test1 test1 = new test1();
        test1 test2 = new test1();
        ExecutorService ExecutorService = Executors.newCachedThreadPool();
        ExecutorService.execute(()->{
            test1.test12();
        });
        ExecutorService.execute(()->{
            test2.test12();
        });
        ExecutorService.shutdown();
    }
}
