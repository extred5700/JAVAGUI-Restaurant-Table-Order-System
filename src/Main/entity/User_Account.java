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
            String query = "SELECT * FROM user_account INNER JOIN user_profile ON user_account.profile_id = user_profile.profile_id WHERE username = '" + username + "' AND password = '" + password + "' AND profile_name = '" + profile +"'";
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
    } // end of method checkUserExistence()

    // Function to Create a user account #3
    public boolean createAccount(String newUsername, String newPassword, String newProfile) {
        boolean isUserCreated = false;
        String active = "Y";
        int profileID = 0;
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        //obtain profile_id first
        try{
            Statement statement = dbConnection.createStatement();
            String query1 = "SELECT profile_id FROM user_profile WHERE profile_name = '" + newProfile + "'";
            ResultSet rs = statement.executeQuery(query1);
            while (rs.next()) { //This is the result set
                profileID = rs.getInt("profile_id");
            }
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        //actually insert into table
        try{
            String query2 = "insert into user_account (username, password, profile_id, account_active)" + " values (?, ?, ?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query2);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setInt(3, profileID);
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
            String query = "SELECT username,password,profile_name,account_active FROM user_account INNER JOIN user_profile ON user_account.profile_id = user_profile.profile_id WHERE username LIKE '%" + dataKeyedIn + "%'";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedUsernames.add(rs.getString("username"));
                arrayListSearchedPasswords.add(rs.getString("password"));
                arrayListSearchedProfiles.add(rs.getString("profile_name"));
                arrayListSearchedActive.add(rs.getString("account_active"));
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
        ArrayList<String> arrayListSearchedUsernames = new ArrayList<>();
        ArrayList<String> arrayListSearchedPasswords = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfiles = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB

        try{
            Statement statement = dbConnection.createStatement();
            String query = "SELECT username,password,profile_name,account_active FROM user_account INNER JOIN user_profile ON user_account.profile_id = user_profile.profile_id";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedUsernames.add(rs.getString("username"));
                arrayListSearchedPasswords.add(rs.getString("password"));
                arrayListSearchedProfiles.add(rs.getString("profile_name"));
                arrayListSearchedActive.add(rs.getString("account_active"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllAccountData = new String[arrayListSearchedUsernames.size()][4];
        for (int row = 0; row < arrayAllAccountData.length; row++){
            for (int column = 0; column < arrayAllAccountData[row].length; column++){
                arrayAllAccountData[row][0] = arrayListSearchedUsernames.get(row);
                arrayAllAccountData[row][1] = arrayListSearchedPasswords.get(row);
                arrayAllAccountData[row][2] = arrayListSearchedProfiles.get(row);
                arrayAllAccountData[row][3] = arrayListSearchedActive.get(row);
            }
        }
        return arrayAllAccountData;
    }

    // Function to Suspend user accounts #7
    public boolean suspendAccount(String selectedUsername, String newActiveStatus) {
        boolean isUserSuspended = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE user_account SET account_active ='" + newActiveStatus + "' WHERE username='" + selectedUsername + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isUserSuspended = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isUserSuspended;
    }
}
