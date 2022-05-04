package Main.controller.Customer;

import Main.entity.Customer;

public class ViewCartController {
    public String [][] viewCart(Customer customer) {
        return customer.viewCart();
    }
}
