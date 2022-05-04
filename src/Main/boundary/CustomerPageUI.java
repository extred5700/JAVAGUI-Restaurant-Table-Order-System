package Main.boundary;

import Main.controller.Customer.AddToCartController;
import Main.controller.Customer.ViewCartController;
import Main.controller.Customer.ViewMenuController;
import Main.entity.Customer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerPageUI extends JFrame {
    /* Variable declaration */
    private int table_no; // capture table number
    private Customer customer; // Create Customer object

    private final JFrame customerUIFrame = new JFrame("Customer Homepage");
    // Buttons
    private final JButton buttonMenu = new JButton("View Menu");
    private final JButton buttonEdit = new JButton("Edit Cart");
    private final JButton buttonView = new JButton("View Cart");
    private final JButton buttonPayment = new JButton("Payment");

    private TitledBorder titledBorder;

    // Displaying Menu
    private final JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // Radio buttons
    private final JButton buttonPasta = new JButton("Pasta");
    private final JButton buttonPizza = new JButton("Pizza");
    private final JButton buttonBakedRice = new JButton("Baked Rice");
    //    JLabel labelCategory = new JLabel("Categories", JLabel.CENTER);
    private JTable tableMenuItems;
    private final JLabel labelItemId = new JLabel("Item Number: ");
    private final JTextField fieldItemId = new JTextField(20);
    private final JLabel labelItemName = new JLabel("Selected: ");
    private final JTextField fieldItemName = new JTextField(20);
    private final JLabel labelItemQty = new JLabel("Quantity: ");
    private final JTextField fieldItemQty = new JTextField(20);
    private final JLabel labelItemPrice = new JLabel("Total Price: ");
    private final JTextField fieldItemPrice = new JTextField(20);
    private final JButton buttonAddToCart = new JButton("Add To Cart");

    // Panel for Editing Order
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // Panel for Viewing Order
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private JTable tableViewCart;

    // Panel for Payment
    private final JPanel panelPayment = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    public CustomerPageUI(int table_no){
        // initialise variables
        this.table_no = table_no;
        customer = new Customer(table_no);

        customerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerUIFrame.getContentPane().setLayout(new FlowLayout());
        customerUIFrame.setSize(520, 705);
        customerUIFrame.setResizable(false);
        customerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        customerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Table Number
        JLabel labelTableNumber = new JLabel("Table Number " + table_no, JLabel.CENTER);
        labelTableNumber.setPreferredSize(new Dimension(500, 30));
        labelTableNumber.setBorder(new LineBorder(Color.BLACK));
        labelTableNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        customerUIFrame.add(labelTableNumber);

        // Add buttons & functions
        constructTopButtons();

        // Display menu
        viewMenu("All");

        // Edit order page
        // editCart();

        // View order page
        viewCart();

        // Payment page
        // payment();

        customerUIFrame.setVisible(true);
    }

    // Method to display buttons for Customer
    public void constructTopButtons(){
        JButton [] topButtons = {buttonMenu, buttonEdit, buttonView, buttonPayment};
        for (JButton jButton : topButtons){
            jButton.setPreferredSize(new Dimension(120, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            customerUIFrame.add(jButton);
        }
    }

    // Category buttons - only display in menu page
    public void constructCategoryButtons() {
        /*labelCategory.setPreferredSize(new Dimension(400, 30));
        labelCategory.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        customerUIFrame.add(labelCategory);*/

        JButton [] categoryButtons = {buttonPasta, buttonPizza, buttonBakedRice};
        for (JButton jButton : categoryButtons){
            jButton.setPreferredSize(new Dimension(120, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(categoryButtonsListener);
            panelMenu.add(jButton);
        }
    }

    // end of actionPerformed
    // Button Listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch(action){
            case "View Menu" -> {
                viewMenu("All");
                panelMenu.setVisible(true);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelPayment.setVisible(false);
            }
            case "Edit Cart" -> {
                panelMenu.setVisible(false);
                panelEdit.setVisible(true);
                panelView.setVisible(false);
                panelPayment.setVisible(false);
            }
            case "View Cart" -> {
                panelMenu.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(true);
                panelPayment.setVisible(false);
            }
            case "Payment" -> {
                panelMenu.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelPayment.setVisible(true);
            }
        } // end of switch statements
    };

    // Button Listener
    ActionListener categoryButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch(action){
            case "Pasta" -> {
                viewMenu("Pasta");
//                refreshMenuTable("Pasta");
            }
            case "Pizza" -> {
                viewMenu("Pizza");
//                refreshMenuTable("Pizza");
            }
            case "Baked Rice" -> {
                viewMenu("Baked Rice");
//                refreshMenuTable("Baked Rice");
            }
        } // end of switch statements
    };

    // Set up DB Connection
    protected Connection dbConnection(){
        String dbUsername = "root";
        String dbPassword = "hopium314_";
        Connection dbConnection = null;
        try{
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csit314", dbUsername, dbPassword);
        } catch (SQLException e){
            // Catches any SQL query issues
            e.printStackTrace();
        }
        return dbConnection;
    }

    // Start of Menu Page
    // View Menu
    public void viewMenu(String category) {
        // Border
        titledBorder = new TitledBorder("Menu/Add to Cart");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // construct the category buttons to allow customer to easily look through menu items
        constructCategoryButtons();

        // Refresh the menu table - after every click/choosing of category
        refreshMenuTable(category);

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) menuTableConstruction(category);

        // Add components to the JPanel
        panelMenu.setPreferredSize(new Dimension(500, 590));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.add(scrollPane);
        panelMenu.setBorder(titledBorder);
        panelMenu.setVisible(true);

        // Allow users to add items to cart while browsing menu
        constructAddingToCartUI();
        customerUIFrame.add(panelMenu);
    }

    // Construct table to display menu items
    public Component menuTableConstruction(String category){
        ViewMenuController viewMenuController = new ViewMenuController();
        String [][] data = viewMenuController.viewMenu(customer, category);
        // Display data in a table format
        String [] columnTableNames = {"Item_Number","Name", "Price"};
        tableMenuItems = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableMenuItems);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    public void refreshMenuTable(String category){
        //Get the components in the panel
        Component[] componentList = panelMenu.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelMenu.remove(c);
            }
        }
    }

    // Construct the UI in the Menu page for Customers to add menu items to cart
    public void constructAddingToCartUI() {
        // Create mouse click listener for menu items
        mouseClickListenerViewMenu();

        // Labels
        labelItemId.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        labelItemName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        labelItemQty.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        labelItemPrice.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));

        // Text Fields
        fieldItemId.setPreferredSize(new Dimension(50, 30));
        // Ensure user only can type in numbers/integers
        fieldItemId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        fieldItemName.setPreferredSize(new Dimension(50, 30));
        fieldItemName.setEditable(false); // name cannot be edited by Customer

        fieldItemQty.setPreferredSize(new Dimension(50, 30));
        // Ensure user only can type in numbers/integers
        fieldItemQty.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        fieldItemPrice.setPreferredSize(new Dimension(50, 30));
        fieldItemPrice.setEditable(false); // name cannot be edited by Customer

        // Add To Cart Button
        buttonAddToCart.setPreferredSize(new Dimension(150, 30));
        buttonAddToCart.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonAddToCart.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonAddToCart.setBackground(Color.WHITE);
        buttonAddToCart.addActionListener(e -> {
            // Call Controller
            AddToCartController addToCartController = new AddToCartController();
            String item_id = fieldItemId.getText();
            String qty = fieldItemQty.getText();
            // Check if text field are left empty by the user
            if (item_id.isEmpty() || qty.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Ensure that quantity entered is not 0
            else if (Integer.parseInt(qty) == 0) {
                JOptionPane.showMessageDialog(null, "Quantity cannot be 0.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Check whether item_id is out of bounds
            else if (!addToCartController.validateItemID(customer, Integer.parseInt(item_id))){
                JOptionPane.showMessageDialog(null, "Invalid Item Number.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else {
                addToCartController.addToCart(customer, Integer.parseInt(item_id), Integer.parseInt(qty));
            }
        });

        panelMenu.add(labelItemId);
        panelMenu.add(fieldItemId);
        panelMenu.add(labelItemName);
        panelMenu.add(fieldItemName);
        panelMenu.add(labelItemQty);
        panelMenu.add(fieldItemQty);
        panelMenu.add(labelItemPrice);
        panelMenu.add(fieldItemPrice);
        panelMenu.add(buttonAddToCart);
    } // End of Menu Page

    // Create a mouse click listener to allow Customers to click on the menu item
    public void mouseClickListenerViewMenu() {
        // Table Mouse Listener
        tableMenuItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableMenuItems.getSelectedRow();
                // Display details on the text fields
                fieldItemId.setText(tableMenuItems.getModel().getValueAt(getRow, 0).toString());
                fieldItemName.setText(tableMenuItems.getModel().getValueAt(getRow, 1).toString());
                fieldItemPrice.setText(tableMenuItems.getModel().getValueAt(getRow, 2).toString());
                fieldItemQty.setText("1"); // set default quantity value
            }
        });
    }

/*    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        int unitPrice = Integer.parseInt(tableMenuItems.getModel().getValueAt(tableMenuItems.getSelectedRow(), 2).toString());

        if (source == fieldItemQty) {
            fieldItemPrice.setText(String.valueOf((Integer.parseInt(fieldItemQty.getText()) * unitPrice)));
        }
    }*/

    public void editCart() {
        // Border
        titledBorder = new TitledBorder("Edit Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));




        panelEdit.setPreferredSize(new Dimension(500, 490));
        panelEdit.setBackground(Color.WHITE);
//        panelEdit.add(scrollPane);
        panelEdit.setBorder(titledBorder);
        panelEdit.setVisible(false);
        customerUIFrame.add(panelEdit);
    }

    // view cart
    public void viewCart() {
        // Border
        titledBorder = new TitledBorder("View Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) cartTableConstruction();

        customerUIFrame.remove(panelView);
        panelView.setPreferredSize(new Dimension(500, 490));
        panelView.setBackground(Color.WHITE);
        panelView.add(scrollPane);
        panelView.setBorder(titledBorder);
        panelView.setVisible(false);
        customerUIFrame.add(panelView);
    }

    // Construct table to display Customer cart
    public Component cartTableConstruction(){
        ViewCartController viewCartController = new ViewCartController();
        String [][] data = viewCartController.viewCart(customer);
        // Display data in a table format
        String [] columnTableNames = {"Item Number","Name", "Quantity", "Price"};
        tableViewCart = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableViewCart);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // make payment
    public void payment() {
        // Border
        titledBorder = new TitledBorder("Payment");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));




        panelPayment.setPreferredSize(new Dimension(500, 490));
        panelPayment.setBackground(Color.WHITE);
//        panelPayment.add(scrollPane);
        panelPayment.setBorder(titledBorder);
        panelPayment.setVisible(false);
        customerUIFrame.add(panelPayment);
    }
}