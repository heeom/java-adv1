package com.example.thread.start;

import static com.example.util.MyLogger.log;

public class ManyThreadMainV2 {

    public static void main(String[] args) {
        log("main() start");

        HelloRunnable helloRunnable = new HelloRunnable(); // 동일한 인스턴스
        for (int i = 0; i < 100; i++) {
            new Thread(helloRunnable).start();
        }
        // 스레드 실행 순서는 보장되지 않음
        log("main() end");
    }
}
