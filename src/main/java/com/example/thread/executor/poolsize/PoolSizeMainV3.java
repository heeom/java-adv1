package com.example.thread.executor.poolsize;

import com.example.thread.executor.RunnableTask;

import java.util.concurrent.*;

import static com.example.thread.executor.ExecutorUtils.printState;
import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        log("pool 생성");
        printState(executorService);

        for (int i = 0; i < 4; i++) {
            String name = "task" + i;
            executorService.execute(new RunnableTask(name));
            printState(executorService);
        }
        sleep(3000);
        log("=== 작업완료 ===");
        printState(executorService);

        sleep(3000);
        log("=== 초과 스레드 대기시간 초과 ===");
        printState(executorService);
        executorService.close();
        log("=== shutdown ===");
    }
}
