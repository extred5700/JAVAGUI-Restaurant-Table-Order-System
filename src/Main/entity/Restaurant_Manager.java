package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

import java.sql.*;
import java.util.ArrayList;

public class Restaurant_Manager extends Staff {
    // Variable Declaration
    private Menu_Items menuItems; // not final - depends on how our UML is gonna look like

    // Default Constructor
    public Restaurant_Manager() {
    }

    // Other Constructor
    public Restaurant_Manager(String userID, String password, String staffType, String profile, Menu_Items menuItems) {
        super(userID, password, staffType, profile);
        this.menuItems = menuItems;
    }


    /* 1) MENU ITEMS */

    // 1a) Function to View menu items #18
    public String [][] viewMenuItems(){
        ArrayList<String> arrayListItemID = new ArrayList<>();
        ArrayList<String> arrayListName = new ArrayList<>();
        ArrayList<String> arrayListItemPrice = new ArrayList<>();
        ArrayList<String> arrayListCategory = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with DB
        String query = "SELECT * from menu_item";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective array list
                arrayListItemID.add(set.getString("item_id"));
                arrayListName.add(set.getString("name"));
                arrayListItemPrice.add(set.getString("item_price"));
                arrayListCategory.add(set.getString("category"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to a 2D array
        String [][] arrayAllMenuItems = new String[arrayListItemID.size()][4];
        for (int row = 0; row < arrayAllMenuItems.length; row++){
            for (int column = 0; column < arrayAllMenuItems[row].length; column++){
                arrayAllMenuItems[row][0] = arrayListItemID.get(row);
                arrayAllMenuItems[row][1] = arrayListName.get(row);
                arrayAllMenuItems[row][2] = arrayListItemPrice.get(row);
                arrayAllMenuItems[row][3] = arrayListCategory.get(row);
            }
        }
        return arrayAllMenuItems;
    }

    // 1b) Function to check if menu item already exists, returns true if it already exists
    public boolean checkMenuItemExistence(String food_name){
        boolean menuItemExistence = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT * FROM menu_item WHERE name = '" + food_name + "'";
        try{
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()){
                //This means no menu_item found
                System.out.println("No menu item found.");
            }
            else{
                //This means menu_item found
                System.out.println("Menu Item already exists.");
                menuItemExistence = true;
            }
        }catch (SQLException e){
            // Catches any SQL query issues
            System.out.println(e);
        }
        return menuItemExistence;
    }

    // Will return true upon successful creation of menu item
    // 1c) Function to Create menu items #15
    public boolean createMenuItems(String food_name, Float item_price, String category) {
        boolean isMenuItemCreated = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try {
            String query = "insert into menu_item (name, item_price, category )" + " values (?, ?, ?) ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, food_name);
            preparedStatement.setFloat(2, item_price);
            preparedStatement.setString(3, category);
            preparedStatement.execute();
            isMenuItemCreated = true;
        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isMenuItemCreated;
    }

    // 1d) Function to Edit menu items #16
    public boolean editMenuItems(int item_id, String food_name, Float new_price) {
        boolean isMenuItemEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE menu_item SET name ='" + food_name + "',item_price='" + new_price + "' WHERE item_id='" + item_id + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isMenuItemEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isMenuItemEdited;
    }

    /* 2) COUPONS */

    // 2a) Function to view all coupons
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

    // 2b) Function to check if Coupon already exists, returns true if it already exists
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

    // 2c)Will return true upon successful creation of Coupon
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

    public boolean editCoupon(String oldCouponName, String newCouponName, Float discount){
        boolean isCouponEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE discount SET coupon ='" + newCouponName + "',discount_value='" + discount + "' WHERE coupon='" + oldCouponName + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isCouponEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isCouponEdited;
    }









    // Function to Search menu items #17
    public void searchMenuItems(Menu_Items menuItems) {

    }


    // Function to Delete menu items #19
    public void deleteMenuItems(Menu_Items menuItems) {

    }
}
