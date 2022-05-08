package Main.controller.Customer;

import Main.entity.Customer;

public class AddToCartController {
/*    // Function to validate whether item_id entered by the Customer is valid
    public boolean validateItemID(Customer customer, int item_id) {
        return customer.validateItemID(item_id);
    }*/

    // Function to add menu items to cart
    public void addToCart(Customer customer, int item_id, int qty) {
        customer.addToCart(item_id, qty);
    }
}
