package Main.controller.RestaurantManager;

import Main.entity.Discount;
import Main.entity.Menu_Items;

public class ManagerCreateController {
    Menu_Items menuItems = new Menu_Items();
    Discount myDiscount = new Discount();

    // Check whether Menu Item is in the DB, if it is, return false
    // else, create the Menu Item and return True
    public boolean validateCreateFoodItem(String food_name, Float item_price, String category){
        // If Menu Item already exist
        if (menuItems.checkMenuItemExistence(food_name)){
            return false; // return false as Menu Item exist, does not validate creation of Menu Item
        }
        else{
            return menuItems.createMenuItems(food_name, item_price, category);
        }
    }

    public boolean validateCreateCoupon(String coupon, Float discount){
        // If coupon already exist
        if (myDiscount.checkCouponExistence(coupon)){
            return false; // return false as Coupon exist, does not validate creation of Coupon
        }
        else{
            return myDiscount.createCoupon(coupon, discount);
        }
    }

}
