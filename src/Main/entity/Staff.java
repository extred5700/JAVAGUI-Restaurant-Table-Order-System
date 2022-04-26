package Main.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staff{
    // Variable Declaration
    private String userID;
    private String password;
    private String staffType;
    private String profile; // Type of staff

    // Default Constructor
    public Staff(){

    }

    // Other Constructor
    public Staff(String userID, String password, String staffType, String profile){
        this.userID = userID;
        this.password = password;
        this.staffType = staffType;
        this.profile = profile;
    }

    // Accessor Methods
    public String getUserID(){
        return userID;
    }
    public String getPassword(){
        return password;
    }
    public String getStaffType(){
        return staffType;
    }
    public String getProfile(){
        return profile;
    }

    // Validate user login profile
    public boolean checkUserExistence(String staffUsername, String password, String profile){
        boolean flag = false;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csit314", "root", "hopium314_");
            Statement statement = connection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM user WHERE username ='"
                            +staffUsername+
                            "' AND password = '"
                            +password+
                            "' AND user_profile = '"
                            +profile+
                            "'");
            if (rs.next() == false){
                //This means no account found
                System.out.println("No account found.");
            }
            else{
                //This means account found
                System.out.println("Login successful.");
                flag = true;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return flag;
    }

    // Create new User Profile
    public boolean createUserAccount(String username, String password, String profile){
        boolean flag = false;
        /*
        * Code
        * */
        return flag;
    }

}
