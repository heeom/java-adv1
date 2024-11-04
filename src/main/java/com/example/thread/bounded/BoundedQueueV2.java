package com.example.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>(); // 공유 자원
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() >= max) {
            log("put 큐가 가득참 -> 대기");
            sleep(1000); // 대기
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터 없음 -> 대기");
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return "queue=" + queue;
    }
}
