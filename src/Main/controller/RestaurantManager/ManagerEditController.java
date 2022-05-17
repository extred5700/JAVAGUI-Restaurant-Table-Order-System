package Main.controller.RestaurantManager;

import Main.entity.Discount;
import Main.entity.Menu_Items;

public class ManagerEditController {
    Menu_Items menuItems = new Menu_Items();
    Discount discount = new Discount();

    // Display all Menu Items
    public String [][] displayMenuItems(){
        return menuItems.viewMenuItems();
    }

    // Edit Menu Items
    public boolean editItem(int itemID, String newFoodName, Float new_item_price){
        return menuItems.editMenuItem(itemID, newFoodName, new_item_price);
    }

    // Display all Coupons
    public String [][] displayCoupons(){
        return discount.viewCoupons();
    }

    // Edit Coupon
    public boolean editCouponValues(String oldCouponName, String newCouponName, Float new_coupon_value){
        return discount.editCoupon(oldCouponName, newCouponName, new_coupon_value);
    }

}
