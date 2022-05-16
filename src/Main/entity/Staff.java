package Main.entity;

import java.sql.*;
import java.util.ArrayList;

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

    // Set up DB Connection
    protected Connection dbConnection(){
        String dbUsername = "root";
        String dbPassword = "hopium314_";
        Connection dbConnection = null;
        try{
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csit314", dbUsername, dbPassword);
        } catch (SQLException e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return dbConnection;
    } // end of method dbConnection()

    // Validate user login profile
    public boolean login(String username, String password, String profile){
        boolean userExistence = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            String query = "SELECT * FROM user_account INNER JOIN user_profile ON user_account.profile_id = user_profile.profile_id WHERE username = '" + username + "' AND password = '" + password + "' AND profile_name = '" + profile + "' AND account_active = 'Y' AND profile_active = 'Y'";
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()){
                //This means no account found
                System.out.println("No account found.");
            }
            else{
                //This means account found
                System.out.println("Account is valid.");
                userExistence = true;
            }
        }catch (SQLException e){
            // Catches any SQL query issues
            System.out.println(e);
        }
        return userExistence;
    } // end of method login()

}
