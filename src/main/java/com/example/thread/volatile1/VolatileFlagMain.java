package com.example.thread.volatile1;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        log("runFlag = " + myTask.flag);
        thread.start();

        sleep(1000);
        log("runFlag = false로 변경");
        myTask.flag = false; // main 스레드의 캐시 메모리의 flag값만 false로 변경됨(메모리 가시성)
        log("runFlag = " + myTask.flag);
        log("main end");
    }

    static class MyTask implements Runnable {
        volatile boolean flag = true; // 모든 스레드가 메인 메모리에 직접 접근

        @Override
        public void run() {
            log("task start");

            while (flag) {

            }

            log("task end");
        }
    }
}
