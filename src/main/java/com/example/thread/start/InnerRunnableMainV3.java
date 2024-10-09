package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class InnerRunnableMainV3 {

    public static void main(String[] args) {
        log(": main start");

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log(" run()");
//            }
//        });
//        thread1.start();
        Thread thread = new Thread(() -> log(" run()"));
        thread.start();
        log(": main end");
    }

}
