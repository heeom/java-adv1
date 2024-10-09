package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class CounterThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                log(i + 1);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
