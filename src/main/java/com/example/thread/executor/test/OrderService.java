package com.example.thread.executor.test;

import java.util.List;
import java.util.concurrent.*;

import static com.example.util.MyLogger.log;
import static com.example.util.ThreadUtils.sleep;

public class OrderService {

    public void order(String orderNo) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AccountingWork accountingWork = new AccountingWork(orderNo);
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);

        // 작업 요청
        List<Callable<Boolean>> orders = List.of(accountingWork, inventoryWork, shippingWork);
        List<Future<Boolean>> futures = executorService.invokeAll(orders);

        // 결과 확인
        for (Future<Boolean> future : futures) {
            if (!future.get()) {
                log("일부 작업이 실패했습니다.");
                break;
            }
        }
        log("모든 주문 처리가 성공적으로 완료되었습니다.");
        executorService.close();
    }

    static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("배송 시스템 알림: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("회계 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

}