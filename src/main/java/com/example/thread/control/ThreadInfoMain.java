package com.example.thread.control;

import com.example.thread.start.HelloThread;

import static com.example.util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId()=" + mainThread.threadId());
        log("mainThread.name()=" + mainThread.getName());
        log("mainThread.priority()=" + mainThread.getPriority());
        log("mainThread.threadGroup()=" + mainThread.getThreadGroup());
        log("mainThread.state()=" + mainThread.getState());

        Thread thread = new Thread(new HelloThread(), "myThread");
        log("myThread = " + thread);
        log("myThread.threadId()=" + thread.threadId());
        log("myThread.name()=" + thread.getName());
        log("myThread.priority()=" + thread.getPriority());
        log("myThread.threadGroup()=" + thread.getThreadGroup());
        log("myThread.state()=" + thread.getState());
    }

}
