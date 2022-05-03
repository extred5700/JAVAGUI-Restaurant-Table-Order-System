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
        ArrayList<ArrayList<String>> arrayListAllAccountData = new ArrayList<>();
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
        // Add ALL array list into 1 array list
        arrayListAllAccountData.add(arrayListUsernames);
        arrayListAllAccountData.add(arrayListPasswords);
        arrayListAllAccountData.add(arrayListProfiles);
        arrayListAllAccountData.add(arrayListActive);

        // Convert array list to an array
        String [] arrayUsername = arrayListAllAccountData.get(0).toArray(new String[0]);
        String [] arrayPassword = arrayListAllAccountData.get(1).toArray(new String[0]);
        String [] arrayProfile = arrayListAllAccountData.get(2).toArray(new String[0]);
        String [] arrayActive = arrayListAllAccountData.get(3).toArray(new String[0]);
        String [][] arrayAllAccountDetails = new String[arrayUsername.length][arrayListAllAccountData.size()];
        for (int i = 0; i < arrayUsername.length; i++){
            arrayAllAccountDetails[i][0] = arrayUsername[i];
            arrayAllAccountDetails[i][1] = arrayPassword[i];
            arrayAllAccountDetails[i][2] = arrayProfile[i];
            arrayAllAccountDetails[i][3] = arrayActive[i];
        }
        return arrayAllAccountDetails;
    }

    // Function to Search for user accounts #5
    public String [][] searchAccount(String usernameKeyedIn, String searchStatus) {
        ArrayList<ArrayList<String>> listSearchedAccountData = new ArrayList<>();
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
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String user_profile = rs.getString("user_profile");
                        String active = rs.getString("active");
                        arrayListSearchedUsernames.add(username);
                        arrayListSearchedPasswords.add(password);
                        arrayListSearchedProfiles.add(user_profile);
                        arrayListSearchedActive.add(active);
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
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String user_profile = rs.getString("user_profile");
                        String active = rs.getString("active");
                        arrayListSearchedUsernames.add(username);
                        arrayListSearchedPasswords.add(password);
                        arrayListSearchedProfiles.add(user_profile);
                        arrayListSearchedActive.add(active);
                    }
                } catch (Exception e){
                    // Catches any SQL query issues
                    e.printStackTrace();
                }
                break;
        }

        // Add ALL array list into 1 array list
        listSearchedAccountData.add(arrayListSearchedUsernames);
        listSearchedAccountData.add(arrayListSearchedPasswords);
        listSearchedAccountData.add(arrayListSearchedProfiles);
        listSearchedAccountData.add(arrayListSearchedActive);

        // Convert array list to an array
        String [] arrayUsername = listSearchedAccountData.get(0).toArray(new String[0]);
        String [] arrayPassword = listSearchedAccountData.get(1).toArray(new String[0]);
        String [] arrayProfile = listSearchedAccountData.get(2).toArray(new String[0]);
        String [] arrayActive = listSearchedAccountData.get(3).toArray(new String[0]);
        String [][] arrayAllSearchedData = new String[arrayUsername.length][listSearchedAccountData.size()];
        for (int i = 0; i < arrayUsername.length; i++){
            arrayAllSearchedData[i][0] = arrayUsername[i];
            arrayAllSearchedData[i][1] = arrayPassword[i];
            arrayAllSearchedData[i][2] = arrayProfile[i];
            arrayAllSearchedData[i][3] = arrayActive[i];
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
}
