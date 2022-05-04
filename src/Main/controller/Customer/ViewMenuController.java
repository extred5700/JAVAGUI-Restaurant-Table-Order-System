package Main.controller.Customer;

import Main.entity.Customer;

public class ViewMenuController {
    public String [][] viewMenu(Customer customer, String category){
        return customer.viewMenu(category);
    }
}
