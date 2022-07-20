package com.emmanuel;

public class Main {



    public static void main(String[] args) {

        StoreStock villageGrocery = new StoreStock();
        villageGrocery.reStock();

        //villageGrocery.printProducts();
        //System.out.println(villageGrocery.search("carrot"));



        User manager = new User("Faculty", Roles.MANAGER);

        StoreHire hr = new StoreHire();
        User cashierFaith = new User("Faith", Roles.APPLICANT);
        hr.hire(manager, cashierFaith);
        //System.out.println();


        User customer = new User("Oluchi", Roles.CUSTOMER);
        customer.loadWallet(10_000.0);
        System.out.println();


        CustomerOrder firstOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder secondOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder thirdOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder fourthOrder = new CustomerOrder(customer, villageGrocery);
        CustomerOrder fifthOrder = new CustomerOrder(customer, villageGrocery);

        firstOrder.addToCart("carrot", 20);
        firstOrder.addToCart("Oatmeal Raisin", 25);
        firstOrder.addToCart("whole wheat", 23);


        secondOrder.addToCart("carrot", 18);
        secondOrder.addToCart("Oatmeal Raisin", 15);
        secondOrder.addToCart("whole wheat", 12);

        thirdOrder.addToCart("carrot", 15);
        thirdOrder.addToCart("whole wheat", 5);
        thirdOrder.addToCart("Potato Chips", 30);

        fourthOrder.addToCart("Oatmeal Raisin", 50);
        fourthOrder.addToCart("Potato Chips", 35);
        fourthOrder.addToCart("whole wheat", 2);

        fifthOrder.addToCart("Oatmeal Raisin", 55);
        fifthOrder.addToCart("carrot", 25);
        fifthOrder.addToCart("whole wheat", 15);

        StoreSales makeSales = new StoreSales(villageGrocery);


        firstOrder.placeOrder(makeSales);
        secondOrder.placeOrder(makeSales);
        thirdOrder.placeOrder(makeSales);
        fourthOrder.placeOrder(makeSales);
        fifthOrder.placeOrder(makeSales);
//        //System.out.println(makeSales.getAllOrders());
//        System.out.println(makeSales.getAllOrders());

        makeSales.loadQueue();
//        makeSales.sell();
//        System.out.println(makeSales.printReceipt());
//        System.out.println();
        makeSales.sell();
        System.out.println(makeSales.printReceipt());












    }











}
