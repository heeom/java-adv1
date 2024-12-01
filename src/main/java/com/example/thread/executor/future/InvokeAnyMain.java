package com.example.thread.executor.future;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);
        Integer value = executorService.invokeAny(tasks);// task1,2,3 중에 하나라도 먼저 완료되면 리턴하고, 나머지 작업은 취소 처리

        log("value = " + value);
        executorService.close();
    }
}
