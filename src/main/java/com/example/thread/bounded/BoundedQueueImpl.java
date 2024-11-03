package com.example.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.util.MyLogger.log;

public class BoundedQueueImpl implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>(); // 공유 자원
    private final int max;

    public BoundedQueueImpl(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() >= max) {
            log("put 큐가 가득참");
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return "queue=" + queue;
    }
}
