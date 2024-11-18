package com.example.thread.collection.simple;

/**
 * 프록시 역할을 하는 클래스
 */
public class SyncProxyList implements SimpleList {

    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target; // 실제 호출되는 대상
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    // 1. 락 획득
    public synchronized void add(Object o) {
        // 2. 원본 메서드 호출
        target.add(o); // synchronized만 걸고 원본 대상 호출
        // 3. 원본 메서드 반납
        // 4. 락 반납
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " by  " + this.getClass().getSimpleName();
    }
}
