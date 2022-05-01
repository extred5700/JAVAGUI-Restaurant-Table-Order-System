package Main;

import Main.boundary.*;
import Main.boundary.StaffUI.ManagerPageUI;
import Main.boundary.StaffUI.OwnerPageUI;

public class HopiumRestaurant {
    public static void main(String[] args) {
        //new StartingPage();

        /* Staff */
        //new StaffLoginPage();
        //new UserAdminPageUI("ADMIN TEST");
        new OwnerPageUI("Owner Test");
        //new ManagerPageUI("Manager Test");

        /* Customer */
        //new CustomerLoginPage();
        //new CustomerPageUI(42);

    }
}
