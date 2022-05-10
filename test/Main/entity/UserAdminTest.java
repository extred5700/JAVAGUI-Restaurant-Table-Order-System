package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserAdminTest {
    User_Admin userAdminTest = new User_Admin();

    @Test
    @Order(1)
    @DisplayName("User_Admin_1")
    void createAccountTest() {
        // Test Create Account Function
        assertTrue(userAdminTest.createAccount("testUser", "testUser", "Restaurant Staff"));
    }

    @Test
    @Order(2)
    @DisplayName("User_Admin_2")
    void editAccountTest() {
        // Test Edit Account Function on the testUser
        assertTrue(userAdminTest.editAccount("testUser", "testUser2", "testUser2", "Restaurant Staff"));
    }

    @Test
    @Order(3)
    @DisplayName("User_Admin_3")
    void getUserInfoFromDBTest() {
        // Test if data can be queried from database
        assertNotNull(userAdminTest.getUserInfoFromDB());
    }

    @Test
    @Order(4)
    @DisplayName("User_Admin_4")
    void searchAccountTest() {
        // Test if user can be searched from database
        assertNotNull(userAdminTest.searchAccount("testUser2", "search_by_user"));
    }

    @Test
    @Order(5)
    @DisplayName("User_Admin_5")
    void suspendAccountTest() {
        // Test if user account can be suspended
        assertTrue(userAdminTest.suspendAccount("testUser2", "N"));
    }
}