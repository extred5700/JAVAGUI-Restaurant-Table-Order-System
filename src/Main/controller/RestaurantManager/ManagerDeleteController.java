package Main.controller.RestaurantManager;

import Main.entity.Restaurant_Manager;

public class ManagerDeleteController {
    Restaurant_Manager restaurant_manager = new Restaurant_Manager();

    // Display all Menu Items
    public String [][] displayMenuItems(){
        return restaurant_manager.viewMenuItems();
    }

    // Display all Coupons
    public String [][] displayCoupons(){
        return restaurant_manager.viewCoupons();
    }

    // Delete Menu Item by passing in the Item ID to the Restaurant Manager Entity class
    public boolean deleteItem(int itemID){
        return restaurant_manager.deleteMenuItem(itemID);
    }

    // Delete Coupon by passing in the coupon name to the Restaurant Manager Entity class
    public boolean deleteCouponByName(String coupon){
        return restaurant_manager.deleteCoupon(coupon);
    }

}
