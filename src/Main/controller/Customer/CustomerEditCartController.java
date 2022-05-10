package Main.controller.Customer;

import Main.entity.Customer;

public class CustomerEditCartController {
    // Edit item quantity in Cart
    public void editQty(Customer customer, int order_id, int qty) {
        customer.editCart(order_id, qty);
    }

    // Delete item from Cart
    public void deleteItem(Customer customer, int order_id) {
        customer.deleteCartItem(order_id);
    }
}
