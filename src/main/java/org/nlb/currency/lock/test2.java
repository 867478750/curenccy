package org.nlb.currency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class test2 {
    public static void test21(){
        synchronized (test2.class) {
            for (int i = 1; i < 10; i++) {
                log.info("test11{}", i);
            }
        }
    }
    //修饰静态方法
    public static synchronized void test22(){
        for(int i=0;i<10;i++){
            log.info("test12:{}",i);
        }
    }
    public static void main(String[] args) {
        ExecutorService ExecutorService = Executors.newCachedThreadPool();
        test2 test31 = new test2();
        test2 test32 = new test2();
        ExecutorService.execute(()->{
            test32.test22();
        });
        ExecutorService.execute(()->{
            test31.test22();
        });
        ExecutorService.shutdown();

    }
}
