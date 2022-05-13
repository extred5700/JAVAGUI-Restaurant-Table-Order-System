package Main.controller.UserAdmin;

import Main.entity.Staff;
import Main.entity.User_Admin;
import Main.entity.User_Profile;

public class AdminCreateController {
    User_Admin userAdmin = new User_Admin();
    User_Profile user_profile = new User_Profile();

    // Validate Account's existence before creation
    public boolean validateCreateAccount(String username, String password, String profile){
        // If User Account already in use
        if (userAdmin.checkUserExistence(username, password, profile)){
            return false; // return false as user exist, does not validate creation of account
        }
        // If User Account does not exist
        else{
            return userAdmin.createAccount(username, password, profile);
        }
    }

    // Validate Profile's existence before creation
    public boolean validateCreateProfile(String profile){
        // If User Profile already in use
        if (user_profile.checkProfileExistence(profile)){
            return false; // return false as user profile exist, does not validate creation of account
        }
        // If User Profile does not exist
        else{
            return user_profile.createProfile(profile);
        }
    }

}