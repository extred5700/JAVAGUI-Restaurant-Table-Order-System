package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantManager.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class ManagerPageUI extends JFrame {
    /* Variable declaration */
    private final JFrame managerUIFrame = new JFrame("Restaurant Manager Homepage");
    // Buttons
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonSearch = new JButton("Search");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonDelete = new JButton("Delete");

    /* 1. CREATE function */
    private final JPanel panelCreate = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Display Buttons
    private final JButton buttonDisplayCreateMenuItem = new JButton("Menu Items");
    private final JButton buttonDisplayCreateCoupon = new JButton("Coupon");
    /* 1a) Create Menu Items */
    // Labels
    private final JLabel labelCreateFoodName = new JLabel("Food Name: ", SwingConstants.CENTER);
    private final JLabel labelCreateFoodPrice = new JLabel("Price: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldCreateFoodName = new JTextField(25);
    private final JTextField fieldCreateFoodPrice = new JTextField(25);
    // Radio Buttons
    private final JRadioButton radioCreatePasta = new JRadioButton("Pasta");
    private final JRadioButton radioCreatePizza = new JRadioButton("Pizza");
    private final JRadioButton radioCreateBakedRice = new JRadioButton("Baked Rice");
    private final JRadioButton radioCreateDrinks = new JRadioButton("Drinks");
    private final ButtonGroup buttonGroupCreate = new ButtonGroup();
    // Button
    private final JButton buttonCreateMenuItem = new JButton("Create Menu Item");
    /* 1b) Create Coupons */
    // Labels
    private final JLabel labelCreateCouponName = new JLabel("Coupon Name: ", SwingConstants.CENTER);
    private final JLabel labelCreateCouponDiscount = new JLabel("Discount: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldCreateCouponName = new JTextField(25);
    private final JTextField fieldCreateCouponDiscount = new JTextField(25);
    // Button
    private final JButton buttonCreateCoupon = new JButton("Create Coupon");

    /* 2. EDIT function */
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Display Buttons
    private final JButton buttonDisplayEditMenuItem = new JButton("Menu Items");
    private final JButton buttonDisplayEditCoupon = new JButton("Coupon");
    /* 2a) Menu Items */
    // Table
    private JTable tableEditMenuItems;
    // Labels
    private final JLabel labelEditItemID = new JLabel("Item ID: ", SwingConstants.CENTER);
    private final JLabel labelEditFoodName = new JLabel("Food Name: ", SwingConstants.CENTER);
    private final JLabel labelEditPrice = new JLabel("Price: ", SwingConstants.CENTER);
    private final JLabel labelEditCategory = new JLabel("Category: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldEditItemID = new JTextField(25);
    private final JTextField fieldEditFoodName = new JTextField(25);
    private final JTextField fieldEditFoodPrice = new JTextField(25);
    private final JTextField fieldEditFoodCategory = new JTextField(25);
    // Button
    private final JButton buttonEditMenuItem = new JButton("Edit Menu Item");
    /* 2b) Coupons */
    // Table
    private JTable tableEditCoupons;
    // Labels
    private final JLabel labelEditCouponName = new JLabel("Coupon Name: ", SwingConstants.CENTER);
    private final JLabel labelEditCouponDiscountValue = new JLabel("Discount Value: ", SwingConstants.CENTER);
    // Text Field
    private final JTextField fieldEditCouponName = new JTextField(25);
    private final JTextField fieldEditCouponDiscountValue = new JTextField(25);
    // Button
    private final JButton buttonEditCoupon = new JButton("Edit Coupon");

    /* 3. SEARCH function */
    private final JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JTextField fieldSearch = new JTextField(25); // Common text field for search
    /* 3a) Menu Items */
    private final JButton buttonSearchMenuItem = new JButton("Search by Menu Item Name");
    private JTable tableSearchMenuItems;
    /* 3b) Coupon */
    private final JButton buttonSearchCoupon = new JButton("Search by Coupon Name");
    private JTable tableSearchCoupons;

    /* 4. VIEW function */
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    /* 4a) Menu Items */
    private final JButton buttonViewMenuItem = new JButton("View All Menu Items");
    private JTable tableViewMenuItems;
    /* 4b) Coupons */
    private final JButton buttonViewCoupon = new JButton("View All Coupons");
    private JTable tableViewCoupons;

    /* 5. DELETE function */
    private final JPanel panelDelete = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Buttons
    private final JButton buttonDisplayDeleteMenuItem = new JButton("Menu Items");
    private final JButton buttonDisplayDeleteCoupon = new JButton("Coupon");
    /* 5a) Menu Item */
    private final JButton buttonDeleteMenuItem = new JButton("Delete Menu Item");
    private JTable tableDeleteMenuItems;
    /* 5b) Coupon */
    private final JButton buttonDeleteCoupon = new JButton("Delete Coupon");
    private JTable tableDeleteCoupon;

    public ManagerPageUI(){
        managerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerUIFrame.getContentPane().setLayout(new FlowLayout());
        managerUIFrame.setSize(520, 705);
        managerUIFrame.setResizable(false);
        managerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        managerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Add buttons & functions for the top of the GUI
        displayManagerUserButtons();

        /* Button Function for Restaurant Manager */
        // 1. Create menu items or coupons
        displayCreatePanel();
        // 2. Edit menu items or coupons
        displayEditPanel();
        // 3. Search menu items or coupons
        displaySearchPanel();
        // 4. View menu items or coupons
        displayViewPanel();
        // 5. Delete menu items or coupons
        displayDeletePanel();

        managerUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
    * void displayManagerUserButtons() - Method to display the buttons on top of the screen
    * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
    * displayTitledBorder(JPanel panel) - Construction and display of the Titled Border
    */

    // Method to display buttons for Restaurant Manager
    public void displayManagerUserButtons(){
        JButton [] myArray = {buttonLogout, buttonCreate, buttonEdit, buttonSearch, buttonView, buttonDelete};
        for (JButton jButton : myArray) {
            jButton.setPreferredSize(new Dimension(78, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            managerUIFrame.add(jButton);
        }
    }

    // Top buttons listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action) {
            case "Logout" -> {
                managerUIFrame.dispose();
                managerUIFrame.setVisible(false);
                new StaffLoginPage();
            }
            case "Create" -> {
                displayCreatePanel();
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                panelView.setVisible(false);
                panelDelete.setVisible(false);
            }
            case "Edit" -> {
                displayEditPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(true);
                panelSearch.setVisible(false);
                panelView.setVisible(false);
                panelDelete.setVisible(false);
            }
            case "Search" -> {
                displaySearchPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelSearch.setVisible(true);
                panelView.setVisible(false);
                panelDelete.setVisible(false);
            }
            case "View" -> {
                displayViewPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                panelView.setVisible(true);
                panelDelete.setVisible(false);
            }
            case "Delete" -> {
                displayDeletePanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                panelView.setVisible(false);
                panelDelete.setVisible(true);
            }
        }
    };

    // Method to construct and display of the Titled Border
    public void displayTitledBorder(JPanel panel, String nameOfPanel){
        TitledBorder titledBorder = new TitledBorder(nameOfPanel);
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        panel.setBorder(titledBorder);
    }


    /* 1. CREATE function
    * 1a) void displayCreatePanel() - Method to display JPanel for Restaurant Manager to create menu items or coupons
    * 1b) void buttonCreateChoices_Onclick(String buttonPressed) - Allows the user to choose between creation of Menu Items OR coupon
    * 1c) void buttonCreateMenuItem_Onclick() - Create Menu Item button function to allow the user to create a Menu Item by passing the data to the controller
    * 1d) void buttonCreateCoupon_Onclick() - Create Coupon button function to allow the user to create a Menu Item by passing the data to the controller
    */
    // 1a) Method to display JPanel for Restaurant Manager to create menu items and coupons
    public void displayCreatePanel(){
        displayTitledBorder(panelCreate, "Create Menu Items/Coupons");

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplayCreateMenuItem, buttonDisplayCreateCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelCreate.add(buttonDisplayCreateMenuItem, 0);
        panelCreate.add(buttonDisplayCreateCoupon, 1);
        // Action listener for displaying creation UI of menu items, also ensuring that the action listener is only created once
        if (buttonDisplayCreateMenuItem.getActionListeners().length == 0){
            buttonDisplayCreateMenuItem.addActionListener(e -> buttonCreateChoices_Onclick("Menu Items"));
        }
        // Action listener for displaying creation UI of coupons, also ensuring that the action listener is only created once
        if (buttonDisplayCreateCoupon.getActionListeners().length == 0){
            buttonDisplayCreateCoupon.addActionListener(e -> buttonCreateChoices_Onclick("Coupon"));
        }

        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(500, 430));
        panelCreate.setBackground(Color.WHITE);
        panelCreate.setVisible(false);
        managerUIFrame.add(panelCreate);
    }

    // 1b) Allows the user to choose between creation of Menu Items OR coupon, then display the respective GUI components
    public void buttonCreateChoices_Onclick(String buttonPressed){
        /* REMOVE COMPONENTS */
        Component[] componentList = panelCreate.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JRadioButton){
                //Remove it
                panelCreate.remove(c);
            }
        }
        panelCreate.add(buttonDisplayCreateMenuItem, 0);
        panelCreate.add(buttonDisplayCreateCoupon, 1);
        panelCreate.revalidate();
        panelCreate.repaint();

        // Display relevant components based on the button PRESSED
        switch (buttonPressed) {
            case "Menu Items" -> {
                // Disable pressed button
                buttonDisplayCreateMenuItem.setEnabled(false);
                buttonDisplayCreateCoupon.setEnabled(true);

                // Labels
                JLabel[] arrayCreateFoodItemLabels = {labelCreateFoodName, labelCreateFoodPrice};
                for (JLabel jLabel : arrayCreateFoodItemLabels) {
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] arrayCreateFoodItemsTextFields = {fieldCreateFoodName, fieldCreateFoodPrice};
                for (JTextField jTextField : arrayCreateFoodItemsTextFields) {
                    jTextField.setPreferredSize(new Dimension(50, 30));
                }

                // Add labels and text fields to the JPanel
                panelCreate.add(labelCreateFoodName);
                panelCreate.add(fieldCreateFoodName);
                panelCreate.add(labelCreateFoodPrice);
                panelCreate.add(fieldCreateFoodPrice);

                // Radio Buttons
                JRadioButton[] tempRadioButtons = {radioCreatePasta, radioCreatePizza, radioCreateBakedRice, radioCreateDrinks};
                String[] actionCommandForMenuItems = {"Pasta", "Pizza", "Baked Rice", "Drinks"};
                for (int i = 0; i < tempRadioButtons.length; i++) {
                    tempRadioButtons[i].setBackground(Color.WHITE);
                    tempRadioButtons[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                    tempRadioButtons[i].setPreferredSize(new Dimension(240, 15));
                    tempRadioButtons[i].setActionCommand(actionCommandForMenuItems[i]);
                    buttonGroupCreate.add(tempRadioButtons[i]);
                    panelCreate.add(tempRadioButtons[i]);
                }
                tempRadioButtons[0].setSelected(true);

                // Button
                buttonCreateMenuItem.setPreferredSize(new Dimension(250, 30));
                buttonCreateMenuItem.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonCreateMenuItem.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonCreateMenuItem.setBackground(Color.WHITE);
                // Action listener for creation of menu items, also ensuring that the action listener is only created once
                if (buttonCreateMenuItem.getActionListeners().length == 0){
                    buttonCreateMenuItem.addActionListener(e -> buttonCreateMenuItem_Onclick());
                }
                panelCreate.add(buttonCreateMenuItem);
            }
            case "Coupon" -> {
                // Disable pressed button
                buttonDisplayCreateCoupon.setEnabled(false);
                buttonDisplayCreateMenuItem.setEnabled(true);

                // Labels
                JLabel[] labelForCoupon = {labelCreateCouponName, labelCreateCouponDiscount};
                for (JLabel jLabel : labelForCoupon) {
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] textFieldsForCoupon = {fieldCreateCouponName, fieldCreateCouponDiscount};
                for (JTextField jTextField : textFieldsForCoupon) {
                    jTextField.setPreferredSize(new Dimension(50, 30));
                }
                // Add labels and text fields to the JPanel
                panelCreate.add(labelCreateCouponName);
                panelCreate.add(fieldCreateCouponName);
                panelCreate.add(labelCreateCouponDiscount);
                panelCreate.add(fieldCreateCouponDiscount);

                // Button
                buttonCreateCoupon.setPreferredSize(new Dimension(250, 30));
                buttonCreateCoupon.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonCreateCoupon.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonCreateCoupon.setBackground(Color.WHITE);
                // Action listener for creation of coupons, also ensuring that the action listener is only created once
                if (buttonCreateCoupon.getActionListeners().length == 0){
                    buttonCreateCoupon.addActionListener(e -> buttonCreateCoupon_Onclick());
                }
                panelCreate.add(buttonCreateCoupon);
            }
        } // end of switch statements
    }

    // 1c) Create Menu Item button function to allow the user to create a Menu Item by passing the data to the controller
    public void buttonCreateMenuItem_Onclick(){
        if (fieldCreateFoodName.getText().isEmpty() || fieldCreateFoodPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        // If text fields are not left empty
        else{
            Float item_price = 0.00f;
            try {
                item_price = Float.parseFloat(fieldCreateFoodPrice.getText());
                // Convert item price to 2 decimal places
                DecimalFormat df = new DecimalFormat("#.##");
                item_price = Float.parseFloat(df.format(item_price));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the price of the food item.", "Error!", JOptionPane.WARNING_MESSAGE);
            } // end of try-catch statements
            if (item_price <= 0.0){
                JOptionPane.showMessageDialog(null, "Price of the food item cannot be 0 or less.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                // Get Food Name from Text Field and the radio button selected
                String food_name = fieldCreateFoodName.getText().toLowerCase();
                String category = buttonGroupCreate.getSelection().getActionCommand();
                // Pass values to the controller
                ManagerCreateController managerCreateController = new ManagerCreateController();
                if (managerCreateController.validateCreateFoodItem(food_name, item_price, category)){
                    JOptionPane.showMessageDialog(null, "Menu Item is created successfully.", "Menu Item Creation", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Menu Item already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } // end of if-else statements
    }

    // 1d) Create Coupon button function to allow the user to create a Menu Item by passing the data to the controller
    public void buttonCreateCoupon_Onclick(){
        if (fieldCreateCouponName.getText().isEmpty() || fieldCreateCouponDiscount.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // If text fields are not left empty
            Float discount = 0.00f;
            try{
                discount = Float.parseFloat(fieldCreateCouponDiscount.getText());
                // Convert item price to 2 decimal places
                DecimalFormat df = new DecimalFormat("#.##");
                discount = Float.parseFloat(df.format(discount));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the price of the food item.", "Error!", JOptionPane.WARNING_MESSAGE);
            } // end of try-catch statements
            if (discount <= 0 || discount > 1){
                JOptionPane.showMessageDialog(null, "Discount value of the coupon cannot be 0 or less.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String coupon = fieldCreateCouponName.getText().toUpperCase(); // Get Coupon name from Text Field, coupon name in UPPER case
                // Pass values to the controller
                ManagerCreateController managerCreateController = new ManagerCreateController();
                if (managerCreateController.validateCreateCoupon(coupon, discount)){
                    JOptionPane.showMessageDialog(null, "Coupon is created successfully.", "Coupon Creation", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Coupon already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } // end of if-else statements
    }


    /* 2. EDIT function
    * 2a) void displayEditPanel() - Method to display JPanel for Restaurant Manager to edit menu items or coupons
    * 2b) void buttonEditChoices_Onclick(String buttonPressed) - Allows the user to choose between editing of Menu Items OR coupon, then display the respective GUI components
    * Methods 2c and 2d belongs to Editing MENU ITEMS
    * 2c) Component editMenuItemTableConstruction() - Construction of table variables for EDIT MENU ITEM functions
    * 2d) void buttonEditMenuItem_Onclick() - Edit Menu Item button function to allow the user to edit a Menu Item by passing the data to the controller
    * Methods 2e and 2f belongs to Editing COUPONS
    * 2e) Component editCouponTableConstruction() - Construction of table variables for EDIT COUPON functions
    * 2f) void buttonEditCoupon_Onclick() - Edit Coupon button function to allow the user to edit a Menu Item by passing the data to the controller
    * */
    // 2a) Method to display JPanel for Restaurant Manager to edit menu items or coupons
    public void displayEditPanel(){
        displayTitledBorder(panelEdit, "Edit Menu Items/Coupons");

        // Remove all components then add choice buttons on the top
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelEdit.remove(c);
            }
            if (c instanceof JButton){
                c.setEnabled(true);
            }
        }

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplayEditMenuItem, buttonDisplayEditCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelEdit.add(buttonDisplayEditMenuItem, 0);
        panelEdit.add(buttonDisplayEditCoupon, 1);
        // Action listener for displaying editing UI of menu items, also ensuring that the action listener is only created once
        if (buttonDisplayEditMenuItem.getActionListeners().length == 0) {
            buttonDisplayEditMenuItem.addActionListener(e -> buttonEditChoices_Onclick("Menu Items"));
        }
        // Action listener for displaying editing UI of coupons, also ensuring that the action listener is only created once
        if (buttonDisplayEditCoupon.getActionListeners().length == 0) {
            buttonDisplayEditCoupon.addActionListener(e -> buttonEditChoices_Onclick("Coupon"));
        }

        // Add components to the JPanel
        panelEdit.setPreferredSize(new Dimension(500, 590));
        panelEdit.setBackground(Color.WHITE);
        managerUIFrame.add(panelEdit);
        panelEdit.setVisible(false);
    }

    // 2b) Allows the user to choose between editing of Menu Items OR coupon, then display the respective GUI components
    public void buttonEditChoices_Onclick(String buttonPressed){
        /* REMOVE COMPONENTS */
        // Remove all components then add choice buttons on the top
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(buttonDisplayEditMenuItem, 0);
        panelEdit.add(buttonDisplayEditCoupon, 1);
        panelEdit.revalidate();
        panelEdit.repaint();

        // Display relevant components based on the button pressed
        switch (buttonPressed) {
            case "Menu Items" -> {
                buttonDisplayEditMenuItem.setEnabled(false);
                buttonDisplayEditCoupon.setEnabled(true);
                // Table
                JScrollPane editMenuItemScrollPane = (JScrollPane) editMenuItemTableConstruction();
                panelEdit.add(editMenuItemScrollPane); // Add table to JPanel
                // Labels
                JLabel[] arrayEditFoodItemLabels = {labelEditItemID, labelEditFoodName, labelEditPrice, labelEditCategory};
                for (JLabel jLabel : arrayEditFoodItemLabels) {
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] arrayEditFoodItemsTextFields = {fieldEditItemID, fieldEditFoodName, fieldEditFoodPrice, fieldEditFoodCategory};
                for (int i = 0; i < arrayEditFoodItemsTextFields.length; i++) {
                    arrayEditFoodItemsTextFields[i].setPreferredSize(new Dimension(50, 30));
                    if (i == 0 || i == 3) {
                        arrayEditFoodItemsTextFields[i].setEditable(false);
                    }
                }
                // Add labels and text fields to the JPanel
                panelEdit.add(labelEditItemID);
                panelEdit.add(fieldEditItemID);
                panelEdit.add(labelEditFoodName);
                panelEdit.add(fieldEditFoodName);
                panelEdit.add(labelEditPrice);
                panelEdit.add(fieldEditFoodPrice);
                panelEdit.add(labelEditCategory);
                panelEdit.add(fieldEditFoodCategory);
                // Button
                buttonEditMenuItem.setPreferredSize(new Dimension(250, 30));
                buttonEditMenuItem.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonEditMenuItem.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonEditMenuItem.setBackground(Color.WHITE);
                // Action listener for editing of menu items, also ensuring that the action listener is only created once
                if (buttonEditMenuItem.getActionListeners().length == 0){
                    buttonEditMenuItem.addActionListener(e -> buttonEditMenuItem_Onclick());
                }
                panelEdit.add(buttonEditMenuItem);
            }
            case "Coupon" -> {
                buttonDisplayEditCoupon.setEnabled(false);
                buttonDisplayEditMenuItem.setEnabled(true);
                // Table
                JScrollPane editCouponScrollPane = (JScrollPane) editCouponTableConstruction();
                panelEdit.add(editCouponScrollPane); // Add table to JPanel
                // Labels
                JLabel[] arrayEditCouponLabels = {labelEditCouponName, labelEditCouponDiscountValue};
                for (JLabel jLabel : arrayEditCouponLabels){
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] arrayEditFoodItemsTextFields = {fieldEditCouponName, fieldEditCouponDiscountValue};
                for (JTextField arrayEditFoodItemsTextField : arrayEditFoodItemsTextFields) {
                    arrayEditFoodItemsTextField.setPreferredSize(new Dimension(50, 30));
                }
                // Add labels and text fields to the JPanel
                panelEdit.add(labelEditCouponName);
                panelEdit.add(fieldEditCouponName);
                panelEdit.add(labelEditCouponDiscountValue);
                panelEdit.add(fieldEditCouponDiscountValue);
                // Button
                buttonEditCoupon.setPreferredSize(new Dimension(250, 30));
                buttonEditCoupon.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonEditCoupon.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonEditCoupon.setBackground(Color.WHITE);
                // Action listener for editing of coupons, also ensuring that the action listener is only created once
                if (buttonEditCoupon.getActionListeners().length == 0){
                    buttonEditCoupon.addActionListener(e -> buttonEditCoupon_Onclick());
                }
                panelEdit.add(buttonEditCoupon);
            }
        } // end of switch statements
    }

    /* Methods 2c and 2d belongs to Editing MENU ITEMS */
    // 2c) Construction of table variables for EDIT MENU ITEM functions
    public Component editMenuItemTableConstruction(){
        // Display Table and table click listener
        ManagerEditController managerEditController = new ManagerEditController();
        String[][] data = managerEditController.displayMenuItems();
        // Display data in a table format
        String[] columnTableNames = {"Item ID", "Food Name", "Price", "Category"};
        tableEditMenuItems = new JTable(data, columnTableNames);
        JScrollPane editMenuItemScrollPane = new JScrollPane(tableEditMenuItems);
        editMenuItemScrollPane.setPreferredSize(new Dimension(485, 200)); // width then height
        // Table Click Listener
        tableEditMenuItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditMenuItems.getSelectedRow();
                // Display details on the text fields
                fieldEditItemID.setText(tableEditMenuItems.getModel().getValueAt(getRow, 0).toString());
                fieldEditFoodName.setText(tableEditMenuItems.getModel().getValueAt(getRow, 1).toString());
                fieldEditFoodPrice.setText(tableEditMenuItems.getModel().getValueAt(getRow, 2).toString());
                fieldEditFoodCategory.setText(tableEditMenuItems.getModel().getValueAt(getRow, 3).toString());
            }
        });
        /* REMOVE COMPONENTS */
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(editMenuItemScrollPane, 2);
        panelEdit.revalidate();
        panelEdit.repaint();
        return editMenuItemScrollPane;
    }

    // 2d) Edit Menu Item button function to allow the user to edit a Menu Item by passing the data to the controller
    public void buttonEditMenuItem_Onclick(){
        if (fieldEditFoodName.getText().isEmpty() || fieldEditFoodPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            /* Get the new values */
            String newFoodName = fieldEditFoodName.getText();
            Float new_item_price = 0.00f;
            try{
                new_item_price = Float.parseFloat(fieldEditFoodPrice.getText());
                // Convert NEW item price to 2 decimal places
                DecimalFormat df = new DecimalFormat("#.##");
                new_item_price = Float.parseFloat(df.format(new_item_price));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the price of the food item.", "Error!", JOptionPane.WARNING_MESSAGE);
            } // end of try-catch statements
            // Check if price entered is > 0
            if (new_item_price <= 0){
                JOptionPane.showMessageDialog(null, "Please ensure Price text field's input is more than 0.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                int itemID = Integer.parseInt(fieldEditItemID.getText());
                // Pass values to the controller
                ManagerEditController managerEditController = new ManagerEditController();
                if (managerEditController.editItem(itemID, newFoodName, new_item_price)){
                    JOptionPane.showMessageDialog(null, "Menu Item has been successful updated.", "Menu Item Update", JOptionPane.INFORMATION_MESSAGE);
                    editMenuItemTableConstruction(); // After edit, refresh edit Menu Item table
                }
                else{
                    JOptionPane.showMessageDialog(null, "Menu Item has not been successful updated.", "Menu Item Update", JOptionPane.ERROR_MESSAGE);
                }
            } // end of if-else statements
        } // end of if-else statements
    }

    /* Methods 2e and 2f belongs to Editing COUPONS */
    // 2e) Construction of table variables for EDIT COUPON functions
    public Component editCouponTableConstruction(){
        // Display Table and table click listener
        ManagerEditController managerEditController = new ManagerEditController();
        String[][] data = managerEditController.displayCoupons();
        // Display data in a table format
        String[] columnTableNames = {"Coupon Name", "Discount Value"};
        tableEditCoupons = new JTable(data, columnTableNames);
        JScrollPane editCouponScrollPane = new JScrollPane(tableEditCoupons);
        editCouponScrollPane.setPreferredSize(new Dimension(485, 200)); // width then height
        // Table Click Listener
        tableEditCoupons.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditCoupons.getSelectedRow();
                // Display details on the text fields
                fieldEditCouponName.setText(tableEditCoupons.getModel().getValueAt(getRow, 0).toString());
                fieldEditCouponDiscountValue.setText(tableEditCoupons.getModel().getValueAt(getRow, 1).toString());
            }
        });
        /* REMOVE COMPONENTS */
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(editCouponScrollPane, 2);
        panelEdit.revalidate();
        panelEdit.repaint();
        return editCouponScrollPane;
    }

    // 2f) Edit Coupon button function to allow the user to edit a Menu Item by passing the data to the controller
    public void buttonEditCoupon_Onclick(){
        if (fieldEditCouponName.getText().isEmpty() || fieldEditCouponDiscountValue.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            /* Get the new values */
            int rowSelected = tableEditCoupons.getSelectedRow();
            if (rowSelected != -1){
                String oldCouponName = tableEditCoupons.getModel().getValueAt(rowSelected,0).toString();
                String newCouponName = fieldEditCouponName.getText().toUpperCase();
                Float new_coupon_value = 0.0f;
                try{
                    new_coupon_value = Float.parseFloat(fieldEditCouponDiscountValue.getText());
                    // Convert NEW discount value to 1 decimal place
                    DecimalFormat df = new DecimalFormat("#.##");
                    new_coupon_value = Float.parseFloat(df.format(new_coupon_value));
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the discount value of the coupon.", "Error!", JOptionPane.WARNING_MESSAGE);
                } // end of try-catch statements
                // Check if the discount value is > 1
                if (new_coupon_value <= 0.0 || new_coupon_value > 1.0){
                    JOptionPane.showMessageDialog(null, "Please ensure Discount Value text field's input is more than 0 and less than 1.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    // Pass values to the controller
                    ManagerEditController managerEditController = new ManagerEditController();
                    if (managerEditController.editCouponValues(oldCouponName, newCouponName, new_coupon_value)){
                        JOptionPane.showMessageDialog(null, "Coupon has been successful updated.", "Coupon Update", JOptionPane.INFORMATION_MESSAGE);
                        editCouponTableConstruction(); // After edit, refresh edit Menu Item table
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Coupon has not been successful updated.", "Coupon Update", JOptionPane.ERROR_MESSAGE);
                    }
                } // end of if-else statements
            }
            else{
                JOptionPane.showMessageDialog(null, "Please reselect the coupon you wish to edit!", "Error!", JOptionPane.WARNING_MESSAGE);
            }

        } // end of if-else statements
    }


    /* 3. SEARCH function
    * 3a) void displaySearchPanel() - Method to display JPanel for Restaurant Manager to search menu items or coupons
    * Methods 3b and 3c belongs to searching MENU ITEMS
    * 3b) void buttonSearchMenuItem_Onclick() - Search by Menu Item Name button function
    * 3c) Component searchMenuItemTableConstruction(String [][] data) - Construction of table variables for MENU ITEMS functions
    * Methods 3d and 3e belongs to searching COUPONS
    * 3d) void buttonSearchCoupon_Onclick() - Search by Coupon Name button function
    * 3e) Component searchCouponTableConstruction(String [][] data) - Construction of table variables for COUPON
    */
    // 3a) Method to display JPanel for Restaurant Manager to search menu items and coupons
    public void displaySearchPanel(){
        displayTitledBorder(panelSearch, "Search Menu Items/Coupons");

        // Search Text Field
        fieldSearch.setPreferredSize(new Dimension(60, 30));
        panelSearch.add(fieldSearch, 0);

        // Choice buttons on top
        JButton [] buttonChoices = {buttonSearchMenuItem, buttonSearchCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelSearch.add(buttonSearchMenuItem, 1);
        panelSearch.add(buttonSearchCoupon, 2);
        // Action listener for searching of menu items, also ensuring that the action listener is only created once
        if (buttonSearchMenuItem.getActionListeners().length == 0) {
            buttonSearchMenuItem.addActionListener(e -> buttonSearchMenuItem_Onclick());
        }
        // Action listener for searching of coupons, also ensuring that the action listener is only created once
        if (buttonSearchCoupon.getActionListeners().length == 0) {
            buttonSearchCoupon.addActionListener(e -> buttonSearchCoupon_Onclick());
        }

        // Add components to the JPanel
        panelSearch.setPreferredSize(new Dimension(500, 390));
        panelSearch.setBackground(Color.WHITE);
        managerUIFrame.add(panelSearch);
        panelSearch.setVisible(false);
    }

    /* Methods 3b and 3c belongs to searching MENU ITEMS */
    // 3b) Search by Menu Item button function, and then display results by constructing a table
    public void buttonSearchMenuItem_Onclick(){
        String searchText = fieldSearch.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // Pass search results into controller to get 2D array of search results
            ManagerSearchController managerSearchController = new ManagerSearchController();
            String [][] arrayAllSearchData = managerSearchController.searchByMenuItemName(searchText);
            JScrollPane searchMenuItemScrollPane = (JScrollPane) searchMenuItemTableConstruction(arrayAllSearchData);
            panelSearch.add(searchMenuItemScrollPane);
        } // end of if-else statements
    }

    // 3c) Construction of table variables for MENU ITEMS
    public Component searchMenuItemTableConstruction(String [][] data){
        // Display data in a table format
        String [] columnTableNames = {"Item ID", "Food Name", "Price", "Category"};
        tableSearchMenuItems = new JTable(data, columnTableNames);
        JScrollPane searchMenuItemScrollPane1 = new JScrollPane(tableSearchMenuItems);
        searchMenuItemScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        //Get the components in the panel
        Component[] componentList = panelSearch.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSearch.remove(c);
            }
        }
        panelSearch.add(searchMenuItemScrollPane1, 3);
        panelSearch.revalidate();
        panelSearch.repaint();
        return searchMenuItemScrollPane1;
    }

    // 3d) Search by Coupon Name button function, and then display results by constructing a table
    public void buttonSearchCoupon_Onclick(){
        String searchText = fieldSearch.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // Pass search results into controller to get 2D array of search results
            ManagerSearchController managerSearchController = new ManagerSearchController();
            String [][] arrayAllSearchData = managerSearchController.searchByCouponName(searchText);
            JScrollPane searchCouponScrollPane = (JScrollPane) searchCouponTableConstruction(arrayAllSearchData);
            panelSearch.add(searchCouponScrollPane);
        } // end of if-else statements
    }

    // 3e) Construction of table variables for COUPON
    public Component searchCouponTableConstruction(String [][] data){
        // Display data in a table format
        String [] columnTableNames = {"Coupon Name", "Discount Value"};
        tableSearchCoupons = new JTable(data, columnTableNames);
        JScrollPane searchCouponScrollPane1 = new JScrollPane(tableSearchCoupons);
        searchCouponScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        //Get the components in the panel
        Component[] componentList = panelSearch.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSearch.remove(c);
            }
        }
        panelSearch.add(searchCouponScrollPane1, 3);
        panelSearch.revalidate();
        panelSearch.repaint();
        return searchCouponScrollPane1;
    }


    /* 4. VIEW function
    * 4a) void displayViewPanel() - Method to display JPanel for Restaurant Manager to display either a table of Menu Items or Coupons
    * Methods 4b and 4c belongs to viewing MENU ITEMS
    * 4b) void buttonViewMenuItem_Onclick() - View Menu Item button function to construct table to VIEW ALL MENU ITEMS
    * 4c) Component viewMenuItemTableConstruction(String [][] data) - Construction of table variables for MENU ITEMS
    * 4d) void buttonViewCoupon_Onclick() - View Coupon button function to construct table to VIEW ALL COUPONS
    * 4e) Component viewCouponTableConstruction(String [][] data) - Construction of table variables for COUPONS
    */
    // 4a) Method to display JPanel for Restaurant Manager to display either a table of Menu Items or Coupons
    public void displayViewPanel(){
        displayTitledBorder(panelView, "View Menu Items/Coupons");

        // Remove all components then add choice buttons on the top
        Component[] componentList = panelView.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelView.remove(c);
            }
            if (c instanceof JButton){
                c.setEnabled(true);
            }
        }

        // View Table buttons
        JButton [] buttonChoices = {buttonViewMenuItem, buttonViewCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelView.add(buttonViewMenuItem, 0);
        panelView.add(buttonViewCoupon, 1);
        // Action listener for viewing of menu items, also ensuring that the action listener is only created once
        if (buttonViewMenuItem.getActionListeners().length == 0) {
            buttonViewMenuItem.addActionListener(e -> buttonViewMenuItem_Onclick());
        }
        // Action listener for viewing of coupons, also ensuring that the action listener is only created once
        if (buttonViewCoupon.getActionListeners().length == 0) {
            buttonViewCoupon.addActionListener(e -> buttonViewCoupon_Onclick());
        }

        // Add components to the JPanel
        panelView.setPreferredSize(new Dimension(500, 390));
        panelView.setBackground(Color.WHITE);
        managerUIFrame.add(panelView);
        panelView.setVisible(false);
    }

    /* Methods 4b and 4c belongs to viewing MENU ITEMS */
    // 4b) View Menu Item button function to allow the user to view all Menu Items on a table upon pressing the View All Menu Items
    public void buttonViewMenuItem_Onclick(){
        buttonViewMenuItem.setEnabled(false);
        buttonViewCoupon.setEnabled(true);

        // Table
        ManagerViewController managerViewController = new ManagerViewController();
        String [][] arrayAllMenuItems = managerViewController.displayMenuItems();
        JScrollPane viewMenuItemScrollPane = (JScrollPane) viewMenuItemTableConstruction(arrayAllMenuItems);
        panelView.add(viewMenuItemScrollPane);
    }

    // 4c) Construction of table variables for MENU ITEMS
    public Component viewMenuItemTableConstruction(String [][] data){
        // Display data in a table format
        String [] columnTableNames = {"Item ID", "Food Name", "Price", "Category"};
        tableViewMenuItems = new JTable(data, columnTableNames);
        JScrollPane viewMenuItemScrollPane1 = new JScrollPane(tableViewMenuItems);
        viewMenuItemScrollPane1.setPreferredSize(new Dimension(485, 270)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelView.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelView.remove(c);
            }
        }
        panelView.add(viewMenuItemScrollPane1, 2);
        panelView.revalidate();
        panelView.repaint();

        return viewMenuItemScrollPane1;
    }

    // 4d) View Coupon button function to allow the user to view all Menu Items on a table upon pressing the View All Coupons
    public void buttonViewCoupon_Onclick(){
        buttonViewCoupon.setEnabled(false);
        buttonViewMenuItem.setEnabled(true);

        // Table
        ManagerViewController managerViewController = new ManagerViewController();
        String [][] arrayAllCoupons = managerViewController.displayCoupons();
        JScrollPane viewMenuItemScrollPane = (JScrollPane) viewCouponTableConstruction(arrayAllCoupons);
        panelView.add(viewMenuItemScrollPane);
    }

    // 4e) Construction of table variables for COUPONS
    public Component viewCouponTableConstruction(String [][] data){
        // Display data in a table format
        String [] columnTableNames = {"Coupon Name", "Discount Value"};
        tableViewCoupons = new JTable(data, columnTableNames);
        JScrollPane viewMenuItemScrollPane1 = new JScrollPane(tableViewCoupons);
        viewMenuItemScrollPane1.setPreferredSize(new Dimension(485, 270)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelView.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelView.remove(c);
            }
        }
        panelView.add(viewMenuItemScrollPane1, 2);
        panelView.revalidate();
        panelView.repaint();
        return viewMenuItemScrollPane1;
    }


    /* 5. SUSPEND function
    * 5a) void displayDeletePanel() - Method to display JPanel for Restaurant Manager to delete menu items or coupons
    * 5b) void buttonDeleteChoices_Onclick(String buttonPressed) - Allows the user to choose between deletion of Menu Items OR coupon, then display the respective GUI components
    * Methods 5c and 5d belongs to deleting MENU ITEMS
    * 5c) void buttonDeleteMenuItem_Onclick() - Delete Menu Item button function to allow the user to delete a Menu Items on a table upon pressing the Delete Menu Item Button
    * 5d) Component deleteMenuItemTableConstruction() - Construction of table variables for MENU ITEMS
    * Methods 5e and 5f belongs to deleting COUPONS
    * 5e) void buttonDeleteCoupon_Onclick() - Delete Coupon button function to allow the user to delete a Coupon on a table upon pressing the Delete Coupon Button
    * 5f) Component deleteCouponTableConstruction() - Construction of table variables for COUPON
    */
    // 5a) Method to display JPanel for Restaurant Manager to delete menu items or coupons
    public void displayDeletePanel(){
        displayTitledBorder(panelDelete, "Delete Menu Items/Coupons");

        // Remove all components then add choice buttons on the top
        Component[] componentList = panelDelete.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelDelete.remove(c);
            }
            if (c instanceof JButton){
                c.setEnabled(true);
            }
        }

        // Buttons
        JButton [] buttonChoices = {buttonDisplayDeleteMenuItem, buttonDisplayDeleteCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelDelete.add(buttonDisplayDeleteMenuItem, 0);
        panelDelete.add(buttonDisplayDeleteCoupon, 1);
        // Action listener for displaying deletion UI of menu items, also ensuring that the action listener is only created once
        if (buttonDisplayDeleteMenuItem.getActionListeners().length == 0){
            buttonDisplayDeleteMenuItem.addActionListener(e -> buttonDeleteChoices_Onclick("Menu Items"));
        }
        // Action listener for displaying deletion UI of coupons, also ensuring that the action listener is only created once
        if (buttonDisplayDeleteCoupon.getActionListeners().length == 0){
            buttonDisplayDeleteCoupon.addActionListener(e -> buttonDeleteChoices_Onclick("Coupon"));
        }

        // Add components to the JPanel
        panelDelete.setPreferredSize(new Dimension(500, 450));
        panelDelete.setBackground(Color.WHITE);
        managerUIFrame.add(panelDelete);
        panelDelete.setVisible(false);
    }

    // 5b) Allows the user to choose between deletion of Menu Items OR coupon, then display the respective GUI components
    public void buttonDeleteChoices_Onclick(String buttonPressed){
        /* REMOVE COMPONENTS of each category */
        Component[] componentList = panelDelete.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JScrollPane){
                //Remove it
                panelDelete.remove(c);
            }
        }
        panelDelete.add(buttonDisplayDeleteMenuItem, 0);
        panelDelete.add(buttonDisplayDeleteCoupon, 1);
        panelDelete.revalidate();
        panelDelete.repaint();

        // Display relevant components based on the button PRESSED
        ManagerDeleteController managerDeleteController = new ManagerDeleteController();
        switch (buttonPressed){
            case "Menu Items" -> {
                // Disable pressed button
                buttonDisplayDeleteMenuItem.setEnabled(false);
                buttonDisplayDeleteCoupon.setEnabled(true);

                // Menu Item Table Construction
                JScrollPane deleteMenuItemScrollPane = (JScrollPane) deleteMenuItemTableConstruction();
                panelDelete.add(deleteMenuItemScrollPane);

                // Delete Menu Item Button Construction
                buttonDeleteMenuItem.setPreferredSize(new Dimension(250, 30));
                buttonDeleteMenuItem.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonDeleteMenuItem.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonDeleteMenuItem.setBackground(Color.WHITE);
                // Action listener for deletion of menu items, also ensuring that the action listener is only created once
                if (buttonDeleteMenuItem.getActionListeners().length == 0){
                    buttonDeleteMenuItem.addActionListener(e -> buttonDeleteMenuItem_Onclick());
                }
                panelDelete.add(buttonDeleteMenuItem);
            }
            case "Coupon" -> {
                // Disable pressed button
                buttonDisplayDeleteCoupon.setEnabled(false);
                buttonDisplayDeleteMenuItem.setEnabled(true);

                // Coupon Table Construction
                JScrollPane deleteCouponScrollPane = (JScrollPane) deleteCouponTableConstruction();
                panelDelete.add(deleteCouponScrollPane);

                // Delete Coupon Button Construction
                buttonDeleteCoupon.setPreferredSize(new Dimension(250, 30));
                buttonDeleteCoupon.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonDeleteCoupon.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonDeleteCoupon.setBackground(Color.WHITE);
                // Action listener for deletion of coupons, also ensuring that the action listener is only created once
                if (buttonDeleteCoupon.getActionListeners().length == 0){
                    buttonDeleteCoupon.addActionListener(e -> buttonDeleteCoupon_Onclick());
                }
                panelDelete.add(buttonDeleteCoupon);
            }
        } // end of switch statements
    }

    // 5c) Delete Menu Item button function to allow the user to delete a Menu Items on a table upon pressing the Delete Menu Item Button
    public void buttonDeleteMenuItem_Onclick(){
        ManagerDeleteController managerDeleteController = new ManagerDeleteController();
        int rowSelected = tableDeleteMenuItems.getSelectedRow();
        if (rowSelected != -1){
            int itemIDSelected = Integer.parseInt((String)tableDeleteMenuItems.getModel().getValueAt(rowSelected, 0));
            if (managerDeleteController.deleteItem(itemIDSelected)){
                JOptionPane.showMessageDialog(null, "Menu Item successfully deleted.", "Menu Item Deletion", JOptionPane.INFORMATION_MESSAGE);
                buttonDeleteChoices_Onclick("Menu Items");
            }
            else{
                JOptionPane.showMessageDialog(null, "Menu Item deletion is unsuccessful.", "Error!", JOptionPane.ERROR_MESSAGE);
            } // end of if-else statements
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a Menu Item from the table to delete it.", "Error!", JOptionPane.WARNING_MESSAGE);
        } // end of if-else statements
    }

    // 5d) Construction of table variables for MENU ITEMS
    public Component deleteMenuItemTableConstruction(){
        ManagerDeleteController managerDeleteController = new ManagerDeleteController();
        String [][] arrayAllMenuItems = managerDeleteController.displayMenuItems();
        // Display data in table format
        String[] columnTableNames = {"Item ID", "Food Name", "Price", "Category"};
        tableDeleteMenuItems = new JTable(arrayAllMenuItems, columnTableNames);
        JScrollPane deleteMenuItemScrollPane1 = new JScrollPane(tableDeleteMenuItems);
        deleteMenuItemScrollPane1.setPreferredSize(new Dimension(485, 270)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelDelete.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelDelete.remove(c);
            }
        }
        panelDelete.add(deleteMenuItemScrollPane1, 2);
        panelDelete.revalidate();
        panelDelete.repaint();

        return deleteMenuItemScrollPane1;
    }

    // 5e) Delete Coupon button function to allow the user to delete a Coupon on a table upon pressing the Delete Coupon Button
    public void buttonDeleteCoupon_Onclick(){
        ManagerDeleteController managerDeleteController = new ManagerDeleteController();
        int rowSelected = tableDeleteCoupon.getSelectedRow();
        if (rowSelected != -1){
            String couponSelected = (String)tableDeleteCoupon.getModel().getValueAt(rowSelected, 0);
            if (managerDeleteController.deleteCouponByName(couponSelected)){
                JOptionPane.showMessageDialog(null, "Coupon successfully deleted.", "Coupon Deletion", JOptionPane.INFORMATION_MESSAGE);
                buttonDeleteChoices_Onclick("Coupon");
            }
            else{
                JOptionPane.showMessageDialog(null, "Coupon deletion is unsuccessful.", "Error!", JOptionPane.ERROR_MESSAGE);
            } // end of if-else statements
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a Coupon from the table to delete it.", "Error!", JOptionPane.WARNING_MESSAGE);
        } // end of if-else statements
    }

    // 5f) Construction of table variables for COUPON
    public Component deleteCouponTableConstruction(){
        ManagerDeleteController managerDeleteController = new ManagerDeleteController();
        String [][] arrayAllCoupons = managerDeleteController.displayCoupons();
        // Display data in table format
        String[] columnTableNames = {"Coupon", "Discount Value"};
        tableDeleteCoupon = new JTable(arrayAllCoupons, columnTableNames);
        JScrollPane deleteCouponScrollPane1 = new JScrollPane(tableDeleteCoupon);
        deleteCouponScrollPane1.setPreferredSize(new Dimension(485, 270)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelDelete.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelDelete.remove(c);
            }
        }
        panelDelete.add(deleteCouponScrollPane1, 2);
        panelDelete.revalidate();
        panelDelete.repaint();
        return deleteCouponScrollPane1;
    }
}