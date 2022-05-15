package Main.controller.RestaurantStaff;

import Main.entity.Cart;

public class StaffEditController{
    Cart cart = new Cart();

    public String [][] displayOrders(){
        return cart.viewAllOrders();
    }

    // EDIT order QUANTITY
    public boolean editCustomerOrder(int orderIDSelected, int newQuantity){
        return cart.editCart(orderIDSelected, newQuantity);
    }

    // FULFILLMENT of orders
    public boolean fulfillOrder(int orderIDSelected){
        return cart.editOrderStatus(orderIDSelected);
    }
}
