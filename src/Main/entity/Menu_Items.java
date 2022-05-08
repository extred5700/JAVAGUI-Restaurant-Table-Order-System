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

    // View Menu Items
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
