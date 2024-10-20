package com.example.thread.sync;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

    private volatile int balance; // 메모리 가시성 문제를 해결해도 동시성 문제는 여전히 발생한다

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래시작 : " + getClass().getSimpleName());
        log("검증 시작. 출금액 : " + amount + " 잔액: " + balance);
        // 잔고 < 출금액 -> 진행X
        if (balance < amount) { // t2 : t1이 아직 차감을 하지 않았기때문에 잔고가 남아있으므로 검증로직 통과
            log("검증 실패");
            return false;
        }

        log("검증 완료. 출금액 : " + amount + " 잔액: " + balance);
        sleep(1000); // t1
        balance -= amount;
        log("출금 완료. 출금액 : " + amount + " 잔액: " + balance);

        log("거래종료 : ");
        return false;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
