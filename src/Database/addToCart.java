import java.sql.*;

public class addToCart {

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
        int tNo = 12; //transaction ID
        int iNo = 5; //item ID
        int quant = 1; // qty

        // Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        Connection dbConnection = dbConnection();
        try{
            String query = "insert into order_history (transaction_id, item_id, qty, fulfilled )" + " values (?, ?, ?, 'N') ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, tNo);
            preparedStatement.setInt(2, iNo);
            preparedStatement.setInt(3, quant);
            preparedStatement.execute();
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
