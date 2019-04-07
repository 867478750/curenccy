package org.nlb.currency.example;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.anotations.threadSafe;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@threadSafe
public class test3 {
    public static int totalRequest = 5000;
    public static int totalThread = 200;
    public  static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(totalThread);
        final CountDownLatch countDownLatch = new CountDownLatch(totalRequest);

        for(int i = 0;i < totalRequest;i++ ){
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            add();
                            semaphore.release();
                        }catch(Exception e){
                            log.error("exception",e);
                        }
                        countDownLatch.countDown();//每次调用后就减一
                    }
            );
        }
        log.info("countDownLatch:{}",countDownLatch);
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
        log.info("countDownLatch:{}",countDownLatch);
    }
    private static void add(){
        count.increment();
        //count.incrementAndGet();先加再取，上面那个先取再加
    }
}
