package com.example.thread.executor;

import java.util.concurrent.*;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class PoolSizeMainV1 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService executorService = new ThreadPoolExecutor(2, 4,
                300, TimeUnit.MILLISECONDS, workQueue);
        printState(executorService);

        executorService.execute(new RunnableTask("task1"));
        printState(executorService, "task1");

        executorService.execute(new RunnableTask("task2"));
        printState(executorService, "task2");

        executorService.execute(new RunnableTask("task3"));
        printState(executorService, "task3");

        executorService.execute(new RunnableTask("task4"));
        printState(executorService, "task4");

        executorService.execute(new RunnableTask("task5")); // 초과 스레드 추가
        printState(executorService, "task5");

        executorService.execute(new RunnableTask("task6"));
        printState(executorService, "task6");

        try {
            executorService.execute(new RunnableTask("task7"));
            printState(executorService, "task7");
        } catch(RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 : "+ e);
        }
        log("=== 작업 수행 완료 ===");
        printState(executorService);
        sleep(3000);
        log("=== maximumPoolSize 대기시간 초과 ===");
        printState(executorService);
        executorService.close();
        printState(executorService);
    }
}
