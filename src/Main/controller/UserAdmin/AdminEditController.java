package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Admin;
import Main.entity.User_Profile;

public class AdminEditController {
    User_Admin userAdmin = new User_Admin();
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();

    public String [][] getArrayOfAccounts(){
        return user_account.viewAccount();
    }

    public String [] getArrayOfProfiles(){
        return user_profile.existingProfile();
    }

    // EDIT User Account
    public boolean editUserAccount(String oldUsername, String newUsername, String newPassword, String newProfile){
        return user_account.editAccount(oldUsername, newUsername, newPassword, newProfile);
    }
}
