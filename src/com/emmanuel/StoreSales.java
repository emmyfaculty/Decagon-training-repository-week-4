package com.emmanuel;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class StoreSales extends StoreStock {
    private StoreStock myStoreStock;
    private Product customerCart;
    private CopyOnWriteArrayList<Product> allOrders;
    private LinkedList<Product> orderList;

    public StoreSales() {
    }

    static final Comparator<Product> QUANTITY_WISE = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            int result = 0;
            if (product1.getQuantity() > product2.getQuantity()) {
                result = -1;
            } else if (product1.getQuantity() < product2.getQuantity()) {
                result = 1;
            } else {
                result = 0;
            }
            return result;
        }
    };



    public StoreSales(StoreStock myStoreStock) {
        this.myStoreStock = myStoreStock;
        this.customerCart = new Product();
        this.allOrders = new CopyOnWriteArrayList<>();
        this.orderList = new LinkedList<>();

    }


    /** while totalOrders (arrayList containing every customer order)
     * this method creates a temporary arrayList
     * and add the first element of totalOrders[o]
     * then loops thru totalOrders to get products with same name as totalOrders[0]
     * and add them into temp where they are sorted by QUANTITY_WISE
     * then pushed into the final queue orderList
     */
    public void loadQueue () {
        while (!allOrders.isEmpty()) {
            CopyOnWriteArrayList<Product> temp = new CopyOnWriteArrayList<>();
            temp.add(allOrders.get(0));
            allOrders.remove(0);
            for (Product x : allOrders) {
                if (x.getName().equalsIgnoreCase(temp.get(0).getName())) {
                    temp.add(x);
                    allOrders.remove(x);
                }
            }
            temp.sort(QUANTITY_WISE);
            orderList.addAll(temp);
            //temp.clear();
        }
        orderList.forEach(System.out::println);
    }

    /** this method pops the top element(Product) in the LinkedList orderList */
    /** then gets the name matching product with productIndex  */
    /** then updates the quantity of the product
     * returns Product object
     * */
    public void  sell () {
        if (!orderList.isEmpty()) {
            Product myOrder = orderList.remove();
            int productIndex = myStoreStock.getMatchIndex(myOrder.getName());
            myStoreStock.inventories.get(productIndex).setQuantity(myStoreStock.inventories.get(productIndex).getQuantity() - myOrder.getQuantity());
            customerCart = myOrder;
        } else {
            System.out.println("Order list is empty");
        }
    }


    public String printReceipt () {
        String output = "";
        if (customerCart.getPrice() == null || customerCart.getName() == null || customerCart.getQuantity() == 0) {
            output ="No purchase!";
        }
        else {
            output =
                    "\t" + "\t" + "    Emaco store" + "\t" + "\t" + "\n" +
                            "\t" + "    Plot 24,Jericho hills" + "\t" + "\n" +
                            "\t" + "       Clements Island" + "\t" + "\n" +
                            "* * * thanks for shopping with us * * *" + "\n" + "\n" +
                            "item" + "\t" + "\t" +  "\t" +  "price" + "\t" + "  quantity" + "\n" +
                            customerCart.getName() + "\t" + "\t" + "    " + String.format("%.2f", customerCart.getPrice()) + "\t" + "  " +customerCart.getQuantity() + "\t" + "\n" + "\n" +
                            "\t" + "   SUBTOTAL" + "\t" + "\t" + "$" + customerCart.getPrice() * customerCart.getQuantity() + "\t" + "\n" +
                            "* * * * * * * * * * * * * * * * * * * *";
        }
        return output;
    }


    public CopyOnWriteArrayList<Product> getAllOrders() {
        return allOrders;
    }
    public LinkedList<Product> getOrderList() {
        return orderList;
    }

}

