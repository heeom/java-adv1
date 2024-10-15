package com.example.thread.control.interrupt;


import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();
        sleep(4000);
        log("작업 중단 지시 runFlag=false");
        myTask.runFlag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true; // 여러 스레드에서 공유하는 값에 사용되는 키워드

        @Override
        public void run() {
            while(runFlag) {
                log("작업중");
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }
    }
}
