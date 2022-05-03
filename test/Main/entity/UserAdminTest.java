package Main.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class UserAdminTest {

    // Make sure to remove the test entries after running the test
    // Test are run in order to use the test account
    User_Admin userAdminTest = new User_Admin();

    @Test
    @DisplayName("User_Admin_1")
    void createAccountTest() {
        // Test Create Account Function
        assertTrue(userAdminTest.createAccount("testUser", "testUser", "Restaurant Staff"));
    }

    @Test
    @DisplayName("User_Admin_2")
    void editAccountTest() {
        // Test Edit Account Function on the testUser
        assertTrue(userAdminTest.editAccount("testUser", "testUser2", "testUser2", "Restaurant Staff"));
    }

    @Test
    @DisplayName("User_Admin_3")
    void getUserInfoFromDBTest() {
        // Test if data can be queried from database
        assertNotNull(userAdminTest.getUserInfoFromDB());
    }

    @Test
    @DisplayName("User_Admin_4")
    void searchAccountTest() {
        // Test if user can be searched from database
        assertNotNull(userAdminTest.searchAccount("testUser2", "search_by_user"));
    }

    @Test
    @DisplayName("User_Admin_5")
    void suspendAccountTest() {
        // Test if user account can be suspended
        assertTrue(userAdminTest.suspendAccount("testUser2", "N"));
    }
}