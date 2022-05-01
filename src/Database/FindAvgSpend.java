import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class FindAvgSpend {

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

        System.out.println(generatedReport[0]);
        System.out.println(generatedReport[1]);
        System.out.println(generatedReport[2]);
    }
}
