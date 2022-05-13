package Main.entity;

import java.sql.*;
import java.util.ArrayList;

public class User_Account extends Staff{
    // Variable Declaration
    Staff staff = new Staff();

    // Default Constructor
    public User_Account() {
    }

    // Other Constructor
    public User_Account(String userID, String password, String staffType, String profile) {
        super(userID, password, staffType, profile);
    }

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
                            "'");
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
    } // end of method checkUserExistence()

    // Function to Create a user account #3
    public boolean createAccount(String newUsername, String newPassword, String newProfile) {
        boolean isUserCreated = false;
        String active = "Y";
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        try{
            String query = "insert into user_account (username, password, user_profile, active)" + " values (?, ?, ?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, newProfile);
            preparedStatement.setString(4, active);
            preparedStatement.execute();
            isUserCreated = true;
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isUserCreated;
    }

    // Function to Edit user accounts #4
    public boolean editAccount(String oldUsername, String newUsername, String newPassword) {
        boolean isUserEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE user_account SET username ='" + newUsername + "',password='" + newPassword + "' WHERE username ='" + oldUsername + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isUserEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isUserEdited;
    }

    // Function to Search for user accounts #5
    public String [][] searchAccount(String dataKeyedIn) {
        ArrayList<String> arrayListSearchedUsernames = new ArrayList<>();
        ArrayList<String> arrayListSearchedPasswords = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfiles = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB

        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT * FROM user_account WHERE username LIKE '%" + dataKeyedIn + "%'");

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedUsernames.add(rs.getString("username"));
                arrayListSearchedPasswords.add(rs.getString("password"));
                arrayListSearchedProfiles.add(rs.getString("user_profile"));
                arrayListSearchedActive.add(rs.getString("active"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllSearchedData = new String[arrayListSearchedUsernames.size()][4];
        for (int row = 0; row < arrayAllSearchedData.length; row++){
            for (int column = 0; column < arrayAllSearchedData[row].length; column++){
                arrayAllSearchedData[row][0] = arrayListSearchedUsernames.get(row);
                arrayAllSearchedData[row][1] = arrayListSearchedPasswords.get(row);
                arrayAllSearchedData[row][2] = arrayListSearchedProfiles.get(row);
                arrayAllSearchedData[row][3] = arrayListSearchedActive.get(row);
            }
        }
        return arrayAllSearchedData;
    }

    // Function to View user accounts #6
    public String [][] viewAccount() {

    }

    // Function to Suspend user accounts #7
    public boolean suspendAccount(String selectedUsername, String newActiveStatus) {
        boolean isUserSuspended = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE user_account SET active ='" + newActiveStatus + "' WHERE username='" + selectedUsername + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isUserSuspended = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isUserSuspended;
    }
}
