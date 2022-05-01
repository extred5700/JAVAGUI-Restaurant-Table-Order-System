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

    /* LOGIN FUNCTIONS FOR ALL ACTORS */
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

    /* USER ADMIN SIDE
    * Lines  86 - 171 */
    // Create new User Account
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

    // Edit User Account CHANGES
    public boolean editUserAccountUsingOldUsername(String oldUsername, String newUsername, String newPassword, String newProfile){
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

    // Suspend User Account
    public boolean suspendUserViaUsername(String selectedUsername, String newActiveStatus){
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

    /* RESTAURANT MANAGER SIDE
     * Lines 174 - */
    //
    public String [] getReportUsingSelectedRadioButton(String radioButtonSelected){
        String [] generatedReport = new String[3];
        Connection dbConnection = dbConnection();
        //obtain daily value
        try{
            float x = 0;
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history WHERE date = current_date()");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
                generatedReport[0] = Float.toString(x);
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        //obtain weekly value
        try{
            //obtain daily value
            float x = 0;
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history  WHERE date >= current_date() - interval 1 week;");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
                generatedReport[1] = Float.toString(x);
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        //obtain monthly values
        try{
            //obtain daily value
            float x = 0;
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history  WHERE date >= current_date() - interval 1 month;");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
                generatedReport[2] = Float.toString(x);
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return generatedReport;
    }
}
