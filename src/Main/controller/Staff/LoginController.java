package Main.controller.Staff;

import Main.entity.Staff;
import Main.entity.User_Profile;

public class LoginController {
    private static Staff staff = new Staff();
    User_Profile user_profile = new User_Profile();



    // Controller will check if Staff profile is in the system/DB
    public boolean validateLogin(String staffUsername, String password, String profile){
        return staff.login(staffUsername, password, profile);
    }

    public static Staff getStaff() {	//	to get Staff
        return staff;
    }

    public static void setStaff(Staff staf) {	// to set new staff when logged out #logoutcontroller
        staff = staf;
    }
}
