package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class ManyThreadMainV1 {

    public static void main(String[] args) {
        log("main() start");

        HelloRunnable helloRunnable = new HelloRunnable(); // 동일한 인스턴스
        Thread thread = new Thread(helloRunnable);
        thread.start();
        Thread thread2 = new Thread(helloRunnable);
        thread2.start();
        Thread thread3 = new Thread(helloRunnable);
        thread3.start();
        log("main() end");
    }
}
