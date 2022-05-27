package Main.controller.Customer;

import Main.entity.Cart;
import Main.entity.Payment;

public class CustomerPaymentController {
    // execute payment method
    public boolean payment(int table_no, String pNum) {
        return new Payment(new Cart(table_no).getTransaction_id()).makePayment(pNum);
    }

    // validate phone number
    public boolean validatePhoneNumber(int table_no, String pNum) {
        return new Payment().validatePhoneNumber(pNum);
    }

    // get total price
    public float getTotalPrice(int table_no) {
        return new Payment(new Cart(table_no).getTransaction_id()).getTotalPrice();
    }
}
