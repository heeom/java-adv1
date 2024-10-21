package com.example.thread.sync.test;

public class Immutable {
    private final int value; // 공유자원 : final 키워드가 붙으면 어떤스레드도 값을 변경할 수 없으므로 멀티스레드 상황에 문제 없는 안전한 공유 자원이다.
    public Immutable(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
