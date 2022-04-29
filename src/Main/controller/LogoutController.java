package Main.controller;

import Main.entity.Staff;

public class LogoutController {
	
	Staff staff;
	
    // A new staff is created and previous staff is loged out
    public boolean validateLogout(){
    	
    	staff=new Staff();// this will call the default constructor assigning the null values to the username and password
        LoginController.setStaff(staff);	// setting the login for new staff
    	return true;
        
    }
    
}
