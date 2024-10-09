package com.example.thread.start;

public class BadThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        // main 스레드가 helloThread객체의 run 메서드를 실행
        helloThread.run(); // run() 이 아니라 start()를 호출해야 별도의 스레드에서 run 메서드가 실행된다
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
