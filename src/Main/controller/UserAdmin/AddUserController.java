package Main.controller.UserAdmin;

import Main.entity.Staff;
import Main.entity.User_Admin;

public class AddUserController {
    Staff staff = new Staff();
    User_Admin userAdmin = new User_Admin();

    public boolean validateCreate(String username, String password, String profile){
        // If user account already in use
        if (staff.checkUserExistence(username, password, profile)){
            return false; // return false as user exist, does not validate creation of account
        }
        // If user does not exist
        else{
            return userAdmin.createAccount(username, password, profile);
        }
    }
}
