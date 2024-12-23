package com.example.thread.control.join;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class JoinTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");
        t1.start(); // 1-1,2,3
        t1.join();
        t2.start(); // 2-1,2,3
        t2.join();
        t3.start(); // 3-1,2,3
        t3.join();
        System.out.println("모든 스레드 실행 완료");
    }
    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        } }
}
