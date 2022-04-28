package Main.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    // Create Staff object to access functions
    Staff staff = new Staff();

    @Test
    @DisplayName("Test Case 1.4")
    // Test checkUserExistence() with valid User Admin credentials
    void testValidUserAdmin() {
        assertTrue(staff.checkUserExistence("user_admin1", "user_admin1", "User Admin"));
    }

    @Test
    @DisplayName("Test Case 1.5")
    // Test checkUserExistence() with invalid User Admin credentials
    void testInvalidUserAdmin() {
        assertFalse(staff.checkUserExistence("user_admin0", "user_admin0", "User Admin"));
    }

    @Test
    @DisplayName("Test Case 1.6")
    // Test checkUserExistence() with valid User Admin credentials but wrong profile
    void testValidUAInvalidProfile() {
        assertFalse(staff.checkUserExistence("user_admin1", "user_admin1", "Restaurant Manager"));
    }

    @Test
    @DisplayName("Test Case 13.4")
        // Test checkUserExistence() with valid Restaurant Manager credentials
    void testValidRestMngr() {
        assertTrue(staff.checkUserExistence("manager1", "manager1", "Restaurant Manager"));
    }

    @Test
    @DisplayName("Test Case 13.5")
        // Test checkUserExistence() with invalid Restaurant Manager credentials
    void testInvalidRestMngr() {
        assertFalse(staff.checkUserExistence("manager0", "manager0", "Restaurant Manager"));
    }

    @Test
    @DisplayName("Test Case 13.6")
        // Test checkUserExistence() with valid Restaurant Manager credentials but wrong profile
    void testValidRMInvalidProfile() {
        assertFalse(staff.checkUserExistence("manager1", "manager1", "User Admin"));
    }

    @Test
    @DisplayName("Test Case 20.4")
        // Test checkUserExistence() with valid Restaurant Staff credentials
    void testValidRestStaff() {
        assertTrue(staff.checkUserExistence("user1", "user1", "Restaurant Staff"));
    }

    @Test
    @DisplayName("Test Case 20.5")
        // Test checkUserExistence() with invalid Restaurant Staff credentials
    void testInvalidRestStaff() {
        assertFalse(staff.checkUserExistence("user0", "user0", "Restaurant Staff"));
    }

    @Test
    @DisplayName("Test Case 20.6")
        // Test checkUserExistence() with valid Restaurant Staff credentials but wrong profile
    void testValidRSInvalidProfile() {
        assertFalse(staff.checkUserExistence("user1", "user1", "User Admin"));
    }

    @Test
    @DisplayName("Test Case 35.4")
        // Test checkUserExistence() with valid Restaurant Owner credentials
    void testValidRestOwner() {
        assertTrue(staff.checkUserExistence("owner1", "owner1", "Restaurant Owner"));
    }

    @Test
    @DisplayName("Test Case 35.5")
        // Test checkUserExistence() with invalid Restaurant Owner credentials
    void testInvalidRestOwner() {
        assertFalse(staff.checkUserExistence("owner0", "owner0", "Restaurant Owner"));
    }

    @Test
    @DisplayName("Test Case 35.6")
        // Test checkUserExistence() with valid Restaurant Owner credentials but wrong profile
    void testValidROInvalidProfile() {
        assertFalse(staff.checkUserExistence("owner1", "owner1", "User Admin"));
    }

}