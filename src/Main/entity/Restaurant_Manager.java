package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

import java.sql.*;

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

    //Function to check if menu item already exists, returns true if it already exists
    public boolean checkMenuItemExistence(String food_name, Float item_price, String category){
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
                System.out.println("menu item already exists.");
                menuItemExistence = true;
            }
        }catch (SQLException e){
            // Catches any SQL query issues
            System.out.println(e);
        }
        return menuItemExistence;
    }

    //Will return true upon successful creation of menu item
    // Function to Create menu items #15
    public boolean createMenuItems(String food_name, Float item_price, String category) {
        boolean isMenuItemCreated = false;
        Connection dbConnection = dbConnection();
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

    public boolean checkCouponExistence(String coupon, Float discount){
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
                System.out.println("coupon already exists.");
                couponExistence = true;
            }
        }catch (SQLException e){
            // Catches any SQL query issues
            System.out.println(e);
        }
        return couponExistence;
    }

    public boolean createCoupon(String coupon, Float discount){
        boolean isCouponCreated = false;
        Connection dbConnection = dbConnection();
        try {
            String query = "insert into discount (coupon, discount_value)" + " values (?, ?,) ";
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

    // Function to Edit menu items #16
    public void editMenuItems(Menu_Items menuItems) {

    }

    // Function to Search menu items #17
    public void searchMenuItems(Menu_Items menuItems) {

    }

    // Function to View menu items #18
    public void viewMenuItems() {

    }

    // Function to Delete menu items #19
    public void deleteMenuItems(Menu_Items menuItems) {

    }
}
