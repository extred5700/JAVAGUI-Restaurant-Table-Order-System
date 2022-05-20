package Main.entity;

import java.sql.*;
import java.util.ArrayList;

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

    // Function to check if Coupon already exists, returns true if it already exists
    public boolean checkCouponExistence(String coupon){
        boolean couponExistence = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT * FROM discount WHERE coupon = '" + coupon + "'";
        try{
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()){
                //This means no coupon found
                System.out.println("No coupon found.");
            }
            else{
                //This means coupon found
                System.out.println("Coupon already exists.");
                couponExistence = true;
            }
        }catch (SQLException e){
            // Catches any SQL query issues
            System.out.println(e);
        }
        return couponExistence;
    }

    // Return true upon successful creation of Coupon
    public boolean createCoupon(String coupon, Float discount){
        boolean isCouponCreated = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try {
            String query = "insert into discount (coupon, discount_value)" + " values (?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, coupon);
            preparedStatement.setFloat(2, discount);
            preparedStatement.execute();
            isCouponCreated = true;
        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isCouponCreated;
    }

    // Function to view all coupons
    public String [][] viewCoupons(){
        ArrayList<String> arrayListCouponName = new ArrayList<>();
        ArrayList<String> arrayListDiscountValue = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT * from discount";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective array list
                arrayListCouponName.add(set.getString("coupon"));
                arrayListDiscountValue.add(set.getString("discount_value"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to a 2D array
        String [][] arrayAllCoupons = new String[arrayListCouponName.size()][2];
        for (int row = 0; row < arrayAllCoupons.length; row++){
            for (int column = 0; column < arrayAllCoupons[row].length; column++){
                arrayAllCoupons[row][0] = arrayListCouponName.get(row);
                arrayAllCoupons[row][1] = arrayListDiscountValue.get(row);
            }
        }
        return arrayAllCoupons;
    }

    // Returns true upon successful editing of Coupon
    public boolean editCoupon(String oldCouponName, String newCouponName, Float new_discount_value){
        boolean isCouponEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE discount SET coupon ='" + newCouponName + "',discount_value='" + new_discount_value + "' WHERE coupon='" + oldCouponName + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isCouponEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isCouponEdited;
    }

    // Search Coupons by Coupon name and return by 2D array
    public String [][] searchCoupon(String searchText){
        ArrayList<String> arrayListSearchedCouponName = new ArrayList<>();
        ArrayList<String> arrayListSearchedDiscountValue = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try{
            Statement statement = dbConnection.createStatement();
            // SQL Query Stuff
            ResultSet rs = statement.executeQuery("SELECT * FROM discount WHERE coupon LIKE '%" + searchText + "%'");

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedCouponName.add(rs.getString("coupon"));
                arrayListSearchedDiscountValue.add(rs.getString("discount_value"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllSearchData = new String[arrayListSearchedCouponName.size()][2];
        for (int row = 0; row < arrayAllSearchData.length; row++){
            for (int column = 0; column < arrayAllSearchData[row].length; column++){
                arrayAllSearchData[row][0] = arrayListSearchedCouponName.get(row);
                arrayAllSearchData[row][1] = arrayListSearchedDiscountValue.get(row);
            }
        }
        return arrayAllSearchData;
    }

    // Function to Delete Coupon
    public boolean deleteCoupon(String coupon){
        boolean isCouponDeleted = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "DELETE FROM discount WHERE coupon = '" + coupon + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isCouponDeleted = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isCouponDeleted;
    }

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
                String query2 = "UPDATE transaction_history SET discounted_price = total_price * " + x + " WHERE transaction_id = " + transaction_id;
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
