package Main.entity;

import java.sql.*;

public class Restaurant_Owner extends Staff {
    // Variable Declaration
    Staff staff = new Staff();

    // Default constructor
    public Restaurant_Owner() {
    }

    // Other Constructor
    public Restaurant_Owner(String userID, String password, String staffType, String profile) {
        super(userID, password, staffType, profile);
    }

    // Function to generate daily average spending per visit #37
    public String dailySpending() {
        float x = 0;
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history WHERE date = current_date()");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
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
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history  WHERE date >= current_date() - interval 1 week");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
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
            ResultSet rs = statement.executeQuery("select AVG(total_price) from transaction_history  WHERE date >= current_date() - interval 1 month");


            while (rs.next()) {
                x = rs.getFloat("AVG(total_price)");
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return Float.toString(x);
    }

    // Additional function to return an array of selected data by the user
    public String [] getReport(String radioButtonSelected){
        String [] generatedReport = new String[3];
        Connection dbConnection = dbConnection();
        switch(radioButtonSelected){
            case "Average Spend":
                /* Call the following functions:
                * 1. dailySpending()
                * 2. weeklySpending()
                * 3. monthlySpending()
                * Change the return type of the functions above from void to int
                * Store the values in generatedReport, remember to use .toString()
                */
                generatedReport[0] = dailySpending();
                generatedReport[1] = weeklySpending();
                generatedReport[2] = monthlySpending();
                break;
            case "Frequency of Visits":
                break;
            case "Food Preference":
                break;
        } // end of switch statements
        return generatedReport;
    }

    // Function to generate daily frequency of visits #40
    public void dailyFrequency() {

    }

    // Function to generate weekly frequency of visit #41
    public void weeklyFrequency() {

    }

    // Function to generate monthly frequency of visit #42
    public void monthlyFrequency() {

    }

    // Function to generate daily dish/drink preference #43
    public void dailyPreference() {

    }

    // Function to generate weekly dish/drink preference #44
    public void weeklyPreference() {

    }

    // Function to generate monthly dish/drink preference #45
    public void monthlyPreference() {

    }
}
