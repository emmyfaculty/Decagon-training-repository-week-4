package test;

import com.emmanuel.CustomerOrder;
import com.emmanuel.Roles;
import com.emmanuel.StoreStock;
import com.emmanuel.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderTest {
    CustomerOrder customerOrder;
    User user;
    User user2;
    StoreStock stock;
    String message;
    String product ;
    int quantity;
    int quantity2;
    String message2;
    String message3;
    String product2;
    String message4;

    @BeforeEach
    void setUp() {
        user = new User("Ema", Roles.CUSTOMER);
        user2 = new User("Faculty", Roles.CASHIER);
        stock = new StoreStock();
        customerOrder = new CustomerOrder(user,stock);
         product ="Carrot";
         product2 = "beans";
        quantity = 5;
        quantity2 = 10000;
        message= "Hello "+ user.getName()+" " +quantity+" quantities of "+ product + " Has been added to your cart";
        stock.reStock();

        message2 = "Out of stock";
        message3 = "Product not available";
        message4 = " Pls Kindly switch to our customer role to use this feature.";




    }

    @Test
    void TestForAGoodCustomerThatCanAddToCart() {
        assertEquals(message,customerOrder.addToCart(user,product,quantity));

    }

    @Test
    void TestForCustomerProductOutOfStock() {
        assertEquals(message2,customerOrder.addToCart(user,product,quantity2));

    }

    @Test
    void TestForCustomerProductNotAvailable() {
        assertEquals(message3,customerOrder.addToCart(user,product2,quantity2));

    }

    @Test
    void TestForWhenRoleIsNotCustomer() {
        assertEquals(message4,customerOrder.addToCart(user2,product2,quantity2));

    }


}