package com.example.thread.executor.future;

import java.util.Random;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class RunnableMain {


    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join(); // join으로 thread-1의 작업이 끝날때까지 기다렸다가 (대기)
        int result = task.value; // main스레드에서 thread-1에서 작업한 결과값을 가져옴 (찾기)
        log("result value " + result);
    }
    
    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10); // 보관
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
