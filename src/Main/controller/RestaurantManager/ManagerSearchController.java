package Main.controller.RestaurantManager;

import Main.entity.Restaurant_Manager;

public class ManagerSearchController {
    Restaurant_Manager restaurant_manager = new Restaurant_Manager();

    // Search by Menu Item Name
   public String [][] searchByMenuItemName(String searchText){
       return restaurant_manager.searchMenuItems(searchText);
   }

   // Search by Coupon Name
    public String [][] searchByCouponName(String searchText){
       return restaurant_manager.searchCoupon(searchText);
    }
}