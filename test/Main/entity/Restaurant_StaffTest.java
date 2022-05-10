package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Restaurant_StaffTest {

    Restaurant_Staff restaurant_staffTest = new Restaurant_Staff();

    @Test
    @Order(1)
    @DisplayName("Restaurant_Staff_1")
    void editOrderStatus() {
        assertTrue(restaurant_staffTest.editOrderStatus(1));
    }

    @Test
    @Order(2)
    @DisplayName("Restaurant_Staff_2")
    @Disabled
    void editOrder() {
    }

    @Test
    @Order(3)
    @DisplayName("Restaurant_Staff_3")
    void searchOrder() {
        assertNotNull(restaurant_staffTest.searchOrder(1));
    }

    @Test
    @Order(4)
    @DisplayName("Restaurant_Staff_4")
    void viewAllOrders() {
        assertNotNull(restaurant_staffTest.viewAllOrders());
    }

    @Test
    @Order(5)
    @DisplayName("Restaurant_Staff_5")
    @Disabled
    void deleteOrder() {
    }
}