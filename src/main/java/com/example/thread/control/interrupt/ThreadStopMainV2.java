package com.example.thread.control.interrupt;


import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

//20:45:52.090 [     work] 작업중
//20:45:55.097 [     work] 작업중
//20:45:56.065 [     main] 작업 중단 지시 Thread.interrupt()
//20:45:56.081 [     main] work 스레드 인터럽트 상태1 = true
//20:45:56.081 [     work] work 스레드 인터럽트 상태2 = false
//20:45:56.083 [     work] interrupt message = sleep interrupted
//20:45:56.084 [     work] state = RUNNABLE
    // 변수 사용하는 것보다 interrupt()를 사용하는것이 반응성이 더 좋다

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();
        sleep(4000);
        log("작업 중단 지시 Thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true; // 여러 스레드에서 공유하는 값에 사용되는 키워드

        @Override
        public void run() {
            try {
                while (true) {
                    log("작업중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message = " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
            }
        }
    }
}
