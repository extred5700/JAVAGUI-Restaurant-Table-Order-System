package Main.controller.RestaurantManager;

import Main.entity.Discount;
import Main.entity.Menu_Items;

public class ManagerSearchController {
    Menu_Items menuItems = new Menu_Items();
    Discount discount = new Discount();

    // Search by Menu Item Name
   public String [][] searchByMenuItemName(String searchText){
       return menuItems.searchMenuItems(searchText);
   }

   // Search by Coupon Name
    public String [][] searchByCouponName(String searchText){
       return discount.searchCoupon(searchText);
    }
}