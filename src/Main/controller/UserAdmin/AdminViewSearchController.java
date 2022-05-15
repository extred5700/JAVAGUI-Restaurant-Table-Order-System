package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Admin;
import java.util.ArrayList;

public class AdminViewSearchController {
    User_Account user_account = new User_Account();
    User_Admin userAdmin = new User_Admin();

    public String[][] getUserAccounts(){
        return user_account.viewAccount();
    }

    public String [][] searchByUsername(String dataKeyedIn){
        return user_account.searchAccount(dataKeyedIn);
    }

    public String [][] searchBy(String dataKeyedIn, String searchStatus){
        return userAdmin.searchAccount(dataKeyedIn, searchStatus);
    }
}
