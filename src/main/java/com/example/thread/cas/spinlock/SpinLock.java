package com.example.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean();

    public void lock() {
        log("락 획득 시도");

        // 1. 락 사용여부 확인 2. 락 획득(락 값을 변경) -> 두 연산을 하나의 원자적인 연산으로 만듦
        while(!lock.compareAndSet(false, true)) {
            log("락 획득 실패 - 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
