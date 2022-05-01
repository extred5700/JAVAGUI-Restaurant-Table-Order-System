package Main.boundary;

import Main.controller.AddUserController;
import Main.controller.EditUserController;
import Main.controller.ViewUserController;

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
    // Buttons
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonSuspend = new JButton("Suspend");

    // Create Panel
    private final JPanel panelCreate = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JLabel labelCreateUsername = new JLabel("Username: ");
    private final JTextField fieldCreateUsername = new JTextField(20);
    private final JLabel labelCreatePassword = new JLabel("Password: ");
    private final JTextField fieldCreatePassword = new JTextField(20);
    private final JComboBox <String> createProfileType = new JComboBox<>(new String[]{"User Admin", "Restaurant Owner", "Restaurant Manager", "Restaurant Staff"});
    private final JButton buttonCreateAccount = new JButton("Create Account");

    /* JTable to display User Account Profiles */
    private JTable tableUsers;

    // Edit Panel
    private final JPanel panelEdit = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JLabel labelEditUsername = new JLabel("Username: ");
    private final JTextField fieldEditUsername = new JTextField(20);
    private final JLabel labelEditPassword = new JLabel("Password: ");
    private final JTextField fieldEditPassword = new JTextField(20);
    private final JLabel labelEditProfile = new JLabel("User Profile: ");
    private final JTextField fieldEditProfile = new JTextField(20);
    private final JButton buttonEditChanges = new JButton("Edit Changes");


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

        // Add buttons & functions
        displayAdminUserButtons();

        /* Button Function for Administrator */
        // CREATE function
        displayCreatePanel();
        panelCreate.setVisible(false);

        // EDIT function
        displayEditPanel(usernameLoggedIn);
        panelEdit.setVisible(false);




        userAdminUIFrame.setVisible(true);
    }

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

    // end of actionPerformed
    // Button Listener
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
                break;
            case "Edit":
                dispose();
                panelEdit.setVisible(true);
                panelCreate.setVisible(false);
                break;
            case "View":
                break;
            case "Suspend":
                break;
        } // end of switch statements
    };

    // Method for User Admin to create a new user account for a new employee
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
    } // end of the method displayCreateFields()

    // Method to store the respective array list into a 2D array
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

    // Construction of table variables for EDIT/SEARCH/SUSPEND functions
    public Component tableConstruction(){
        ViewUserController viewUserController = new ViewUserController();
        ArrayList<ArrayList<String>> arrayListUserAllDetails = viewUserController.getUserInfo();
        String [][] data = getUserAccountTable(arrayListUserAllDetails); // Call method to "convert" ArrayLists inside a "big" Arraylist to a 2D array
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableUsers = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableUsers);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height

        // Table Mouse Listener
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getRow = tableUsers.getSelectedRow();
                fieldEditUsername.setText(tableUsers.getModel().getValueAt(getRow, 0).toString());
                fieldEditPassword.setText(tableUsers.getModel().getValueAt(getRow, 1).toString());
                fieldEditProfile.setText(tableUsers.getModel().getValueAt(getRow, 2).toString());
            }
        });
        return sp;
    }

    // Method for User Admin to display all User Profiles in a table format
    public void displayEditPanel(String usernameLoggedIn){
        // Border
        titledBorder = new TitledBorder("Edit User Accounts");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) tableConstruction();

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
    } // end of the method displayUsersInTable()

    // Method to allow the user to confirm their changes before update
    public void editUserDetails(String newUsername, String newPassword, String newProfile){

        EditUserController editUserController = new EditUserController();
        int rowSelected = tableUsers.getSelectedRow();
        if (rowSelected != -1){
            String oldUsername = tableUsers.getModel().getValueAt(rowSelected,0).toString();
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



}
