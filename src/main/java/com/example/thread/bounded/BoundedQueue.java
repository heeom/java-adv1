package com.example.thread.bounded;

public interface BoundedQueue {

    /**
     * 버퍼에 data 보관
     * @param data
     */
    void put(String data);

    /**
     * 버퍼에 데이터 꺼냄
     * @return
     */
    String take();
}
