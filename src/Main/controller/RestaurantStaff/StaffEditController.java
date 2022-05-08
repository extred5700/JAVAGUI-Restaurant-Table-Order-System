package Main.controller.RestaurantStaff;

import Main.entity.Restaurant_Staff;

public class StaffEditController{
    Restaurant_Staff restaurant_staff = new Restaurant_Staff();

    public String [][] displayOrders(){
        return restaurant_staff.viewAllOrders();
    }

    // EDIT order QUANTITY
    public boolean editCustomerOrder(int orderIDSelected, int newQuantity){
        return restaurant_staff.editOrder(orderIDSelected, newQuantity);
    }

    // FULFILLMENT or orders
    public boolean fulfillOrder(int orderIDSelected){
        return restaurant_staff.editOrderStatus(orderIDSelected);
    }
}
