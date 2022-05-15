package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Admin;
import Main.entity.User_Profile;

import java.util.ArrayList;

public class AdminViewSearchController {
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();
    User_Admin userAdmin = new User_Admin();

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

    // Ignore will delete later
    public String [][] searchBy(String dataKeyedIn, String searchStatus){
        return userAdmin.searchAccount(dataKeyedIn, searchStatus);
    }
}
