package com.example.thread.executor.poolsize;

import com.example.thread.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class PoolSizeMainV4 {

//    private static final int TASK_SIZE = 1100; // 일반
//    private static final int TASK_SIZE = 1200; // 긴급
    private static final int TASK_SIZE = 1201; // 거절

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                100, 200,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
        printState(executorService);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = String.format("task-%d", i);

            try {
                executorService.execute(new RunnableTask(taskName, 1000));
                printState(executorService);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
                printState(executorService);
            }
        }
        executorService.close();
        long endMs = System.currentTimeMillis();
        log("==== shutdown === ");
        log("endMs - startMs : " + (endMs - startMs));
    }
}
