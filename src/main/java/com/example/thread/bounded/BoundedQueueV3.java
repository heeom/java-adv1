package com.example.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>(); // 공유 자원
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() >= max) {
            log("put 큐가 가득참 -> 대기");
            try {
                wait(); // RUNNABLE -> WAITING / 락 반납하고 대기상태로 빠짐
                log("put 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("put 생산자 데이터 저장, notify() 호출");
        notify(); // 대기 스레드 WAIT -> BLOCKED
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터 없음 -> 대기");
            try {
                wait();
                log("[take] consumer 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] consumer 데이터 획득, notify() 호출");
        notify(); //
        return data;
    }

    @Override
    public String toString() {
        return "queue=" + queue;
    }
}
