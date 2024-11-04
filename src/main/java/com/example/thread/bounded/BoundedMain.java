package com.example.thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BoundedMain {

    public static void main(String[] args) {
        BoundedQueue boundedQueue = new BoundedQueueV3(2);
        // 생산자 먼저 실행
//        producerFirst(boundedQueue);
        // 소비자 먼저 실행
        consumerFirst(boundedQueue);
    }

    private static void consumerFirst(BoundedQueue boundedQueue) {
        log("== 소비자 먼저 실행 시작, " + boundedQueue.getClass().getSimpleName() + "===");
        List<Thread> threads = new ArrayList<>();
        startConsumer(boundedQueue, threads);
        printAllState(boundedQueue, threads);
        startProducer(boundedQueue, threads);
        printAllState(boundedQueue, threads);
        log("== 소비자 먼저 실행 종료, " + boundedQueue.getClass().getSimpleName() + "===");

    }

    private static void producerFirst(BoundedQueue boundedQueue) {
        log("== 생산자 먼저 실행 시작, " + boundedQueue.getClass().getSimpleName() + "===");
        List<Thread> threads = new ArrayList<>();
        startProducer(boundedQueue, threads);
        printAllState(boundedQueue, threads);
        startConsumer(boundedQueue, threads);
        printAllState(boundedQueue, threads);
        log("== 생산자 먼저 실행 종료, " + boundedQueue.getClass().getSimpleName() + "===");
    }

    private static void startConsumer(BoundedQueue boundedQueue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(boundedQueue), "consumer-" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue boundedQueue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 : " + boundedQueue);
        for (Thread thread : threads) {
            log(thread.getName() + " : " + thread.getState());
        }
    }

    private static void startProducer(BoundedQueue boundedQueue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(boundedQueue, "data " + i), "producer-" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }
}
