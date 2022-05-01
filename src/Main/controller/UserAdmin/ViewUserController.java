package Main.controller.UserAdmin;

import Main.entity.Staff;
import Main.entity.User_Admin;

import java.util.ArrayList;

public class ViewUserController {
    User_Admin userAdmin = new User_Admin();

    public ArrayList<ArrayList<String>> getUserInfo(){
        return userAdmin.getUserInfoFromDB();
    }
}
