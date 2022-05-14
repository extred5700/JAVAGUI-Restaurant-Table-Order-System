package Main.entity;

import java.sql.*;
import java.util.ArrayList;

public class User_Profile extends Staff{
    // Variable Declaration
    Staff staff = new Staff();

    /* Not sure whether need constructor

    // Default Constructor
    public User_Profile(){
    }

    // Other Constructor
    public User_Profile(String profile){
        super(profile);
    }
    */

    // Validate User Profile
    public boolean checkProfileExistence(String profile){
        boolean profileExistence = false;
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        try{
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT profile_name FROM user_profile WHERE profile_name = '" + profile + "'");

            if (!rs.next()) {
                //no profile found -> do nothing
            }
            else{
                //profile found
                profileExistence = true;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return profileExistence;
    }

    // Function to create a User Profile #8
    public boolean createProfile(String newProfile){
        boolean isProfileCreated = false;
        String active = "Y";
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        // Need to auto-increment the profile_id
        try{
            String query = "insert into user_profile (profile_name, profile_active)" + " values (?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, newProfile);
            preparedStatement.setString(2, active);
            preparedStatement.execute();
            isProfileCreated = true;
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isProfileCreated;
    }

    // Function to edit a User Profile #9
    public boolean editProfile(int selectedProfileID, String newProfile){
        boolean isProfileEdited = false;
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        String query = "UPDATE user_profile SET profile_name ='" + newProfile +"' WHERE profile_id ='" + selectedProfileID + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isProfileEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isProfileEdited;
    }

    /* Function to return a array of String to display in the Dropdown Box for:
    * 1) StaffLoginPage.java
    * 2) UserAdminPageUI, Creation of User Profiles
    * Purely returning the Strings of the different types of User Admin
    * Will only return active user profiles
    */
    public String [] existingProfile(){
        ArrayList<String> arrayListProfiles = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        String query = "SELECT profile_name FROM user_profile WHERE profile_active = 'Y'";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective array list
                arrayListProfiles.add(set.getString("profile_name"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to an array
        String [] arrayProfiles = new String[arrayListProfiles.size()];
        for (int i = 0; i < arrayProfiles.length; i++){
            arrayProfiles[i] = arrayListProfiles.get(i);
        } // end of for-loop statements
        return arrayProfiles;
    }

    // Function to search User Profile #10
    public String[][] searchProfile(String dataKeyedIn){
        ArrayList<String> arrayListSearchedProfileID = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfileName = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB

        try{
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user_profile WHERE profile_name LIKE '%" + dataKeyedIn + "%'");

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedProfileID.add(rs.getString("profile_id"));
                arrayListSearchedProfileName.add(rs.getString("profile_name"));
                arrayListSearchedActive.add(rs.getString("profile_active"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllSearchedData = new String[arrayListSearchedProfileID.size()][3];
        for (int row = 0; row < arrayAllSearchedData.length; row++){
            for (int column = 0; column < arrayAllSearchedData[row].length; column++){
                arrayAllSearchedData[row][0] = arrayListSearchedProfileID.get(row);
                arrayAllSearchedData[row][1] = arrayListSearchedProfileName.get(row);
                arrayAllSearchedData[row][2] = arrayListSearchedActive.get(row);
            }
        }
        return arrayAllSearchedData;
    }

    // Function to view User Profile #11
    public String[][] viewProfile(){
        ArrayList<String> arrayListSearchedProfileID = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfileName = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB

        try{
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user_profile");

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedProfileID.add(Integer.toString(rs.getInt("profile_id")));
                arrayListSearchedProfileName.add(rs.getString("profile_name"));
                arrayListSearchedActive.add(rs.getString("profile_active"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllProfileData = new String[arrayListSearchedProfileID.size()][3];
        for (int row = 0; row < arrayAllProfileData.length; row++){
            for (int column = 0; column < arrayAllProfileData[row].length; column++){
                arrayAllProfileData[row][0] = arrayListSearchedProfileID.get(row);
                arrayAllProfileData[row][1] = arrayListSearchedProfileName.get(row);
                arrayAllProfileData[row][2] = arrayListSearchedActive.get(row);
            }
        }
        return arrayAllProfileData;
    }

    // Function to suspend/un-suspend User Profile #7
    public boolean suspendProfile(int selectedProfileID, String newActiveStatus){
        boolean isProfileSuspended = false;
        Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        String query = "UPDATE user_profile SET profile_active ='" + newActiveStatus + "' WHERE profile_id='" + selectedProfileID + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isProfileSuspended = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isProfileSuspended;
    }

}