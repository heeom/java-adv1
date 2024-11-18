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
    public synchronized void add(Object o) {
        target.add(o); // synchronized만 걸고 원본 대상 호출
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
