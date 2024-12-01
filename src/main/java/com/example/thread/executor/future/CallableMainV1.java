package com.example.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class CallableMainV1 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new MyCallable()); // Callable 인스턴스가 블로킹 큐에 전달됨 -> 스레드풀의 스레드 중 하나가 작업 실행 -> 작업 결과 반환
        Integer result = future.get(); // 작업이 완료가 안됐다면? -> 결과를 나중에 받을 수 있는 Future 객체를 대신 제공한다.
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
