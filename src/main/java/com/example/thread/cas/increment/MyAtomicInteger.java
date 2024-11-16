package com.example.thread.cas.increment;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 특정 값을 증가하거나 감소해야하는데, 여러 스레드가 해당 값을 공유해야한다면, AtomicInteger을 사용하면 된다
 * AtomicInteger, AtomicLong, AtomicBoolean 등 다양한 AtomicXXX 클래스가 존재한다.
 */
public class MyAtomicInteger implements IncrementInteger {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public synchronized void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public synchronized int get() {
        return atomicInteger.get();
    }
}
