package Main.controller.Staff;

import Main.entity.Staff;

public class LoginController {
    private static Staff staff = new Staff();
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
