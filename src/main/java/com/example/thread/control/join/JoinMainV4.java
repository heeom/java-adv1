package com.example.thread.control.join;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread-1");
        thread1.start();

        // 스레드가 종료될때까지 대기
        // join() 대상 스레드가 완료될때까지 무한정 대기
        // join(ms) 대상 스레드가 완료될때까지 특정 시간동안만 대기
        log("main 스레드가 thread1 종료(TERMINATED)까지 1초 대기");
        thread1.join(1000); // main 스레드 : TIMED_WAITING 상태
        log("대기 완료");
        log("task1 = " + task1.result);

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
