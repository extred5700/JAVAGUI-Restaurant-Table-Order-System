package Main.controller.Staff;

import Main.entity.Staff;
import Main.entity.User_Profile;

public class LoginController {
    private static Staff staff = new Staff();
    User_Profile user_profile = new User_Profile();

    // Controller will check if Staff profile is in the system/DB
    public boolean validateLogin(String username, String password, String user_profile){
        return staff.login(username, password, user_profile);
    }

    // Get all available profiles for User Admin to select from the dropdown box
    public String [] getArrayOfProfiles(){
        return user_profile.existingProfile();
    }

    public static Staff getStaff() {	//	to get Staff
        return staff;
    }

    public static void setStaff(Staff staf) {	// to set new staff when logged out #logoutcontroller
        staff = staf;
    }
}
