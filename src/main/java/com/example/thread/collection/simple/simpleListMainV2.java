package com.example.thread.collection.simple;

import static com.example.util.MyLogger.log;

/**
 * 21:01:47.548 [     main] BasicList
 * 21:01:47.652 [ Thread-1] Thread-1: list.add(A)
 * 21:01:47.656 [ Thread-2] Thread-2: list.add(B)
 * 21:01:47.656 [     main] [B, null]  size = 2  capacity = 5
 */
public class simpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
        SimpleList basicList = new BasicList();
        test(basicList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread thread = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");
        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        log(list);
    }
}
