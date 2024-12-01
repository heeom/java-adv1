package com.example.thread.executor.future;

import java.util.concurrent.*;

import static com.example.util.MyLogger.log;

public class SumTaskMainV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = executorService.submit(sumTask1);
        Future<Integer> future2 = executorService.submit(sumTask2);

        Integer sum1 = future1.get();
        Integer sum2 = future2.get();

        log("task1.result : " + sum1);
        log("task2.result : " + sum2);

        int sum = sum1 + sum2;
        log("sumAll = " + sum);
        executorService.close();
        log("== End ==");
    }

    static class SumTask implements Callable<Integer> {
        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws InterruptedException { // checked 예외를 던질 수 있다
            log("작업시작");
            Thread.sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            log("작업완료 sum = " + sum);

            return sum;
        }
    }
}
