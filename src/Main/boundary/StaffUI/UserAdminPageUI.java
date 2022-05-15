package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.UserAdmin.AdminCreateController;
import Main.controller.UserAdmin.AdminEditController;
import Main.controller.UserAdmin.AdminSuspendController;
import Main.controller.UserAdmin.AdminViewSearchController;

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
    /* Buttons for top of the GUI*/
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonSuspend = new JButton("Suspend");

    /* 1. CREATE function */
    private final JPanel panelCreate = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Display Buttons
    private final JButton buttonDisplayCreateAccount = new JButton("User Account");
    private final JButton buttonDisplayCreateProfile = new JButton("User Profile");
    /* 1a) Create User Account */
    // Labels
    private final JLabel labelCreateUsername = new JLabel("Username: ", SwingConstants.CENTER);
    private final JLabel labelCreatePassword = new JLabel("Password: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldCreateUsername = new JTextField(20);
    private final JTextField fieldCreatePassword = new JTextField(20);
    // Choice
    private final Choice choiceCreateProfile = new Choice();
    // Button
    private final JButton buttonCreateUserAccount = new JButton("Create User Account");
    /* 1b) Create User Profile */
    // Label
    private final JLabel labelCreateProfile = new JLabel("User Profile: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldCreateProfile = new JTextField(20);
    // Button
    private final JButton buttonCreateUserProfile = new JButton("Create User Profile");

    /* 2. EDIT function */
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Display Buttons
    private final JButton buttonDisplayEditAccount = new JButton("User Account");
    private final JButton buttonDisplayEditProfile = new JButton("User Profile");
    /* 2a) Edit User Account */
    // Table
    private JTable tableEditAccount;
    // Labels
    private final JLabel labelEditUsername = new JLabel("Username: ", SwingConstants.CENTER);
    private final JLabel labelEditPassword = new JLabel("Password: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldEditUsername = new JTextField(20);
    private final JTextField fieldEditPassword = new JTextField(20);
    // Choice
    private final Choice choiceEditProfile = new Choice();
    // Button
    private final JButton buttonEditUserAccount = new JButton("Edit User Account");
    /* 2b) Edit User Profiles */
    // Table
    private JTable tableEditProfile;
    // Labels
    private final JLabel labelEditProfileID = new JLabel("Profile ID: ", SwingConstants.CENTER);
    private final JLabel labelEditProfileName = new JLabel("Profile Name: ", SwingConstants.CENTER);
    // Text Fields
    private final JTextField fieldEditProfileID = new JTextField(20);
    private final JTextField fieldEditProfileName = new JTextField(20);
    // Button
    private final JButton buttonEditUserProfile = new JButton("Edit User Profile");

    /* 3. VIEW/SEARCH function */
    private final JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Display Buttons
    private final JButton buttonDisplaySearchAccount = new JButton("User Account");
    private final JButton buttonDisplaySearchProfile = new JButton("User Profile");
    /* 3a) View/Search User Accounts */
    // Table
    private JTable tableSearchAccount;
    // Label
    private final JLabel labelSearchUsername = new JLabel("Username: ", SwingConstants.CENTER);
    // Text Field
    private final JTextField fieldSearchUsername = new JTextField(20);
    // Button
    private final JButton buttonSearchUserAccount = new JButton("Search By Username");
    /* 3a) View/Search User Profiles */
    // Table
    private JTable tableSearchProfiles;
    // Label
    private final JLabel labelSearchProfile = new JLabel("Profile Name: ", SwingConstants.CENTER);
    // Text Field
    private final JTextField fieldSearchProfile = new JTextField(20);
    // Button
    private final JButton buttonSearchUserProfile = new JButton("Search By Profile Name");

    /* 4. SUSPEND function */
    private final JPanel panelSuspend = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    // Tables
    private JTable tableSuspendAccount;
    private JTable tableSuspendProfiles;
    // Display Buttons
    private final JButton buttonDisplaySuspendAccount = new JButton("User Account");
    private final JButton buttonDisplaySuspendProfile = new JButton("User Profile");
    // Buttons
    private final JButton buttonSuspendUserAccount = new JButton("Suspend / Unsuspend User Account");
    private final JButton buttonSuspendUserProfile = new JButton("Suspend / Unsuspend User Profile");


    public UserAdminPageUI(){
        userAdminUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userAdminUIFrame.getContentPane().setLayout(new FlowLayout());
        userAdminUIFrame.setSize(520, 705);
        userAdminUIFrame.setResizable(false);
        userAdminUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        userAdminUIFrame.getContentPane().setBackground(Color.WHITE);

        // Add buttons & functions for the top of the GUI
        displayUserAdminButtons();

        /* Button Function for User Admin */
        // 1. CREATE function
        displayCreatePanel();

        // 2. EDIT function
        displayEditPanel();

        // 3. VIEW/SEARCH function
        displaySearchPanel();

        // 4. SUSPEND function
        displaySuspendPanel();
        panelSuspend.setVisible(true);

        userAdminUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
     * displayUserAdminButtons() - Method to display the buttons on top of the screen
     * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
     * void displayTitledBorder(JPanel panel, String nameOfPanel) - Method to construct and display of the Titled Border
     */
    // Method to display buttons for User Admin
    public void displayUserAdminButtons(){
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

    // Top Button Listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch(action){
            case "Logout" -> {
                userAdminUIFrame.dispose();
                userAdminUIFrame.setVisible(false);
                new StaffLoginPage();
            }
            case "Create" -> {
                displayCreatePanel();
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                panelSuspend.setVisible(false);
            }
            case "Edit" -> {
                displayEditPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(true);
                panelSearch.setVisible(false);
                panelSuspend.setVisible(false);
            }
            case "View" -> {
                displaySearchPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelSearch.setVisible(true);
                panelSuspend.setVisible(false);
            }
            case "Suspend" -> {
                displaySuspendPanel();
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelSearch.setVisible(false);
                panelSuspend.setVisible(true);
            }
        } // end of switch statements
    };

    // Method to construct and display of the Titled Border
    public void displayTitledBorder(JPanel panel, String nameOfPanel){
        TitledBorder titledBorder = new TitledBorder(nameOfPanel);
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        panel.setBorder(titledBorder);
    }

    /* 1. CREATE function
     * 1a) void displayCreatePanel() - Display JPanel for User Admin to create an account or profile
     * 1b) void buttonCreateChoices_Onclick() - Allows the user to choose between creation of User Account or User Profile
     * 1c) void buttonCreateUserAccount_Onclick() - Create button function to allow the user to create a User Account by passing the data to the controller
     */
    // 1a) Display JPanel for User Admin to create an account or profile
    public void displayCreatePanel(){
        displayTitledBorder(panelCreate, "Create User Account / User Profile");

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplayCreateAccount, buttonDisplayCreateProfile};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelCreate.add(buttonDisplayCreateAccount, 0);
        panelCreate.add(buttonDisplayCreateProfile, 1);
        // Action listener for displaying creation UI of User Accounts, also ensuring that the action listener is only created once
        if (buttonDisplayCreateAccount.getActionListeners().length == 0){
            buttonDisplayCreateAccount.addActionListener(e -> buttonCreateChoices_Onclick("User Account"));
        }
        // Action listener for displaying creation UI of User Profiles, also ensuring that the action listener is only created once
        if (buttonDisplayCreateProfile.getActionListeners().length == 0){
            buttonDisplayCreateProfile.addActionListener(e -> buttonCreateChoices_Onclick("User Profile"));
        }

        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(500, 320));
        panelCreate.setBackground(Color.WHITE);
        panelCreate.setVisible(false);
        userAdminUIFrame.add(panelCreate);
    }

    // 1b) Allows the user to choose between creation of User Account or User Profile, then display the respective GUI components
    public void buttonCreateChoices_Onclick(String buttonPressed){
        /* REMOVE COMPONENTS */
        Component[] componentList = panelCreate.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelCreate.remove(c);
            }
        }
        panelCreate.add(buttonDisplayCreateAccount, 0);
        panelCreate.add(buttonDisplayCreateProfile, 1);
        panelCreate.revalidate();
        panelCreate.repaint();

        // Display relevant components based on the button PRESSED
        switch(buttonPressed){
            case "User Account" -> {
                // Disable pressed button
                buttonDisplayCreateAccount.setEnabled(false);
                buttonDisplayCreateProfile.setEnabled(true);

                // Labels
                JLabel [] arrayCreateLabels = {labelCreateUsername, labelCreatePassword};
                for (JLabel jLabel : arrayCreateLabels){
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField [] arrayCreateFields = {fieldCreateUsername, fieldCreatePassword};
                for (JTextField jTextField : arrayCreateFields){
                    jTextField.setPreferredSize(new Dimension(50, 30));
                }
                // Choice/Dropdown list
                choiceCreateProfile.setPreferredSize(new Dimension(250, 30));
                AdminCreateController adminCreateController = new AdminCreateController();
                String [] arrayAllProfiles = adminCreateController.getArrayOfProfiles();
                choiceCreateProfile.removeAll();
                // To prevent duplicated values in the dropdown list
                if (choiceCreateProfile.getItemCount() != arrayAllProfiles.length){
                    for (String arrayAllProfile : arrayAllProfiles) {
                        choiceCreateProfile.add(arrayAllProfile);
                    }
                }
                // Button
                buttonCreateUserAccount.setPreferredSize(new Dimension(250, 30));
                buttonCreateUserAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonCreateUserAccount.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonCreateUserAccount.setBackground(Color.WHITE);
                // Action listener for creation of User Accounts, also ensuring that the action listener is only created once
                if (buttonCreateUserAccount.getActionListeners().length == 0){
                    buttonCreateUserAccount.addActionListener(e -> buttonCreateUserAccount_Onclick());
                }

                // Add respective components to the JPanel
                panelCreate.add(labelCreateUsername);
                panelCreate.add(fieldCreateUsername);
                panelCreate.add(labelCreatePassword);
                panelCreate.add(fieldCreatePassword);
                panelCreate.add(choiceCreateProfile);
                panelCreate.add(buttonCreateUserAccount);
            }
            case "User Profile" -> {
                // Disable pressed button
                buttonDisplayCreateProfile.setEnabled(false);
                buttonDisplayCreateAccount.setEnabled(true);

                // Labels
                labelCreateProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                // Text Field
                fieldCreateProfile.setPreferredSize(new Dimension(50, 30));
                // Button
                buttonCreateUserProfile.setPreferredSize(new Dimension(250, 30));
                buttonCreateUserProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonCreateUserProfile.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonCreateUserProfile.setBackground(Color.WHITE);
                // Action listener for creation of User Profiles, also ensuring that the action listener is only created once
                if (buttonCreateUserProfile.getActionListeners().length == 0){
                    buttonCreateUserProfile.addActionListener(e -> buttonCreateUserProfile_Onclick());
                }

                // Add respective components to the JPanel
                panelCreate.add(labelCreateProfile);
                panelCreate.add(fieldCreateProfile);
                panelCreate.add(buttonCreateUserProfile);
            }
        } // end of switch statements
    }

    // 1c) Create button function to allow the user to create a User Account by passing the data to the controller
    public void buttonCreateUserAccount_Onclick(){
        // Check if either text field is empty
        if (fieldCreateUsername.getText().isEmpty() || fieldCreatePassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        // If Text Fields are filled up
        else{
            String username = fieldCreateUsername.getText().toLowerCase();
            String password = fieldCreatePassword.getText().toLowerCase();
            String profile = choiceCreateProfile.getSelectedItem();
            // Determine if the user account exist, if not, create a User Account
            AdminCreateController addUserController = new AdminCreateController();
            if (addUserController.validateCreateAccount(username, password, profile)){
                JOptionPane.showMessageDialog(null, "User Account is created successfully.", "User Account Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "User Account already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } // end of if-else statements
    }

    // 1d) Create button function to allow the user to create a User Profile by passing the data to the controller
    public void buttonCreateUserProfile_Onclick(){
        // Check if text field is empty
        if (fieldCreateProfile.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String createProfile = fieldCreateProfile.getText().toLowerCase();
            // Determine if the user profile exist, if not, create a User Account
            AdminCreateController addUserController = new AdminCreateController();
            if (addUserController.validateCreateProfile(createProfile)){
                JOptionPane.showMessageDialog(null, "User Profile is created successfully.", "User Profile Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "User Profile already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /* 2. EDIT function
    * 2a) void displayEditPanel() - Display JPanel for User Admin to edit an account or profile
    * 2b) void buttonEditChoices_Onclick(String buttonPressed) - Allows the user to choose between editing of User Account or User Profile
    * Methods 2c and 2d belongs to EDITING User Accounts
    * 2c) Component editAccountTableConstruction() - Construction of table variables for EDIT USER ACCOUNT functions
    * 2d) void buttonEditUserAccount_Onclick() - Edit User Account button function to allow the user to edit a User Account by passing the data to the controller
    * Methods 2e and 2f belongs to EDITING User Profiles
    * 2e) Component editProfileTableConstruction() - Construction of table variables for EDIT USER PROFILE functions
    * 2f) void buttonEditUserAccount_Onclick() - Edit User Profile button function to allow the user to edit a User Profile by passing the data to the controller
    * */
    // 2a) Display JPanel for User Admin to edit an account or profile
    public void displayEditPanel(){
        displayTitledBorder(panelEdit, "Edit User Account / User Profile");

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
        JButton [] buttonChoices = {buttonDisplayEditAccount, buttonDisplayEditProfile};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelEdit.add(buttonDisplayEditAccount, 0);
        panelEdit.add(buttonDisplayEditProfile, 1);
        // Action listener for displaying editing UI of User Account, also ensuring that the action listener is only created once
        if (buttonDisplayEditAccount.getActionListeners().length == 0) {
            buttonDisplayEditAccount.addActionListener(e -> buttonEditChoices_Onclick("User Account"));
        }
        // Action listener for displaying editing UI of User Profile, also ensuring that the action listener is only created once
        if (buttonDisplayEditProfile.getActionListeners().length == 0) {
            buttonDisplayEditProfile.addActionListener(e -> buttonEditChoices_Onclick("User Profile"));
        }

        // Add components to the JPanel
        panelEdit.setPreferredSize(new Dimension(500, 550));
        panelEdit.setBackground(Color.WHITE);
        panelEdit.setVisible(false);
        userAdminUIFrame.add(panelEdit);
    }

    // 2b) Allows the user to choose between editing of User Account or User Profile
    public void buttonEditChoices_Onclick(String buttonPressed){
        // Remove all components then add choice buttons on the top
        Component[] componentList = panelEdit.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(buttonDisplayEditAccount, 0);
        panelEdit.add(buttonDisplayEditProfile, 1);
        panelEdit.revalidate();
        panelEdit.repaint();

        // Display relevant components based on the button pressed
        switch(buttonPressed){
            case "User Account" -> {
                buttonDisplayEditAccount.setEnabled(false);
                buttonDisplayEditProfile.setEnabled(true);
                // Table
                JScrollPane editUserAccountScrollPane = (JScrollPane) editAccountTableConstruction();
                panelEdit.add(editUserAccountScrollPane); // Add table to JPanel
                // Labels
                JLabel[] arrayEditAccountLabels = {labelEditUsername, labelEditPassword};
                for (JLabel jLabel : arrayEditAccountLabels) {
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] arrayEditAccountTextFields = {fieldEditUsername, fieldEditPassword};
                for (int i = 0; i < arrayEditAccountTextFields.length; i++) {
                    arrayEditAccountTextFields[i].setPreferredSize(new Dimension(50, 30));
                }
                // Choice/Dropdown list
                choiceEditProfile.setPreferredSize(new Dimension(250, 30));
                AdminEditController adminEditController = new AdminEditController();
                String [] arrayAllProfiles = adminEditController.getArrayOfProfiles();
                choiceEditProfile.removeAll();
                // To prevent duplicated values in the dropdown list
                if (choiceEditProfile.getItemCount() != arrayAllProfiles.length){
                    for (String arrayAllProfile : arrayAllProfiles) {
                        choiceEditProfile.add(arrayAllProfile);
                    }
                }
                // Button
                buttonEditUserAccount.setPreferredSize(new Dimension(250, 30));
                buttonEditUserAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonEditUserAccount.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonEditUserAccount.setBackground(Color.WHITE);
                // Action listener for creation of User Profiles, also ensuring that the action listener is only created once
                if (buttonEditUserAccount.getActionListeners().length == 0){
                    buttonEditUserAccount.addActionListener(e -> buttonEditUserAccount_Onclick());
                }
                // Add Components to the JPanel
                panelEdit.add(labelEditUsername);
                panelEdit.add(fieldEditUsername);
                panelEdit.add(labelEditPassword);
                panelEdit.add(fieldEditPassword);
                panelEdit.add(choiceEditProfile);
                panelEdit.add(buttonEditUserAccount);
            }
            case "User Profile" -> {
                buttonDisplayEditProfile.setEnabled(false);
                buttonDisplayEditAccount.setEnabled(true);
                // Table
                JScrollPane editUserProfileScrollPane = (JScrollPane) editProfileTableConstruction();
                panelEdit.add(editUserProfileScrollPane); // Add table to JPanel
                // Labels
                JLabel[] arrayEditProfileLabels = {labelEditProfileID, labelEditProfileName};
                for (JLabel jLabel : arrayEditProfileLabels) {
                    jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                }
                // Text Fields
                JTextField[] arrayEditProfileTextFields = {fieldEditProfileID, fieldEditProfileName};
                for (int i = 0; i < arrayEditProfileTextFields.length; i++) {
                    arrayEditProfileTextFields[i].setPreferredSize(new Dimension(50, 30));
                }
                fieldEditProfileID.setEditable(false);
                // Button
                buttonEditUserProfile.setPreferredSize(new Dimension(250, 30));
                buttonEditUserProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonEditUserProfile.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonEditUserProfile.setBackground(Color.WHITE);
                // Action listener for creation of User Profiles, also ensuring that the action listener is only created once
                if (buttonEditUserProfile.getActionListeners().length == 0){
                    buttonEditUserProfile.addActionListener(e -> buttonEditUserProfile_Onclick());
                }
                // Add Components to the JPanel
                panelEdit.add(labelEditProfileID);
                panelEdit.add(fieldEditProfileID);
                panelEdit.add(labelEditProfileName);
                panelEdit.add(fieldEditProfileName);
                panelEdit.add(buttonEditUserProfile);
            }
        } // end of switch statements
    }

    /* Methods 2c and 2d belongs to EDITING User Accounts */
    // 2c) Construction of table variables for EDIT USER ACCOUNT functions
    public Component editAccountTableConstruction(){
        // Display Table and table click listener
        AdminEditController adminEditController = new AdminEditController();
        String [][] data = adminEditController.getArrayOfAccounts();
        // Display data in a table format
        String[] columnTableNames = {"Username", "Password", "User Profile", "Active"};
        tableEditAccount = new JTable(data, columnTableNames);
        JScrollPane editUserAccountScrollPane1 = new JScrollPane(tableEditAccount);
        editUserAccountScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height
        // Table Click Listener
        tableEditAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditAccount.getSelectedRow();
                // Display details on the text fields
                fieldEditUsername.setText(tableEditAccount.getModel().getValueAt(getRow, 0).toString());
                fieldEditPassword.setText(tableEditAccount.getModel().getValueAt(getRow, 1).toString());
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
        panelEdit.add(editUserAccountScrollPane1, 2);
        panelEdit.revalidate();
        panelEdit.repaint();
        return editUserAccountScrollPane1;
    }

    // 2d) Edit User Account button function to allow the user to edit a Menu Item by passing the data to the controller
    public void buttonEditUserAccount_Onclick(){
        if (fieldEditUsername.getText().isEmpty() || fieldEditPassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int rowSelected = tableEditAccount.getSelectedRow();
            // Check if the User Admin Actor doesn't key in an invalid User Account
            if (rowSelected != -1){
                String oldUsername = tableEditAccount.getModel().getValueAt(rowSelected,0).toString();
                String newUsername = fieldEditUsername.getText().toLowerCase();
                String newPassword = fieldEditPassword.getText().toLowerCase();
                String newProfile = choiceEditProfile.getSelectedItem();
                AdminEditController adminEditController = new AdminEditController();
                // Send data over to the controller
                if (adminEditController.editUserAccount(oldUsername, newUsername, newPassword, newProfile)){
                    JOptionPane.showMessageDialog(null, "User Account has been successful updated.", "Account Update", JOptionPane.INFORMATION_MESSAGE);
                    // Empty the text fields upon successful editing and refresh the table
                    fieldEditUsername.setText("");
                    fieldEditPassword.setText("");
                    editAccountTableConstruction();
                }
                else{
                    JOptionPane.showMessageDialog(null, "User Account has not been successful updated.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No such account!", "Error!", JOptionPane.ERROR_MESSAGE);
            } // end of if-else statements
        } // end of if-else statements
    }

    /* Methods 2e and 2f belongs to EDITING User Profiles */
    // 2e) Construction of table variables for EDIT USER PROFILE functions
    public Component editProfileTableConstruction(){
        // Display Table and table click listener
        AdminEditController adminEditController = new AdminEditController();
        String [][] data = adminEditController.getProfileTable();
        // Display data in a table format
        String[] columnTableNames = {"Profile ID", "Profile Name", "Active"};
        tableEditProfile = new JTable(data, columnTableNames);
        JScrollPane editUserProfileScrollPane1 = new JScrollPane(tableEditProfile);
        editUserProfileScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height
        // Table Click Listener
        tableEditProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableEditProfile.getSelectedRow();
                // Display details on the text fields
                fieldEditProfileID.setText(tableEditProfile.getModel().getValueAt(getRow, 0).toString());
                fieldEditProfileName.setText(tableEditProfile.getModel().getValueAt(getRow, 1).toString());
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
        panelEdit.add(editUserProfileScrollPane1, 2);
        panelEdit.revalidate();
        panelEdit.repaint();
        return editUserProfileScrollPane1;
    }

    // 2f) Edit User Profile button function to allow the user to edit a User Profile by passing the data to the controller
    public void buttonEditUserProfile_Onclick(){
        if (fieldEditProfileName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int rowSelected = tableEditProfile.getSelectedRow();
            // Check if the User Admin Actor doesn't key in an invalid User Profile
            if (rowSelected != -1){
                int selectedProfileID = Integer.parseInt(tableEditProfile.getModel().getValueAt(rowSelected,0).toString());
                String newProfile = fieldEditProfileName.getText().toLowerCase();
                AdminEditController adminEditController = new AdminEditController();
                // Send data over to the controller
                if (adminEditController.editUserProfile(selectedProfileID, newProfile)){
                    JOptionPane.showMessageDialog(null, "User Profile has been successful updated.", "Profile Update", JOptionPane.INFORMATION_MESSAGE);
                    // Empty the text fields upon successful editing and refresh the table
                    fieldEditProfileID.setText("");
                    fieldEditProfileName.setText("");
                    editProfileTableConstruction();
                }
                else{
                    JOptionPane.showMessageDialog(null, "User Account has not been successful updated.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No such profile!", "Error!", JOptionPane.ERROR_MESSAGE);
            } // end of if-else statements
        } // end of if-else statements
    }


    /* 3. VIEW/SEARCH function
    * 3a) void displaySearchPanel() - Display JPanel for User Admin to View & Search for an account or profile
    * 3b) void buttonSearchChoices_Onclick(String buttonPressed) - Allows the user to choose between viewing/searching of User Account or User Profile
    * Methods 3c and 3d belongs to VIEWING & SEARCHING User Accounts
    * 3c) Component viewAccountTableConstruction(String [][] data) - Construction of table variables for SEARCHING USER ACCOUNT functions
    * 3d) void buttonViewUserAccount_Onclick() - View button function to allow the user to search for a username by passing the data to the controller
    * Methods 3e and 3f to VIEWING & SEARCHING User Profiles
    * 3e) Component viewProfileTableConstruction(String [][] data) - Construction of table variables for SEARCHING USER PROFILE functions
    * 3f) void buttonSearchUserProfile_Onclick() - Search User Profile button function to allow the user to search for a User Profile by passing the data to the controller
    * */
    // 3a) Display JPanel for User Admin to View & Search for an account or profile
    public void displaySearchPanel(){
        displayTitledBorder(panelSearch, "View & Search User Account / User Profile");

        // Remove all components then add choice buttons on the top
        Component[] componentList = panelSearch.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelSearch.remove(c);
            }
            if (c instanceof JButton){
                c.setEnabled(true);
            }
        }

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplaySearchAccount, buttonDisplaySearchProfile};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelSearch.add(buttonDisplaySearchAccount, 0);
        panelSearch.add(buttonDisplaySearchProfile, 1);
        // Action listener for displaying viewing/searching UI of User Account, also ensuring that the action listener is only created once
        if (buttonDisplaySearchAccount.getActionListeners().length == 0) {
            buttonDisplaySearchAccount.addActionListener(e -> buttonSearchChoices_Onclick("User Account"));
        }
        // Action listener for displaying viewing/searching UI of User Profile, also ensuring that the action listener is only created once
        if (buttonDisplaySearchProfile.getActionListeners().length == 0) {
            buttonDisplaySearchProfile.addActionListener(e -> buttonSearchChoices_Onclick("User Profile"));
        }

        // Add components to the JPanel
        panelSearch.setPreferredSize(new Dimension(500, 550));
        panelSearch.setBackground(Color.WHITE);
        panelSearch.setVisible(false);
        userAdminUIFrame.add(panelSearch);
    }

    // 3b) Allows the user to choose between viewing/searching of User Account or User Profile
    public void buttonSearchChoices_Onclick(String buttonPressed){
        // Remove all components then add choice buttons on the top
        Component[] componentList = panelSearch.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelSearch.remove(c);
            }
        }
        panelSearch.add(buttonDisplaySearchAccount, 0);
        panelSearch.add(buttonDisplaySearchProfile, 1);
        panelSearch.revalidate();
        panelSearch.repaint();

        // Display the relevant components based on the button pressed
        switch(buttonPressed){
            case "User Account" -> {
                buttonDisplaySearchAccount.setEnabled(false);
                buttonDisplaySearchProfile.setEnabled(true);

                /* Table
                * Call Controller to get a 2D array of all User Accounts to construct the table
                */
                AdminViewSearchController adminViewSearchController = new AdminViewSearchController();
                String [][] arrayAllAccountData =  adminViewSearchController.getUserAccounts();
                JScrollPane searchUserAccountScrollPane = (JScrollPane) viewAccountTableConstruction(arrayAllAccountData);
                // Label
                labelSearchUsername.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                // Text Field
                fieldSearchUsername.setPreferredSize(new Dimension(50, 30));
                // Button
                buttonSearchUserAccount.setPreferredSize(new Dimension(220, 30));
                buttonSearchUserAccount.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                buttonSearchUserAccount.setBackground(Color.WHITE);
                // Action listener for searching of User Account, also ensuring that the action listener is only created once
                if (buttonSearchUserAccount.getActionListeners().length == 0){
                    buttonSearchUserAccount.addActionListener(e -> buttonSearchUserAccount_Onclick());
                }

                // Add Components to the JPanel
                panelSearch.add(searchUserAccountScrollPane);
                panelSearch.add(labelSearchUsername);
                panelSearch.add(fieldSearchUsername);
                panelSearch.add(buttonSearchUserAccount);
            }
            case "User Profile" -> {
                buttonDisplaySearchProfile.setEnabled(false);
                buttonDisplaySearchAccount.setEnabled(true);

                /* Table
                 * Call Controller to get a 2D array of all User Profiles to construct the table
                 */
                AdminViewSearchController adminViewSearchController = new AdminViewSearchController();
                String [][] arrayAllProfileData = adminViewSearchController.getUserProfiles();
                JScrollPane searchUserProfileScrollPane = (JScrollPane) viewProfileTableConstruction(arrayAllProfileData);
                // Label
                labelSearchProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18));
                // Text Field
                fieldSearchProfile.setPreferredSize(new Dimension(50, 30));
                // Button
                buttonSearchUserProfile.setPreferredSize(new Dimension(220, 30));
                buttonSearchUserProfile.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                buttonSearchUserProfile.setBackground(Color.WHITE);
                // Action listener for searching of User Account, also ensuring that the action listener is only created once
                if (buttonSearchUserProfile.getActionListeners().length == 0){
                    buttonSearchUserProfile.addActionListener(e -> buttonSearchUserProfile_Onclick());
                }

                // Add Components to the JPanel
                panelSearch.add(searchUserProfileScrollPane);
                panelSearch.add(labelSearchProfile);
                panelSearch.add(fieldSearchProfile);
                panelSearch.add(buttonSearchUserProfile);
            }
        } // end of switch statements
    }

    // 3c) Construction of table variables for SEARCHING USER ACCOUNT functions
    public Component viewAccountTableConstruction(String [][] data){
        // Display data in a table format
        String[] columnTableNames = {"Username", "Password", "User Profile", "Active"};
        tableSearchAccount = new JTable(data, columnTableNames);
        JScrollPane searchUserAccountScrollPane1 = new JScrollPane(tableSearchAccount);
        searchUserAccountScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelSearch.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSearch.remove(c);
            }
        }
        panelSearch.add(searchUserAccountScrollPane1, 2);
        panelSearch.revalidate();
        panelSearch.repaint();
        return searchUserAccountScrollPane1;
    }

    // 3d) View button function to allow the user to search for a username by passing the data to the controller
    public void buttonSearchUserAccount_Onclick(){
        // Check if text field is empty
        if (fieldSearchUsername.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String dataKeyedIn = fieldSearchUsername.getText();
            AdminViewSearchController adminViewSearchController = new AdminViewSearchController();
            String [][] arrayAllSearchedData = adminViewSearchController.searchByUsername(dataKeyedIn);
            // Refresh Table
            viewAccountTableConstruction(arrayAllSearchedData);
        } // end of if-else statements
    }

    // 3e) Construction of table variables for SEARCHING USER PROFILE functions
    public Component viewProfileTableConstruction(String [][] data){
        // Display data in a table format
        String[] columnTableNames = {"Profile ID", "Profile Name", "Active"};
        tableSearchProfiles = new JTable(data, columnTableNames);
        JScrollPane searchUserProfileScrollPane1 = new JScrollPane(tableSearchProfiles);
        searchUserProfileScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelSearch.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSearch.remove(c);
            }
        }
        panelSearch.add(searchUserProfileScrollPane1, 2);
        panelSearch.revalidate();
        panelSearch.repaint();
        return searchUserProfileScrollPane1;
    }

    // 3f) Search User Profile button function to allow the user to search for a User Profile by passing the data to the controller
    public void buttonSearchUserProfile_Onclick(){
        // Check if text field is empty
        if (fieldSearchProfile.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String dataKeyedIn = fieldSearchProfile.getText();
            AdminViewSearchController adminViewSearchController = new AdminViewSearchController();
            String [][] arrayAllSearchedData = adminViewSearchController.searchByProfile(dataKeyedIn);
            // Refresh Table
            viewProfileTableConstruction(arrayAllSearchedData);
        } // end of if-else statements
    }


    /* 4. SUSPEND function
    * 4a) void displaySuspendPanel() - Display JPanel for User Admin to suspend an account or profile
    * 4b) buttonSuspendChoices_Onclick(String buttonPressed) - Allows the user to choose between suspension of User Account or User Profile
    * Methods 4c and 4d are for suspending User Accounts
    * 4c) Component suspendAccountTableConstruction() - Construction of table variables for SUSPEND USER ACCOUNT functions
    * 4d) void buttonSuspendUserAccount_Onclick() - Suspend button function to allow the user to suspend a User Account by their username by passing the data to the controller
    * Methods 4e and 4f are for suspending User Profiles
    * 4e) Component suspendProfileTableConstruction() - Construction of table variables for SUSPEND USER PROFILE functions
    * 4f) void buttonSuspendUserProfile_Onclick() - Suspend button function to allow the user to suspend a User Profile by their username by passing the data to the controller
    * */
    // 4a) Display JPanel for User Admin to suspend an account or profile
    public void displaySuspendPanel(){
        displayTitledBorder(panelSuspend, "Suspend & Unsuspend User Account / User Profile");

        // Remove all components then add choice buttons on the top
        Component[] componentList = panelSuspend.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelSuspend.remove(c);
            }
            if (c instanceof JButton){
                c.setEnabled(true);
            }
        }

        // Choice buttons on top
        JButton [] buttonChoices = {buttonDisplaySuspendAccount, buttonDisplaySuspendProfile};
        for (JButton jButton : buttonChoices){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
        }
        panelSuspend.add(buttonDisplaySuspendAccount, 0);
        panelSuspend.add(buttonDisplaySuspendProfile, 1);
        // Action listener for displaying suspend UI of User Account, also ensuring that the action listener is only created once
        if (buttonDisplaySuspendAccount.getActionListeners().length == 0) {
            buttonDisplaySuspendAccount.addActionListener(e -> buttonSuspendChoices_Onclick("User Account"));
        }
        // Action listener for displaying suspend UI of User Profile, also ensuring that the action listener is only created once
        if (buttonDisplaySuspendProfile.getActionListeners().length == 0) {
            buttonDisplaySuspendProfile.addActionListener(e -> buttonSuspendChoices_Onclick("User Profile"));
        }

        // Add components to the JPanel
        panelSuspend.setPreferredSize(new Dimension(500, 550));
        panelSuspend.setBackground(Color.WHITE);
        panelSuspend.setVisible(false);
        userAdminUIFrame.add(panelSuspend);
    }

    // 4b) Allows the user to choose between suspension of User Account or User Profile
    public void buttonSuspendChoices_Onclick(String buttonPressed){
        // Remove all components then add choice buttons on the top
        Component[] componentList = panelSuspend.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField || c instanceof JScrollPane || c instanceof Choice){
                //Remove it
                panelSuspend.remove(c);
            }
        }
        panelSuspend.add(buttonDisplaySuspendAccount, 0);
        panelSuspend.add(buttonDisplaySuspendProfile, 1);
        panelSuspend.revalidate();
        panelSuspend.repaint();

        // Display relevant components based on the button pressed
        switch(buttonPressed){
            case "User Account" -> {
                buttonDisplaySuspendAccount.setEnabled(false);
                buttonDisplaySuspendProfile.setEnabled(true);
                // Table
                JScrollPane suspendUserAccountScrollPane = (JScrollPane) suspendAccountTableConstruction();
                // Button
                buttonSuspendUserAccount.setPreferredSize(new Dimension(280, 30));
                buttonSuspendUserAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonSuspendUserAccount.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonSuspendUserAccount.setBackground(Color.WHITE);
                // Action listener for creation of User Account, also ensuring that the action listener is only created once
                if (buttonSuspendUserAccount.getActionListeners().length == 0){
                    buttonSuspendUserAccount.addActionListener(e -> buttonSuspendUserAccount_Onclick());
                }
                // Add Components to the JPanel
                panelSuspend.add(suspendUserAccountScrollPane); // Add table to JPanel
                panelSuspend.add(buttonSuspendUserAccount);
            }
            case "User Profile" -> {
                buttonDisplaySuspendProfile.setEnabled(false);
                buttonDisplaySuspendAccount.setEnabled(true);
                // Table
                JScrollPane suspendUserProfileScrollPane = (JScrollPane) suspendProfileTableConstruction();
                // Button
                buttonSuspendUserProfile.setPreferredSize(new Dimension(280, 30));
                buttonSuspendUserProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
                buttonSuspendUserProfile.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                buttonSuspendUserProfile.setBackground(Color.WHITE);
                // Action listener for creation of User Profile, also ensuring that the action listener is only created once
                if (buttonSuspendUserProfile.getActionListeners().length == 0){
                    buttonSuspendUserProfile.addActionListener(e -> buttonSuspendUserProfile_Onclick());
                }
                // Add Components to the JPanel
                panelSuspend.add(suspendUserProfileScrollPane); // Add table to JPanel
                panelSuspend.add(buttonSuspendUserProfile);
            }
        } // end of switch statements
    }

    // 4c) Construction of table variables for SUSPEND USER ACCOUNT functions
    public Component suspendAccountTableConstruction(){
        // Display Table and table click listener
        AdminSuspendController adminSuspendController = new AdminSuspendController();
        String [][] data = adminSuspendController.getUserAccounts();
        // Display data in a table format
        String[] columnTableNames = {"Username", "Password", "User Profile", "Active"};
        tableSuspendAccount = new JTable(data, columnTableNames);
        JScrollPane suspendUserAccountScrollPane1 = new JScrollPane(tableSuspendAccount);
        suspendUserAccountScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelSuspend.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSuspend.remove(c);
            }
        }
        panelSuspend.add(suspendUserAccountScrollPane1, 2);
        panelSuspend.revalidate();
        panelSuspend.repaint();
        return suspendUserAccountScrollPane1;
    }

    // 4d) Suspend button function to allow the user to suspend a User Account by their username by passing the data to the controller
    public void buttonSuspendUserAccount_Onclick(){
        int rowSelected = tableSuspendAccount.getSelectedRow();
        if (rowSelected != -1){
            String selectedUsername = tableSuspendAccount.getModel().getValueAt(rowSelected,0).toString();
            String currentActiveStatus = tableSuspendAccount.getModel().getValueAt(rowSelected, 3).toString();
            String newActiveStatus = switch (currentActiveStatus) {
                case "Y" -> "N";
                case "N" -> "Y";
                default -> "";
                // Change active status to the opposite and update the database
            };
            AdminSuspendController adminSuspendController = new AdminSuspendController();
            if (adminSuspendController.suspendUserAccount(selectedUsername, newActiveStatus)){
                if (newActiveStatus.equals("N")){
                    JOptionPane.showMessageDialog(null, "User Account has been successfully suspended!", "Suspend User Account", JOptionPane.INFORMATION_MESSAGE);
                    suspendAccountTableConstruction();
                }
                else if (newActiveStatus.equals("Y"))
                {
                    JOptionPane.showMessageDialog(null, "User Account has been successfully un-suspended!", "Un-suspend User Account", JOptionPane.INFORMATION_MESSAGE);
                    suspendAccountTableConstruction();
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "User Profile suspension failed.", "Suspend User Account", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No such account!", "Error!", JOptionPane.ERROR_MESSAGE);
        } // end of if-else statements
    }

    // 4e) Construction of table variables for SUSPEND USER PROFILE functions
    public Component suspendProfileTableConstruction(){
        // Display Table and table click listener
        AdminSuspendController adminSuspendController = new AdminSuspendController();
        String [][] data = adminSuspendController.getUserProfiles();
        // Display data in a table format
        String[] columnTableNames = {"Profile ID", "User Profile", "Active"};
        tableSuspendProfiles = new JTable(data, columnTableNames);
        JScrollPane suspendUserProfileScrollPane1 = new JScrollPane(tableSuspendProfiles);
        suspendUserProfileScrollPane1.setPreferredSize(new Dimension(485, 200)); // width then height

        /* REMOVE COMPONENTS */
        Component[] componentList = panelSuspend.getComponents();
        // Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelSuspend.remove(c);
            }
        }
        panelSuspend.add(suspendUserProfileScrollPane1, 2);
        panelSuspend.revalidate();
        panelSuspend.repaint();
        return suspendUserProfileScrollPane1;
    }

    // 4f) Suspend button function to allow the user to suspend a User Profile by their username by passing the data to the controller
    public void buttonSuspendUserProfile_Onclick(){
        int rowSelected = tableSuspendProfiles.getSelectedRow();
        if (rowSelected != -1){
            int selectedUserProfileID = Integer.parseInt(tableSuspendProfiles.getModel().getValueAt(rowSelected,0).toString());
            String currentActiveStatus = tableSuspendProfiles.getModel().getValueAt(rowSelected, 2).toString();
            String newActiveStatus = switch (currentActiveStatus) {
                case "Y" -> "N";
                case "N" -> "Y";
                default -> "";
                // Change active status to the opposite and update the database
            };
            AdminSuspendController adminSuspendController = new AdminSuspendController();
            if (adminSuspendController.suspendUserProfile(selectedUserProfileID, newActiveStatus)){
                if (newActiveStatus.equals("N")){
                    JOptionPane.showMessageDialog(null, "User Profile has been successfully suspended!", "Suspend User Profile", JOptionPane.INFORMATION_MESSAGE);
                    suspendProfileTableConstruction();
                }
                else if (newActiveStatus.equals("Y"))
                {
                    JOptionPane.showMessageDialog(null, "User Profile has been successfully un-suspended!", "Un-suspend User Profile", JOptionPane.INFORMATION_MESSAGE);
                    suspendProfileTableConstruction();
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "User Profile suspension failed.", "Suspend User Profile", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No such profile!", "Error!", JOptionPane.ERROR_MESSAGE);
        } // end of if-else statements
    }
}