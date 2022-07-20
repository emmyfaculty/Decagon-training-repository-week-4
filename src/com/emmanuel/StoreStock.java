package com.emmanuel;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StoreStock {
    protected ArrayList<Product> inventories;


    public StoreStock() {
        this.inventories = new ArrayList<>();
    }





    public ArrayList categorize (String category) {
        ArrayList myCategory = new ArrayList<Product>();
        for(Product eachProduct : inventories) {
            if(eachProduct.getCategory().equalsIgnoreCase(category)) {
                myCategory.add(eachProduct);
            }
        }
        return myCategory;
    }




    public ArrayList reStock () {
        try (BufferedReader productFile = new BufferedReader(new FileReader("products_data.txt"))) {
            String input = "";
            while ((input = productFile.readLine()) != null) {
                String[] productData = input.split(",");
                String category = productData[0].toLowerCase();
                String name = productData[1].toLowerCase();
                int quantity = Integer.parseInt(productData[2]);
                double price = Double.parseDouble(productData[3]);
                if (getMatchIndex(name) >= 0) {             //call our predefined getMatchIndex() method
                    Product primary = inventories.get(getMatchIndex(name));
                    primary.setQuantity(primary.getQuantity() + quantity);
                } else {
                    inventories.add(new Product(category, name.toLowerCase(), quantity, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventories;
    }


    public boolean search (String item) {
        HashMap<String, Integer> finder = new HashMap<>(); //create a HashMap to hold productName and product index
        for (Product eachProduct : inventories) {
            finder.put(eachProduct.getName().toLowerCase(), inventories.indexOf(eachProduct));
        }
        if (finder.containsKey(item.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void printProducts () {
        for(Product prod : inventories) {
            System.out.println(prod);
        }
    }



    public int getMatchIndex(String name) {
        for(Product inv : inventories) {
            if(inv.getName().equalsIgnoreCase(name)) {
                return inventories.indexOf(inv);
            }
        }
        return -1;
    }


    public ArrayList<Product> getInventories() {
        return inventories;
    }
}

