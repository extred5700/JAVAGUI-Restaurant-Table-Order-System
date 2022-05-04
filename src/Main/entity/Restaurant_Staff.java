package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Restaurant_Staff extends Staff {
    // Variable Declaration
    private Customer customer; // not final - depends on how our UML is gonna look like
    private Order order; // not final - depends on how our UML is gonna look like

    // Default Constructor
    public Restaurant_Staff() {
    }

    // Other Constructor
    public Restaurant_Staff(String userID, String password, String staffType, String profile, Customer customer, Order order) {
        super(userID, password, staffType, profile);
        this.customer = customer;
        this.order = order;
    }

    // Function to Edit order status #22
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

    // Function to Edit customer orders #23
    public boolean editOrder(int orderIDSelected, int newQuantity) {
        boolean isOrderEdited = false;
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "UPDATE order_history SET qty ='" + newQuantity + "' WHERE order_id='" + orderIDSelected + "'";
        try (Statement statement = dbConnection.createStatement()){
            statement.executeUpdate(query);
            isOrderEdited = true;
        } catch (SQLException e){
            System.out.println(e);
        }

        return isOrderEdited;
    }

    // Function to Search customer orders #24
    public void searchOrder(Order order) {

    }

    // Function to View customer orders #25
    public String [][] viewOrders() {
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
                // Add data to their respective respective array list
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

    // Function to Delete customer orders #26
    public void deleteOrder(Order order) {

    }
}
