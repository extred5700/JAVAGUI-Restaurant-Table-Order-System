package Main.controller.UserAdmin;

import Main.entity.User_Admin;

public class SuspendUserController{
    User_Admin userAdmin = new User_Admin();

    public String [][] getUserInfo(){
        return userAdmin.getUserInfoFromDB();
    }

    public boolean suspendUserAccount(String selectedUsername, String newActiveStatus){
        return userAdmin.suspendAccount(selectedUsername, newActiveStatus);
    }

    public boolean suspendUserProfile(String selectedUsername, String newActiveStatus){
        return userAdmin.suspendProfile(selectedUsername, newActiveStatus);
    }
}
