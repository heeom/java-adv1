package com.example.thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class CallableMainV2 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1); // 스레드 풀 생성
        log("submit() 호출");
        Future<Integer> future = executorService.submit(new MyCallable()); // Future 인터페이스의 구현체 -> FutureTask 생성
        // 생성한 Future 객체에 MyCallable 인스턴스 보관 -> 인스턴스 내부에 task 작업완료여부, 작업의 결과물 보관
        // submit 호출시 task가 바로 블로킹 큐에 담기는 것이 아니라 task를 감싸고 있는 Future가 대신 BlockingQueue에 담긴다.
        log("future 즉시 반환, future = " + future); // 작업 전달시 생성한 Future는 즉시 반환됨, 요청(main) 스레드는 대기하지 않음

        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        // future 실행 -> futureTask.run() -> myCallable.call() 호출
        Integer result = future.get(); // 블로킹 : Future가 완료상태가 될때까지 대기
        log("future.get() [블로킹] 메서드 호출 완료 -> main 스레드 RUNNABLE");


        log("result value = " + result); // task 작업 완료 -> future에 작업 결과 담고, future 완료 상태로 변경
        log("future 완료, future = " + future); // main 스레드 : WAITING -> RUNNABLE 상태가 됨
        executorService.close();
    }
    
    static class MyCallable implements Callable<Integer> { // 반환할 제네릭 타입

        @Override
        public Integer call() throws Exception {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(100);
            log("create value = " + value);
            log("Callable 완료 ");
            return value;
        }
    }
}
