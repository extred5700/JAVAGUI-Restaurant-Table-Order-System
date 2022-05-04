package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffEditController{
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public boolean editCustomerOrder(int orderIDSelected, int newQuantity){
        return restaurant_staff.editOrder(orderIDSelected, newQuantity);
    }
}
