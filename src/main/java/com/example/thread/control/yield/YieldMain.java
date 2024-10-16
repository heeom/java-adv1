package com.example.thread.control.yield;


import static com.example.util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable()); // 1000개 쓰레드 실행
            thread.start(); // 각쓰레드는 0-9 출력
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
//                sleep(1); // RUNNABLE -> TIMED_WAITING -> RUNNABLE 복잡한 과정 거침, 나머지 스레드가 모두 쉬고 있는데 해당 스레드도 쉬는 상황 발생할 수도있음
                Thread.yield(); // 대기 큐, CPU에서 실행 -> 모두 RUNNABLE 상태, 상태가 변경되지 않음
                // RUNNABLE 상태를 유지하면서 cpu 양보한다, running 상태에서 ready 상태가 되는 것
            }
        }
    }
}
