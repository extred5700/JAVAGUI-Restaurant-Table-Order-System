package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerTest {

    //Create customer object with table number of 100
    Customer customerTest = new Customer(100);

    @Test
    @Order(1)
    @DisplayName("Customer_1")
    void addToCartTest() {
        customerTest.addToCart(1, 1);
    }

    @Test
    @Order(2)
    @DisplayName("Customer_2")
    void editCartTest() {
        customerTest.editCart(21, 2);
    }

    @Test
    @Order(3)
    @DisplayName("Customer_3")
    @Disabled
    void searchMenuItemsTest() {
    }

    @Test
    @Order(4)
    @DisplayName("Customer_4")
    void viewCartTest() {
        assertNotNull(customerTest.viewCart());
    }

    @Test
    @Order(5)
    @DisplayName("Customer_5")
    void deleteCartItemTest() {
        customerTest.deleteCartItem(21);
    }

    @Test
    @Order(6)
    @DisplayName("Customer_6")
    @Disabled
    void ePaymentTest() {
    }
}