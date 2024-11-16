package com.example.thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static com.example.util.ThreadUtils.sleep;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicInteger());
        test(new VolatileInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10);
                incrementInteger.increment(); // 1000번 호출
            }
        };

        List<Thread> threads = new ArrayList<>();
        // Thread 1000개 생성
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        int result = incrementInteger.get();
        // result = 1000이 아님
        System.out.println(incrementInteger.getClass().getSimpleName() + " result : " + result);
    }
}
