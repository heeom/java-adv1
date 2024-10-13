package com.example.thread.control;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedException implements Runnable {

        @Override
        public void run() { // throws Exception -> 부모 메서드가 체크 예외를 던지지 않음 -> 재정의된 메서드도 체크 예외를 던질 수 없음
//            throw new Exception(); 자식 메서드는 부모 메서드가 던지는 체크예외의 하위 타입만 던질 수 있음
            // RuntimeException : 언체크 예외이므로 던질 수 있음
        }
    }
}
