package com.emmanuel;


import java.util.*;

public class CustomerOrder {
    private User customer;
    private StoreStock stock = new StoreStock();
    private ArrayList<Product> cart;

    public CustomerOrder() {
    }
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
     *
     * finally adds a new aproduct object into the cart
     */

    public synchronized String addToCart (User user,String product, int quantity) {

        String message ="";
        if(user.getRole() == Roles.CUSTOMER) {
            int index = stock.getMatchIndex(product);
            if (index >= 0) {
                Product newPro = stock.getInventories().get(index);
                if(quantity <= newPro.getQuantity()) {
                    cart.add(new Product(newPro.getCategory(), product, quantity, newPro.getPrice()));
                    message = "Hello "+ user.getName()+" " +quantity+" quantities of "+ product + " Has been added to your cart";

                }
                else message = "Out of stock";
            }
            else {
                message = "Product not available";
            }
        } else {
            message= " Pls Kindly switch to our customer role to use this feature.";
        }


        return  message;

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

