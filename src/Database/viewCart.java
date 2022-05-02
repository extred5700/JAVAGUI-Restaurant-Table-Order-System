import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class viewCart {
    public static Connection dbConnection(){
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

    public static void main(String[] args) {
        int tNo = 1; //transaction_id to pass in to method
        ArrayList<ArrayList<String>> listCartData = new ArrayList<>(); //Arraylist to return
        ArrayList<String> arrayListOrderid = new ArrayList<>(); //Arraylist of orderids
        ArrayList<String> arrayListName = new ArrayList<>(); //Arraylist of item names
        ArrayList<String> arrayListQty = new ArrayList<>(); //Arraylist of quantity
        ArrayList<String> arrayListPrice = new ArrayList<>(); //Arraylist of price of row
        ArrayList<String> arrayListFufilled = new ArrayList<>(); //Arraylist of fufilled or not

        Connection dbConnection = dbConnection();

        try{
            Statement statement = dbConnection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery("SELECT order_id, name, qty, price, fufilled FROM order_history INNER JOIN menu_item ON order_history.item_id = menu_item.item_id WHERE transaction_id =" + tNo);

            while (rs.next()) { //This is the result set
                //store strings - conversion done here
                String orderid = Integer.toString(rs.getInt("order_id"));
                String name = rs.getString("name");
                String qty = Integer.toString(rs.getInt("qty"));
                String price = Float.toString(rs.getFloat("price"));
                String fufilled = rs.getString("fufilled");
                //add to arrays
                arrayListOrderid.add(orderid);
                arrayListName.add(name);
                arrayListQty.add(qty);
                arrayListPrice.add(price);
                arrayListFufilled.add(fufilled);
            }
        } catch (Exception e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        //construct 2d array
        listCartData.add(arrayListOrderid);
        listCartData.add(arrayListName);
        listCartData.add(arrayListQty);
        listCartData.add(arrayListPrice);
        listCartData.add(arrayListFufilled);
        //return 2d array (for now, used println to check)
        System.out.println(listCartData);
    }

}
