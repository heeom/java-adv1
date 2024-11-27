package com.example.thread.executor;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class RunnableTask implements Runnable {

    private final String name;
    private int sleepMs = 100;

    public RunnableTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log(name + " 시작");
        sleep(sleepMs); // 작업 시간 시뮬레이션
        log(name + " 완료");
    }
}
