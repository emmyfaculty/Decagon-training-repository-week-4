package com.emmanuel;


public class PurchaseTread implements Runnable{
    private final User user;
    private final String productName;
    private final int quantity;
    private StoreStock stock;

    StoreStock storeStock = new StoreStock();




    public PurchaseTread(User user, String productName, int quantity,StoreStock stock) {
        this.user = user;
        this.productName = productName;
        this.quantity = quantity;
        this.stock =stock;
    }


    @Override
     public void run() {
        CustomerOrder customerOrder = new CustomerOrder(user,stock);

        System.out.println(Thread.currentThread().getName() + " is currently running");
        System.out.println();
        System.out.println(customerOrder.addToCart(user, productName, quantity));
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " has stopped running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
