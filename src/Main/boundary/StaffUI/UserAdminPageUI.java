package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.UserAdmin.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAdminPageUI extends JFrame{
    /* Variable declaration */
    private final JFrame userAdminUIFrame = new JFrame("User Admin Homepage");
    private TitledBorder titledBorder;

    /* Buttons for top of the GUI*/
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonSuspend = new JButton("Suspend");

    /* 1. CREATE Function */
    private final JPanel panelCreate = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JLabel labelCreateUsername = new JLabel("Username: ");
    private final JTextField fieldCreateUsername = new JTextField(20);
    private final JLabel labelCreatePassword = new JLabel("Password: ");
    private final JTextField fieldCreatePassword = new JTextField(20);
    private final JLabel labelCreateProfile = new JLabel("User Profile: ");
    private final JTextField fieldCreateProfile = new JTextField(20);
    private final JButton buttonCreateAccount = new JButton("Create Account");

    /* 2. EDIT Function */
    private JTable tableEditUsers;
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JLabel labelEditUsername = new JLabel("Username: ");
    private final JTextField fieldEditUsername = new JTextField(20);
    private final JLabel labelEditPassword = new JLabel("Password: ");
    private final JTextField fieldEditPassword = new JTextField(20);
    private final JLabel labelEditProfile = new JLabel("User Profile: ");
    private final JTextField fieldEditProfile = new JTextField(20);
    private final JButton buttonEditChanges = new JButton("Edit Changes");

    /* 3. VIEW function */
    private JTable tableViewUsers;
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JTextField fieldSearchUser = new JTextField(20);
    private final JButton buttonSearchUser = new JButton("Search by Username");
    private final JButton buttonSearchProfile = new JButton("Search by User Profile");

    /* 4. SUSPEND Function */
    private JTable tableSuspendUsers;
    private final JPanel panelSuspend = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JButton buttonSuspendAccount = new JButton("Suspend/Un-suspend Account");
    private final JButton buttonSuspendProfile = new JButton("Suspend/Un-suspend Profile");


    public UserAdminPageUI(){
        userAdminUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userAdminUIFrame.getContentPane().setLayout(new FlowLayout());
        userAdminUIFrame.setSize(520, 705);
        userAdminUIFrame.setResizable(false);
        userAdminUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        userAdminUIFrame.getContentPane().setBackground(Color.WHITE);

        // Add buttons & functions for the top of the GUI
        displayAdminUserButtons();

        /* Button Function for Administrator */
        // 1. CREATE function
        displayCreatePanel();

        // 2. EDIT function
        displayEditPanel();

        // 3. VIEW function
        displaySearchPanel();

        // 4. SUSPEND function
        displaySuspendPanel();

        userAdminUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
    * displayAdminUserButtons() - Method to display the buttons on top of the screen
    * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
    */
    // Method to display buttons for User Admin
    public void displayAdminUserButtons(){
        JButton [] myArray = {buttonLogout, buttonCreate, buttonEdit, buttonView, buttonSuspend};
        for (JButton jButton : myArray) {
            jButton.setPreferredSize(new Dimension(95, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            userAdminUIFrame.add(jButton);
        }
    }

    // Top buttons Listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action) {
            case "Logout" -> {
                userAdminUIFrame.dispose();
                userAdminUIFrame.setVisible(false);
                new StaffLoginPage();
            }
            case "Create" -> {
                displayCreatePanel();
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelSuspend.setVisible(false);
            }
            case "Edit" -> {
                displayEditPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(true);
                panelView.setVisible(false);
                panelSuspend.setVisible(false);
            }
            case "View" -> {
                displaySearchPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(true);
                panelSuspend.setVisible(false);
            }
            case "Suspend" -> {
                displaySuspendPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelSuspend.setVisible(true);
            }
        } // end of switch statements
    };


    /* 1. CREATE function
    * 1a) void displayCreatePanel() - Display JPanel for User Admin to create an account
    * 1b) void createButton_Onclick() - Create button function to allow the user to create an account by passing the data to the controller
    */
    // 1a) Method for User Admin to CREATE a new user account for a new employee
    public void displayCreatePanel(){
        // Border
        titledBorder = new TitledBorder("Add New User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Labels
        JLabel [] arrayCreateLabels = {labelCreateUsername, labelCreatePassword, labelCreateProfile};
        for (JLabel jLabel : arrayCreateLabels){
            jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        }

        // Text Fields
        JTextField [] arrayCreateFields = {fieldCreateUsername, fieldCreatePassword, fieldCreateProfile};
        for (JTextField jTextField : arrayCreateFields){
            jTextField.setPreferredSize(new Dimension(50, 30));
        }

        // Create Account Button
        buttonCreateAccount.setPreferredSize(new Dimension(150, 30));
        buttonCreateAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonCreateAccount.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonCreateAccount.setBackground(Color.WHITE);
        // Action listener for creation of User Account and User Profile, also ensuring that the action listener is only created once
        if (buttonCreateAccount.getActionListeners().length == 0){
            buttonCreateAccount.addActionListener(e -> createButton_Onclick());
        }

        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(420, 270));
        panelCreate.setBackground(Color.WHITE);
        panelCreate.add(labelCreateUsername);
        panelCreate.add(fieldCreateUsername);
        panelCreate.add(labelCreatePassword);
        panelCreate.add(fieldCreatePassword);
        panelCreate.add(labelCreateProfile);
        panelCreate.add(fieldCreateProfile);
        panelCreate.add(buttonCreateAccount);
        panelCreate.setBorder(titledBorder);
        panelCreate.setVisible(false);
        userAdminUIFrame.add(panelCreate);
    }

    // 1b) Create button function to allow the user to create an account by passing the data to the controller
    public void createButton_Onclick(){
        // Convert input parameters for account creation to all capital letters
        String createUsernameText = fieldCreateUsername.getText().toLowerCase();
        String createPasswordText = fieldCreatePassword.getText().toLowerCase();
        String createProfileText = fieldCreateProfile.getText().toUpperCase();
        // validateCreate() method located in AdminCreateController.java
        if (createUsernameText.isEmpty() || createPasswordText.isEmpty() || createProfileText.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            AdminCreateController addUserController = new AdminCreateController();
            if (addUserController.validateCreateAccount(createUsernameText, createPasswordText, createProfileText)){
                JOptionPane.showMessageDialog(null, "Account is created successfully.", "Account Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Account already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /* 2. EDIT function
    * 2a) void displayEditPanel(String usernameLoggedIn) - Display JPanel for User Admin to edit an account's details
    * 2b) Component editTableConstruction() - Construction of the JTable, Mouse Click Listener to display an account's details in the text field (JTable type returned as a JScrollPane type)
    * 2c) void editButton_Onclick() - Edit button function to allow the user to edit an account details by passing the data to the controller
    */
    // 2a) Method for User Admin to EDIT a user account details
    public void displayEditPanel(){
        // Border
        titledBorder = new TitledBorder("Edit User Accounts");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane editScrollPane1 = (JScrollPane) editTableConstruction();

        // Labels
        JLabel [] arrayEditLabels = {labelEditUsername, labelEditPassword, labelEditProfile};
        for (JLabel jLabel : arrayEditLabels){
            jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        }

        // Text Fields
        JTextField [] arrayEditFields = {fieldEditUsername, fieldEditPassword, fieldEditProfile};
        for (JTextField jTextField : arrayEditFields){
            jTextField.setPreferredSize(new Dimension(110, 30));
        }

        // Edit Changes Button
        buttonEditChanges.setPreferredSize(new Dimension(150, 30));
        buttonEditChanges.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonEditChanges.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonEditChanges.setBackground(Color.WHITE);
        // Action listener for editing of User Account and User Profile, also ensuring that the action listener is only created once
        if (buttonEditChanges.getActionListeners().length == 0){
            buttonEditChanges.addActionListener(e -> editButton_Onclick());
        }

        panelEdit.setPreferredSize(new Dimension(500, 490));
        panelEdit.setBackground(Color.WHITE);
        panelEdit.add(editScrollPane1);
        panelEdit.add(labelEditUsername);
        panelEdit.add(fieldEditUsername);
        panelEdit.add(labelEditPassword);
        panelEdit.add(fieldEditPassword);
        panelEdit.add(labelEditProfile);
        panelEdit.add(fieldEditProfile);
        panelEdit.add(buttonEditChanges);
        panelEdit.setBorder(titledBorder);
        panelEdit.setVisible(false);
        userAdminUIFrame.add(panelEdit);
    }

    // 2b) Construction of table variables for EDIT functions
    public Component editTableConstruction(){
        AdminViewController adminViewController = new AdminViewController();
        String [][] data = adminViewController.getUserInfo();
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableEditUsers = new JTable(data, columnTableNames);
        JScrollPane editScrollPane2 = new JScrollPane(tableEditUsers);
        editScrollPane2.setPreferredSize(new Dimension(485, 200)); // width then height

        // Table Mouse Click Listener
        tableEditUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditUsers.getSelectedRow();
                // Display details on the text fields
                fieldEditUsername.setText(tableEditUsers.getModel().getValueAt(getRow, 0).toString());
                fieldEditPassword.setText(tableEditUsers.getModel().getValueAt(getRow, 1).toString());
                fieldEditProfile.setText(tableEditUsers.getModel().getValueAt(getRow, 2).toString());
            }
        });

        // Get the components in the panel
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(editScrollPane2, 0);
        panelEdit.revalidate();
        panelEdit.repaint();

        return editScrollPane2;
    }

    // 2d) Create button function to allow the user to edit an account by passing the data to the controller
    public void editButton_Onclick(){
        String newUsername = fieldEditUsername.getText().toLowerCase();
        String newPassword = fieldEditPassword.getText().toLowerCase();
        String newProfile = fieldEditProfile.getText().toUpperCase();
        // Check if text field are left empty by the user
        if (newUsername.isEmpty() || newPassword.isEmpty() || newProfile.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int rowSelected = tableEditUsers.getSelectedRow();
            if (rowSelected != -1){
                String oldUsername = tableEditUsers.getModel().getValueAt(rowSelected,0).toString();
                AdminEditController editUserController = new AdminEditController();
                if (editUserController.editUserAccount(oldUsername, newUsername, newPassword, newProfile)){
                    JOptionPane.showMessageDialog(null, "Account has been successful updated.", "Account Update", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Account has not been successful updated.", "Account Update", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No such account!", "Account Update", JOptionPane.ERROR_MESSAGE);
            }
            //After Edit, refresh edit table
            editTableConstruction();
        }
    }


    /* 3. VIEW function
    * 3a) void displaySearchPanel() - Display JPanel for User Admin to View accounts' details
    * 3b) Component viewTableConstruction(String [][] data) - Construction of the JTable, JTable type returned as a JScrollPane type, to display search results
    * 3c) void viewButton_Onclick(String nameOfButton) - View button function to allow the user to search for a username or user profile by passing the 2 data to the controller
    * */
    // 3a) Method for User Admin to VIEW a user account details
    public void displaySearchPanel(){
        // Border
        titledBorder = new TitledBorder("View Users");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Initial Table Construction
        AdminViewController adminViewController = new AdminViewController();
        String [][] ini_data = adminViewController.getUserInfo();
        JScrollPane searchScrollPane1 = (JScrollPane) viewTableConstruction(ini_data);

        // Text field
        fieldSearchUser.setPreferredSize(new Dimension(60, 30));

        // Search Buttons (Username & User Profile)
        JButton [] butttonsForViewUsers = {buttonSearchUser, buttonSearchProfile};
        for (JButton jButton : butttonsForViewUsers){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        // Action listener for searching of User Account, also ensuring that the action listener is only created once
        if (buttonSearchUser.getActionListeners().length == 0){
            buttonSearchUser.addActionListener(e -> viewButton_Onclick("Search By Username"));
        }
        // Action listener for searching of User Profile, also ensuring that the action listener is only created once
        if (buttonSearchProfile.getActionListeners().length == 0){
            buttonSearchProfile.addActionListener(e -> viewButton_Onclick("Search By Profile"));
        }

        // Components of JPanel
        panelView.setPreferredSize(new Dimension(500, 450));
        panelView.setBackground(Color.WHITE);
        panelView.setBorder(titledBorder);
        panelView.add(searchScrollPane1);
        panelView.add(fieldSearchUser);
        panelView.add(buttonSearchUser);
        panelView.add(buttonSearchProfile);
        userAdminUIFrame.add(panelView);
        panelView.setVisible(false);
    }

    // 3b) Construction of the JTable, JTable type returned as a JScrollPane type, to display search results
    public Component viewTableConstruction(String [][] searchData){
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password", "User Profile", "Active"};
        tableViewUsers = new JTable(searchData, columnTableNames);
        JScrollPane searchScrollPane2 = new JScrollPane(tableViewUsers);
        searchScrollPane2.setPreferredSize(new Dimension(485, 200)); // width then height

        //Get the components in the panel
        Component[] componentList = panelView.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelView.remove(c);
            }
        }
        panelView.add(searchScrollPane2, 0);
        panelView.revalidate();
        panelView.repaint();

        return searchScrollPane2;
    }

    // 3c) View button function to allow the user to search for a username or user profile by passing the 2 data to the controller
    public void viewButton_Onclick(String searchStatus){
        String dataKeyedIn = fieldSearchUser.getText();
        AdminViewController adminViewController = new AdminViewController();
        String [][] data = adminViewController.searchByUsername(dataKeyedIn, searchStatus); // get data from controller which gets it from entity User Admin
        viewTableConstruction(data); // Refresh table
    }


    /* 4. SUSPEND function
    * 4a) void displaySuspendPanel() - Display JPanel for User Admin to suspend an account's details
    * 4b) Component suspendTableConstruction() - Construction of the JTable, JTable type returned as a JScrollPane type
    * 4c) void suspendButton_Onclick() - Suspend button function to allow the user to suspend a user by passing the selected username into the controller
    */
    // 4a) Method for the User Admin to SUSPEND a user account
    public void displaySuspendPanel(){
        // Border
        titledBorder = new TitledBorder("Suspend a User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane suspendScrollPane1 = (JScrollPane) suspendTableConstruction();

        // Suspend Buttons
        JButton [] buttonsForSuspend = {buttonSuspendAccount, buttonSuspendProfile};
        for (JButton jButton : buttonsForSuspend){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        // Action listener for suspend of User Account, also ensuring that the action listener is only created once
        if (buttonSuspendAccount.getActionListeners().length == 0){
            buttonSuspendAccount.addActionListener(e -> suspendUserButton_Onclick());
        }
        // Action listener for suspend of User Profile, also ensuring that the action listener is only created once
        if (buttonSuspendProfile.getActionListeners().length == 0){
            buttonSuspendProfile.addActionListener(e -> suspendProfileButton_Onclick());
        }

        // Add components to the JPanel
        panelSuspend.setPreferredSize(new Dimension(500, 330));
        panelSuspend.setBackground(Color.WHITE);
        panelSuspend.add(suspendScrollPane1);
        panelSuspend.add(buttonSuspendAccount);
        panelSuspend.add(buttonSuspendProfile);
        panelSuspend.setBorder(titledBorder);
        panelSuspend.setVisible(false);
        userAdminUIFrame.add(panelSuspend);
    }

    // 4b) Construction of the JTable, Mouse Click Listener to display an account's details in the text field (JTable type returned as a JScrollPane type)
    public Component suspendTableConstruction(){
        AdminSuspendController adminSuspendController = new AdminSuspendController();
        String [][] data = adminSuspendController.getUserInfo();
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableSuspendUsers = new JTable(data, columnTableNames);
        JScrollPane suspendScrollPane2 = new JScrollPane(tableSuspendUsers);
        suspendScrollPane2.setPreferredSize(new Dimension(485, 200)); // width then height

        //Get the components in the panel
        Component[] componentList = panelSuspend.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSuspend.remove(c);
            }
        }
        panelSuspend.add(suspendScrollPane2, 0);
        panelSuspend.revalidate();
        panelSuspend.repaint();

        return suspendScrollPane2;
    }

    // 4c) Suspend button function to allow the user to suspend a USER ACCOUNT by passing the selected username into the controller
    public void suspendUserButton_Onclick(){
        int getRow = tableSuspendUsers.getSelectedRow();
        // Ensure the User has selected a row from the table displaying all the user accounts
        if (getRow != -1){
            // Get the selected username by getting the value of the
            String selectedUsername = (String) tableSuspendUsers.getModel().getValueAt(getRow, 0);
            String currentActiveStatus = (String) tableSuspendUsers.getModel().getValueAt(getRow, 3);
            String newActiveStatus = switch (currentActiveStatus) {
                case "Y" -> "N";
                case "N" -> "Y";
                default -> "";
                // Change active status to the opposite and update the database
            };
            AdminSuspendController adminSuspendController = new AdminSuspendController();
            if (adminSuspendController.suspendUserAccount(selectedUsername, newActiveStatus)){
                if (newActiveStatus.equals("N")){
                    JOptionPane.showMessageDialog(null, "Account has been successfully suspended!", "Suspend User", JOptionPane.INFORMATION_MESSAGE);
                    suspendTableConstruction();
                }
                else if (newActiveStatus.equals("Y"))
                {
                    JOptionPane.showMessageDialog(null, "Account has been successfully un-suspended!", "Un-suspend User", JOptionPane.INFORMATION_MESSAGE);
                    suspendTableConstruction();
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Account suspension failed.", "Suspend User", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a user account to suspend from the table.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 4d) Suspend button function to allow the user to suspend a USER PROFILE by passing the selected username into the controller
    public void suspendProfileButton_Onclick(){
        int getRow = tableSuspendUsers.getSelectedRow();
        // Ensure the User has selected a row from the table displaying all the user accounts
        if (getRow != -1){
            // Get the selected user profile by getting the value of the
            String selectedUserProfile = (String) tableSuspendUsers.getModel().getValueAt(getRow, 2); // User Profile Column
            String currentActiveStatus = (String) tableSuspendUsers.getModel().getValueAt(getRow, 3); // Active Column
            String newActiveStatus = switch (currentActiveStatus) {
                case "Y" -> "N";
                case "N" -> "Y";
                default -> "";
                // Change active status to the opposite and update the database
            };
            AdminSuspendController adminSuspendController = new AdminSuspendController();
            if (adminSuspendController.suspendUserProfile(selectedUserProfile, newActiveStatus)){
                if (newActiveStatus.equals("N")){
                    JOptionPane.showMessageDialog(null, "Profile has been successfully suspended!", "Suspend User Profile", JOptionPane.INFORMATION_MESSAGE);
                    suspendTableConstruction();
                }
                else if (newActiveStatus.equals("Y"))
                {
                    JOptionPane.showMessageDialog(null, "Profile has been successfully un-suspended!", "Un-suspend User Profile", JOptionPane.INFORMATION_MESSAGE);
                    suspendTableConstruction();
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Profile suspension failed.", "Suspend User Profile", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a user profile to suspend from the table.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}