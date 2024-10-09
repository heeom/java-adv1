package com.example.thread.start;

public class StartTest1Main {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();
    }
}
