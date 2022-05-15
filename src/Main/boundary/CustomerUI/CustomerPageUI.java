package Main.boundary.CustomerUI;

import Main.boundary.CustomerLoginPage;
import Main.controller.Customer.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerPageUI extends JFrame {
    /* Variable declaration */
    int table_no;

    /* Customer Page JFrame */
    private final JFrame customerUIFrame = new JFrame("Customer Homepage");

    /* Buttons for top of the GUI */
    private final JButton buttonMenu = new JButton("View Menu");
    private final JButton buttonEdit = new JButton("Edit Cart");
    private final JButton buttonView = new JButton("View Cart");
    private final JButton buttonPayment = new JButton("Payment");

    /* Title Border - For all the Pages */
    private TitledBorder titledBorder;

    /* 1. MENU Function */
    // JPanel for Menu
    private final JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // JButtons to allow Customers to click and filter menu based on Categories
    private final JButton buttonPasta = new JButton("Pasta");
    private final JButton buttonPizza = new JButton("Pizza");
    private final JButton buttonBakedRice = new JButton("Baked Rice");
    private final JButton buttonDrinks = new JButton("Drinks");

    // JTable to display menu items
    private JTable tableMenuItems;

    // JLabels
    private final JLabel labelItemId = new JLabel("Item Number: ");
    private final JLabel labelItemName = new JLabel("Selected:        ");
    private final JLabel labelItemQty = new JLabel("Quantity:        ");

    // JTextFields
    private final JTextField fieldItemId = new JTextField(20);
    private final JTextField fieldItemName = new JTextField(20);
    private final JTextField fieldItemQty = new JTextField(20);

    // JButton - Allow user to Add menu items to Cart
    private final JButton buttonAddToCart = new JButton("Add To Cart");

    /* JTable to Display Cart - Used for EDIT/VIEW/PAYMENT Page */
    private JTable tableViewCart;

    /* 2. EDIT Function */
    // JPanel for EDIT
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // JLabels
    private final JLabel labelEditOrderId = new JLabel("Item Number: ");
    private final JLabel labelEditItemName = new JLabel("Selected:        ");
    private final JLabel labelEditItemQty = new JLabel("Quantity:        ");

    // JTextFields
    private final JTextField fieldEditOrderId = new JTextField(20);
    private final JTextField fieldEditItemName = new JTextField(20);
    private final JTextField fieldEditItemQty = new JTextField(20);

    // JButtons - One to Update Cart based on quantity, another one to Delete selected item
    private final JButton buttonUpdateCart = new JButton("Update Cart");
    private final JButton buttonDeleteItem = new JButton("Delete Item");

    /* 3. VIEW Function */
    // JPanel for VIEW
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    /* 4. PAYMENT Function */
    // JPanel for PAYMENT
    private final JPanel panelPayment = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    // JLabels
    private final JLabel labelPaymentPhoneNumber = new JLabel("Phone Number:");
    private final JLabel labelPaymentCoupon = new JLabel("Input Coupon:  ");
    private final JLabel labelPaymentTotalPrice = new JLabel("Total Price:      ");

    // JTextFields
    private final JTextField fieldPaymentPhoneNumber = new JTextField(20);
    private final JTextField fieldPaymentInputCoupon = new JTextField(20);
    private final JTextField fieldPaymentTotalPrice = new JTextField(20);

    // JButtons
    private final JButton buttonInputCoupon = new JButton("Input Coupon");
    private final JButton buttonMakePayment = new JButton("Make Payment");

    // Constructor
    public CustomerPageUI(int table_no){
        // initialise variables
        this.table_no = table_no;

        // set JFrame
        customerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerUIFrame.getContentPane().setLayout(new FlowLayout());
        customerUIFrame.setSize(520, 705);
        customerUIFrame.setResizable(false);
        customerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        customerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Table Number - At the top
        JLabel labelTableNumber = new JLabel("Table Number: " + table_no, JLabel.CENTER);
        labelTableNumber.setPreferredSize(new Dimension(500, 30));
        labelTableNumber.setBorder(new LineBorder(Color.WHITE));
        labelTableNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        customerUIFrame.add(labelTableNumber);

        // Add buttons & functions
        constructTopButtons();

        // MENU Page
        viewMenu("All");

        // EDIT Page
        editCartPanel();

        // VIEW Page
        viewCart();

        // PAYMENT Page
        payment();

        customerUIFrame.setVisible(true);
    }

    // Display Navigation Buttons for Customer
    private void constructTopButtons(){
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

    // Button Listener for Navigation Buttons
    private final ActionListener topButtonsListener = e -> {
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
                editCartPanel();
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

    /* 1. MENU function
     * 1a) void viewMenu(String category) - Display JPanel for Customers to View Menu based on Category (DEFAULT "ALL")
     * 1b) void constructCategoryButtons() - Display Category Buttons for Customer ONLY in the MENU Page
     * 1c) void categoryButton_Onclick(JButton jButton) - ActionListener for Category Buttons
     * 1d) Component menuTableConstruction(String category) - Construction of the JTable to display Menu Items based on Category(DEFAULT "ALL") - JTable type returned as a JScrollPane type
     * 1e) void addToCartComponents() - Construct the Components for Customers to add menu items to cart in the MENU Page
     * 1f) void addToCartButton_Onclick() - ActionListener for the addToCart Button
     */
    // Display JPanel for Customers to View Menu based on Category (DEFAULT "ALL")
    private void viewMenu(String category) {
        // Border
        titledBorder = new TitledBorder("Menu/Add To Cart");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // construct the category buttons to allow customer to easily look through menu items
        constructCategoryButtons();

        /* Refresh the menu table - after every click/choosing of category */
        // Get the components in the panel
        Component[] componentList = panelMenu.getComponents();

        // Loop through the components
        for(Component c : componentList){
            // Find the components to remove
            if(c instanceof JScrollPane){
                panelMenu.remove(c); // Remove
            }
        }

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

        customerUIFrame.add(panelMenu);
    }

    // Display Category Buttons for Customer ONLY in the MENU Page
    private void constructCategoryButtons() {
        JButton [] categoryButtons = {buttonPasta, buttonPizza, buttonBakedRice, buttonDrinks};
        for (JButton jButton : categoryButtons){
            jButton.setPreferredSize(new Dimension(90, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            jButton.setBackground(Color.WHITE);
            // Add ActionListener for Category Buttons
            if (jButton.getActionListeners().length == 0) {
                jButton.addActionListener(e -> categoryButton_Onclick(jButton));
            }
            panelMenu.add(jButton);
        }
    }

    // Button Listener - for Category Buttons in the MENU Page
    private void categoryButton_Onclick(JButton jButton) {
        String action = jButton.getText();
        switch(action){
            case "Pasta" -> viewMenu("Pasta");
            case "Pizza" -> viewMenu("Pizza");
            case "Baked Rice" -> viewMenu("Baked Rice");
            case "Drinks" -> viewMenu("Drinks");
        }
    }

    // Construct table to display menu items
    private Component menuTableConstruction(String category) {
        CustomerViewMenuController viewMenuController = new CustomerViewMenuController();
        String [][] data = viewMenuController.viewMenu(category);
        // Display data in a table format
        String [] columnTableNames = {"Item ID","Food Name", "Price"};
        tableMenuItems = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableMenuItems);
        // Mouse click listener for menu items
        tableMenuItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableMenuItems.getSelectedRow();
                // Display details on the text fields
                fieldItemId.setText(tableMenuItems.getModel().getValueAt(getRow, 0).toString());
                fieldItemName.setText(tableMenuItems.getModel().getValueAt(getRow, 1).toString());
                fieldItemQty.setText("1"); // set default quantity value
            }
        });
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // Construct the Components for Customers to add menu items to cart
    private void addToCartComponents() {
        /* Labels */
        labelItemId.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelItemName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelItemQty.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        /* Text Fields */
        // fieldItemId
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
        // fieldItemName
        fieldItemName.setPreferredSize(new Dimension(50, 30));
        fieldItemName.setEditable(false); // name cannot be edited by Customer
        // fieldItemQty
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

        /* Add To Cart Button */
        buttonAddToCart.setPreferredSize(new Dimension(150, 30));
        buttonAddToCart.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonAddToCart.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonAddToCart.setBackground(Color.WHITE);

        // Create ActionListener
        if (buttonAddToCart.getActionListeners().length == 0) {
            buttonAddToCart.addActionListener(e -> addToCartButton_Onclick());
        }

        // Add Components to panelMenu
        panelMenu.add(labelItemId);
        panelMenu.add(fieldItemId);
        panelMenu.add(labelItemName);
        panelMenu.add(fieldItemName);
        panelMenu.add(labelItemQty);
        panelMenu.add(fieldItemQty);
        panelMenu.add(buttonAddToCart);
    }

    // ActionListener - addToCart Button
    private void addToCartButton_Onclick() {
        // Create Controller Object
        CustomerAddToCartController addToCartController = new CustomerAddToCartController();

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
            addToCartController.addToCart(table_no, Integer.parseInt(item_id), Integer.parseInt(qty));
            JOptionPane.showMessageDialog(null, "Item has been added to your cart!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            fieldItemId.setText("");
            fieldItemName.setText("");
            fieldItemQty.setText("");
        }
    }

    /* 2. EDIT function
     * 2a) void editCartPanel() - Display JPanel for Customers to EDIT their Cart
     * 2b) void editCartComponents() - Construct the Components for Customers to EDIT their Cart
     * 2c) void updateCartButton_Onclick() - Create Action Listener for Update Cart Button
     * 2d) void deleteItemButton_Onclick() - Create Action Listener for Delete Item Button
     * 2e) Component editCartTableConstruction() - Construction of the JTable to display Customer Cart - JTable type returned as a JScrollPane type
     */
    // Display JPanel for Customers to Edit their Cart
    private void editCartPanel() {
        // Border
        titledBorder = new TitledBorder("Edit Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // create Components
        editCartComponents();

        fieldEditOrderId.setText("");
        fieldEditItemName.setText("");
        fieldEditItemQty.setText("");

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) editCartTableConstruction();

        /* Update Cart Table */
        // Get the components in the panel
        Component[] componentList = panelEdit.getComponents();

        // Loop through the components
        for(Component c : componentList){
            // Find the components to remove
            if(c instanceof JScrollPane){
                panelEdit.remove(c); // Remove
            }
        }

        // Add Components to JPanel
        panelEdit.setPreferredSize(new Dimension(500, 450));
        panelEdit.setBackground(Color.WHITE);
        panelEdit.add(scrollPane);
        panelEdit.setBorder(titledBorder);
        panelEdit.setVisible(false);
        panelEdit.add(labelEditItemName);
        panelEdit.add(fieldEditItemName);
        panelEdit.add(labelEditItemQty);
        panelEdit.add(fieldEditItemQty);
        panelEdit.add(buttonDeleteItem);
        panelEdit.add(buttonUpdateCart);

        customerUIFrame.add(panelEdit);
    }

    // Construct the Components for Customers to EDIT their Cart
    private void editCartComponents() {
        /* Labels */
        labelEditOrderId.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelEditItemName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelEditItemQty.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        /* Text Fields */
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

        // Update Cart and Delete Item Buttons
        JButton [] topButtons = {buttonUpdateCart, buttonDeleteItem};
        for (JButton jButton : topButtons){
            jButton.setPreferredSize(new Dimension(150, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
        }

        // Add ActionListener for update cart button
        if (buttonUpdateCart.getActionListeners().length == 0) {
            buttonUpdateCart.addActionListener(e -> updateCartButton_Onclick());
        }

        // Add ActionListener for delete item button
        if (buttonDeleteItem.getActionListeners().length == 0) {
            buttonDeleteItem.addActionListener(e -> deleteItemButton_Onclick());
        }
    }

    // Create Action Listener for Update Cart Button
    private void updateCartButton_Onclick() {
        // Call Controller
        CustomerEditCartController editCartController = new CustomerEditCartController();
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
                editCartController.deleteItem(table_no, Integer.parseInt(order_id));
            }
            else {
                editCartController.editQty(table_no, Integer.parseInt(order_id), Integer.parseInt(qty));
            }
            JOptionPane.showMessageDialog(null, "Your Cart has been Updated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            // Refresh the cart items
            editCartPanel();
            panelEdit.setVisible(true);
        }
    }

    // Create Action Listener for Delete Item Button
    private void deleteItemButton_Onclick() {
        // Call Controller
        CustomerEditCartController editCartController = new CustomerEditCartController();
        String order_id = fieldEditOrderId.getText(); // get order_id
        // Check if customer has selected an item to delete
        if (order_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have not selected an item!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else {
            editCartController.deleteItem(table_no, Integer.parseInt(order_id));
            JOptionPane.showMessageDialog(null, "Item has been deleted from your cart!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            // Refresh the cart items
            editCartPanel();
            panelEdit.setVisible(true);
        }
    }

    // Construct table for EDIT Page
    private Component editCartTableConstruction() {
        CustomerViewCartController viewCartController = new CustomerViewCartController();
        String [][] data = viewCartController.viewCart(table_no);
        // Display data in a table format
        String [] columnTableNames = {"Order ID","Food Name", "Quantity", "Price"};
        tableViewCart = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableViewCart);
        // Create mouse click listener for cart items
        tableViewCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableViewCart.getSelectedRow();
                // Display details on the text fields
                fieldEditOrderId.setText(tableViewCart.getModel().getValueAt(getRow, 0).toString());
                fieldEditItemName.setText(tableViewCart.getModel().getValueAt(getRow, 1).toString());
                fieldEditItemQty.setText(tableViewCart.getModel().getValueAt(getRow, 2).toString());
            }
        });
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    /* 3. VIEW function
     * 3a) void viewCart() - Display JPanel for Customers to VIEW their Cart
     * 3b) Component viewCartTableConstruction() - Construction of the JTable to display Customer Cart - JTable type returned as a JScrollPane type
     */
    // Display JPanel for Customers to VIEW their Cart
    private void viewCart() {
        // Border
        titledBorder = new TitledBorder("View Your Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) viewCartTableConstruction();

        /* Update Cart Table */
        // Get the components in the panel
        Component[] componentList = panelView.getComponents();

        // Loop through the components
        for(Component c : componentList){
            // Find the components to remove
            if(c instanceof JScrollPane){
                panelView.remove(c); // Remove
            }
        }

        // Add Components to JPanel
        panelView.setPreferredSize(new Dimension(500, 350));
        panelView.setBackground(Color.WHITE);
        panelView.add(scrollPane);
        panelView.setBorder(titledBorder);
        panelView.setVisible(false);

        customerUIFrame.add(panelView);
    }

    // Construct table for VIEW Page
    private Component viewCartTableConstruction() {
        CustomerViewCartController viewCartController = new CustomerViewCartController();
        String [][] data = viewCartController.viewCart(table_no);
        // Display data in a table format
        String [] columnTableNames = {"Order ID","Food Name", "Quantity", "Price"};
        tableViewCart = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableViewCart);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    /* 4. PAYMENT function
     * 4a) void payment() - Display JPanel for Customers to Make Payment
     * 4b) Component paymentCartTableConstruction() - Construction of the JTable to display Customer Cart - JTable type returned as a JScrollPane type
     * 4c) void paymentComponents() - Construct the Components for Customers to make PAYMENT
     * 4d) void inputCouponButton_Onclick() - Create Action Listener for Input Coupon Button
     * 4e) void makePaymentButton_Onclick(CustomerPaymentController paymentController) - Create Action Listener for Make Payment Button
     */
    // Display JPanel for Customers to make PAYMENT
    private void payment() {
        // Border
        titledBorder = new TitledBorder("Payment");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) paymentCartTableConstruction();

        /* Update Cart Table */
        // Get the components in the panel
        Component[] componentList = panelPayment.getComponents();

        // Loop through the components
        for(Component c : componentList){
            // Find the components to remove
            if(c instanceof JScrollPane){
                panelPayment.remove(c); // Remove
            }
        }

        paymentComponents();

        // Add Components to JPanel
        panelPayment.setPreferredSize(new Dimension(500, 490));
        panelPayment.setBackground(Color.WHITE);
        panelPayment.add(scrollPane);
        panelPayment.setBorder(titledBorder);
        panelPayment.setVisible(false);
        panelPayment.add(labelPaymentPhoneNumber);
        panelPayment.add(fieldPaymentPhoneNumber);
        panelPayment.add(labelPaymentCoupon);
        panelPayment.add(fieldPaymentInputCoupon);
        panelPayment.add(labelPaymentTotalPrice);
        panelPayment.add(fieldPaymentTotalPrice);
        panelPayment.add(buttonInputCoupon);
        panelPayment.add(buttonMakePayment);
        customerUIFrame.add(panelPayment);
    }

    // Construct table for PAYMENT Page
    private Component paymentCartTableConstruction() {
        CustomerViewCartController viewCartController = new CustomerViewCartController();
        String [][] data = viewCartController.viewCart(table_no);
        // Display data in a table format
        String [] columnTableNames = {"Order ID","Food Name", "Quantity", "Price"};
        tableViewCart = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableViewCart);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // Construct the Components for Customers to make PAYMENT
    private void paymentComponents() {
        /* Labels */
        labelPaymentPhoneNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelPaymentTotalPrice.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));
        labelPaymentCoupon.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 22));

        /* Text Fields */
        // fieldPaymentPhoneNumber
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
        // fieldPaymentTotalPrice
        fieldPaymentTotalPrice.setPreferredSize(new Dimension(50, 30));
        fieldPaymentTotalPrice.setEditable(false); // Total Price cannot be edited by Customer
        // fieldPaymentCoupon
        fieldPaymentInputCoupon.setPreferredSize(new Dimension(50, 30));

        // Call Controller
        CustomerPaymentController paymentController = new CustomerPaymentController();

        // set total price field
        float totalPrice = Float.parseFloat(String.valueOf(paymentController.getTotalPrice(table_no)));
        fieldPaymentTotalPrice.setText(String.valueOf(totalPrice));
        /*fieldPaymentTotalPrice.setText(String.valueOf(paymentController.getTotalPrice(table_no)));*/

        /* Buttons */
        // Input Coupon and Make Payment Buttons
        JButton [] topButtons = {buttonInputCoupon, buttonMakePayment};
        for (JButton jButton : topButtons) {
            jButton.setPreferredSize(new Dimension(150, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
        }

        // Create Action Listener for Coupon Button
        if (buttonInputCoupon.getActionListeners().length == 0) {
            buttonInputCoupon.addActionListener(e -> inputCouponButton_Onclick(paymentController));
        }

        // Create Action Listener for Make Payment Button
        if (buttonMakePayment.getActionListeners().length == 0) {
            buttonMakePayment.addActionListener(e -> makePaymentButton_Onclick(paymentController));
        }
    }

    // ActionListener for Input Coupon Button
    private void inputCouponButton_Onclick(CustomerPaymentController paymentController) {
        String coupon = fieldPaymentInputCoupon.getText(); // get phone number

        if (coupon.isEmpty()) { // Check if text field is left empty by the user
            JOptionPane.showMessageDialog(null, "You have not entered a coupon!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        // Validate coupon
        else {
            if (!paymentController.discountCoupon(table_no, coupon)) { // validate coupon method returns false - invalid coupon
                JOptionPane.showMessageDialog(null, "Coupon is invalid!", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else { // validate coupon method return true - valid coupon - apply discount
                JOptionPane.showMessageDialog(null, "Coupon is valid!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                // update total price field
                fieldPaymentTotalPrice.setText(String.valueOf(paymentController.getTotalPrice(table_no)));
            }
        }
    }

    // ActionListener for Make Payment Button
    private void makePaymentButton_Onclick(CustomerPaymentController paymentController) {
        String pNum = fieldPaymentPhoneNumber.getText(); // get phone number

        if (pNum.isEmpty()) { // Check if text field is left empty by the user
            JOptionPane.showMessageDialog(null, "You have not entered your phone number!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        // Validate phone number
        else if (!paymentController.validatePhoneNumber(table_no, pNum)) { // validate phone number method returns false - invalid phone number
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else {
            if (paymentController.payment(table_no, pNum)) { // payment method returns true - payment successful
                JOptionPane.showMessageDialog(null, "Payment successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                // Go back to CustomerLoginPage
                dispose();
                customerUIFrame.setVisible(false);
                new CustomerLoginPage();
            }
            else { // payment method returns false - payment unsuccessful
                JOptionPane.showMessageDialog(null, "Payment unsuccessful. Please try again.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}