package Main.controller;

import Main.entity.Staff;

public class LoginController {
    Staff staff = new Staff();
    // Controller will check if Staff profile is in the system/DB
    public boolean validateLogin(String staffUsername, String password, String profile){
        return staff.checkUserExistence(staffUsername, password, profile);
    }
}
