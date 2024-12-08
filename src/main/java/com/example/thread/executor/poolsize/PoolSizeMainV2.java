package com.example.thread.executor.poolsize;

import com.example.thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        log("pool 생성");
        printState(executorService);

        for (int i = 0; i < 6; i++) {
            String name = "task" + i;
            executorService.execute(new RunnableTask(name));
            printState(executorService);
        }
        executorService.shutdown();
        log("=== shutdown ===");
    }
}
