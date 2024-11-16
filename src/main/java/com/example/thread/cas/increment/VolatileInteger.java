package com.example.thread.cas.increment;

/**
 * volatile은 여러 CPU 사이에 발생하는 캐시 메모리와 메인 메모리가 동기화 되지 않는 문제를 해결할 뿐
 * - cpu의 캐시 메모리를 무시하고, 메인 메모리르 직접 사용하도록 한다.
 * - 하지만 이 문제는 캐시메모리가 영향을 줄 수는 있지만, 메인메모리를 직접 사용해도 여전히 발생하는 문제
 * - 원인 : 연산 자체가 나눠져 있기떄문 (1. value 읽기 2. value + 1 3. value에 새로운 값 저장)
 * -> volatile은 연산 자체를 원자적으로 묶어주는 기능이 아님
 * -> 이렇게 연산 자체가 나눠져 있는 경우(원자적이지 않은 연산)에는 synchronized 블럭이나 Lock을 사용해서 안전한 임계 영역을 만들어야 한다.
 */
public class VolatileInteger implements IncrementInteger {

    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
