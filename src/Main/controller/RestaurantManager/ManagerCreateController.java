package Main.controller.RestaurantManager;

import Main.entity.Restaurant_Manager;

public class ManagerCreateController {
    Restaurant_Manager restaurant_manager = new Restaurant_Manager();

    public boolean validateCreateFoodItem(String food_name, Float item_price, String category){
        // If Menu Item already exist
        if (restaurant_manager.checkMenuItemExistence(food_name)){
            return false; // return false as Menu Item exist, does not validate creation of Menu Item
        }
        else{
            return restaurant_manager.createMenuItems(food_name, item_price, category);
        }
    }

    public boolean validateCreateCoupon(String coupon, Float discount){
        // If coupon already exist
        if (restaurant_manager.checkCouponExistence(coupon)){
            return false; // return false as Coupon exist, does not validate creation of Coupon
        }
        else{
            return restaurant_manager.createCoupon(coupon, discount);
        }
    }

}
