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
    public ArrayList<ArrayList<String>> getUserInfoFromDB(){
        ArrayList<ArrayList<String>> listAccountData = new ArrayList<>();
        ArrayList<String> arrayListUsernames = new ArrayList<>();
        ArrayList<String> arrayListPasswords = new ArrayList<>();
        ArrayList<String> arrayListProfiles = new ArrayList<>();
        ArrayList<String> arrayListActive = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT * from user";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective category, then... (line 134)
                String username = set.getString("username");
                String password = set.getString("password");
                String user_profile = set.getString("user_profile");
                String active = set.getString("active");
                arrayListUsernames.add(username);
                arrayListPasswords.add(password);
                arrayListProfiles.add(user_profile);
                arrayListActive.add(active);
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // COMBINE/ADD ALL array list to one array list
        listAccountData.add(arrayListUsernames);
        listAccountData.add(arrayListPasswords);
        listAccountData.add(arrayListProfiles);
        listAccountData.add(arrayListActive);
        return listAccountData;
    }

    // Function to Search for user accounts #5
    public void searchAccount(String username, String password, String staffType) {

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
}
