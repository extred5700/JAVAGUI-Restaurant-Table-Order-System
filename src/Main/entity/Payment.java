package Main.entity;

import java.sql.*;

public class Payment {
    // Variable Declaration
    private int transaction_id;

    // Default Contructor
    public Payment() {
        transaction_id = 0;
    }

    // Other Constructor
    public Payment(int transaction_id) {
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

    // Returns float of total price of current cart - for any payment displays
    public float getTotalPrice(){
        float x = 0;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT total_price FROM transaction_history WHERE transaction_id = " + transaction_id; //query
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query); //execute query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) { //This is the result set
                x = rs.getFloat("total_price");
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return x;
    }

    // Updates transaction history with payment - requires phone number of customer. upon success, return true
    public boolean makePayment (String phoneNumber){
        boolean isPaymentSuccessful = false;

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE transaction_history SET date = current_date(), pNum = '" + phoneNumber + "', paid = 'Y' WHERE transaction_id = " + transaction_id; //query
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query); //execute query
            isPaymentSuccessful = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isPaymentSuccessful;
    }

    // Validate Customer's phone number
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 8 && (phoneNumber.charAt(0) == '8' || phoneNumber.charAt(0) == '9');
    }
}
