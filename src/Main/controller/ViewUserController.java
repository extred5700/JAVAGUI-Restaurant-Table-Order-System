package Main.controller;

import Main.entity.Staff;

import java.util.ArrayList;

public class ViewUserController {
    Staff user = new Staff();

    public ArrayList<ArrayList<String>> getUserInfo(){
        return user.getUserInfoFromDB();
    }
}
