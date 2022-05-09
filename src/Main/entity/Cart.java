package Main.entity;
import java.sql.*;
import java.util.ArrayList;

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

    // Get Cart items using the transaction ID
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
            isItemAdded = true; //should be here - do let me know if its wrong place
        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isItemAdded;
    }

    // Edit items from the cart, if successful, return true
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
    public boolean deleteFromCart(int order_id){
        boolean isItemDeleted = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "DELETE FROM order_history WHERE order_id='" + order_id + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isItemDeleted = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isItemDeleted;
    }

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
                String query2 = "UPDATE transaction_history SET total_price = total_price * " + x + " WHERE transaction_id = " + transaction_id;
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

    //Updates transaction history with payment - requires phone number of customer. upon success, return true
    public boolean makePayment (String phoneNumber){
        boolean isPaymentSuccessful = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE transaction_history SET date = current_date(), pNum = '" + phoneNumber + "', paid = Y WHERE transaction_id = " + transaction_id; //query
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query); //execute query
            isPaymentSuccessful = true;
        } catch (SQLException e){
            System.out.println(e);
        }
        return isPaymentSuccessful;
    }

}
