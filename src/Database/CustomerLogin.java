import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CustomerLogin {


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
        Connection dbConnection = dbConnection(); // Set up connection with the DB
        //table number of customer (to be passed in)
        int tableNo = 4;
        //transaction number (default is 0, will be updated below)
        int transactionNo = 0;
        while (transactionNo <1) {
            //loops while transactionNo is 0
            System.out.println("transactionNo is currently " + transactionNo); //print out current transaction No - remove once you understand the loop
            try {
                //check for existence of a cart
                Statement statement = dbConnection.createStatement();
                //Query for a trasaction/cart
                ResultSet rs = statement.executeQuery(
                        "SELECT transaction_id FROM transaction_history WHERE table_no ='"
                                + tableNo +
                                "' AND paid = 'N'"
                );
                if (rs.next() == false) {
                    //This means no unpaid transaction found for this table number
                    System.out.println("No open transaction/cart");
                    //Create a row in transaction_history
                    String query = "INSERT into transaction_history (transaction_id, table_no, paid )" + " values (null, ?, 'N') ";
                    PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                    preparedStatement.setInt(1, tableNo);
                    preparedStatement.execute();
                    System.out.println("created a new row in transaction_history");
                    // transactionNo is still 0, will loop again
                } else {
                    //This means tranasaction/cart found
                    System.out.println("transaction/cart found");
                    transactionNo = rs.getInt("transaction_id"); //update transactionNo, will break loop
                    System.out.println("transactionNo updated");
                }

            } catch (Exception e) {
                //Catches any SQl query issues
                e.printStackTrace();
            }
        }
        System.out.println("transactionNo is now " + transactionNo); //out of loop transaction number - to be stored in customer class
        }

    }


