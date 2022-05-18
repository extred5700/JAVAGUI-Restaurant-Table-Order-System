package Main.controller.RestaurantOwner;

import Main.entity.Cart;
import Main.entity.Payment;

public class GenerateReportController {
    Payment payment = new Payment();
    Cart cart = new Cart();

    public String [][] getReport(String radioButtonSelected){
        String [][] data = {
                {"", "", ""}
        }; // Temp values inside 2D array, leave it as empty strings
        switch (radioButtonSelected) {
            case "Average Spend", "Frequency of Visits" -> data = payment.generateReport(radioButtonSelected);
            case "Food Preference" -> data = cart.generatePreferenceReport();
        }
        return data;
    }
}