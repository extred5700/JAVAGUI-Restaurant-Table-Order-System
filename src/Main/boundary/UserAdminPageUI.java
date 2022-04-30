package Main.boundary;

import Main.controller.AddUserController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAdminPageUI extends JFrame{
    /* Variable declaration */
    private JFrame userAdminUIFrame = new JFrame("User Admin Homepage");
    private final JLabel labelTopHeader;
    private TitledBorder titledBorder;
    // Buttons
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonSuspend = new JButton("Suspend");

    // Create Account Fields
    private final JPanel panelCreate = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JLabel labelUsername = new JLabel("Username: ");
    private final JTextField fieldUsername = new JTextField(20);
    private final JLabel labelPassword = new JLabel("Password: ");
    private final JTextField fieldPassword = new JTextField(20);
    private JComboBox <String> profileType = new JComboBox<>(new String[]{"User Admin", "Restaurant Owner", "Restaurant Manager", "Restaurant Staff"});
    private final JButton buttonCreateAccount = new JButton("Create Account");

    public UserAdminPageUI(String username){
        userAdminUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userAdminUIFrame.getContentPane().setLayout(new FlowLayout());
        userAdminUIFrame.setSize(520, 705);
        userAdminUIFrame.setResizable(false);
        userAdminUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        userAdminUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Admin's Username
        labelTopHeader = new JLabel("You are currently logged in as: " + username, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.BLACK));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        userAdminUIFrame.add(labelTopHeader);

        // Add buttons & functions
        displayAdminUserButtons();

        displayCreateFields();
        panelCreate.setVisible(false);

        userAdminUIFrame.setVisible(true);
    }

    // Method to display buttons for User Admin
    public void displayAdminUserButtons(){
        JButton [] myArray = {buttonLogout, buttonCreate, buttonEdit, buttonView, buttonSuspend};
        for (int i = 0; i < 5; i++){
            myArray[i].setPreferredSize(new Dimension(95, 30));
            myArray[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            myArray[i].setBorder(BorderFactory.createLineBorder(Color.RED,1));
            myArray[i].setBackground(Color.WHITE);
            myArray[i].addActionListener(topButtonsListener);
            userAdminUIFrame.add(myArray[i]);
        }
    }

    // Button Listener
    ActionListener topButtonsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton)e.getSource();
            String action = buttonPressed.getText();
            switch (action){
                case "Logout":
                    userAdminUIFrame.dispose();
                    userAdminUIFrame.setVisible(false);
                    new StaffLoginPage();
                    break;
                case "Create":
                    panelCreate.setVisible(true);
                    break;
                case "Edit":
                    dispose();
                    panelCreate.setVisible(false);
                    break;
                case "View":
                    break;
                case "Suspend":
                    break;
            } // end of switch statements
        } // end of actionPerformed
    };

    // Method for User Admin to create a new user account for a new employee
    public void displayCreateFields(){
        // Border
        titledBorder = new TitledBorder("Add New User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Labels
        labelUsername.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        labelPassword.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));

        // Text Fields
        fieldUsername.setPreferredSize(new Dimension(50, 30));
        fieldPassword.setPreferredSize(new Dimension(50, 30));

        // JComboBox/Dropdown List
        /*
        profileType.addItem("User Admin");
        profileType.addItem("Restaurant Owner");
        profileType.addItem("Restaurant Manager");
        profileType.addItem("Restaurant Staff");

        */
        profileType.setPreferredSize(new Dimension(230, 20));

        // Create Account Button
        buttonCreateAccount.setPreferredSize(new Dimension(150, 30));
        buttonCreateAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonCreateAccount.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonCreateAccount.setBackground(Color.WHITE);
        buttonCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fieldUsername.getText();
                String password = fieldPassword.getText();
                String profile = profileType.getSelectedItem().toString();
                AddUserController addUserController = new AddUserController();
                // validateCreateUserAccount() method located in AddUserController.java
                if ((username.isEmpty()) || (password.isEmpty())){
                    JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    if (addUserController.validateCreate(username, password, profile) == true){
                        JOptionPane.showMessageDialog(null, "Account is created successfully.", "Account Creation", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Account already exist.", "Error!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(420, 270));
        panelCreate.setBackground(Color.WHITE);
        panelCreate.add(labelUsername);
        panelCreate.add(fieldUsername);
        panelCreate.add(labelPassword);
        panelCreate.add(fieldPassword);
        panelCreate.add(profileType);
        panelCreate.add(buttonCreateAccount);
        panelCreate.setBorder(titledBorder);
        panelCreate.setVisible(true);
        userAdminUIFrame.add(panelCreate);
    } // end of the method displayCreateFields()

    // Method for User Admin to display all User Profiles in a table format


}
