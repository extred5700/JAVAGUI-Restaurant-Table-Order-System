package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffViewController {
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public String [][] displayOrders(){
        return restaurant_staff.viewAllOrders();
    }
}
