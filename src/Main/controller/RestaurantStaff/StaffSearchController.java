package Main.controller.RestaurantStaff;

import Main.entity.Cart;

public class StaffSearchController {

    public String [][] searchBy(int table_num){
        Cart cart = new Cart(table_num);
        return cart.viewCart();
    }
}
