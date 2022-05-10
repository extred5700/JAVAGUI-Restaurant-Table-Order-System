package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantManager.ManagerCreateController;
import Main.controller.RestaurantManager.ManagerEditController;
import Main.controller.RestaurantManager.ManagerSearchController;

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




    public ManagerPageUI(String setDisplayPage){
        managerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerUIFrame.getContentPane().setLayout(new FlowLayout());
        managerUIFrame.setSize(520, 705);
        managerUIFrame.setResizable(false);
        managerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        managerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Add buttons & functions
        displayManagerUserButtons();

        /* Button Function for Restaurant Manager */
        // 1. Create menu items & coupons
        displayCreatePanel();
        panelCreate.setVisible(false);

        // 2. Edit menu items & coupons
        displayEditPanel();
        panelEdit.setVisible(false);

        // 3. Search menu items & coupons
        displaySearchPanel();
        panelSearch.setVisible(false);

        switch(setDisplayPage) {
            case "Default":
                break;
            case "Create":
                panelCreate.setVisible(true);
                break;
            case "Edit":
                panelEdit.setVisible(true);
                break;
            case "Search":
                panelSearch.setVisible(true);
                break;
            case "View":
                break;
            case "Suspend":
                break;
        }
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
        switch (action){
            case "Logout":
                managerUIFrame.dispose();
                managerUIFrame.setVisible(false);
                new StaffLoginPage();
                break;
            case "Create":
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                break;
            case "Edit":
                managerUIFrame.dispose();
                new ManagerPageUI("Edit");
                break;
            case "Search":
                managerUIFrame.dispose();
                new ManagerPageUI("Search");
                break;
            case "View":
                managerUIFrame.dispose();
                new ManagerPageUI("View");
                break;
            case "Delete":
                managerUIFrame.dispose();
                new ManagerPageUI("Delete");
                break;
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
    * 1a) void displayCreatePanel() - Method to display JPanel for Restaurant Manager to create menu items and coupons
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
            panelCreate.add(jButton);
        }
        buttonDisplayCreateMenuItem.addActionListener(e -> buttonCreateChoices_Onclick("Menu Items"));
        buttonDisplayCreateCoupon.addActionListener(e -> buttonCreateChoices_Onclick("Coupon"));


        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(500, 430));
        panelCreate.setBackground(Color.WHITE);
        managerUIFrame.add(panelCreate);
        panelCreate.setVisible(true);
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
                buttonCreateMenuItem.addActionListener(e -> buttonCreateMenuItem_Onclick());
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
                buttonCreateCoupon.addActionListener(e -> buttonCreateCoupon_Onclick());
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
            // Get Food Name from Text Field and the radio button selected
            String food_name = fieldCreateFoodName.getText();
            String category = buttonGroupCreate.getSelection().getActionCommand();
            // Pass values to the controller
            ManagerCreateController managerCreateController = new ManagerCreateController();
            if (managerCreateController.validateCreateFoodItem(food_name, item_price, category)){
                JOptionPane.showMessageDialog(null, "Menu Item is created successfully.", "Menu Item Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Menu Item already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
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
            String coupon = fieldCreateCouponName.getText().toUpperCase(); // Get Coupon name from Text Field, coupon name in UPPER case
            // Pass values to the controller
            ManagerCreateController managerCreateController = new ManagerCreateController();
            if (managerCreateController.validateCreateCoupon(coupon, discount)){
                JOptionPane.showMessageDialog(null, "Coupon is created successfully.", "Coupon Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Coupon already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } // end of if-else statements
    }


    /* 2. EDIT function
    * 2a) void displayEditPanel() - Method to display JPanel for Restaurant Manager to edit menu items and coupons
    * 2b) void buttonEditChoices_Onclick(String buttonPressed) - Allows the user to choose between editing of Menu Items OR coupon, then display the respective GUI components
    * Methods 2c and 2d belongs to Editing MENU ITEMS
    * 2c) Component editMenuItemTableConstruction() - Construction of table variables for EDIT MENU ITEM functions
    * 2d) void buttonEditMenuItem_Onclick() - Edit Menu Item button function to allow the user to edit a Menu Item by passing the data to the controller
    * Methods 2e and 2f belongs to Editing COUPONS
    * 2e) Component editCouponTableConstruction() - Construction of table variables for EDIT COUPON functions
    * 2f) void buttonEditCoupon_Onclick() - Edit Coupon button function to allow the user to edit a Menu Item by passing the data to the controller
    * */
    // 2a) Method to display JPanel for Restaurant Manager to edit menu items and coupons
    public void displayEditPanel(){
        displayTitledBorder(panelEdit, "Edit Menu Items/Coupons");

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplayEditMenuItem, buttonDisplayEditCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
            panelEdit.add(jButton);
        }
        buttonDisplayEditMenuItem.addActionListener(e -> buttonEditChoices_Onclick("Menu Items"));
        buttonDisplayEditCoupon.addActionListener(e -> buttonEditChoices_Onclick("Coupon"));

        // Add components to the JPanel
        panelEdit.setPreferredSize(new Dimension(500, 590));
        panelEdit.setBackground(Color.WHITE);
        managerUIFrame.add(panelEdit);
        panelEdit.setVisible(true);
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
                buttonEditMenuItem.addActionListener(e -> buttonEditMenuItem_Onclick());
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
                for (int i = 0; i < arrayEditFoodItemsTextFields.length; i++) {
                    arrayEditFoodItemsTextFields[i].setPreferredSize(new Dimension(50, 30));
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
                buttonEditCoupon.addActionListener(e -> buttonEditCoupon_Onclick());
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
            if (new_item_price < 0){
                JOptionPane.showMessageDialog(null, "Please ensure Price text field's input is more than 0.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                int item_id = Integer.parseInt(fieldEditItemID.getText());
                // Pass values to the controller
                ManagerEditController managerEditController = new ManagerEditController();
                if (managerEditController.editItem(item_id, newFoodName, new_item_price)){
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
        String[] columnTableNames = {"Coupon Name", "Discount Value Multiplier"};
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
            String oldCouponName = tableEditCoupons.getModel().getValueAt(rowSelected,0).toString();
            String newCouponName = fieldEditCouponName.getText().toUpperCase();
            Float new_discount_value = 0.0f;
            try{
                new_discount_value = Float.parseFloat(fieldEditCouponDiscountValue.getText());
                // Convert NEW discount value to 1 decimal place
                DecimalFormat df = new DecimalFormat("#.#");
                new_discount_value = Float.parseFloat(df.format(new_discount_value));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the discount value of the coupon.", "Error!", JOptionPane.WARNING_MESSAGE);
            } // end of try-catch statements
            // Check if the discount value is > 1
            if (new_discount_value > 1.0){
                JOptionPane.showMessageDialog(null, "Please ensure Discount Value text field's input is less than 1.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                // Pass values to the controller
                ManagerEditController managerEditController = new ManagerEditController();
                if (managerEditController.editCouponValues(oldCouponName, newCouponName, new_discount_value)){
                    JOptionPane.showMessageDialog(null, "Coupon has been successful updated.", "Coupon Update", JOptionPane.INFORMATION_MESSAGE);
                    editCouponTableConstruction(); // After edit, refresh edit Menu Item table
                }
                else{
                    JOptionPane.showMessageDialog(null, "Coupon has not been successful updated.", "Coupon Update", JOptionPane.ERROR_MESSAGE);
                }
            } // end of if-else statements
        } // end of if-else statements
    }


    /* 3. SEARCH function
    * 3a) void displaySearchPanel() - Method to display JPanel for Restaurant Manager to search menu items and coupons
    * 3b) void buttonSearchMenuItem_Onclick() - Search by Menu Item Name button function, and then display results by constructing a table
    * 3c) void buttonSearchCoupon_Onclick() - Search by Coupon Name button function, and then display results by constructing a table
    */
    // 3a) Method to display JPanel for Restaurant Manager to search menu items and coupons
    public void displaySearchPanel(){
        displayTitledBorder(panelSearch, "Search Menu Items/Coupons");

        // Search Text Field
        fieldSearch.setPreferredSize(new Dimension(60, 30));
        panelSearch.add(fieldSearch);

        // Choice buttons on top
        JButton [] buttonChoices = {buttonSearchMenuItem, buttonSearchCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
            panelSearch.add(jButton);
        }
        buttonSearchMenuItem.addActionListener(e -> buttonSearchMenuItem_Onclick());
        buttonSearchCoupon.addActionListener(e -> buttonSearchCoupon_Onclick());

        // Add components to the JPanel
        panelSearch.setPreferredSize(new Dimension(500, 390));
        panelSearch.setBackground(Color.WHITE);
        managerUIFrame.add(panelSearch);
        panelSearch.setVisible(true);
    }

    // 3b) Search by Menu Item button function, and then display results by constructing a table
    public void buttonSearchMenuItem_Onclick(){
        String searchText = fieldSearch.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // Pass search results into controller to get 2D array of search results
            ManagerSearchController managerSearchController = new ManagerSearchController();
            String [][] searchResults = managerSearchController.searchByMenuItemName(searchText);
            // Display data in a table format
            String [] columnTableNames = {"Item ID", "Food Name", "Price", "Category"};
            tableSearchMenuItems = new JTable(searchResults, columnTableNames);
            JScrollPane searchMenuItemScrollPane = new JScrollPane(tableSearchMenuItems);
            searchMenuItemScrollPane.setPreferredSize(new Dimension(485, 200)); // width then height

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
            panelSearch.add(searchMenuItemScrollPane, 3);
            panelSearch.revalidate();
            panelSearch.repaint();
        } // end of if-else statements
    }

    // 3c) Search by Coupon Name button function, and then display results by constructing a table
    public void buttonSearchCoupon_Onclick(){
        String searchText = fieldSearch.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            // Pass search results into controller to get 2D array of search results
            ManagerSearchController managerSearchController = new ManagerSearchController();
            String [][] searchResults = managerSearchController.searchByCouponName(searchText);
            // Display data in a table format
            String [] columnTableNames = {"Coupon Name", "Discount Value Multiplier"};
            tableSearchCoupons = new JTable(searchResults, columnTableNames);
            JScrollPane searchCouponScrollPane = new JScrollPane(tableSearchCoupons);
            searchCouponScrollPane.setPreferredSize(new Dimension(485, 200)); // width then height

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
            panelSearch.add(searchCouponScrollPane, 3);
            panelSearch.revalidate();
            panelSearch.repaint();
        } // end of if-else statements
    }



}