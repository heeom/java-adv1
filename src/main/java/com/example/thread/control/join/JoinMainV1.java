package com.example.thread.control.join;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class JoinMainV1 {

    public static void main(String[] args) {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();
        log("task1 = " + task1.result);
        log("task2 = " + task2.result);

        int total = task1.result + task2.result;
        log("total = " + total);
        log("end");
    }

    static class SumTask implements Runnable {
        int start;
        int end;
        int result;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            log("시작");
            sleep(2000);
            log("끝");
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 result = " + result);
        }
    }
}
