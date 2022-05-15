package Main.controller.RestaurantStaff;

import Main.entity.Cart;

public class StaffDeleteController {
    Cart cart = new Cart();

    public String [][] displayAllOrders(){
        return cart.viewAllOrders();
    }

    public boolean deleteByOrderID(int order_id){
        return cart.deleteFromCart(order_id);
    }
}
