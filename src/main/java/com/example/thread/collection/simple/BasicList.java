package com.example.thread.collection.simple;

import java.util.Arrays;

import static com.example.util.ThreadUtils.sleep;

public class BasicList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elem;
    private int size;

    public BasicList() {
        this.elem = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 원자적이지 않은 연산
     */
    @Override
    public void add(Object o) {
        elem[size] = o; // 1. 마지막 index + 1에 Object 저장
        sleep(100);
        size++; // 2. size 하나 올림
    }

    @Override
    public Object get(int index) {
        return elem[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elem, size)) + "  size = " + size +
                "  capacity = " + elem.length;
    }
}
