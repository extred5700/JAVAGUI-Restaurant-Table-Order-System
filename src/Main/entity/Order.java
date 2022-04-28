package Main.entity;

// This is just a draft for the Order class - May change depending on our UML design in the future

public class Order {
    // Variable Declaration
    Menu_Items menuItems;
    Customer customer;
    static int instanceCounter = 0;
    int orderNumber;

    // Default Constructor
    public Order() {
        // increment instanceCounter
        instanceCounter++;

        // instantiate orderNumber
        orderNumber = instanceCounter;
    }

    // Other Constructor
    public Order(Menu_Items menuItems, Customer customer) {
        this.menuItems = menuItems;
        this.customer = customer;

        // increment instanceCounter
        instanceCounter++;

        // instantiate orderNumber
        orderNumber = instanceCounter;
    }
}
