package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Profile;

public class AdminViewSearchController {
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();

    // Returns all DB User Accounts
    public String[][] getUserAccounts(){
        return user_account.viewAccount();
    }

    // Returns search result of 2D arrays based on the searched username
    public String [][] searchByUsername(String dataKeyedIn){
        return user_account.searchAccount(dataKeyedIn);
    }

    // Returns all DB User Profiles
    public String [][] getUserProfiles(){
        return user_profile.viewProfile();
    }

    // Returns search result of 2D arrays based on the searched profile
    public String [][] searchByProfile(String dataKeyedIn){
        return user_profile.searchProfile(dataKeyedIn);
    }
}
