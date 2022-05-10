package Main.controller.Customer;

import Main.entity.Customer;

public class CustomerViewMenuController {
    // allow customer to view menu
    public String [][] viewMenu(Customer customer, String category){
        return customer.viewMenu(category);
    }
}
