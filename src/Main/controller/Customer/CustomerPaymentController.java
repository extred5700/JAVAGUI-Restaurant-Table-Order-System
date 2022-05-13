package Main.controller.Customer;

import Main.entity.Cart;

public class CustomerPaymentController {
    // execute payment method
    public boolean payment(int table_no, String pNum) {
        return new Cart(table_no).makePayment(pNum);
    }

    // validate phone number
    public boolean validatePhoneNumber(int table_no, String pNum) {
        return new Cart(table_no).validatePhoneNumber(pNum);
    }

    // get total price
    public float getTotalPrice(int table_no) {
        return new Cart(table_no).getTotalPrice();
    }

    // check for coupon
    public boolean discountCoupon(int table_no, String coupon) {
        return new Cart(table_no).applyDiscount(coupon);
    }
}
