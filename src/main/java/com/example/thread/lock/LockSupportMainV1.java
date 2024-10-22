package com.example.thread.lock;

import java.util.concurrent.locks.LockSupport;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new PartTest());
        thread.start();

        // thread가 park에 빠질 시간을 준다.
        sleep(100);
        log("state : " + thread.getState());
        log("main -> unpark " + Thread.currentThread().getState());
        LockSupport.unpark(thread);
        log("인터럽트 상태 " + Thread.currentThread().isInterrupted());
    }

    static class PartTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료 : " + Thread.currentThread().getState());
            log("park interrupt : " + Thread.currentThread().isInterrupted());
        }
    }
}
