package com.example.thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class MyPrinterV1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");
        thread.start();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            log("프린터할 문서를 입력하세요 종료 q ");
            String s = scanner.nextLine();
            if (s.equals("q")) {
                printer.work = false;
                break;
            }
            printer.add(s);
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>(); // 여러 스레드가 동시에 접근하는 변수는 동시성을 지원하는 동시성 컬렉션을 사용해야한다.

        @Override
        public void run() {
            while(work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                String job = jobQueue.poll();
                log("출력 시작 " + job + ", 대기 문서 " + jobQueue);
                sleep(3000);
                log("출력 완료");
            }
        }
        public void add(String s) {
            jobQueue.offer(s);
        }
    }
}
