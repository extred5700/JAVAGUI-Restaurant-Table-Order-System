package Main.controller.Customer;

import Main.entity.Cart;

public class CustomerEditCartController {
    // Edit item quantity in Cart
    public void editQty(int table_no, int order_id, int qty) {
        new Cart(table_no).editCart(order_id, qty);
    }

    // Delete item from Cart
    public void deleteItem(int table_no, int order_id) {
        new Cart(table_no).deleteFromCart(order_id);
    }
}
