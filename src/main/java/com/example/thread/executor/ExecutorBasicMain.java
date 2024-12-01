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
        // main스레드 -> queue 에 작업 보관하고 바로 다음 코드 수행
        // task가 블로킹 큐에 들어오면 작업을 처리하기 위해 스레드 생성 -> 작업이 들어올때마다 corePoolSize의 크기까지 스레드를 생성한다. -> corePoolSize까지 스레드가 생성되고 나면, 이후에는 만들어진 스레드를 재생성한다.
        log("== 작업 수행 중 ==");
        printState(executorService);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        // 작업이 완료되면 스레드 풀에 스레드가 반납 -> 반납된 스레드는 Runnable -> Waiting 상태로 스레드 풀에서 대기한다 (반납된 스레드는 재사용됨)
        printState(executorService);

        executorService.close(); // ThreadPoolExecutor가 종료된다 -> 스레드 풀에 대기하던 스레드도 제거된다.
        log("== shutdown ==");
        printState(executorService);
    }
}
