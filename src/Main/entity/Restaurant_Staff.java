package Main.entity;

// Variable and Function Parameters are just a placeholder - May change depending on future designs

public class Restaurant_Staff extends Staff {
    // Variable Declaration
    private Customer customer; // not final - depends on how our UML is gonna look like
    private Order order; // not final - depends on how our UML is gonna look like

    // Default Constructor
    public Restaurant_Staff() {
    }

    // Other Constructor
    public Restaurant_Staff(String userID, String password, String staffType, String profile, Customer customer, Order order) {
        super(userID, password, staffType, profile);
        this.customer = customer;
        this.order = order;
    }

    // Function to Edit order status #22
    public void editOrderStatus(Order order) {

    }

    // Function to Edit customer orders #23
    public void editOrder(Order order) {

    }

    // Function to Search customer orders #24
    public void searchOrder(Order order) {

    }

    // Function to View customer orders #25
    public void viewOrder(Order order) {

    }

    // Function to Delete customer orders #26
    public void deleteOrder(Order order) {

    }
}
