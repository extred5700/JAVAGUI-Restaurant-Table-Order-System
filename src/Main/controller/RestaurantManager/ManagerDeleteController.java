package Main.controller.RestaurantManager;

import Main.entity.Discount;
import Main.entity.Menu_Items;

public class ManagerDeleteController {
    Menu_Items menuItems = new Menu_Items();
    Discount discount = new Discount();

    // Display all Menu Items
    public String [][] displayMenuItems(){
        return menuItems.viewMenuItems();
    }

    // Delete Menu Item by passing in the Item ID to the Restaurant Manager Entity class
    public boolean deleteItem(int itemID){
        return menuItems.deleteMenuItem(itemID);
    }

    // Display all Coupons
    public String [][] displayCoupons(){
        return discount.viewCoupons();
    }

    // Delete Coupon by passing in the coupon name to the Restaurant Manager Entity class
    public boolean deleteCouponByName(String coupon){
        return discount.deleteCoupon(coupon);
    }

}
