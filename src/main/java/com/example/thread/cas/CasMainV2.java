package com.example.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value: " + atomicInteger.get());

        int result = atomicInteger.incrementAndGet();
        System.out.println("result = " + result);

        int result2 = atomicInteger.incrementAndGet();
        System.out.println("result2 = " + result2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get(); // 0
            log("getValue : " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // cas 연산으로 변경
            log("result : " + result);
        } while (!result);
        return getValue + 1;
    }
}
