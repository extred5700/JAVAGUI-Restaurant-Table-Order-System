package Main.controller.Customer;

import Main.entity.Customer;

public class CustomerAddToCartController {
    // Function to add to cart
    public void addToCart(Customer customer, int item_id, int qty) {
        customer.addToCart(item_id, qty);
    }
}
