package Main.boundary.StaffUI;

import Main.boundary.CustomerUI.CustomerPageUI;
import Main.controller.UserAdmin.AdminCreateController;
import Main.controller.UserAdmin.AdminEditController;
import Main.controller.UserAdmin.AdminSuspendController;
import Main.controller.UserAdmin.AdminViewSearchController;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TDDTest {

    //User Story #3 Admin Create Account
    @Test
    @Order(1)
    @DisplayName("Admin Create Account and Assign Profile")
    void testAdminCreateAccount()
    {
        AdminCreateController testController = new AdminCreateController();
        assertTrue(testController.validateCreateAccount("testUser1", "testUser1", "User Admin"));
    }

    //User Story #4 Admin Edit Account
    @Test
    @Order(2)
    @DisplayName("Admin Edit Account and Profile")
    void testAdminEditAccount()
    {
        AdminEditController testController = new AdminEditController();
        assertTrue(testController.editUserAccount("testUser1", "testUser2", "testUser2", "User Admin"));
    }

    //User Story #5 Admin Search Account
    @Test
    @Order(3)
    @DisplayName("Admin Search Accounts")
    void testAdminSearchAccount()
    {
        AdminViewSearchController testController = new AdminViewSearchController();
        String[][] result = testController.searchByUsername("manager");
        assertNotNull(result);
    }

    //User Story #6 Admin View Account
    @Test
    @Order(4)
    @DisplayName("Admin View Accounts")
    void testAdminViewAccount()
    {
        AdminViewSearchController testController = new AdminViewSearchController();
        String[][] result = testController.getUserAccounts();
        assertNotNull(result);
    }

    //User Story #7 Admin Suspend Account
    @Test
    @Order(5)
    @DisplayName("Admin Suspend Account")
    void testAdminSuspendAccount()
    {
        AdminSuspendController testController = new AdminSuspendController();
        assertTrue(testController.suspendUserAccount("testUser2", "N"));
    }

    //User Story #8 Admin Assign User Profile
    @Test
    @Order(6)
    @DisplayName("Admin Create Profile")
    void testAdminCreateProfile()
    {
        AdminCreateController testController = new AdminCreateController();
        assertTrue(testController.validateCreateProfile("testProfile"));
    }

    //User Story #9 Admin Edit User Profile
    @Test
    @Order(7)
    @DisplayName("Admin Edit Profile")
    void testAdminEditProfile()
    {
        AdminEditController testController = new AdminEditController();
        assertTrue(testController.editUserProfile(1, "new test profile"));
    }

    //User Story #10 Admin Search User Profiles
    @Test
    @Order(8)
    @DisplayName("Admin Search User Profile")
    void testAdminSearchProfile()
    {
        AdminViewSearchController testController = new AdminViewSearchController();
        String[][] result = testController.searchByProfile("manager");
        assertNotNull(result);
    }

    //User Story #11 Admin View User Profiles
    @Test
    @Order(9)
    @DisplayName("Admin View User Profiles")
    void testAdminViewProfile()
    {
        AdminViewSearchController testController = new AdminViewSearchController();
        String[][] result = testController.getUserProfiles();
        assertNotNull(result);
    }

    //User Story #12 Admin Suspend User Profiles
    @Test
    @Order(10)
    @DisplayName("Admin Suspend User Profiles")
    void testAdminSuspendProfile()
    {
        AdminSuspendController testController = new AdminSuspendController();
        assertTrue(testController.suspendUserProfile(4, "N"));
    }

    //User Story #27 Customer Insert Table Number
    @Test
    @Order(11)
    @DisplayName("Customer Insert Table Number")
    void testCustomerInsertTableNumber()
    {
        new CustomerPageUI(1);
    }
}