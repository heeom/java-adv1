package com.example.util;

import static com.example.util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("interrupt " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
