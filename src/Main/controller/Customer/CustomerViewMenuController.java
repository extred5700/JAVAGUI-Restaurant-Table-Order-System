package Main.controller.Customer;

import Main.entity.Menu_Items;

import java.awt.*;

public class CustomerViewMenuController {
    // allow customer to view menu
    public String [][] viewMenu(String category){
        return new Menu_Items().viewMenu(category);
    }
}
