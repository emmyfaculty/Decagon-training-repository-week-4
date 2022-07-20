package com.emmanuel;


import java.util.LinkedList;
import java.util.ListIterator;

public class User {
    private String name;
    private Roles role;
    private Wallet wallet;


    // to create manager, cashier
    public User(String name, Roles role) {
        this.name = name;
        this.role = role;
        this.wallet = new Wallet();
    }



    // methods
    public String getName() {
        return name;
    }


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
        //return role;
    }


    public double getBalance () {
        return wallet.getAmount();
    }

    public void loadWallet (Double amount) {
        wallet.setAmount(amount);
    }





}

