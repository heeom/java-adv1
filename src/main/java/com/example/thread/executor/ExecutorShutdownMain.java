package com.example.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("longTask", 100_000)); // 100초 대기
        printState(executorService);
        log("=== shutdown 시작");
        shutdownAndAwaitTermination(executorService);

        log("=== shutdown 완료");
        printState(executorService);

    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown(); // non-blocking, 새로운 작업을 받지 않는다. 처리중이거나 큐에 이미 대기중인 작업은 처리함 -> 이후에 풀의 스레드 종료

        try {
            // 이미 대기중인 작업 완료까지 10초 기다린다.
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log("정상 종료 실패 -> 강제 종료 시도");
                executorService.shutdownNow(); // 강제 종료
                // 작업 취소될 때까지 대기한다.
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기 중인 현재 스레드가 interrupt 될 수 있다.
            executorService.shutdownNow();
        }

    }
}
