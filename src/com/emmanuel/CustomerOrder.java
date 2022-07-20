package com.emmanuel;


import java.util.*;

public class CustomerOrder {
    private User customer;
    private StoreStock stock;
    private ArrayList<Product> cart;
  //  static final Comparator<Product> QUANTITY_ORDER = new Comparator<Product>() {
//        @Override
//        public int compare(Product product1, Product product2) {
//            int result = 0;
//            if (product1.getQuantity() > product2.getQuantity()) {
//                result = -1;
//            } else if (product1.getQuantity() < product2.getQuantity()) {
//                result = 1;
//            } else {
//                result = 0;
//            }
//            return result;
//        }
//    };


    public CustomerOrder(User customer, StoreStock stock) {
        this.customer = customer;
        this.stock = stock;
        this.cart = new ArrayList<>();
    }


//
//    public User getCustomer() {
//        return customer;
//    }

    public ArrayList<Product> getCart() {
        return cart;
    }


    /**
     /* @param productName
     /* @param quantity
     * this method uses getMatchIndex to check the availability of the product,
     * then create a Product object with the return value
     * then checks for sufficient balance and quantity
     * finally adds a new aproduct object into the cart
     */

    public void addToCart (String product, int quantity) {
        int index = stock.getMatchIndex(product);
        if (index >= 0) {
            Product newPro = stock.getInventories().get(index);
            if(customer.getBalance() >= (quantity * newPro.getPrice()) && quantity <= newPro.getQuantity()) {
                cart.add(new Product(newPro.getCategory(), product, quantity, newPro.getPrice()));
                //System.out.println(cart);
            }
            else System.out.println("insufficient funds or product out of stock");
        }
        else {
            System.out.println("Product not available");
        }

    }


    /**
     * @param sales (in order to access our totalOrders array list)
     * this method sends all the orders into the totalOrders array list
     */
    public void placeOrder(StoreSales sales) {
        //cart.sort(QUANTITY_ORDER);
        for(Product eachOrder : cart) {
            sales.getAllOrders().add(eachOrder);
        }
    }



}

