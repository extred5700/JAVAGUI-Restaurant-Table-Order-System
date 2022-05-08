package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffDeleteController {
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public String [][] displayAllOrders(){
        return restaurant_staff.viewAllOrders();
    }

    public boolean deleteByOrderID(int order_id){
        return restaurant_staff.deleteOrder(order_id);
    }
}
