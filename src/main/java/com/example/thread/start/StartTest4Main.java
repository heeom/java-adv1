package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {
        Thread threadA = new Thread(new TestRunnable("A", 1000));
        threadA.start();
        Thread threadB = new Thread(new TestRunnable("B", 500));
        threadB.start();
    }

    static class TestRunnable implements Runnable {
        private String name;
        private int milliSeconds;

        public TestRunnable(String name, int milliSeconds) {
            this.name = name;
            this.milliSeconds = milliSeconds;
        }

        @Override
        public void run() {
            while (true) {
                log(name);
                try {
                    Thread.sleep(milliSeconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
