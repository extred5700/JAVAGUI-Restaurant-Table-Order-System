package Main.entity;

// This is just a draft for the Order class - May change depending on our UML design in the future

public class Menu_Items {
    // Variable Declaration
    private String name;
    private double price;

    // Default Constructor
    public Menu_Items() {
    }

    // Other Constructor
    public Menu_Items(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
