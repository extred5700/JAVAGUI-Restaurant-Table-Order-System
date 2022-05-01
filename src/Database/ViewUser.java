import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ViewUser {

    public static class userRow { //userRow class used to store rows of data
         String UN = "null"; //store username
         String PW = "null"; //store password
         String PF = "null"; //store user_profile
         String AT = "null"; //store active


        public userRow(String x, String y, String z, String a){ //class constructor
            this.UN = x;
            this.PW = y;
            this.PF = z;
            this.AT = a;
        }

        @Override //can remove if not needed
        public String toString(){ //to println
            return (UN + " " + PW + " " + PF + " " + AT);
        }
    }
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
        ArrayList<userRow> Users = new ArrayList<userRow>(); //create array list of userRow
        Connection dbConnection = dbConnection();
        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT * FROM user");

            int i = 0;

            while (rs.next()) { //This is the result set
                // System.out.println(rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("user_profile") + " " + rs.getString("active"));
                //This rs.GetString("whatever") will return whatever u need in case my code does not fit ur needs
                userRow uRow = new userRow(rs.getString("username"), rs.getString("password"), rs.getString("user_profile"), rs.getString("active")); //create userRow object for this row
                Users.add(i,uRow); //add userRow object to array list
                i++;
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        //printing out the contents of array list
        for (userRow row: Users){
            System.out.println(row);
        }


    }


}
