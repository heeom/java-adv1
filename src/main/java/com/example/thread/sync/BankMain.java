package com.example.thread.sync;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

/**
 * 동시성 문제 발생
 */
public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        BankAccountV5 bankAccountV1 = new BankAccountV5(1000); // x001 -> 공유자원
        Thread t1 = new Thread(new WithdrawTask(bankAccountV1, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(bankAccountV1, 800), "t2");
        // 동시에 출금 시도
        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기

        log(t1.getName() + " state : " + t1.getState());
        log(t2.getName() + " state : " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액" + bankAccountV1.getBalance());
    }
}

//19:55:30.740 [       t2] 거래시작 : BankAccountV1
//19:55:30.740 [       t1] 거래시작 : BankAccountV1
//19:55:30.747 [       t1] 검증 시작. 출금액 : 800 잔액: 1000
//19:55:30.747 [       t1] 검증 완료. 출금액 : 800 잔액: 1000
//19:55:30.750 [       t2] 검증 시작. 출금액 : 800 잔액: 1000 <- !!!
//19:55:30.750 [       t2] 검증 완료. 출금액 : 800 잔액: 1000
//19:55:31.240 [     main] t1 state : TIMED_WAITING
//19:55:31.241 [     main] t2 state : TIMED_WAITING
//19:55:31.753 [       t1] 출금 완료. 출금액 : 800 잔액: 200
//19:55:31.754 [       t1] 거래종료 :
//19:55:31.755 [       t2] 출금 완료. 출금액 : 800 잔액: -600
//19:55:31.756 [       t2] 거래종료 :
//19:55:31.760 [     main] 최종 잔액-600
