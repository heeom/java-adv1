package com.example.thread.bounded;

import static com.example.util.MyLogger.log;

public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도] " + queue);
        String take = queue.take();
        log("[생산 완료] " + take + "-> " + queue);
    }
}
