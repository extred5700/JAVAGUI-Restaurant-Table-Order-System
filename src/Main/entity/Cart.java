package Main.entity;

public class Cart {

    // Get TransactionID from Table Number
    public int getTransactionID(int table_num){
        int transaction_id = 0;

        return transaction_id;
    }

    // Get Cart items using the transaction ID
    public String [][] viewCart(int transaction_id){
        String [][] cartItems = {{"1"}};

        return cartItems;
    }

    // Add items to the cart, if successful, return true
    public boolean addToCart (int item_id, int qty, int transaction_id){
        boolean isItemAdded = false;

        return isItemAdded;
    }

    // Edit items from the cart, if successful, return true
    public boolean editCart (int order_id, int qty){
        boolean isItemEdited = false;

        return isItemEdited;
    }

    // Delete items from the cart, if successful, return true
    public boolean deleteFromCaret(int order_id){
        boolean isItemDeleted = false;

        return isItemDeleted;
    }

}
