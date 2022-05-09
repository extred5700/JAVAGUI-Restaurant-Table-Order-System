package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StaffTest {

    // Create Staff object to access functions
    Staff staffTest = new Staff();

    @Test
    @Order(1)
    @DisplayName("Staff_1")
    // Test checkUserExistence() with valid User Admin credentials
    void validUserAdminTest() {
        assertTrue(staffTest.login("user_admin1", "user_admin1", "User Admin"));
    }

    @Test
    @Order(2)
    @DisplayName("Staff_2")
    // Test checkUserExistence() with invalid User Admin credentials
    void invalidUserAdminTest() {
        assertFalse(staffTest.login("user_admin0", "user_admin0", "User Admin"));
    }

    @Test
    @Order(3)
    @DisplayName("Staff_3")
    // Test checkUserExistence() with valid User Admin credentials but wrong profile
    void validUAInvalidProfileTest() {
        assertFalse(staffTest.login("user_admin1", "user_admin1", "Restaurant Manager"));
    }

    @Test
    @Order(4)
    @DisplayName("Staff_4")
        // Test checkUserExistence() with valid Restaurant Manager credentials
    void validRestMngrTest() {
        assertTrue(staffTest.login("manager1", "manager1", "Restaurant Manager"));
    }

    @Test
    @Order(5)
    @DisplayName("Staff_5")
        // Test checkUserExistence() with invalid Restaurant Manager credentials
    void invalidRestMngrTest() {
        assertFalse(staffTest.login("manager0", "manager0", "Restaurant Manager"));
    }

    @Test
    @Order(6)
    @DisplayName("Staff_6")
        // Test checkUserExistence() with valid Restaurant Manager credentials but wrong profile
    void validRMInvalidProfileTest() {
        assertFalse(staffTest.login("manager1", "manager1", "User Admin"));
    }

    @Test
    @Order(7)
    @DisplayName("Staff_7")
        // Test checkUserExistence() with valid Restaurant Staff credentials
    void validRestStaffTest() {
        assertTrue(staffTest.login("user1", "user1", "Restaurant Staff"));
    }

    @Test
    @Order(8)
    @DisplayName("Staff_8")
        // Test checkUserExistence() with invalid Restaurant Staff credentials
    void invalidRestStaffTest() {
        assertFalse(staffTest.login("user0", "user0", "Restaurant Staff"));
    }

    @Test
    @Order(9)
    @DisplayName("Staff_9")
        // Test checkUserExistence() with valid Restaurant Staff credentials but wrong profile
    void validRSInvalidProfileTest() {
        assertFalse(staffTest.login("user1", "user1", "User Admin"));
    }

    @Test
    @Order(10)
    @DisplayName("Staff_10")
        // Test checkUserExistence() with valid Restaurant Owner credentials
    void validRestOwnerTest() {
        assertTrue(staffTest.login("owner1", "owner1", "Restaurant Owner"));
    }

    @Test
    @Order(11)
    @DisplayName("Staff_11")
        // Test checkUserExistence() with invalid Restaurant Owner credentials
    void invalidRestOwnerTest() {
        assertFalse(staffTest.login("owner0", "owner0", "Restaurant Owner"));
    }

    @Test
    @Order(12)
    @DisplayName("Staff_12")
        // Test checkUserExistence() with valid Restaurant Owner credentials but wrong profile
    void validROInvalidProfileTest() {
        assertFalse(staffTest.login("owner1", "owner1", "User Admin"));
    }
}