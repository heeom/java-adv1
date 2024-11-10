package com.example.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.example.util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // IllegalStateException – if this queue is full
    }

    @Override
    public String take() {
        return queue.remove(); // NoSuchElementException – if this queue is empty
    }

    @Override
    public String toString() {
        return "queue=" + queue;
    }
}
