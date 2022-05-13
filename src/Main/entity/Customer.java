package Main.entity;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Customer {
    // Variable Declaration
    /*private int table_no;
    private String phoneNumber;
    Cart cart;*/


    // Default Constructor
    /*public Customer() {
        this.table_no = 0;
        this.phoneNumber = "";
        this.cart = new Cart();
    }*/

    // Other Constructor
    /*public Customer(int table_no) {
        this.table_no = table_no;
        this.phoneNumber = "";
        cart = new Cart(table_no);
    }*/

    // Set up DB Connection
    /*private Connection dbConnection() {
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
    } // end of method dbConnection()*/

    // Get cart
    /*public Cart getCart() {
        return cart;
    }*/

    // Function for viewing menu #28 AND search for items based on categories #31
    /*public String[][] viewMenu(String category) {
        String[][] menuData = new Menu_Items().viewMenu(category);
        return menuData;
    }*/

    // Function for adding items to cart #29
    /*public void addToCart(int item_id, int qty) {
        cart.addToCart(item_id, qty);
    }*/

    // Function for remove items from cart #30
    /*public void editCart(int order_id, int qty) {
        cart.editCart(order_id, qty);
    }*/

    // Function for view cart items #32
    /*public String [][] viewCart() {
        String[][] cartData = cart.viewCart();
        return cartData;
    }*/

    // Function for delete cart items #33
    /*public void deleteCartItem(int order_id) {
        cart.deleteFromCart(order_id);
    }*/

    // Function for making e-payment #34
    /*public boolean ePayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        boolean paymentStatus = cart.makePayment(phoneNumber);
        return paymentStatus;
    }*/

    // Function for applying coupon code
    /*public boolean inputDiscount(String couponCode) {
        boolean discount = cart.applyDiscount(couponCode);
        return discount;
    }*/
}