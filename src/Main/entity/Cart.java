package Main.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Cart {

    // Variables
    private int transaction_id;

    // Default Constructor
    public Cart() {
        transaction_id = 0;
    }

    // Other Constructor
    public Cart(int table_no) {
        // initialise transaction_id
        setTransaction_id(table_no);
    }

    // get transaction_id
    public int getTransaction_id() {
        return transaction_id;
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

    // Set TransactionID from Table Number
    public void setTransaction_id(int table_no){
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        while (transaction_id <1) {
            //loops while transaction_id is 0
            try {
                //check for existence of a cart
                Statement statement = dbConnection.createStatement();
                //Query for a transaction/cart
                ResultSet rs = statement.executeQuery(
                        "SELECT transaction_id FROM transaction_history WHERE table_no ='"
                                + table_no +
                                "' AND paid = 'N'"
                );
                if (!rs.next()) {
                    //This means no unpaid transaction found for this table number
                    //Create a row in transaction_history
                    String query = "INSERT into transaction_history (transaction_id, table_no, paid )" + " values (null, ?, 'N') ";
                    PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                    preparedStatement.setInt(1, table_no);
                    preparedStatement.execute();
                    // transactionNo is still 0, will loop again
                } else {
                    //This means transaction/cart found
                    transaction_id = rs.getInt("transaction_id"); //update transaction_id, will break loop
                }
            } catch (Exception e) {
                //Catches any SQl query issues
                e.printStackTrace();
            }
        }
    }

    // Get Cart items, returns a 2d array of items in cart
    // User stories 32 and 24
    public String [][] viewCart(){
        ArrayList<String> arrayListOrderId = new ArrayList<>(); //Arraylist of order_id
        ArrayList<String> arrayListName = new ArrayList<>(); //Arraylist of item names
        ArrayList<String> arrayListQty = new ArrayList<>(); //Arraylist of quantity
        ArrayList<String> arrayListPrice = new ArrayList<>(); //Arraylist of price of row
        ArrayList<String> arrayListFulfilled = new ArrayList<>(); //Arraylist of fulfilled or not

        Connection dbConnection = dbConnection();
        String query = "SELECT order_id, name, qty, price, fulfilled FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id WHERE transaction_id =" + transaction_id;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //SQL query stuff
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) { //This is the result set
                // Add data to their respective category
                arrayListOrderId.add(Integer.toString(rs.getInt("order_id")));
                arrayListName.add(rs.getString("name"));
                arrayListQty.add(Integer.toString(rs.getInt("qty")));
                arrayListPrice.add(Float.toString(rs.getFloat("price")));
                arrayListFulfilled.add(rs.getString("fulfilled"));
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }

        // Convert Array List to a 2D array
        String [][] cartItems = new String[arrayListOrderId.size()][5];
        for (int row = 0; row < cartItems.length; row++){
            for (int column = 0; column < cartItems[row].length; column++){
                cartItems[row][0] = arrayListOrderId.get(row);
                cartItems[row][1] = arrayListName.get(row);
                cartItems[row][2] = arrayListQty.get(row);
                cartItems[row][3] = arrayListPrice.get(row);
                cartItems[row][4] = arrayListFulfilled.get(row);
            }
        }
        return cartItems;
    }

    // Add items to the cart, if successful, return true
    // User story 29
    public boolean addToCart (int item_id, int qty){
        boolean isItemAdded = false;
        Connection dbConnection = dbConnection();
        try {
            String query = "insert into order_history (transaction_id, item_id, qty, fulfilled )" + " values (?, ?, ?, 'N') ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, transaction_id);
            preparedStatement.setInt(2, item_id);
            preparedStatement.setInt(3, qty);
            preparedStatement.execute();
            isItemAdded = true;
        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isItemAdded;
    }

    // Edit items from the cart, if successful, return true
    // User stories 30 and 23
    public boolean editCart (int order_id, int qty){
        boolean isItemEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE order_history SET qty ='" + qty + "' WHERE order_id='" + order_id + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isItemEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isItemEdited;
    }

    // Delete items from the cart, if successful, return true
    // User stories 26 and 33
    public boolean deleteFromCart(int order_id){
        boolean isItemDeleted = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "DELETE FROM order_history WHERE order_id = '" + order_id + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isItemDeleted = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isItemDeleted;
    }


    // Edit order status to fulfilled, if successful, return true
    // User story 22
    public boolean editOrderStatus(int orderIDSelected) {
        boolean isOrderFulfilled = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE order_history SET fulfilled ='" + "Y" + "' WHERE order_id='" + orderIDSelected + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isOrderFulfilled = true;
        } catch (SQLException e){
            System.out.println(e);
        }

        return isOrderFulfilled;
    }

    // View all orders, returns 2d array of all orders
    // User story 25
    public String [][] viewAllOrders() {
        ArrayList<String> arrayListOrderID = new ArrayList<>();
        ArrayList<String> arrayListFoodName = new ArrayList<>();
        ArrayList<String> arrayListQuantity = new ArrayList<>();
        ArrayList<String> arrayListPrice = new ArrayList<>();
        ArrayList<String> arrayListFulfill = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT order_id, name, qty, price, fulfilled FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id;";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective array list
                arrayListOrderID.add(set.getString("order_id"));
                arrayListFoodName.add(set.getString("name"));
                arrayListQuantity.add(set.getString("qty"));
                arrayListPrice.add(set.getString("price"));
                arrayListFulfill.add(set.getString("fulfilled"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        // Convert Array List to a 2D array
        String [][] arrayAllOrderData = new String[arrayListOrderID.size()][5];
        for (int row = 0; row < arrayAllOrderData.length; row++){
            for (int column = 0; column < arrayAllOrderData[row].length; column++){
                arrayAllOrderData[row][0] = arrayListOrderID.get(row);
                arrayAllOrderData[row][1] = arrayListFoodName.get(row);
                arrayAllOrderData[row][2] = arrayListQuantity.get(row);
                arrayAllOrderData[row][3] = arrayListPrice.get(row);
                arrayAllOrderData[row][4] = arrayListFulfill.get(row);
            }
        }
        return arrayAllOrderData;
    }

    // For Restaurant Owner Report Generation: preference of Items
    // Function to generate daily dish/drink preference in array list
    // User story 43
    public ArrayList<String> dailyPreference() {
        String x = "NULL";
        ArrayList<String> tempDaily = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date = current_date() GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 10");

            int i = 0;
            while (rs.next()) {
                x = rs.getString("name");
                tempDaily.set(i,x);
                i++;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempDaily;
    }

    // Function to generate weekly dish/drink preference in array list
    // User story 44
    public ArrayList<String> weeklyPreference() {
        String x = "NULL";
        ArrayList<String> tempWeekly = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date >= current_date() - interval 1 week GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 10");

            int i = 0;
            while (rs.next()) {
                x = rs.getString("name");
                tempWeekly.set(i,x);
                i++;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempWeekly;
    }

    // Function to generate monthly dish/drink preference in array list
    // User Story 45
    public ArrayList<String> monthlyPreference() {
        String x = "NULL";
        ArrayList<String> tempMonthly = new ArrayList<>(Arrays.asList("NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL"));
        try{
            Connection dbConnection = dbConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, SUM(qty) FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id INNER JOIN transaction_history ON order_history.transaction_id = transaction_history.transaction_id WHERE date >= current_date() - interval 1 month GROUP BY order_history.item_id ORDER BY SUM(qty) DESC LIMIT 10");

            int i=0;
            while (rs.next()) {
                x = rs.getString("name");
                tempMonthly.set(i,x);
                i++;
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return tempMonthly;
    }

    // Function to return 2D array of all Menu Item preferences
    // Converts arraylists to 2D array
    // User stories 43,44,45
    public String [][] generatePreferenceReport(){
        String [][] data = new String[10][3];
        for (int row = 0; row < data.length; row++){
            for (int column = 0; column < data[row].length; column++){
                data[row][0] = dailyPreference().get(row); // User story 43
                data[row][1] = weeklyPreference().get(row); // User story 44
                data[row][2] = monthlyPreference().get(row); // User story 45
            }
        } // end of for loop
        return data;
    }
}
