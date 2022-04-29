package Main.controller;

import Main.entity.Staff;

public class AddUserController {
    Staff newUser = new Staff();

    public boolean validateCreate(String username, String password, String profile){
        // If user account already in use
        if (newUser.checkUserExistence(username, password, profile)){
            return false; // return false as user exist, does not validate creation of account
        }
        // If user does not exist
        else{
            return newUser.createUserAccount(username, password, profile);
        }
    }
}
