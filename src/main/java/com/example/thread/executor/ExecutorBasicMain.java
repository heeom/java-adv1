package com.example.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2,2,0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        log("== 초기 상태 ==");
        printState(executorService);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(executorService);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(executorService);

        executorService.close();
        log("== shutdown ==");
        printState(executorService);
    }
}
