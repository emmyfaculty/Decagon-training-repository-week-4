package com.emmanuel;

public class SalesThread  extends Thread {
    StoreSales storeSales;

    public SalesThread(StoreSales storeSales) {
        this.storeSales = storeSales;
    }

    @Override
    public void run() {
        storeSales.sell();
        System.out.println(Thread.currentThread().getName() + " has stopped running");
        System.out.println();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}
