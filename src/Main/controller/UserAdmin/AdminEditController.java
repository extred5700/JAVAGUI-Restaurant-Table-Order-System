package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Profile;

public class AdminEditController {
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();

    // To display all User Accounts in a form of a Table
    public String [][] getArrayOfAccounts(){
        return user_account.viewAccount();
    }

    // To display for the dropdown box in User Account Editing Panel
    public String [] getArrayOfProfiles(){
        return user_profile.existingProfile();
    }

    // To display all User Profiles in a form of a Table
    // Can rename
    public String [][] getProfileTable(){
        return user_profile.viewProfile();
    }

    // EDIT User Account
    public boolean editUserAccount(String oldUsername, String newUsername, String newPassword, String newProfile){
        return user_account.editAccount(oldUsername, newUsername, newPassword, newProfile);
    }

    // EDIT User Profile
    public boolean editUserProfile(int selectedProfileID, String newProfile){
        return user_profile.editProfile(selectedProfileID, newProfile);
    }


}
