package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Restaurant_ManagerTest {
    Restaurant_Manager restaurant_manager = new Restaurant_Manager();

    @Test
    @Order(1)
    @DisplayName("Restaurant_Manager_1")
    void checkMenuItemExistenceTest() {
        assertTrue(restaurant_manager.checkMenuItemExistence("marinara"));
    }

    @Test
    @Order(2)
    @DisplayName("Restaurant_Manager_2")
    void createMenuItemsTest() {
        assertTrue(restaurant_manager.createMenuItems("testFood", 10.0f, "pasta"));
    }

    @Test
    @Order(3)
    @DisplayName("Restaurant_Manager_3")
    void checkCouponExistenceTest() {
        assertTrue(restaurant_manager.checkCouponExistence("10OFF"));
    }

    @Test
    @Order(4)
    @DisplayName("Restaurant_Manager_4")
    void createCouponTest() {
        assertTrue(restaurant_manager.createCoupon("testOFF", 0.95f));
    }

    @Test
    @Order(5)
    @DisplayName("Restaurant_Manager_5")
    void editMenuItemsTest() {
    }

    @Test
    @Order(6)
    @DisplayName("Restaurant_Manager_6")
    void searchMenuItemsTest() {
    }

    @Test
    @Order(7)
    @DisplayName("Restaurant_Manager_7")
    void viewMenuItemsTest() {
    }

    @Test
    @Order(8)
    @DisplayName("Restaurant_Manager_8")
    void deleteMenuItemsTest() {
    }
}