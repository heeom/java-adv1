package com.example.thread.sync;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

    private int balance;

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래시작 : " + getClass().getSimpleName());
        log("검증 시작. 출금액 : " + amount + " 잔액: " + balance);
        // 잔고 < 출금액 -> 진행X
        if (balance < amount) {
            log("검증 실패");
            return false;
        }

        log("검증 완료. 출금액 : " + amount + " 잔액: " + balance);
        sleep(1000);
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
