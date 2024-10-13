package com.example.thread.control;

import static com.example.util.MyLogger.log;

public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        log("myThread.state1 = " + thread.getState());
        log("myThread.start() ");
        thread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState()); // TIMED_WAITING
        Thread.sleep(4000);
        log("myThread.state5 = " + thread.getState()); // RUNNABLE


    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("start");
            log("myThread.state2 = " + Thread.currentThread().getState());
            log("myThread.sleep() start");
            try {
                Thread.sleep(3000); // myThread
                log("myThread.sleep() end");
                log("myThread.state4 = " + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
