package com.example.thread.reject;

import com.example.thread.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.util.MyLogger.log;

public class RejectMainV1 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        executor.submit(new RunnableTask("task1"));
        try {
            executor.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            log(e);
        }

        executor.close();
    }
}
