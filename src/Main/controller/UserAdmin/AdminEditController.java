package Main.controller.UserAdmin;

import Main.entity.User_Admin;

public class AdminEditController {
    User_Admin userAdmin = new User_Admin();

    public boolean editUserAccount(String oldUsername, String newUsername, String newPassword, String newProfile){
        return userAdmin.editAccount(oldUsername, newUsername, newPassword, newProfile);
    }
}
