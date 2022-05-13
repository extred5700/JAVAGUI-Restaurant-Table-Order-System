package Main.controller.RestaurantOwner;

import Main.entity.Restaurant_Owner;
import Main.entity.Staff;

public class OwnerGenerateController {
    Restaurant_Owner owner = new Restaurant_Owner();

    public String [][] getReport(String radioButtonSelected){
        return owner.getReport(radioButtonSelected);
    }
}
