package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.UserAdmin.AddUserController;
import Main.controller.UserAdmin.EditUserController;
import Main.controller.UserAdmin.SuspendUserController;
import Main.controller.UserAdmin.ViewUserController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

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
    private final JComboBox <String> createProfileType = new JComboBox<>(new String[]{"User Admin", "Restaurant Owner", "Restaurant Manager", "Restaurant Staff"});
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
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

    /* 4. SUSPEND Function */
    private JTable tableSuspendUsers;
    private final JPanel panelSuspend = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JButton buttonSuspendChanges = new JButton("Suspend Account");


    public UserAdminPageUI(String usernameLoggedIn){
        userAdminUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userAdminUIFrame.getContentPane().setLayout(new FlowLayout());
        userAdminUIFrame.setSize(520, 705);
        userAdminUIFrame.setResizable(false);
        userAdminUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        userAdminUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Admin's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        userAdminUIFrame.add(labelTopHeader);

        // Add buttons & functions for the top of the GUI
        displayAdminUserButtons();


        /* Button Function for Administrator */
        // 1. CREATE function
        displayCreatePanel();
        panelCreate.setVisible(false);


        // 2. EDIT function
        displayEditPanel(usernameLoggedIn);
        panelEdit.setVisible(false);


        // 3. VIEW function


        // 4. SUSPEND function
        displaySuspendPanel();
        panelSuspend.setVisible(false);


        userAdminUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
    * displayAdminUserButtons() - Method to display the buttons on top of the screen
    * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
    * */
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
        switch (action){
            case "Logout":
                userAdminUIFrame.dispose();
                userAdminUIFrame.setVisible(false);
                new StaffLoginPage();
                break;
            case "Create":
                dispose();
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelSuspend.setVisible(false);
                break;
            case "Edit":
                dispose();
                panelEdit.setVisible(true);
                panelCreate.setVisible(false);
                panelSuspend.setVisible(false);
                break;
            case "View":
                break;
            case "Suspend":
                dispose();
                panelSuspend.setVisible(true);
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                break;
        } // end of switch statements
    };


    /* 1. CREATE function
    * 1a) displayCreatePanel() - Display JPanel for User Admin to create an account
    */
    // 1a) Method for User Admin to CREATE a new user account for a new employee
    public void displayCreatePanel(){
        // Border
        titledBorder = new TitledBorder("Add New User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Labels
        labelCreateUsername.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));
        labelCreatePassword.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 17));

        // Text Fields
        fieldCreateUsername.setPreferredSize(new Dimension(50, 30));
        fieldCreatePassword.setPreferredSize(new Dimension(50, 30));

        // JComboBox/Dropdown List
        createProfileType.setPreferredSize(new Dimension(230, 20));

        // Create Account Button
        buttonCreateAccount.setPreferredSize(new Dimension(150, 30));
        buttonCreateAccount.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonCreateAccount.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonCreateAccount.setBackground(Color.WHITE);
        buttonCreateAccount.addActionListener(e -> {
            String createUsernameText = fieldCreateUsername.getText();
            String createPasswordText = fieldCreatePassword.getText();
            String createProfileText = Objects.requireNonNull(createProfileType.getSelectedItem()).toString();
            AddUserController addUserController = new AddUserController();
            // validateCreateUserAccount() method located in AddUserController.java
            if ((createUsernameText.isEmpty()) || (createPasswordText.isEmpty())){
                JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                if (addUserController.validateCreate(createUsernameText, createPasswordText, createProfileText)){
                    JOptionPane.showMessageDialog(null, "Account is created successfully.", "Account Creation", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Account already exist.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the JPanel
        panelCreate.setPreferredSize(new Dimension(420, 270));
        panelCreate.setBackground(Color.WHITE);
        panelCreate.add(labelCreateUsername);
        panelCreate.add(fieldCreateUsername);
        panelCreate.add(labelCreatePassword);
        panelCreate.add(fieldCreatePassword);
        panelCreate.add(createProfileType);
        panelCreate.add(buttonCreateAccount);
        panelCreate.setBorder(titledBorder);
        panelCreate.setVisible(true);
        userAdminUIFrame.add(panelCreate);
    }


    /* 2. EDIT function
    * 2a) displayEditPanel(String usernameLoggedIn) - Display JPanel for User Admin to edit an account's details
    * 2b) editTableConstruction() - Construction of the JTable, Mouse Click Listener to display an account's details in the text field (JTable type returned as a JScrollPane type)
    * 2c) getUserAccountTable(ArrayList<ArrayList<String>> arrayListUserAllDetails) - Convert ArrayLists to a 2D array, return the 2D array to display on the table
    */
    // 2a) Method for User Admin to EDIT a user account details
    public void displayEditPanel(String usernameLoggedIn){
        // Border
        titledBorder = new TitledBorder("Edit User Accounts");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) editTableConstruction();

        // Labels
        labelEditUsername.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        labelEditPassword.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        labelEditProfile.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));

        // Text Fields
        fieldEditUsername.setPreferredSize(new Dimension(110, 30));
        fieldEditPassword.setPreferredSize(new Dimension(110, 30));
        fieldEditProfile.setPreferredSize(new Dimension(110, 30));

        // Edit Changes Button
        buttonEditChanges.setPreferredSize(new Dimension(150, 30));
        buttonEditChanges.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonEditChanges.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonEditChanges.setBackground(Color.WHITE);
        buttonEditChanges.addActionListener(e -> {
            String username = fieldEditUsername.getText();
            String password = fieldEditPassword.getText();
            String profile = fieldEditProfile.getText();
            // Check if text field are left empty by the user
            if (username.isEmpty() || password.isEmpty() || profile.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please do not leave the text fields empty.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                editUserDetails(username, password, profile);
                // After data has been edited and updated, recall the JFrame and display it again
                // Problem: doesnt automatically show Edit Functions when JFrame is called again
                dispose();
                userAdminUIFrame.setVisible(false);
                new UserAdminPageUI(usernameLoggedIn);
                //displayEditPanel(usernameLoggedIn);
                buttonEdit.doClick();
                panelEdit.setVisible(true);
            }
        });

        panelEdit.setPreferredSize(new Dimension(500, 490));
        panelEdit.setBackground(Color.WHITE);
        panelEdit.add(scrollPane);
        panelEdit.add(labelEditUsername);
        panelEdit.add(fieldEditUsername);
        panelEdit.add(labelEditPassword);
        panelEdit.add(fieldEditPassword);
        panelEdit.add(labelEditProfile);
        panelEdit.add(fieldEditProfile);
        panelEdit.add(buttonEditChanges);
        panelEdit.setBorder(titledBorder);
        panelEdit.setVisible(true);
        userAdminUIFrame.add(panelEdit);
    }

    // 2b) Construction of table variables for EDIT functions
    public Component editTableConstruction(){
        ViewUserController viewUserController = new ViewUserController();
        ArrayList<ArrayList<String>> arrayListUserAllDetails = viewUserController.getUserInfo();
        String [][] data = getUserAccountTable(arrayListUserAllDetails); // Call method to "convert" ArrayLists inside a "big" Arraylist to a 2D array
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableEditUsers = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableEditUsers);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height

        // Table Mouse Listener
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
        return sp;
    }

    // 2c) Method to store the respective array list into a 2D array
    public String[][] getUserAccountTable(ArrayList<ArrayList<String>> arrayListUserAllDetails){
        String [] arrayUsername = arrayListUserAllDetails.get(0).toArray(new String[0]);
        String [] arrayPassword = arrayListUserAllDetails.get(1).toArray(new String[0]);
        String [] arrayProfile = arrayListUserAllDetails.get(2).toArray(new String[0]);
        String [] arrayActive = arrayListUserAllDetails.get(3).toArray(new String[0]);

        String [][] arrayAllDetails = new String[arrayUsername.length][arrayUsername.length];
        for (int i = 0; i < arrayUsername.length; i++){
            arrayAllDetails[i][0] = arrayUsername[i];
            arrayAllDetails[i][1] = arrayPassword[i];
            arrayAllDetails[i][2] = arrayProfile[i];
            arrayAllDetails[i][3] = arrayActive[i];
        }
        return arrayAllDetails;
    }

    // 2d) Method to pass data to the controller before updating the DB
    public void editUserDetails(String newUsername, String newPassword, String newProfile){

        EditUserController editUserController = new EditUserController();
        int rowSelected = tableEditUsers.getSelectedRow();
        if (rowSelected != -1){
            String oldUsername = tableEditUsers.getModel().getValueAt(rowSelected,0).toString();
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
    } // end of the method updateUserDetails()


    /* 3. VIEW function */


    /* 4. SUSPEND function
    * 4a) displaySuspendPanel() - Display JPanel for User Admin to suspend an account's details
    * */
    // 4a) Method for the User Admin to SUSPEND a user account
    public void displaySuspendPanel(){
        // Border
        titledBorder = new TitledBorder("Suspend a User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane3 = (JScrollPane) suspendTableConstruction();

        // Suspend Button
        buttonSuspendChanges.setPreferredSize(new Dimension(150, 30));
        buttonSuspendChanges.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonSuspendChanges.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonSuspendChanges.setBackground(Color.WHITE);
        buttonSuspendChanges.addActionListener(e -> {
            int getRow = tableSuspendUsers.getSelectedRow();
            // Ensure the User has selected a row from the table displaying all the user accounts
            if (getRow != -1){
                // Get the selected username by getting the value of the
                String selectedUsername = (String) tableSuspendUsers.getModel().getValueAt(getRow, 0);
                String currentActiveStatus = (String) tableSuspendUsers.getModel().getValueAt(getRow, 3);
                String newActiveStatus = "";
                // Change active status to the opposite and update the database
                switch (currentActiveStatus){
                    case "Y":
                        newActiveStatus = "N";
                        break;
                    case "N":
                        newActiveStatus = "Y";
                        break;
                }
                if (new SuspendUserController().suspendUser(selectedUsername, newActiveStatus)){
                    JOptionPane.showMessageDialog(null, "Account has been successfully suspended!", "Suspend User", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Account suspension failed.", "Suspend User", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select a user account to suspend from the table.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to the JPanel
        panelSuspend.setPreferredSize(new Dimension(500, 490));
        panelSuspend.setBackground(Color.WHITE);
        panelSuspend.add(scrollPane3);
        panelSuspend.add(buttonSuspendChanges);
        panelSuspend.setBorder(titledBorder);
        panelSuspend.setVisible(true);
        userAdminUIFrame.add(panelSuspend);
    }

    // 4b) suspendTableConstruction() - Construction of the JTable, Mouse Click Listener to display an account's details in the text field (JTable type returned as a JScrollPane type)
    public Component suspendTableConstruction(){
        ViewUserController viewUserController = new ViewUserController();
        ArrayList<ArrayList<String>> arrayListUserAllDetails = viewUserController.getUserInfo();
        String [][] data = getUserAccountTable(arrayListUserAllDetails); // Call method to "convert" ArrayLists inside a "big" Arraylist to a 2D array
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableSuspendUsers = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableSuspendUsers);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

}
