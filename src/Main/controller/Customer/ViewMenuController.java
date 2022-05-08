package Main.controller.Customer;

import Main.entity.Customer;
import Main.entity.Menu_Items;

import java.awt.*;

public class ViewMenuController {
    private Menu_Items menuItems = new Menu_Items();

    public String [][] viewMenu(String category){
        return menuItems.viewMenu(category);
    }
}
