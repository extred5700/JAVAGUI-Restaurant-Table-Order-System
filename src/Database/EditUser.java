import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class EditUser {

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

        String UN = "user4"; //username - pass in username of account to edit
        String PW = "testing"; //password - pass in new password to change to
        String PF = "Restaurant Staff"; //user profile - pass in new user profile of the account
        Connection dbConnection = dbConnection();
        try{
            String query = "UPDATE user SET password = ?, user_profile = ? WHERE username = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, PW);
            preparedStatement.setString(2, PF);
            preparedStatement.setString(3, UN);
            preparedStatement.executeUpdate();
            System.out.println("account editing successful.");
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

}
