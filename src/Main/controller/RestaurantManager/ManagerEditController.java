package Main.controller.RestaurantManager;

import Main.entity.Restaurant_Manager;

public class ManagerEditController {
    Restaurant_Manager restaurant_manager = new Restaurant_Manager();

    // Display all Menu Items
    public String [][] displayMenuItems(){
        return restaurant_manager.viewMenuItems();
    }

    // Display all Coupons
    public String [][] displayCoupons(){
        return restaurant_manager.viewCoupons();
    }

    // Edit Menu Items
    public boolean editItem(int item_id, String newFoodName, Float new_item_price){
        return restaurant_manager.editMenuItems(item_id, newFoodName, new_item_price);
    }

    // Edit Coupon
    public boolean editCouponValues(String oldCouponName, String newCouponName, Float new_discount_value){
        return restaurant_manager.editCoupon(oldCouponName, newCouponName, new_discount_value);
    }
}
