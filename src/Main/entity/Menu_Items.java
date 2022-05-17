package Main.entity;

// This is just a draft for the Order class - May change depending on our UML design in the future

import java.sql.*;
import java.util.ArrayList;

public class Menu_Items {
    // Variable Declaration
    private String name;
    private double price;

    // Default Constructor
    public Menu_Items() {
    }

    // Other Constructor
    public Menu_Items(String name, double price) {
        this.name = name;
        this.price = price;
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

    // Function to check if menu item already exists, returns true if it already exists
    // Used only in ManagerCreateController
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
    // Function to Create menu items #15
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

    // View Menu Items for Restaurant Manager
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

    // Function to Edit menu items #16
    public boolean editMenuItem(int itemID, String newFoodName, Float new_price) {
        boolean isMenuItemEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE menu_item SET name ='" + newFoodName + "',item_price='" + new_price + "' WHERE item_id='" + itemID + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isMenuItemEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isMenuItemEdited;
    }

    // Function to Search menu items #17
    public String [][] searchMenuItems(String searchText) {
        ArrayList<String> arrayListSearchedItemID = new ArrayList<>();
        ArrayList<String> arrayListSearchedFoodName = new ArrayList<>();
        ArrayList<String> arrayListSearchedPrice = new ArrayList<>();
        ArrayList<String> arrayListSearchedCategory = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try{
            Statement statement = dbConnection.createStatement();
            // SQL Query Stuff
            ResultSet rs = statement.executeQuery("SELECT * FROM menu_item WHERE name LIKE '%" + searchText + "%'");

            while (rs.next()) { //This is the result set
                // Add data to their respective array list
                arrayListSearchedItemID.add(rs.getString("item_id"));
                arrayListSearchedFoodName.add(rs.getString("name"));
                arrayListSearchedPrice.add(rs.getString("item_price"));
                arrayListSearchedCategory.add(rs.getString("category"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        // Convert Array List to a 2D array
        String [][] arrayAllSearchData = new String[arrayListSearchedItemID.size()][4];
        for (int row = 0; row < arrayAllSearchData.length; row++){
            for (int column = 0; column < arrayAllSearchData[row].length; column++){
                arrayAllSearchData[row][0] = arrayListSearchedItemID.get(row);
                arrayAllSearchData[row][1] = arrayListSearchedFoodName.get(row);
                arrayAllSearchData[row][2] = arrayListSearchedPrice.get(row);
                arrayAllSearchData[row][3] = arrayListSearchedCategory.get(row);
            }
        }
        return arrayAllSearchData;
    }

    // Function to Delete menu item #19
    public boolean deleteMenuItem(int itemID) {
        boolean isMenuItemDeleted = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "DELETE FROM menu_item WHERE item_id = '" + itemID + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isMenuItemDeleted = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isMenuItemDeleted;
    }

    // View Menu Items for Customer
    public String [][] viewMenu(String category){
        ArrayList<String> arrayListItemID = new ArrayList<>();
        ArrayList<String> arrayListItemName = new ArrayList<>();
        ArrayList<String> arrayListItemPrice = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        try {
            // Display menu items from DB
            String query = "";
            if (category.equals("All")) {
                query = "SELECT item_id, name, item_price FROM menu_item";
            } else if (category.equals("Pasta")) {
                query = "SELECT item_id, name, item_price FROM menu_item WHERE category = 'Pasta'";
            } else if (category.equals("Pizza")) {
                query = "SELECT item_id, name, item_price FROM menu_item WHERE category = 'Pizza'";
            } else if (category.equals("Baked Rice")){
                query = "SELECT item_id, name, item_price FROM menu_item WHERE category = 'Baked Rice'";
            } else {
                query = "SELECT item_id, name, item_price FROM menu_item WHERE category = 'Drinks'";
            }
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                // Add data to their respective category
                arrayListItemID.add(rs.getString("item_id"));
                arrayListItemName.add(rs.getString("name"));
                arrayListItemPrice.add(rs.getString("item_price"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to a 2D array
        String [][] arrayMenuItems = new String[arrayListItemName.size()][3];
        for (int row = 0; row < arrayMenuItems.length; row++){
            for (int column = 0; column < arrayMenuItems[row].length; column++){
                arrayMenuItems[row][0] = arrayListItemID.get(row);
                arrayMenuItems[row][1] = arrayListItemName.get(row);
                arrayMenuItems[row][2] = arrayListItemPrice.get(row);
            }
        }
        return arrayMenuItems;
    }

}
