package com.example.thread.volatile1;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);
        thread.start();
        sleep(1000);
        myTask.flag = false;
        log("flag = " + myTask.flag + " count = " + myTask.count + " in main");
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while(flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + " count = " + count);
                }
            }
            log("flag = " + flag + " count = " + count);
        }
    }
}
