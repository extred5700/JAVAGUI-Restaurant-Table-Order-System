package Main.controller.UserAdmin;

import Main.entity.Staff;
import Main.entity.User_Admin;

public class SuspendUserController{
    User_Admin userAdmin = new User_Admin();

    public String[][] getUserInfo(){
        return userAdmin.getUserInfoFromDB();
    }
    public boolean suspendUser(String selectedUsername, String newActiveStatus){
        return userAdmin.suspendAccount(selectedUsername, newActiveStatus);
    }

}
