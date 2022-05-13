package Main.controller.Customer;

import Main.entity.Cart;
import Main.entity.Customer;

public class CustomerAddToCartController {
    // Function to add to cart
    public void addToCart(int table_no, int item_id, int qty) {
        new Cart(table_no).addToCart(item_id, qty);
    }
}
