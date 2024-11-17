package com.example.thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new MyAtomicInteger()); // 락사용 X
        test(new SyncInteger()); //락 사용
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();

        System.out.println(incrementInteger.getClass().getSimpleName() + " : ms = " + (endMs - startMs));

    }
}
