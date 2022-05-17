package Main.entity;

import java.sql.*;

public class Discount {
    // Variable Declaration
    private int transaction_id;

    // Constructor
    public Discount() {
    }

    // Other Constructor
    public Discount(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    // Set up DB Connection
    private Connection dbConnection() {
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

    //Checks for valid discount and applies it, if successful, return true
    public boolean applyDiscount(String discount_code){
        boolean discountApplied = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT discount_value FROM discount WHERE coupon = '" + discount_code+"'"; //query
        try {
            //SQL query stuff
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);//execute query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) { //This is the result set
                float x = rs.getFloat("discount_value"); //save discount value temporarily
                //update total price in transaction history
                String query2 = "UPDATE transaction_history SET total_price = total_price * " + x + " WHERE transaction_id = " + transaction_id;
                Statement statement = dbConnection.createStatement();
                statement.executeUpdate(query2);
                discountApplied = true;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return discountApplied;
    }
}
