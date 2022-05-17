package Main.controller.RestaurantManager;

import Main.entity.Discount;
import Main.entity.Menu_Items;

public class ManagerViewController {
    Menu_Items menuItems = new Menu_Items();
    Discount discount = new Discount();

    // Display all Menu Items
    public String [][] displayMenuItems(){
        return menuItems.viewMenuItems();
    }

    // Display all Coupons
    public String [][] displayCoupons(){
        return discount.viewCoupons();
    }
}
