package com.example.thread.executor.future;

import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new ExCallable());
        sleep(1000); // 잠시 대기
        try {
            log("future.get() 호출 시도, future.state() : " + future.state());
            Integer result = future.get();
            log("result value = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) { // 실행 예외 call() 내부에서 예외가 발생했을때 발생하는 예외
            log("e : " + e);
            Throwable cause = e.getCause();
            log("cause : " + cause);
        }
        executorService.close();
    }

    static class ExCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("Callable 실행 예외 발생");
            throw new IllegalStateException("runtime ex");
        }
    }
}
