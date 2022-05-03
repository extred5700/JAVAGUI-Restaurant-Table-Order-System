package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

import java.sql.*;
import java.util.ArrayList;

public class User_Admin extends Staff {
    // Variable Declaration
    Staff staff = new Staff();

    // Default Constructor
    public User_Admin() {
    }

    // Other Constructor
    public User_Admin(String userID, String password, String staffType, String profile) {
        super(userID, password, staffType, profile);
    }

    // Function to Create a user account #3
    public boolean createAccount(String username, String password, String profile) {
        boolean isUserCreated = false;
        String active = "Y";
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
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
    }

    // Function to Edit user accounts #4
    public boolean editAccount(String oldUsername, String newUsername, String newPassword, String newProfile) {
        boolean isUserEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE user SET username ='" + newUsername + "',password='" + newPassword + "',user_profile='" + newProfile + "' WHERE username='" + oldUsername + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isUserEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isUserEdited;
    }

    // Method to get an arraylist of user account information
    // Array list contains 4 array list: arrayListUsernames, arrayListPasswords, arrayListProfiles, arrayListActive
    public String [][] getUserInfoFromDB(){
        ArrayList<String> arrayListUsernames = new ArrayList<>();
        ArrayList<String> arrayListPasswords = new ArrayList<>();
        ArrayList<String> arrayListProfiles = new ArrayList<>();
        ArrayList<String> arrayListActive = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT * from user";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective array list
                arrayListUsernames.add(set.getString("username"));
                arrayListPasswords.add(set.getString("password"));
                arrayListProfiles.add(set.getString("user_profile"));
                arrayListActive.add(set.getString("active"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to a 2D array
        String [][] arrayAllAccountDetails = new String[arrayListUsernames.size()][4];
        for (int row = 0; row < arrayAllAccountDetails.length; row++){
            for (int column = 0; column < arrayAllAccountDetails[row].length; column++){
                arrayAllAccountDetails[row][0] = arrayListUsernames.get(row);
                arrayAllAccountDetails[row][1] = arrayListPasswords.get(row);
                arrayAllAccountDetails[row][2] = arrayListProfiles.get(row);
                arrayAllAccountDetails[row][3] = arrayListActive.get(row);
            }
        }
        return arrayAllAccountDetails;
    }

    // Function to Search for user accounts #5
    public String [][] searchAccount(String usernameKeyedIn, String searchStatus) {
        ArrayList<String> arrayListSearchedUsernames = new ArrayList<>();
        ArrayList<String> arrayListSearchedPasswords = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfiles = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB

        switch (searchStatus){
            case "search_by_username":
                try{
                    Statement statement = dbConnection.createStatement();
                    //SQL query stuff
                    ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE username LIKE '%" + usernameKeyedIn + "%'");

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
                break;
            case "search_by_profile":
                try{
                    Statement statement = dbConnection.createStatement();
                    //SQL query stuff
                    ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE user_profile LIKE '%" + usernameKeyedIn + "%'");

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
                break;
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
    public void viewAccount() {

    }

    // Function to Suspend user accounts #7
    public boolean suspendAccount(String selectedUsername, String newActiveStatus) {
        boolean isUserSuspended = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE user SET active ='" + newActiveStatus + "' WHERE username='" + selectedUsername + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isUserSuspended = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isUserSuspended;
    }

    // Function to Create user profile #8
    public void createProfile(String userID, String userPassword, String staffType) {
    }

    // Function to Edit user profile #9
    public void editProfile(String userID, String profile) {

    }

    // Function to Search user profile #10
    public void searchProfile(String userID, String profile) {

    }

    // Function to View user profile #11
    public void viewProfile() {

    }

    // Function to Suspend user profile #12
    public void suspendProfile(String userID, String profile) {

    }

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
}
