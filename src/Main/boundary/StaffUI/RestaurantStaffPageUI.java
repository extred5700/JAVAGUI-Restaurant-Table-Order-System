package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantStaff.StaffEditController;
import Main.controller.RestaurantStaff.StaffFulfillOrderController;
import Main.controller.RestaurantStaff.StaffViewController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestaurantStaffPageUI extends JFrame {
    /* Variable declaration */
    private final JFrame staffUIFrame = new JFrame("Restaurant Staff Homepage");

    /* Buttons for top of the GUI*/
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonSearch = new JButton("Search");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonDelete = new JButton("Delete");

    /* 1. EDIT Function */
    private final JPanel panelEditOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private JTable tableEditOrder;
    // Labels
    private final JLabel labelOrderID = new JLabel("Order ID: ");
    private final JLabel labelFoodName = new JLabel("Food Name: ");
    private final JLabel labelQuantity = new JLabel("Quantity: ");
    // Text Fields
    private final JTextField fieldOrderID = new JTextField(20);
    private final JTextField fieldFoodName = new JTextField(20);
    private final JTextField fieldQuantity = new JTextField(20);
    // Buttons
    private final JButton buttonEditOrder = new JButton("Edit Order");
    private final JButton buttonFulfillment = new JButton("Fulfill Order");

    /* 2. SEARCH function */
    private final JPanel panelSearchOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private JTable tableSearchOrder;

    public RestaurantStaffPageUI(String usernameLoggedIn){
        staffUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffUIFrame.getContentPane().setLayout(new FlowLayout());
        staffUIFrame.setSize(520, 705);
        staffUIFrame.setResizable(false);
        staffUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        staffUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Staff's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        staffUIFrame.add(labelTopHeader);

        // Add buttons & functions for the top of the GUI
        displayStaffUserButton();


        /* Button Function for Administrator */
        // 1. EDIT function
        displayEditPanel();
        panelEditOrder.setVisible(true);

        // 2. SEARCH function
        displaySearchPanel();
        panelSearchOrder.setVisible(false);


        staffUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
     * displayAdminUserButtons() - Method to display the buttons on top of the screen
     * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
     * displayTitledBorder(JPanel panel) - Construction and display of the Titled Border
     */
    public void displayStaffUserButton(){
        JButton [] myArray = {buttonLogout, buttonEdit, buttonSearch, buttonView, buttonDelete};
        for (JButton jButton : myArray) {
            jButton.setPreferredSize(new Dimension(95, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            staffUIFrame.add(jButton);
        }
    }

    // Top buttons listener
    ActionListener topButtonsListener = e ->{
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action) {
            case "Logout" -> {
                staffUIFrame.dispose();
                staffUIFrame.setVisible(false);
                new StaffLoginPage();
            }
            case "Edit" -> {
                dispose();
                panelEditOrder.setVisible(true);
                panelSearchOrder.setVisible(false);
            }
            case "Search" -> {
                dispose();
                panelSearchOrder.setVisible(true);
                panelEditOrder.setVisible(false);
            }
            case "View" -> {
                dispose();
                panelEditOrder.setVisible(false);
                panelSearchOrder.setVisible(false);
            }
            case "Delete" -> {
                dispose();
                panelEditOrder.setVisible(false);
                panelSearchOrder.setVisible(false);
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
    * 1a) displayEditPanel method - Display JPanel for Restaurant Staff to edit orders
    * 1b) displayTableConstruction() method - Construction of the JTable, Mouse Click Listener to display all transactions (JTable type returned as a JScrollPane type)
    * 1c) refreshEditTable(int indexInDB) - Used to refresh only the JScrollPane for Edit page
    * 1d) checkOrderFulfillment() - Check if Customer Order is fulfilled based on the Edit Table
    * 1e) editPanelButtonOnClick(String nameOfButton) - All button action based on switch case
    */
    // 1a) Method to display JPanel for Restaurant Staff to edit orders
    public void displayEditPanel(){
        displayTitledBorder(panelEditOrder, "Edit/Fulfill Customer Order"); // Display titled border

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane editOrderScrollPane = (JScrollPane) editTableConstruction();

        // Label
        labelOrderID.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        labelFoodName.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        labelQuantity.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));

        // Text Fields
        fieldOrderID.setPreferredSize(new Dimension(60, 30));
        fieldOrderID.setEditable(false);
        fieldFoodName.setPreferredSize(new Dimension(60, 30));
        fieldFoodName.setEditable(false);
        fieldQuantity.setPreferredSize(new Dimension(60, 30));

        // Buttons
        JButton [] editButtonInPanel = {buttonEditOrder, buttonFulfillment};
        for (JButton jButton : editButtonInPanel){
            jButton.setPreferredSize(new Dimension(250, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
            jButton.setEnabled(false);
        }
        buttonEditOrder.setActionCommand("Edit Order");
        buttonFulfillment.setActionCommand("Fulfill Order");

        // EDIT Button Click Listener
        buttonEditOrder.addActionListener(e -> {
            editPanelButtonOnClick(e.getActionCommand());
        });

        // Fulfill Button Click Listener
        buttonFulfillment.addActionListener(e -> {
            editPanelButtonOnClick(e.getActionCommand());
        });

        // Add components to the JPanel
        panelEditOrder.setPreferredSize(new Dimension(500, 550));
        panelEditOrder.setBackground(Color.WHITE);
        panelEditOrder.add(editOrderScrollPane);
        panelEditOrder.add(labelOrderID);
        panelEditOrder.add(fieldOrderID);
        panelEditOrder.add(labelFoodName);
        panelEditOrder.add(fieldFoodName);
        panelEditOrder.add(labelQuantity);
        panelEditOrder.add(fieldQuantity);
        panelEditOrder.add(buttonEditOrder);
        panelEditOrder.add(buttonFulfillment);
        staffUIFrame.add(panelEditOrder);
        panelEditOrder.setVisible(true);
    }

    // 1b) Method to construction of the JTable, Mouse Click Listener to display all transactions (JTable type returned as a JScrollPane type)
    public Component editTableConstruction(){
        StaffViewController staffViewController = new StaffViewController();
        String [][] data = staffViewController.displayOrders();
        // Display data in a table format
        String [] columnTableNames = {"Order ID", "Food Name", "Quantity", "Price", "Fulfilled"};
        tableEditOrder = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableEditOrder);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height

        // Table Mouse Listener
        tableEditOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditOrder.getSelectedRow();
                // Display details on the text fields
                fieldOrderID.setText(tableEditOrder.getModel().getValueAt(getRow, 0).toString());
                fieldFoodName.setText(tableEditOrder.getModel().getValueAt(getRow, 1).toString());
                fieldQuantity.setText(tableEditOrder.getModel().getValueAt(getRow, 2).toString());
                buttonEditOrder.setEnabled(true);
                buttonFulfillment.setEnabled(true);
            }
        });

        // Reset Text Fields and Buttons
        JTextField [] editTextFields = {fieldOrderID, fieldFoodName, fieldQuantity};
        for (JTextField jTextField : editTextFields){
            jTextField.setText("");
        }
        JButton [] editButtons = {buttonEditOrder, buttonFulfillment};
        for (JButton jButton : editButtons){
            jButton.setEnabled(false);
        }
        return sp;
    }

    // 1c) Used to refresh only the JScrollPane for Edit page
    public void refreshEditTable(){
        //Get the components in the panel
        Component[] componentList = panelEditOrder.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelEditOrder.remove(c);
            }
        }
        panelEditOrder.add(editTableConstruction(), 0);
        panelEditOrder.revalidate();
        panelEditOrder.repaint();
    }

    // 1d) Check if Customer Order is fulfilled based on the Edit Table
    public boolean checkOrderFulfillment(){
        boolean isCustomerOrderFulfilled = false;
        String getCurrentFulfillmentStatus = tableEditOrder.getModel().getValueAt(tableEditOrder.getSelectedRow(), 4).toString();
        if (getCurrentFulfillmentStatus.equals("Y")) {
            isCustomerOrderFulfilled = true;
        }
        return isCustomerOrderFulfilled;
    }

    // 1e)
    public void editPanelButtonOnClick(String nameOfButton){
        switch (nameOfButton){
            case "Edit Order":
                // If Customer Order fulfillment status == "Y"
                if (checkOrderFulfillment()){
                    JOptionPane.showMessageDialog(null, "Customer Order has already been fulfilled.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                // If Customer Order fulfillment status == "N"
                else{
                    int orderIDSelected = Integer.parseInt(tableEditOrder.getModel().getValueAt(tableEditOrder.getSelectedRow(), 0).toString());
                    // Check if the data keyed in Quantity Text Field is a numeric data type
                    if ((fieldQuantity.getText() != null) && (fieldQuantity.getText().matches("[0-9.]+"))){
                        int oldQuantity = Integer.parseInt(tableEditOrder.getModel().getValueAt(tableEditOrder.getSelectedRow(), 2).toString());
                        // Check if quantity text field is edited to a new value
                        int newQuantity = Integer.parseInt(fieldQuantity.getText());
                        /* If the new quantity keyed into the text field is (MUST satisfy the following conditions):
                         * a) A numeric data type
                         * b) New quantity is a different value from the old quantity value
                         */
                        if ((newQuantity != oldQuantity)){
                            StaffEditController staffEditController = new StaffEditController();
                            if (staffEditController.editCustomerOrder(orderIDSelected, newQuantity)){
                                JOptionPane.showMessageDialog(null, "Customer Order has been successful updated.", "Customer Order Update", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Customer Order has not been successful updated.", "Customer Order Update", JOptionPane.ERROR_MESSAGE);
                            }
                            // Refresh Table, text fields and disable buttons
                            refreshEditTable();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Quantity text field has not been edited.", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please enter a valid number in the quantity text field.", "Error!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Fulfill Order":
                int orderIDSelected = Integer.parseInt(tableEditOrder.getModel().getValueAt(tableEditOrder.getSelectedRow(), 0).toString());
                // If Customer Order fulfillment status == "Y"
                if (checkOrderFulfillment()){
                    JOptionPane.showMessageDialog(null, "Customer Order has already been fulfilled.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    StaffFulfillOrderController staffFulfillOrderController = new StaffFulfillOrderController();
                    if (staffFulfillOrderController.fulfillOrder(orderIDSelected)){
                        JOptionPane.showMessageDialog(null, "Customer order fulfillment is successful.", "Customer Order Fulfillment", JOptionPane.INFORMATION_MESSAGE);
                        refreshEditTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Customer order fulfilled has failed.", "Error!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
        }
    }


    /* 2. SEARCH function
    * 2a) displaySearchPanel - Display JPanel for Restaurant Staff to search orders
    * */
    public void displaySearchPanel(){
        displayTitledBorder(panelSearchOrder, "Search Customer Order"); // Display titled border



        // Add components to the JPanel
        panelSearchOrder.setPreferredSize(new Dimension(500, 550));
        panelSearchOrder.setBackground(Color.WHITE);

        staffUIFrame.add(panelSearchOrder);
        panelSearchOrder.setVisible(true);
    }


}
