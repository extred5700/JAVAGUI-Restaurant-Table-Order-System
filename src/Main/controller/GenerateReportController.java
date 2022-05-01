package Main.controller;

import Main.entity.Staff;

public class GenerateReportController {
    Staff user = new Staff();
    public String [] getReport(String radioButtonSelected){
        return user.getReportUsingSelectedRadioButton(radioButtonSelected);
    }
}
