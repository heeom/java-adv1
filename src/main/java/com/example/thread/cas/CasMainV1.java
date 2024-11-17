package com.example.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value: " + atomicInteger.get());

        // 원자적으로 실행되는 메서드
        boolean result1 = atomicInteger.compareAndSet(0, 1); // 비교 결과가 true면 set
        System.out.println("result1 value: " + result1 + " value : "+atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result2 value: " + result2 + " value : "+atomicInteger.get());
    }
}
