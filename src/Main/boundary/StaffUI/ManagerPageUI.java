package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantManager.ManagerCreateController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
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
    private final JPanel panelCreateOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Buttons
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




    public ManagerPageUI(String usernameLoggedIn){
        managerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerUIFrame.getContentPane().setLayout(new FlowLayout());
        managerUIFrame.setSize(520, 705);
        managerUIFrame.setResizable(false);
        managerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        managerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Manager's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        managerUIFrame.add(labelTopHeader);

        // Add buttons & functions
        displayManagerUserButtons();

        /* Button Function for Restaurant Manager */
        // 1. Create menu items
        displayCreatePanel();
        panelCreateOrder.setVisible(true);

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
                panelCreateOrder.setVisible(true);
                break;
            case "Edit":
                break;
            case "Search":
                break;
            case "Delete":
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
    * */
    // 1a) Method to display JPanel for Restaurant Manager to create menu items and coupons
    public void displayCreatePanel(){
        displayTitledBorder(panelCreateOrder, "Create Menu Items/Coupons");

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplayCreateMenuItem, buttonDisplayCreateCoupon};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
            panelCreateOrder.add(jButton);
        }
        buttonDisplayCreateMenuItem.addActionListener(e -> buttonCreateChoices_Onclick("Menu Items"));
        buttonDisplayCreateCoupon.addActionListener(e -> buttonCreateChoices_Onclick("Coupon"));





        // Add components to the JPanel
        panelCreateOrder.setPreferredSize(new Dimension(500, 550));
        panelCreateOrder.setBackground(Color.WHITE);
        managerUIFrame.add(panelCreateOrder);
        panelCreateOrder.setVisible(true);
    }

    // 1b) Allows the user to choose between creation of Menu Items OR coupon, then display the respective GUI components
    public void buttonCreateChoices_Onclick(String buttonPressed){
        /* REMOVE COMPONENTS */
        Component[] componentList = panelCreateOrder.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JRadioButton){
                //Remove it
                panelCreateOrder.remove(c);
            }
        }
        panelCreateOrder.add(buttonDisplayCreateMenuItem, 0);
        panelCreateOrder.add(buttonDisplayCreateCoupon, 1);
        panelCreateOrder.revalidate();
        panelCreateOrder.repaint();

        // Display relevant components based on the button PRESSED
        switch (buttonPressed){
            case "Menu Items":
                // Disable pressed button
                buttonDisplayCreateMenuItem.setEnabled(false);
                buttonDisplayCreateCoupon.setEnabled(true);

                // Labels
                JLabel [] labelForFoodItems = {labelCreateFoodName, labelCreateFoodPrice};
                for (JLabel jLabel : labelForFoodItems){
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField [] textFieldsForFoodItems = {fieldCreateFoodName, fieldCreateFoodPrice};
                for (JTextField jTextField : textFieldsForFoodItems){
                    jTextField.setPreferredSize(new Dimension(50, 30));
                }

                // Add components to the JPanel
                panelCreateOrder.add(labelCreateFoodName);
                panelCreateOrder.add(fieldCreateFoodName);
                panelCreateOrder.add(labelCreateFoodPrice);
                panelCreateOrder.add(fieldCreateFoodPrice);

                // Radio Buttons
                JRadioButton [] tempRadioButtons = {radioCreatePasta, radioCreatePizza, radioCreateBakedRice, radioCreateDrinks};
                String [] actionCommandForMenuItems = {"Pasta", "Pizza", "Baked Rice", "Drinks"};
                for (int i = 0; i < tempRadioButtons.length; i++){
                    tempRadioButtons[i].setBackground(Color.WHITE);
                    tempRadioButtons[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                    tempRadioButtons[i].setPreferredSize(new Dimension(240, 15));
                    tempRadioButtons[i].setActionCommand(actionCommandForMenuItems[i]);
                    buttonGroupCreate.add(tempRadioButtons[i]);
                    panelCreateOrder.add(tempRadioButtons[i]);
                }
                tempRadioButtons[0].setSelected(true);

                // Button
                buttonCreateMenuItem.setPreferredSize(new Dimension(250, 30));
                buttonCreateMenuItem.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonCreateMenuItem.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonCreateMenuItem.setBackground(Color.WHITE);
                buttonCreateMenuItem.addActionListener(e -> buttonCreateMenuItem_Onclick());
                panelCreateOrder.add(buttonCreateMenuItem);
                break;

            case "Coupon":
                // Disable pressed button
                buttonDisplayCreateCoupon.setEnabled(false);
                buttonDisplayCreateMenuItem.setEnabled(true);
                break;
        } // end of switch statements

        /*
        if (fieldCreateFoodName.getText().isEmpty() || fieldCreateFoodPrice.getText().matches("[+-]?[0-9]+") || fieldCreateFoodCategory.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter the correct values in the text fields.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String foodName = fieldCreateFoodName.getText();
            Float item_price = Float.parseFloat(fieldCreateFoodPrice.getText());
            String category = fieldCreateFoodCategory.getText();
            System.out.println(item_price);
            ManagerCreateController managerCreateController = new ManagerCreateController();
            if (managerCreateController.validateCreateFoodItem(foodName, item_price, category)){

            }
        }
        */
    }

    // 1c) Create Menu Item button function to allow the user to create a Menu Item by passing the data to the controller
    public void buttonCreateMenuItem_Onclick(){
        if (fieldCreateFoodName.getText().isEmpty() || fieldCreateFoodPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        // If text fields are not left empty
        else{
            float item_price = 0.00f;
            try {
                item_price = Float.parseFloat(fieldCreateFoodPrice.getText());
                // Convert item price to 2 decimal places
                DecimalFormat df = new DecimalFormat("#.##");
                item_price = Float.parseFloat(df.format(item_price));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the price of the food item.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Get Food Name from Text Field and the radio button selected
            String food_name = fieldCreateFoodName.getText();
            String category = buttonGroupCreate.getSelection().getActionCommand();
            // Pass values to the controller
            ManagerCreateController managerCreateController = new ManagerCreateController();
            if (managerCreateController.validateCreateFoodItem(food_name, item_price, category)){
                JOptionPane.showMessageDialog(null, "Menu Item is created successfully.", "Account Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Menu Item already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }





}