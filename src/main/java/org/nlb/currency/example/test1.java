package org.nlb.currency.example;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.anotations.threadNotSafe;

import java.util.concurrent.*;

@Slf4j
@threadNotSafe
public class test1 {
    public static int totalRequest = 5000;
    public static int totalThread = 200;
    public  static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(totalThread);
        final CountDownLatch countDownLatch = new CountDownLatch(totalRequest);
        for(int i = 0;i < totalRequest;i++ ){
            executorService.execute(
//                    () -> {
//                try {
//                semaphore.acquire();
//
//                add();
//                semaphore.release();
//                }catch(Exception e){
//                    log.error("exception",e);
//                }
//                countDownLatch.countDown();//每次调用后就减一
//                    }
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                semaphore.acquire();

                add();
                semaphore.release();
                }catch(Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
                        }
                    }
            );
            if(i == 255){
                log.info("semaphore.availablePermits:{}",semaphore.availablePermits());
            }
        }
        log.info("countDownLatch:{}",countDownLatch);
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
        log.info("countDownLatch:{}",countDownLatch);
    }
    private static void add(){
        count++;
    }
}
