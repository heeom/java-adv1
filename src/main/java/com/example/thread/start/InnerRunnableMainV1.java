package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class InnerRunnableMainV1 {

    public static void main(String[] args) {
        log(": main start");
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        log(": main end");
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log(": run()");
        }
    }

}
