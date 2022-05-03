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
    public void editOrderStatus(Order order) {

    }

    // Function to Edit customer orders #23
    public void editOrder(Order order) {

    }

    // Function to Search customer orders #24
    public void searchOrder(Order order) {

    }

    // Function to View customer orders #25
    public ArrayList<ArrayList<String>> viewOrders() {
        ArrayList<ArrayList<String>> combinedOrder = new ArrayList<>();
        ArrayList<String> arrayListOrderID = new ArrayList<>();
        ArrayList<String> arrayListFoodName = new ArrayList<>();
        ArrayList<String> arrayListQuantity = new ArrayList<>();
        ArrayList<String> arrayListPrice = new ArrayList<>();
        ArrayList<String> arrayListFulfill = new ArrayList<>();

        Connection dbConnection = dbConnection(); // Set up connection with the DB
        String query = "SELECT order_id, name, qty, price, fufilled FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id;";
        try (Statement statement = dbConnection.createStatement()){
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                // Add data to their respective category, then... (line 134)
                String order_id = set.getString("order_id");
                String food_name = set.getString("name");
                String quantity = set.getString("qty");
                String price = set.getString("price");
                String fulfillStatus = set.getString("fufilled");
                arrayListOrderID.add(order_id);
                arrayListFoodName.add(food_name);
                arrayListQuantity.add(quantity);
                arrayListPrice.add(price);
                arrayListFulfill.add(fulfillStatus);
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        combinedOrder.add(arrayListOrderID);
        combinedOrder.add(arrayListFoodName);
        combinedOrder.add(arrayListQuantity);
        combinedOrder.add(arrayListPrice);
        combinedOrder.add(arrayListFulfill);
        return combinedOrder;
    }

    // Function to Delete customer orders #26
    public void deleteOrder(Order order) {

    }
}
