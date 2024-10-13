package com.example.thread.control.join;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("Start");
        Thread thread = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");
        thread.start();
        thread2.start();
        log("End");
        // thread1,2에서 작업한 결과를 main스레드에서 받아서 작업해야하는 경우
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("시작");
            sleep(2000);
            log("끝");
        }
    }
}
