package com.example.thread.cas.spinlock;

import static com.example.util.MyLogger.log;

public class SpinLockMain {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    // critical section -> 임계영역 보호가 안됨
                    log("비즈니스 로직 실행");
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread thread = new Thread(runnable, "T-1");
        Thread thread2 = new Thread(runnable, "T-2");
        thread.start();
        thread2.start();

    }
}
