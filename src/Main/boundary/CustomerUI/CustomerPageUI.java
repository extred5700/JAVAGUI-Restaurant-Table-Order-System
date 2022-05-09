package Main.boundary.CustomerUI;

import Main.controller.Customer.*;
import Main.entity.Customer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerPageUI extends JFrame {
    /* Variable declaration */
    private Customer customer; // Create Customer object

    // JFrame
    private final JFrame customerUIFrame = new JFrame("Customer Homepage");

    // Buttons
    private final JButton buttonMenuPage = new JButton("View Menu");
    private final JButton buttonEditPage = new JButton("Edit Cart");
    private final JButton buttonViewPage = new JButton("View Cart");
    private final JButton buttonPaymentPage = new JButton("Payment");

    // Title Border
    private TitledBorder titledBorder;

    // Panel for Displaying Menu
    private final JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // Category Buttons
    private final JButton buttonPasta = new JButton("Pasta");
    private final JButton buttonPizza = new JButton("Pizza");
    private final JButton buttonBakedRice = new JButton("Baked Rice");
    private final JButton buttonDrinks = new JButton("Drinks");

    // Table to display menu items
    private JTable tableMenuItems;

    // Labels, TextFields, and Button
    private final JLabel labelItemId = new JLabel("Item Number: ");
    private final JTextField fieldItemId = new JTextField(20);
    private final JLabel labelItemName = new JLabel("Selected:        ");
    private final JTextField fieldItemName = new JTextField(20);
    private final JLabel labelItemQty = new JLabel("Quantity:        ");
    private final JTextField fieldItemQty = new JTextField(20);
    private final JLabel labelItemPrice = new JLabel("Total Price:    ");
    private final JTextField fieldItemPrice = new JTextField(20);
    private final JButton buttonAddToCart = new JButton("Add To Cart");

    // Panel for Editing Cart
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // Labels, TextFields, and Button
    private final JLabel labelEditOrderId = new JLabel("Item Number: ");
    private final JTextField fieldEditOrderId = new JTextField(20);
    private final JLabel labelEditItemName = new JLabel("Selected:        ");
    private final JTextField fieldEditItemName = new JTextField(20);
    private final JLabel labelEditItemQty = new JLabel("Quantity:        ");
    private final JTextField fieldEditItemQty = new JTextField(20);
    private final JLabel labelEditItemPrice = new JLabel("Total Price:    ");
    private final JTextField fieldEditItemPrice = new JTextField(20);
    private final JButton buttonUpdateCart = new JButton("Update Cart");
    private final JButton buttonDeleteItem = new JButton("Delete Item");

    // Panel for Viewing Cart
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private JTable tableViewCart;

    // Panel for Payment
    private final JPanel panelPayment = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // Labels, TextFields, and Button
    private final JLabel labelPaymentPhoneNumber = new JLabel("Phone Number:");
    private final JTextField fieldPaymentPhoneNumber = new JTextField(20);
    private final JLabel labelPaymentTotalPrice = new JLabel("Total Price:");
    private final JTextField fieldPaymentTotalPrice = new JTextField(20);
    private final JButton buttonPayment = new JButton("Payment");

    public CustomerPageUI(int table_no){
        // initialise variables
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
        editCart();

        // View order page
        viewCart();

        // Payment page
        payment();

        customerUIFrame.setVisible(true);
    }

    // Method to display buttons for Customer
    private void constructTopButtons(){
        JButton [] topButtons = {buttonMenuPage, buttonEditPage, buttonViewPage, buttonPaymentPage,};
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
    private void constructCategoryButtons() {
        JButton [] categoryButtons = {buttonPasta, buttonPizza, buttonBakedRice, buttonDrinks};
        for (JButton jButton : categoryButtons){
            jButton.setPreferredSize(new Dimension(90, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(categoryButtonsListener);
            panelMenu.add(jButton);
        }
    }
    // end of actionPerformed

    // Button Listener
    private ActionListener topButtonsListener = e -> {
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
                editCart();
                panelMenu.setVisible(false);
                panelEdit.setVisible(true);
                panelView.setVisible(false);
                panelPayment.setVisible(false);
            }
            case "View Cart" -> {
                viewCart();
                panelMenu.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(true);
                panelPayment.setVisible(false);
            }
            case "Payment" -> {
                payment();
                panelMenu.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelPayment.setVisible(true);
            }
        } // end of switch statements
    };

    // Button Listener - for category buttons in the Menu page
    ActionListener categoryButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch(action){
            case "Pasta" -> {
                viewMenu("Pasta");
            }
            case "Pizza" -> {
                viewMenu("Pizza");
            }
            case "Baked Rice" -> {
                viewMenu("Baked Rice");
            }
            case "Drinks" -> {
                viewMenu("Drinks");
            }
        } // end of switch statements
    };

    // Set up DB Connection
    private Connection dbConnection(){
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

    // View Menu
    public void viewMenu(String category) {
        // Border
        titledBorder = new TitledBorder("Menu/Add to Cart");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // construct the category buttons to allow customer to easily look through menu items
        constructCategoryButtons();

        // Refresh the menu table - after every click/choosing of category
        refreshTable(panelMenu);

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) menuTableConstruction(category);

        // Add components to the panelMenu
        panelMenu.setPreferredSize(new Dimension(500, 590));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.add(scrollPane);
        panelMenu.setBorder(titledBorder);
        panelMenu.setVisible(true);
        // Allow users to add items to cart while browsing menu
        addToCartComponents();
        // Add mouse click listener for selecting menu items
        menuMouseClickListener();

        customerUIFrame.add(panelMenu);
    }

    // Construct table to display menu items
    private Component menuTableConstruction(String category){
        ViewMenuController viewMenuController = new ViewMenuController();
        String [][] data = viewMenuController.viewMenu(category);
        // Display data in a table format
        String [] columnTableNames = {"Item_Number","Name", "Price"};
        tableMenuItems = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableMenuItems);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // Refresh the display table
    private void refreshTable(JPanel jPanel){
        // Get the components in the panel
        Component[] componentList = jPanel.getComponents();
        // Loop through the components
        for(Component c : componentList){
            // Find the components you want to remove
            if(c instanceof JScrollPane){
                // Remove it
                jPanel.remove(c);
            }
        }
    }

    // Construct the Components for Customers to add menu items to cart
    public void addToCartComponents() {
        // Labels
        labelItemId.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelItemName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelItemQty.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelItemPrice.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        // Text Fields
        fieldItemId.setPreferredSize(new Dimension(50, 30));
        fieldItemId.setEditable(false);
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
        fieldItemPrice.setEditable(false); // Price cannot be edited by Customer

        // Add To Cart Button
        buttonAddToCart.setPreferredSize(new Dimension(150, 30));
        buttonAddToCart.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonAddToCart.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonAddToCart.setBackground(Color.WHITE);

        // Create Action Listener
        if (buttonAddToCart.getActionListeners().length == 0) {
            buttonAddToCart.addActionListener(e -> {
                // Create Controller Object
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
                else {
                    addToCartController.addToCart(customer, Integer.parseInt(item_id), Integer.parseInt(qty));
                    JOptionPane.showMessageDialog(null, "Item has been added to your cart!", "Success!", JOptionPane.WARNING_MESSAGE);
                    fieldItemId.setText("");
                    fieldItemName.setText("");
                    fieldItemPrice.setText("");
                    fieldItemQty.setText("");
                }
            });
        }

        // Add Components to panelMenu
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

    // Create mouse click listener for menu items
    private void menuMouseClickListener(){
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

    public void editCart() {
        // Border
        titledBorder = new TitledBorder("Edit Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // create Components
        editCartComponents();

        fieldEditOrderId.setText("");
        fieldEditItemName.setText("");
        fieldEditItemQty.setText("");
        fieldEditItemPrice.setText("");

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) cartTableConstruction();

        // Update Cart Table
        refreshTable(panelEdit);

        // mouse listener for cart items
        cartMouseClickListener();

        // Add Components to JPanel
        panelEdit.setPreferredSize(new Dimension(500, 490));
        panelEdit.setBackground(Color.WHITE);
        panelEdit.add(scrollPane);
        panelEdit.setBorder(titledBorder);
        panelEdit.setVisible(false);
        panelEdit.add(labelEditItemName);
        panelEdit.add(fieldEditItemName);
        panelEdit.add(labelEditItemQty);
        panelEdit.add(fieldEditItemQty);
        panelEdit.add(labelEditItemPrice);
        panelEdit.add(fieldEditItemPrice);
        panelEdit.add(buttonDeleteItem);
        panelEdit.add(buttonUpdateCart);

        customerUIFrame.add(panelEdit);
    }

    // View cart
    public void viewCart() {
        // Border
        titledBorder = new TitledBorder("View Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) cartTableConstruction();

        // Update Cart Table
        refreshTable(panelView);

        // Add Components to JPanel
        panelView.setPreferredSize(new Dimension(500, 350));
        panelView.setBackground(Color.WHITE);
        panelView.add(scrollPane);
        panelView.setBorder(titledBorder);
        panelView.setVisible(false);

        customerUIFrame.add(panelView);
    }

    private void editCartComponents() {
        // Labels
        labelEditOrderId.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelEditItemName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelEditItemQty.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelEditItemPrice.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        // Text Fields
        fieldEditOrderId.setPreferredSize(new Dimension(50, 30));
        fieldEditOrderId.setEditable(false); // item_id cannot be edited by Customer

        fieldEditItemName.setPreferredSize(new Dimension(50, 30));
        fieldEditItemName.setEditable(false); // Name cannot be edited by Customer

        fieldEditItemQty.setPreferredSize(new Dimension(50, 30));
        // Ensure user only can type in numbers/integers
        fieldEditItemQty.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        fieldEditItemPrice.setPreferredSize(new Dimension(50, 30));
        fieldEditItemPrice.setEditable(false); // Price cannot be edited by Customer

        // Update Cart and Delete Item Buttons
        JButton [] topButtons = {buttonUpdateCart, buttonDeleteItem};
        for (JButton jButton : topButtons){
            jButton.setPreferredSize(new Dimension(150, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
        }

        // Create Action Listener for Update Cart Button
        if (buttonUpdateCart.getActionListeners().length == 0) {
            buttonUpdateCart.addActionListener(e -> {
                // Call Controller
                EditCartController editCartController = new EditCartController();
                String order_id = fieldEditOrderId.getText(); // get order_id
                String qty = fieldEditItemQty.getText(); // get quantity
                // Check if text field are left empty by the user
                if (order_id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have not selected an item!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else if (qty.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have not entered a quantity!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                // Ensure that quantity entered is not 0
                else if (Integer.parseInt(qty) < 0) {
                    JOptionPane.showMessageDialog(null, "Quantity cannot be less than 0!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if (Integer.parseInt(qty) == 0) {
                        editCartController.deleteItem(customer, Integer.parseInt(order_id));
                    }
                    else {
                        editCartController.editQty(customer, Integer.parseInt(order_id), Integer.parseInt(qty));
                    }
                    JOptionPane.showMessageDialog(null, "Your Cart has been Updated!", "Success!", JOptionPane.WARNING_MESSAGE);
                    // Refresh the cart items
                    editCart();
                    panelEdit.setVisible(true);
                }
            });
        }

        // Create Action Listener for Delete Item Button
        if (buttonDeleteItem.getActionListeners().length == 0) {
            buttonDeleteItem.addActionListener(e -> {
                // Call Controller
                EditCartController editCartController = new EditCartController();
                String order_id = fieldEditOrderId.getText(); // get order_id
                // Check if customer has selected an item to delete
                if (order_id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You have not selected an item!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    editCartController.deleteItem(customer, Integer.parseInt(order_id));
                    JOptionPane.showMessageDialog(null, "Item has been deleted from your cart!", "Success!", JOptionPane.WARNING_MESSAGE);
                    // Refresh the cart items
                    editCart();
                    panelEdit.setVisible(true);
                }
            });
        }
    }

    // Construct table to display Customer cart
    public Component cartTableConstruction(){
        ViewCartController viewCartController = new ViewCartController();
        String [][] data = viewCartController.viewCart(customer);
        // Display data in a table format
        String [] columnTableNames = {"Order_Id","Name", "Quantity", "Price"};
        tableViewCart = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableViewCart);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // Create mouse click listener for cart items
    private void cartMouseClickListener(){
        tableViewCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableViewCart.getSelectedRow();
                // Display details on the text fields
                fieldEditOrderId.setText(tableViewCart.getModel().getValueAt(getRow, 0).toString());
                fieldEditItemName.setText(tableViewCart.getModel().getValueAt(getRow, 1).toString());
                fieldEditItemQty.setText(tableViewCart.getModel().getValueAt(getRow, 2).toString());
                fieldEditItemPrice.setText(tableViewCart.getModel().getValueAt(getRow, 3).toString());
            }
        });
    }

    // make payment
    public void payment() {
        // Border
        titledBorder = new TitledBorder("Payment");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) cartTableConstruction();

        // Update Cart Table
        refreshTable(panelPayment);

        paymentComponents();


        // Add Components to JPanel
        panelPayment.setPreferredSize(new Dimension(500, 490));
        panelPayment.setBackground(Color.WHITE);
        panelPayment.add(scrollPane);
        panelPayment.setBorder(titledBorder);
        panelPayment.setVisible(false);
        panelPayment.add(labelPaymentPhoneNumber);
        panelPayment.add(fieldPaymentPhoneNumber);
        panelPayment.add(labelPaymentTotalPrice);
        panelPayment.add(fieldPaymentTotalPrice);
        panelPayment.add(buttonPayment);
        customerUIFrame.add(panelPayment);
    }

    private void paymentComponents() {
        // Labels
        labelPaymentPhoneNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelPaymentTotalPrice.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        // Text Fields
        fieldPaymentPhoneNumber.setPreferredSize(new Dimension(50, 30));
        // Ensure user only can type in numbers/integers
        fieldPaymentPhoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        fieldPaymentTotalPrice.setPreferredSize(new Dimension(50, 30));
        fieldPaymentTotalPrice.setEditable(false); // Total Price cannot be edited by Customer

        // Call Controller
        PaymentController paymentController = new PaymentController();

        fieldPaymentTotalPrice.setText(String.valueOf(paymentController.getTotalPrice(customer)));

        // Payment Button
        buttonPayment.setPreferredSize(new Dimension(150, 30));
        buttonPayment.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonPayment.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonPayment.setBackground(Color.WHITE);

        // Create Action Listener
        if (buttonPayment.getActionListeners().length == 0) {
            buttonPayment.addActionListener(e -> {
                String pNum = fieldPaymentPhoneNumber.getText(); // get phone number

                if (pNum.isEmpty()) { // Check if text field is left empty by the user
                    JOptionPane.showMessageDialog(null, "You have not entered your phone number!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                // Validate phone number
                else if (!paymentController.validatePhoneNumber(customer, pNum)) { // validate phone number method returns false - invalid phone number
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if (paymentController.payment(customer, pNum)) { // payment method returns true - payment successful
                        JOptionPane.showMessageDialog(null, "Payment successful!", "Success!", JOptionPane.WARNING_MESSAGE);
                    }
                    else { // payment method returns false - payment unsuccessful
                        JOptionPane.showMessageDialog(null, "Payment unsuccessful. Please try again.", "Error!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
    }
}