package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.UserAdmin.AdminCreateController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserAdminPageUI_V2 extends JFrame{
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


    public UserAdminPageUI_V2(){
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
        panelCreate.setVisible(true);

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
            }
            case "Edit" -> {

            }
            case "View" -> {

            }
            case "Suspend" -> {

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
                // Choice
                choiceCreateProfile.setPreferredSize(new Dimension(250, 30));
                String [] arrayAllProfiles = {"Admin", "Owner", "Manager", "Staff"};
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

                // Add respective components to the JFrame
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

                // Add respective components to the JFrame
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
            String createUsernameText = fieldCreateUsername.getText().toLowerCase();
            String createPasswordText = fieldCreatePassword.getText().toLowerCase();
            String createSelectedProfile = choiceCreateProfile.getSelectedItem();
            // Determine if the user account exist, if not, create a User Account
            AdminCreateController addUserController = new AdminCreateController();
            if (addUserController.validateCreate(createUsernameText, createPasswordText, createSelectedProfile)){
                JOptionPane.showMessageDialog(null, "User Account is created successfully.", "Account Creation", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "User Account already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } // end of if-else statements
    }

    // 1d) Create button function to allow the user to create a User Profile by passing the data to the controller
    public void buttonCreateUserProfile_Onclick(){

    }


}
