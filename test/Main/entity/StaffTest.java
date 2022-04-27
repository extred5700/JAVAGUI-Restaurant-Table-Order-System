package Main.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    // Create Staff object to access functions
    Staff staff = new Staff();

    @Test
    @DisplayName("Test checkUserExistence True")
    void checkUserExistenceTrue() {
        assertTrue(staff.checkUserExistence("User1", "User1", "Restaurant Staff"));
    }

    @Test
    @DisplayName("Test checkUserExistence False")
    void checkUserExistenceFalse() {
        assertFalse(staff.checkUserExistence("User0", "User0", "Staff"));
    }
}