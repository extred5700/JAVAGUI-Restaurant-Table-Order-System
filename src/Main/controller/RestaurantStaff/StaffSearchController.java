package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffSearchController {
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public String [][] searchBy(int table_num){
        return restaurant_staff.searchOrder(table_num);
    }
}
