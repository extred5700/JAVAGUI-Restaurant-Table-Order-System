package Main.controller.Customer;

import Main.entity.Customer;

public class CustomerViewCartController {
    // view customer cart
    public String [][] viewCart(Customer customer) {
        return customer.viewCart();
    }
}
