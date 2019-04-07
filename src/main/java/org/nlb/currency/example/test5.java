package org.nlb.currency.example;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.anotations.threadSafe;

import java.util.concurrent.*;

@Slf4j
@threadSafe
public class test5 {
    public static int totalRequest = 5000;
    public static int totalThread = 200;
    public  static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(totalThread);
        final CountDownLatch countDownLatch = new CountDownLatch(totalRequest);

        for(int i = 0;i < totalRequest;i++ ) {
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            add();
                            semaphore.release();
                        } catch (Exception e) {
                            log.error("exception", e);
                        }
                        countDownLatch.countDown();//每次调用后就减一
                    });}
            executorService.shutdown();
            log.info("count:{}", count);
         }
    private synchronized static void add(){
        count++;
    }
}

