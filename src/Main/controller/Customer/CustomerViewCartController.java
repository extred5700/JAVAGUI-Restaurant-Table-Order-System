package Main.controller.Customer;

import Main.entity.Cart;

public class CustomerViewCartController {
    // view customer cart
    public String [][] viewCart(int table_no) {
        return new Cart(table_no).viewCart();
    }
}
