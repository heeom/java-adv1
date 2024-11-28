package com.example.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class CallableMainV2 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        log("submit() 호출");
        Future<Integer> future = executorService.submit(new MyCallable());
        log("future 즉시 반환, future = " + future);
        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");

        Integer result = future.get();

        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 RUNNABLE");

        log("result value = " + result);
        log("future 완료, future = " + future);
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
