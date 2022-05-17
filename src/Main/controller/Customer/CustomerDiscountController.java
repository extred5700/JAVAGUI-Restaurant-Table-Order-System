package Main.controller.Customer;

import Main.entity.Cart;
import Main.entity.Discount;

public class CustomerDiscountController {
    // check for coupon
    public boolean discountCoupon(int table_no, String coupon) {
        return new Discount(new Cart(table_no).getTransaction_id()).applyDiscount(coupon);
    }
}
