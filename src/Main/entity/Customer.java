package Main.entity;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Customer {
    // Variable Declaration
    private int table_no;
    private String phoneNumber;
    Cart cart;


    // Default Constructor
    public Customer() {
        table_no = 0;
        phoneNumber = "";
        cart = new Cart();
    }

    // Other Constructor
    public Customer(int table_no) {
        this.table_no = table_no;
        phoneNumber = "";
        cart = new Cart(table_no);
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

    // Function for adding items to cart #29
    public void addToCart(int item_id, int qty) {
        cart.addToCart(item_id, qty, cart.getTransaction_id());
    }

    // Function for remove items from cart #30
    public void editCart(int order_id, int qty) {
        cart.editCart(order_id, qty);
    }

    // Function for search for items from menu #31
    public void searchMenuItems(String category) {

    }

    // Function for view cart items #32
    public String [][] viewCart() {
        return cart.viewCart();
    }

    // Function for delete cart items #33
    public void deleteCartItem(int order_id) {
        cart.deleteFromCart(order_id);
    }

    // Function for making epayment #34
    public void ePayment() {

    }
}