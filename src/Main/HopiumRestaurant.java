package Main;

import Main.boundary.*;
import Main.boundary.CustomerPageUI;
import Main.boundary.StaffLoginPage;
import Main.boundary.StaffUI.*;

public class HopiumRestaurant {
    public static void main(String[] args) {
        //new StartingPage();

        /* Staff */
        //new StaffLoginPage();
        // Admin
        new UserAdminPageUI("Admin Test","View");
        // Owner
        //new OwnerPageUI("Owner Test");
        // Manager
        //new ManagerPageUI("Manager Test");

        /* Customer */
        //new CustomerLoginPage();
        //new CustomerPageUI(42);

    }
}
