package Main.controller.UserAdmin;

import Main.entity.User_Account;
import Main.entity.User_Profile;

public class AdminCreateController {
    User_Account user_account = new User_Account();
    User_Profile user_profile = new User_Profile();

    // Get all available profiles for User Admin to select from the dropdown box
    public String [] getArrayOfProfiles(){
        return user_profile.existingProfile();
    }

    // Validate Account's existence before creation
    public boolean validateCreateAccount(String username, String password, String profile){
        // If User Account already in use
        if (user_account.checkUserExistence(username, password, profile)){
            return false; // return false as user exist, does not validate creation of account
        }
        // If User Account does not exist
        else{
            return user_account.createAccount(username, password, profile);
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