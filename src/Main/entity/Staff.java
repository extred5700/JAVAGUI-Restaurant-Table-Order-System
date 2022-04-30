package Main.entity;

import java.sql.*;

import static java.time.chrono.JapaneseEra.values;

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
    private Connection dbConnection(){
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
    public boolean checkUserExistence(String username, String password, String profile){
        boolean userExistence = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM user WHERE username ='"
                            +username+
                            "' AND password = '"
                            +password+
                            "' AND user_profile = '"
                            +profile+
                            "' AND active = 'Y'");
            if (rs.next() == false){
                //This means no account found
                System.out.println("No account found.");
            }
            else{
                //This means account found
                System.out.println("Login successful.");
                userExistence = true;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return userExistence;
    } // end of method checkUserExistence()

    // Create new User Profile
    public boolean createUserAccount(String username, String password, String profile){
        boolean isUserCreated = false;
        String active = "Y";
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try{
            String query = "insert into user (username, password, user_profile, active )" + " values (?, ?, ?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, profile);
            preparedStatement.setString(4, active);
            preparedStatement.execute();
            isUserCreated = true;
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }


        return isUserCreated;
    } // end of method createUserAccount()

}
