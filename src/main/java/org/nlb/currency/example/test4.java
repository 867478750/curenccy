package org.nlb.currency.example;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.anotations.threadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@threadSafe
public class test4 {
    private static AtomicBoolean result = new AtomicBoolean(false);
    public static int client  = 1000;
    public static int server  =10;
    public static void main(String[] args) {
        ExecutorService ExecutorService  = Executors.newCachedThreadPool();
        final Semaphore Semaphore = new Semaphore(server);
        final CountDownLatch CountDownLatch = new CountDownLatch(server);
        for (int i=1;i<client;i++){
            ExecutorService.execute(() ->
                    {try {
                            Semaphore.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CountDownLatch.countDown();
                        add();
                        Semaphore.release();

                    }
            );

        }
        log.info("result:{}", result.get());
        ExecutorService.shutdown();
    }
    private static void add() {
        result.compareAndSet(false,true);
    }
}
