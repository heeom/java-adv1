package com.example.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class CallableMainV1 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new MyCallable());
        Integer result = future.get();
        log("result value = " + result);
        executorService.close();
    }
    
    static class MyCallable implements Callable<Integer> { // 반환할 제네릭 타입

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(100);
            log("create value = " + value);
            log("Callable 완료 ");
            return value;
        }
    }
}
