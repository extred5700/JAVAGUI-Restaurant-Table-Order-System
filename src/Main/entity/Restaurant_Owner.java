package Main.entity;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

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
    public String [][] getReport(String radioButtonSelected){
        String [][] generatedReport = {
                {"", "", ""}
        }; // Temp values inside 2D array, leave it as empty strings

        Connection dbConnection = dbConnection();
        switch(radioButtonSelected){
            case "Average Spend":
                generatedReport = new String[][]{{dailySpending(), weeklySpending(), monthlySpending()}};
                break;
            case "Frequency of Visits":
                generatedReport = new String[][]{{dailyFrequency(), weeklyFrequency(), monthlyFrequency()}};
                break;
            case "Food Preference":
                generatedReport = new String[5][3];
                for (int row = 0; row < generatedReport.length; row++){
                    for (int column = 0; column < generatedReport[row].length; column++){
                        generatedReport[row][0] = dailyPreference().get(row);
                        generatedReport[row][1] = weeklyPreference().get(row);
                        generatedReport[row][2] = monthlyPreference().get(row);
                    }
                }
                break;
        } // end of switch statements
        return generatedReport;
    }

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


    // Function to generate weekly frequency of visit #41
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

    // Function to generate daily dish/drink preference #43
    public ArrayList<String> dailyPreference() {
        String x = "NULL";
        ArrayList<String> tempDaily = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date = current_date() GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 5");

            int i = 0;
            while (rs.next()) {
                x = rs.getString("name");
                tempDaily.set(i,x);
                i++;
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempDaily;
    }

    // Function to generate weekly dish/drink preference #44
    public ArrayList<String> weeklyPreference() {
        String x = "NULL";
        ArrayList<String> tempWeekly = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date >= current_date() - interval 1 week GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 5");

            int i = 0;
            while (rs.next()) {
                x = rs.getString("name");
                tempWeekly.set(i,x);
                i++;
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempWeekly;
    }

    // Function to generate monthly dish/drink preference #45
    public ArrayList<String> monthlyPreference() {
        String x = "NULL";
        ArrayList<String> tempMonthly = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date >= current_date() - interval 1 month GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 5");

            int i=0;
            while (rs.next()) {
                x = rs.getString("name");
                tempMonthly.set(i,x);
                i++;
            }
            //probably have to run a return for array list here in main program
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempMonthly;
    }
}

