package Main.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        String query = "SELECT discounted_price FROM transaction_history WHERE transaction_id = " + transaction_id; //query
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query); //execute query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) { //This is the result set
                x = rs.getFloat("discounted_price");
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

    /* For Restaurant Owner Report Generation: Average Spending */
    // Function to generate daily average spending per visit #37
    public String dailySpending() {
        float x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(discounted_price) from transaction_history WHERE date = current_date()");


            while (rs.next()) {
                x = rs.getFloat("AVG(discounted_price)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Float.toString(x);
    }

    // Function to generate weekly average spending per visit #38
    public String weeklySpending() {
        float x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(discounted_price) from transaction_history  WHERE date >= current_date() - interval 1 week");


            while (rs.next()) {
                x = rs.getFloat("AVG(discounted_price)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Float.toString(x);
    }

    // Function to generate monthly average spending per visit #39
    public String monthlySpending() {
        float x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(discounted_price) from transaction_history  WHERE date >= current_date() - interval 1 month");


            while (rs.next()) {
                x = rs.getFloat("AVG(discounted_price)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Float.toString(x);
    }

    /* For Restaurant Owner Report Generation: Frequency of Visits */
    // Function to generate daily frequency of visits #40
    public String dailyFrequency() {
        int x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT count(*) FROM transaction_history WHERE date = current_date()");


            while (rs.next()) {
                x = rs.getInt("count(*)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Integer.toString(x);
    }

    // Function to generate weekly frequency of visits #41
    public String weeklyFrequency() {
        int x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT count(*) FROM transaction_history WHERE date >= current_date() - interval 1 week");


            while (rs.next()) {
                x = rs.getInt("count(*)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Integer.toString(x);
    }

    // Function to generate monthly frequency of visit #42
    public String monthlyFrequency() {
        int x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT count(*) FROM transaction_history WHERE date >= current_date() - interval 1 month");


            while (rs.next()) {
                x = rs.getInt("count(*)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Integer.toString(x);
    }

    /* Function to generate either:
    * 1. Average Spending
    * 2. Frequency of Visits
    * And return the data as a 2D array back to GenerateReportController
    */
    public String [][] generateReport(String radioButtonSelection){
        String [][] data = {
                {"", "", ""}
        }; // Temp values inside 2D array, leave it as empty string
        switch(radioButtonSelection){
            case "Average Spend" -> data = new String[][]{{dailySpending(), weeklySpending(), monthlySpending()}};
            case "Frequency of Visits" -> data = new String[][]{{dailyFrequency(), weeklyFrequency(), monthlyFrequency()}};
        } // end of switch statements
        return data;
    }

}
