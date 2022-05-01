package Main.controller;

import Main.entity.Staff;

public class EditUserController {
    Staff user = new Staff();

    public boolean editUserAccount(String oldUsername, String newUsername, String newPassword, String newProfile){
        return user.editUserAccountUsingOldUsername(oldUsername, newUsername, newPassword, newProfile);
    }
}
