package test;

import com.emmanuel.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StoreStockTest {
    protected ArrayList<Product> inventories = new ArrayList<>();

    @BeforeEach
    void setUp() {

        try (BufferedReader productFile = new BufferedReader(new FileReader("/Users/mac/Downloads/week3task/products_data.txt"))) {
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
                    // check previous iv value
                    // check for the new add

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return inventories;
    }

    @Test
    void testInventoryIsNotNull(){

        assertNotNull(inventories);
        assertTrue(inventories.size() > 0);

        assertTrue(inventories.get(0).getName().equalsIgnoreCase("carrot"));
    }


    @Test
    void reStock() {
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

    @Test
    void testSearchMethod() {
        String name = "whole wheat";
        assertTrue(search(name));
    }

    @Test
    void testSearchProductNotFoundMethod() {
        String name = "beans";
        assertFalse(search(name));
    }

    @Test
    void printProducts() {
    }


    int getMatchIndex(String n) {

        for(Product inv : inventories) {
            if(inv.getName().equalsIgnoreCase(n)) {
                return inventories.indexOf(inv);
            }
        }
        return -1;


    }

    @Test
    void testIfGetMatchReturnPositiveValue(){
        String n = "chocolate chip";
        assertTrue(getMatchIndex(n) > 0);

    }

    @Test
    void testIfGetMatchReturnNegativeValue(){
        String n = "";
        assertFalse(getMatchIndex(n) > 0);

    }

    @Test
    void getInventories() {
    }
}