package com.example.thread.sync;

public interface BankAccount {

    /**
     * 계좌에서 돈을 출금한다
     * @param amount 출금할 금액
     * @return 계좌 잔액 >= 출금할 금액 -> true
     */
    boolean withdraw(int amount);

    /**
     * 계좌의 잔액 조회
     * @return 계좌 잔고
     */
    int getBalance();
}
