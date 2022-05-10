package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartTest {
    //Static cart as it is given a unique transaction id
    static Cart cartTest;

    @Test
    @Order(2)
    @DisplayName("Cart_1")
    void getTransaction_idTest() {
        assertNotEquals(0, cartTest.getTransaction_id());
    }

    @Test
    @Order(1)
    @DisplayName("Cart_2")
    //This function is run first as it creates the cart object and gives a transaction id
    void setTransaction_idTest() {
        cartTest = new Cart(101);
    }

    @Test
    @Order(3)
    @DisplayName("Cart_3")
    void viewCartTest() {
        assertNotNull(cartTest.viewCart());
    }

    @Test
    @Order(4)
    @DisplayName("Cart_4")
    void addToCartTest() {
        assertTrue(cartTest.addToCart(1, 1));
    }

    @Test
    @Order(5)
    @DisplayName("Cart_5")
    void editCartTest() {
        assertTrue(cartTest.editCart(1, 2));
    }

    @Test
    @Order(6)
    @DisplayName("Cart_6")
    void deleteFromCartTest() {
        assertTrue(cartTest.deleteFromCart(1));
    }

    @Test
    @Order(7)
    @DisplayName("Cart_7")
    void getTotalPriceTest() {
        assertNotNull(cartTest.getTotalPrice());
    }

    @Test
    @Order(8)
    @DisplayName("Cart_8")
    void applyDiscountTest() {
        assertTrue(cartTest.applyDiscount("10OFF"));
    }

    @Test
    @Order(9)
    @DisplayName("Cart_9")
    void makePaymentTest() {
        assertTrue(cartTest.makePayment("88888888"));
    }
}