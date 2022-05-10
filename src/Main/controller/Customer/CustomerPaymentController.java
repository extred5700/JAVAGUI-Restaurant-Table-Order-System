package Main.controller.Customer;

import Main.entity.Customer;

public class CustomerPaymentController {
    // execute payment method
    public boolean payment(Customer customer, String pNum) {
        return customer.ePayment(pNum);
    }

    // validate phone number
    public boolean validatePhoneNumber(Customer customer, String pNum) {
        return customer.getCart().validatePhoneNumber(pNum);
    }

    // get total price
    public float getTotalPrice(Customer customer) {
        return customer.getCart().getTotalPrice();
    }

    // check for coupon
    public boolean discountCoupon(Customer customer, String coupon) {
        return customer.inputDiscount(coupon);
    }
}
