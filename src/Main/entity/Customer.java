package Main.entity;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Customer {
    // Variable Declaration
    private int transaction_id;
    private int table_no;
    private String phoneNumber;


    // Default Constructor
    public Customer() {

    }

    // Other Constructor
    public Customer(int table_no) {
        this.table_no = table_no;
        setTransaction_id();
    }

    // Other Constructor
    public Customer(int table_no, int transaction_id) {
        this.table_no = table_no;
        this.transaction_id = transaction_id;

        // automatically set transaction_id
//        setTransaction_id(table_no);
    }

    // Getter
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

    // Function for inserting table number #27
    public void insertTableNumber() {

    }

    // Function for browsing menu #28
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
            } else {
                query = "SELECT item_id, name, item_price FROM menu_item WHERE category = 'Baked Rice'";
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

    // Function for adding items to cart #29
    public void addToCart(int transaction_id, int item_id, int qty) {
        // Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        Connection dbConnection = dbConnection();
        try {
            String query = "insert into order_history (transaction_id, item_id, qty, fulfilled )" + " values (?, ?, ?, 'N') ";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, transaction_id);
            preparedStatement.setInt(2, item_id);
            preparedStatement.setInt(3, qty);
            preparedStatement.execute();
        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    // Function for remove items from cart #30
    public void removeFromCart(int menuItemID, int quantity) {

    }

    // Function for search for items from menu #31
    public void searchMenuItems(String category) {

    }

    // Function for view cart items #32
    public String [][] viewCart() {
        ArrayList<String> arrayListItemId = new ArrayList<>(); //Arraylist of orderids
        ArrayList<String> arrayListName = new ArrayList<>(); //Arraylist of item names
        ArrayList<String> arrayListQty = new ArrayList<>(); //Arraylist of quantity
        ArrayList<String> arrayListPrice = new ArrayList<>(); //Arraylist of price of row
//        ArrayList<String> arrayListFufilled = new ArrayList<>(); //Arraylist of fufilled or not

        Connection dbConnection = dbConnection();
        String query = "SELECT order_history.item_id, name, qty, price FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id WHERE transaction_id =" + transaction_id;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //SQL query stuff
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) { //This is the result set
                // Add data to their respective category
                arrayListItemId.add(Integer.toString(rs.getInt("item_id")));
                arrayListName.add(rs.getString("name"));
                arrayListQty.add(Integer.toString(rs.getInt("qty")));
                arrayListPrice.add(Float.toString(rs.getFloat("price")));
//                arrayListFufilled.add(rs.getString("fufilled"));

                //store strings - conversion done here
                /*String orderid = Integer.toString(rs.getInt("order_id"));
                String name = rs.getString("name");
                String qty = Integer.toString(rs.getInt("qty"));
                String price = Float.toString(rs.getFloat("price"));
                String fufilled = rs.getString("fufilled");
                //add to arrays
                arrayListOrderid.add(orderid);
                arrayListName.add(name);
                arrayListQty.add(qty);
                arrayListPrice.add(price);
                arrayListFufilled.add(fufilled);*/
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }

        // Convert Array List to a 2D array
        String [][] arrayCart = new String[arrayListItemId.size()][4];
        for (int row = 0; row < arrayCart.length; row++){
            for (int column = 0; column < arrayCart[row].length; column++){
                arrayCart[row][0] = arrayListItemId.get(row);
                arrayCart[row][1] = arrayListName.get(row);
                arrayCart[row][2] = arrayListQty.get(row);
                arrayCart[row][3] = arrayListPrice.get(row);
//                arrayCart[row][4] = arrayListFufilled.get(row);
            }
        }
        //construct 2d array
/*        listCartData.add(arrayListOrderid);
        listCartData.add(arrayListName);
        listCartData.add(arrayListQty);
        listCartData.add(arrayListPrice);
        listCartData.add(arrayListFufilled);
        //return 2d array (for now, used println to check)
        System.out.println(listCartData);*/

        return arrayCart;
    }

    // Function for delete cart items #33
    public void deleteCartItem(int menuItemID, int quantity) {

    }

    // Function for making epayment #34
    public void ePayment() {

    }

    // Function to set transaction ID
    public void setTransaction_id() {
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
                    transaction_id = rs.getInt("transaction_id"); //update transactionNo, will break loop
                }
            } catch (Exception e) {
                //Catches any SQl query issues
                e.printStackTrace();
            }
        }
    }

    // Function to validate item_id - check whether item_id user enters is valid
    public boolean validateItemID(int item_id) {
        int min = 0;
        int max = 0;
        boolean flag = false;
        // Connection dbConnection = staff.dbConnection(); // Set up connection with the DB
        Connection dbConnection = dbConnection();
        try {
            // check whether item_id exists in database
            String query = "SELECT item_id FROM menu_item";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                if (item_id == rs.getInt("item_id")) {
                    flag = true;
                }
            }
        }catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return flag;
    }
}