package Main.controller.RestaurantStaff;

import Main.entity.Cart;

public class StaffViewController {
    Cart cart = new Cart();

    public String [][] displayOrders(){
        return cart.viewAllOrders();
    }
}
