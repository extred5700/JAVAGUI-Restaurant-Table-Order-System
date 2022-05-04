package Main.controller.Customer;

import Main.entity.Customer;

public class AddToCartController {
/*    // Variable Declaration
    private int table_no, transaction_id;
    Customer customer = new Customer(table_no, transaction_id);*/

/*    public AddToCartController(int table_no, int transaction_id) {
        this.table_no = table_no;
        this.transaction_id = transaction_id;
    }*/

    // Function to validate whether item_id entered by the Customer is valid
    public boolean validateItemID(Customer customer, int item_id) {
        return customer.validateItemID(item_id);
    }

    public void addToCart(Customer customer, int item_id, int qty) {
        customer.addToCart(customer.getTransaction_id(), item_id, qty);
    }
}
