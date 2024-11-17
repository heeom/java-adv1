package com.example.thread.cas.spinlock;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");

        while(true) {
            if (!lock) { // 락 사용여부 확인
                sleep(100);
                lock = true; // 락 획득
                break;
            } else {
                // 락획득할떄까지 스핀 대기
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 성공");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
