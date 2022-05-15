package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Admin;
import Main.entity.User_Profile;

public class AdminSuspendController {
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();

    // Returns all DB User Accounts
    public String[][] getUserAccounts(){
        return user_account.viewAccount();
    }

    // Pass the new Active status of the selected Username to the entity
    public boolean suspendUserAccount(String selectedUsername, String newActiveStatus){
        return user_account.suspendAccount(selectedUsername, newActiveStatus);
    }

    // Returns all DB User Profiles
    public String [][] getUserProfiles(){
        return user_profile.viewProfile();
    }

    // Pass the new Active status of the selected Username to the entity
    public boolean suspendUserProfile(int selectedUserProfileID, String newActiveStatus){
        return user_profile.suspendProfile(selectedUserProfileID, newActiveStatus);
    }
}
