package Main.controller;

import Main.entity.Staff;

public class SuspendUserController{
    Staff user = new Staff();
    public boolean suspendUser(String selectedUsername, String newActiveStatus){
        return user.suspendUserViaUsername(selectedUsername, newActiveStatus);
    }

}
