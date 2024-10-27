package com.example.thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private int balance;
    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래시작 : " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입실패] 이미 처리중인 작업 있음");
                // ReenterantLock 을 이용해 lock 걸기
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            log("검증 시작. 출금액 : " + amount + " 잔액: " + balance);
            if (balance < amount) { // t2 : t1이 아직 차감을 하지 않았기때문에 잔고가 남아있으므로 검증로직 통과
                log("검증 실패");
                log("검증 완료. 출금액 : " + amount + " 잔액: " + balance);
                return false;
            }

            sleep(1000); // t1
            balance -= amount;

            log("출금 완료. 출금액 : " + amount + " 잔액: " + balance);

        } finally {
            lock.unlock(); // lock 호출 이후에는 반드시 unlock
        }

        log("거래종료 : ");
        return false;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
