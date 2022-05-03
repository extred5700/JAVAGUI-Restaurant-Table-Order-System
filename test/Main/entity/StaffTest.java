package Main.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StaffTest {

    // Create Staff object to access functions
    Staff staffTest = new Staff();

    @Test
    @Order(1)
    @DisplayName("Staff_1")
    // Test checkUserExistence() with valid User Admin credentials
    void testValidUserAdmin() {
        assertTrue(staffTest.login("user_admin1", "user_admin1", "User Admin"));
    }

    @Test
    @Order(2)
    @DisplayName("Staff_2")
    // Test checkUserExistence() with invalid User Admin credentials
    void testInvalidUserAdmin() {
        assertFalse(staffTest.login("user_admin0", "user_admin0", "User Admin"));
    }

    @Test
    @Order(3)
    @DisplayName("Staff_3")
    // Test checkUserExistence() with valid User Admin credentials but wrong profile
    void testValidUAInvalidProfile() {
        assertFalse(staffTest.login("user_admin1", "user_admin1", "Restaurant Manager"));
    }

    @Test
    @Order(4)
    @DisplayName("Staff_4")
        // Test checkUserExistence() with valid Restaurant Manager credentials
    void testValidRestMngr() {
        assertTrue(staffTest.login("manager1", "manager1", "Restaurant Manager"));
    }

    @Test
    @Order(5)
    @DisplayName("Staff_5")
        // Test checkUserExistence() with invalid Restaurant Manager credentials
    void testInvalidRestMngr() {
        assertFalse(staffTest.login("manager0", "manager0", "Restaurant Manager"));
    }

    @Test
    @Order(6)
    @DisplayName("Staff_6")
        // Test checkUserExistence() with valid Restaurant Manager credentials but wrong profile
    void testValidRMInvalidProfile() {
        assertFalse(staffTest.login("manager1", "manager1", "User Admin"));
    }

    @Test
    @Order(7)
    @DisplayName("Staff_7")
        // Test checkUserExistence() with valid Restaurant Staff credentials
    void testValidRestStaff() {
        assertTrue(staffTest.login("user1", "user1", "Restaurant Staff"));
    }

    @Test
    @Order(8)
    @DisplayName("Staff_8")
        // Test checkUserExistence() with invalid Restaurant Staff credentials
    void testInvalidRestStaff() {
        assertFalse(staffTest.login("user0", "user0", "Restaurant Staff"));
    }

    @Test
    @Order(9)
    @DisplayName("Staff_9")
        // Test checkUserExistence() with valid Restaurant Staff credentials but wrong profile
    void testValidRSInvalidProfile() {
        assertFalse(staffTest.login("user1", "user1", "User Admin"));
    }

    @Test
    @Order(10)
    @DisplayName("Staff_10")
        // Test checkUserExistence() with valid Restaurant Owner credentials
    void testValidRestOwner() {
        assertTrue(staffTest.login("owner1", "owner1", "Restaurant Owner"));
    }

    @Test
    @Order(11)
    @DisplayName("Staff_11")
        // Test checkUserExistence() with invalid Restaurant Owner credentials
    void testInvalidRestOwner() {
        assertFalse(staffTest.login("owner0", "owner0", "Restaurant Owner"));
    }

    @Test
    @Order(12)
    @DisplayName("Staff_12")
        // Test checkUserExistence() with valid Restaurant Owner credentials but wrong profile
    void testValidROInvalidProfile() {
        assertFalse(staffTest.login("owner1", "owner1", "User Admin"));
    }

    @Test
    @Order(13)
    @DisplayName("Staff_13")
        // Test that dbConnection() creates a connection successfully
    void dbConnectionTest() {
        assertNotNull(staffTest.dbConnection());
    }
}