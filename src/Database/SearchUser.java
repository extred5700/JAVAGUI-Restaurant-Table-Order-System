import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchUser {

    public static Connection dbConnection(){
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

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> listSearchedAccountData = new ArrayList<>();
        ArrayList<String> arrayListSearchedUsernames = new ArrayList<>();
        ArrayList<String> arrayListSearchedPasswords = new ArrayList<>();
        ArrayList<String> arrayListSearchedProfiles = new ArrayList<>();
        ArrayList<String> arrayListSearchedActive = new ArrayList<>();

        Connection dbConnection = dbConnection();
        String sUser = "user"; //this the variable to pass in to search

        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE username LIKE '%" + sUser + "%'");


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
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }

        listSearchedAccountData.add(arrayListSearchedUsernames);
        listSearchedAccountData.add(arrayListSearchedPasswords);
        listSearchedAccountData.add(arrayListSearchedProfiles);
        listSearchedAccountData.add(arrayListSearchedActive);
        System.out.println(listSearchedAccountData);
    }


}
