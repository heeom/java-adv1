package com.example.thread.executor.future;

import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new MyTask());
        log("future.state " + future.state());

        // 일정 시간 후 취소 시도
        sleep(3000);

        // cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancel = future.cancel(mayInterruptIfRunning);
        log("cancel : " + mayInterruptIfRunning + " result : " + cancel);


        try {
            log("Future result : " + future.get());
        } catch (CancellationException e) {
            log("Future 이미 취소");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {

            try {
                for (int i = 0; i < 10; i++) {
                    log("작업중 : " + i);
                    Thread.sleep(1000); // 1초 동안 sleep
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "InterruptedException";
            }
            return "Completed";
        }
    }
}
