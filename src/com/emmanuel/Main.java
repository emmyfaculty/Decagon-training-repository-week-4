package com.emmanuel;

import java.io.InterruptedIOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {



    public static void main(String[] args) throws InterruptedException {

        StoreStock villageGrocery = new StoreStock();
        villageGrocery.reStock();
        System.out.println(" Testing ...." + villageGrocery.reStock().get(0).getName()  );
        //villageGrocery.printProducts();
        //System.out.println(villageGrocery.search("carrot"));



        User manager = new User("Faculty", Roles.MANAGER);

        StoreHire hr = new StoreHire();
        User cashierFaith = new User("Faith", Roles.APPLICANT);
        hr.hire(manager, cashierFaith);
        //System.out.println();


        User customer = new User("Oluchi", Roles.CUSTOMER);
        customer.loadWallet(10_000.0);
        User customer2 = new User("Joyce", Roles.CUSTOMER);
        customer.loadWallet(5_000.0);
        User customer3 = new User("Patsy", Roles.CUSTOMER);
        customer.loadWallet(15_000.0);
        User customer4 = new User("Tricia", Roles.CUSTOMER);
        customer.loadWallet(20_000.0);
        User customer5 = new User("Lizzy", Roles.CUSTOMER);
        customer.loadWallet(25_000.0);
        System.out.println();


        CustomerOrder firstOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder secondOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder thirdOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder fourthOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder fifthOrder = new CustomerOrder(customer, villageGrocery);

        for (int i = 0; i < 100; i++) {
            firstOrder.addToCart(customer, "Carrot", 20);
//            firstOrder.addToCart(customer, "Oatmeal Raisin", 25);
//            firstOrder.addToCart(customer, "whole wheat", 23);
        }

        System.out.println(firstOrder.addToCart(customer, "Carrot", 20));
        System.out.println(firstOrder.addToCart(customer, "Oatmeal Raisin", 25));
        System.out.println(firstOrder.addToCart(customer, "whole wheat", 23));


        secondOrder.addToCart(customer2,"carrot", 18);
        secondOrder.addToCart(customer2,"Oatmeal Raisin", 15);
        secondOrder.addToCart(customer2,"whole wheat", 12);

        thirdOrder.addToCart(customer3,"carrot", 15);
        thirdOrder.addToCart(customer3,"whole wheat", 5);
        thirdOrder.addToCart(customer3,"Potato Chips", 30);

        fourthOrder.addToCart(customer4,"Oatmeal Raisin", 50);
        fourthOrder.addToCart(customer4,"Potato Chips", 35);
        fourthOrder.addToCart(customer4,"whole wheat", 2);

        fifthOrder.addToCart(customer5,"Oatmeal Raisin", 55);
        fifthOrder.addToCart(customer5,"carrot", 25);
        fifthOrder.addToCart(customer5,"whole wheat", 15);


        StoreSales makeSales = new StoreSales(villageGrocery);


        firstOrder.placeOrder(makeSales);
        secondOrder.placeOrder(makeSales);
        thirdOrder.placeOrder(makeSales);
        fourthOrder.placeOrder(makeSales);
        fifthOrder.placeOrder(makeSales);
//        //System.out.println(makeSales.getAllOrders());
//        System.out.println(makeSales.getAllOrders());

        makeSales.loadQueue();
        makeSales.sell();
        System.out.println(makeSales.printReceipt());
//        System.out.println();
//        makeSales.sell();
//        System.out.println(makeSales.printReceipt());

        CopyOnWriteArrayList<Thread> threadList = new CopyOnWriteArrayList<>();

        PurchaseTread purchaseTread = new PurchaseTread(customer,"Carrot",20,villageGrocery);
        Thread thread = new Thread(purchaseTread);
        PurchaseTread purchaseTread2 = new PurchaseTread(customer,"Oatmeal Raisin", 25,villageGrocery);
        Thread thread2 = new Thread(purchaseTread2);
        PurchaseTread purchaseTread3 = new PurchaseTread(customer,"whole wheat", 23,villageGrocery);
        Thread thread3 = new Thread(purchaseTread3);
//        PurchaseTread purchaseTread4 = new PurchaseTread(customer,"Carrot",20);
//        Thread thread = new Thread(purchaseTread);
        thread.setName("Carrot thread");
        thread2.setName("Oatmeal thread");
        thread3.setName("Whole wheat thread");
//        thread.setName("Carrot thread");

        threadList.add(thread);
        threadList.add(thread2);
        threadList.add(thread3);

        for(Thread th : threadList){
            th.start();
           th.join();

        }

        System.out.println(villageGrocery.getInventories().get(villageGrocery.getMatchIndex("carrot")));
        for (int i = 0; i < 100; i++) {
            SalesThread salesThread = new SalesThread(makeSales);
            salesThread.start();
            try {
               salesThread.join();
            } catch (InterruptedException e) {

            }
        }

        System.out.println(villageGrocery.getInventories().get(villageGrocery.getMatchIndex("carrot")));
//        System.out.println(villageGrocery.getInventories().get(villageGrocery.getMatchIndex("Oatmeal Raisin")));
//        System.out.println(villageGrocery.getInventories().get(villageGrocery.getMatchIndex("whole wheat")));

    }

}
