package com.example.thread.control.interrupt;


import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

//21:06:39.173 [     work] 작업중
//21:06:39.173 [     work] 작업중
//21:06:39.173 [     main] 작업 중단 지시 Thread.interrupt()
//21:06:39.174 [     work] 작업중
//21:06:39.182 [     main] work 스레드 인터럽트 상태1 = true
//21:06:39.182 [     work] work 스레드 인터럽트 상태2 = true -> false로 다시 안바꿈
//21:06:39.182 [     work] 자원 정리
//21:06:39.182 [     work] 자원 종료
public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();
        sleep(100);
        log("작업 중단 지시 Thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {


        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) { // interrupt 상태 변경X
                log("작업중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            try {
                log("자원 정리");
                Thread.sleep(1000); // 인터럽트 상태가 유지되고 있음
            } catch (InterruptedException e) {
                log("자원 정리 실패 - interrupt 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("자원 종료");
        }
    }
}
