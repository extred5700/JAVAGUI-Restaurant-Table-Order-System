package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

public class Restaurant_Manager extends Staff {
    // Variable Declaration
    private Menu_Items menuItems; // not final - depends on how our UML is gonna look like

    // Default Constructor
    public Restaurant_Manager() {
    }

    // Other Constructor
    public Restaurant_Manager(String userID, String password, String staffType, String profile, Menu_Items menuItems) {
        super(userID, password, staffType, profile);
        this.menuItems = menuItems;
    }

    public boolean checkMenuItemExistenece(String food_name, Float item_price, String category){
        boolean menuItemExistence = false;
        // SQL codes here
        // Cna follow User_Admin Entity checkUserExistence()
        return menuItemExistence;
    }

    // Function to Create menu items #15
    public boolean createMenuItems(String food_name, Float item_price, String category) {
        boolean isMenuItemCreated = false;
        // SQL Codes here
        // Need to increment item_id before proceeding with sql queries
        // Can follow User_Admin Entity createAccount()
        return isMenuItemCreated;
    }

    public boolean checkCouponExistence(String coupon, Float discount){
        boolean couponExistence = false;
        // SQL codes here
        // Cna follow User_Admin Entity checkUserExistence()
        return couponExistence;
    }

    public boolean createCoupon(String coupon, Float discount){
        boolean isCouponCreated = false;
        // SQL Codes here
        // Can follow User_Admin Entity createAccount()
        return isCouponCreated;
    }

    // Function to Edit menu items #16
    public void editMenuItems(Menu_Items menuItems) {

    }

    // Function to Search menu items #17
    public void searchMenuItems(Menu_Items menuItems) {

    }

    // Function to View menu items #18
    public void viewMenuItems() {

    }

    // Function to Delete menu items #19
    public void deleteMenuItems(Menu_Items menuItems) {

    }
}
