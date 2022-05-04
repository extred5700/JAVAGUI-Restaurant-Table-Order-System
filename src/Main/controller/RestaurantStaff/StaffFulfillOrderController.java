package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffFulfillOrderController {
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public boolean fulfillOrder(int orderIDSelected){
        return restaurant_staff.editOrderStatus(orderIDSelected);
    }
}
