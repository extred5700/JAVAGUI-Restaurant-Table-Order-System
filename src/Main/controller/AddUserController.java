package Main.controller;

import Main.entity.Staff;

public class AddUserController {
    Staff newUser = new Staff();

    public boolean validateCreateUserAccount(String username, String password, String profile){
        if (newUser.checkUserExistence(username, password, profile)){
            return false;
        }
        return newUser.createUserAccount(username, password, profile);
    }
}
