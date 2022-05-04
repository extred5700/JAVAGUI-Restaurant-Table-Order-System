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
    private JTable tableViewUsers;
    private final JPanel panelView = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JTextField fieldSearchUser = new JTextField(20);
    private final JButton buttonSearchUser = new JButton("Search by Username");
    private final JButton buttonSearchProfile = new JButton("Search by User Profile");

    /* 4. SUSPEND Function */
    private JTable tableSuspendUsers;
    private final JPanel panelSuspend = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private final JButton buttonSuspendChanges = new JButton("Suspend/Un-suspend Account");


    public UserAdminPageUI(String usernameLoggedIn, String setDisplayPage){
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
        displayEditPanel();
        panelEdit.setVisible(false);


        // 3. VIEW function
        displaySearchPanel();
        panelView.setVisible(false);


        // 4. SUSPEND function
        displaySuspendPanel();
        panelSuspend.setVisible(false);

        switch (setDisplayPage){
            case "Default":
                break;
            case "Create":
                panelCreate.setVisible(true);
                break;
            case "Edit":
                refreshEditTable();
                panelEdit.setVisible(true);
                break;
            case "View":
                refreshViewTable("default", "default");
                panelView.setVisible(true);
                break;
            case "Suspend":
                refreshSuspendTable();
                panelSuspend.setVisible(true);
                break;
        }
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
                panelCreate.setVisible(true);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
                panelSuspend.setVisible(false);
            }
            case "Edit" -> {
                //When swapping back to the edit page, refresh the edit table
                refreshEditTable();
                panelEdit.setVisible(true);
                panelView.setVisible(false);
                panelSuspend.setVisible(false);
                panelCreate.setVisible(false);
            }
            case "View" -> {
                //When swapping back to the view page, refresh the view table
                refreshViewTable("default", "default");
                panelView.setVisible(true);
                panelSuspend.setVisible(false);
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
            }
            case "Suspend" -> {
                //When swapping back to the suspend page, refresh the suspend table
                refreshSuspendTable();
                panelSuspend.setVisible(true);
                panelCreate.setVisible(false);
                panelEdit.setVisible(false);
                panelView.setVisible(false);
            }
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
            // validateCreate() method located in AddUserController.java
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
    * 2c) editUserDetails(String newUsername, String newPassword, String newProfile) - Method to pass data to the controller before updating the DB
    * 2d) refreshEditTable() - Used to refresh only the JScrollPane for Edit page
    */
    // 2a) Method for User Admin to EDIT a user account details
    public void displayEditPanel(){
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
                //After Edit, refresh edit table
                refreshEditTable();
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
        String [][] data = viewUserController.getUserInfo();
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

    // 2c) Method to pass data to the controller before updating the DB
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

    // 2d) Used to refresh only the JScrollPane for Edit page
    public void refreshEditTable(){
        //Get the components in the panel
        Component[] componentList = panelEdit.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelEdit.remove(c);
            }
        }
        panelEdit.add(editTableConstruction(),0);
        panelEdit.revalidate();
        panelEdit.repaint();
    }


    /* 3. VIEW function
    * 3a) displaySearchPanel() - Display JPanel for User Admin to View accounts' details
    * 3b) viewTableConstruction() - Construction of the JTable, JTable type returned as a JScrollPane type, to display search results
    * 3c) refreshViewTable(String usernameKeyedIn) - Used to refresh only the JScrollPane for View page
    * */
    // 3a) Method for User Admin to VIEW a user account details
    public void displaySearchPanel(){
        panelView.removeAll();
        // Border
        titledBorder = new TitledBorder("View Users");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane = (JScrollPane) viewTableConstruction("default", "default");
        panelView.add(scrollPane);

        // Text field
        fieldSearchUser.setPreferredSize(new Dimension(60, 30));
        panelView.add(fieldSearchUser);

        // Search Buttons (Username & User Profile)
        JButton [] butttonsForViewUsers = {buttonSearchUser, buttonSearchProfile};
        for (JButton jButton : butttonsForViewUsers){
            jButton.setPreferredSize(new Dimension(220, 30));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED,1));
            jButton.setBackground(Color.WHITE);
            panelView.add(jButton);
        }
        // Search Username Button
        buttonSearchUser.addActionListener(e -> {
            String usernameKeyedIn = fieldSearchUser.getText();
            if (usernameKeyedIn.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                //Refresh View Table with search query results
                refreshViewTable(usernameKeyedIn, "search_by_username");
            }
        });
        // Search User Profile Button
        buttonSearchProfile.addActionListener(e -> {
            String userProfileKeyedIn = fieldSearchUser.getText();
            if (userProfileKeyedIn.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please do not leave the text field empty.", "Error!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                //Refresh View Table with search query results
                refreshViewTable(userProfileKeyedIn, "search_by_profile");
            }
        });

        // Components of JPanel
        panelView.setPreferredSize(new Dimension(500, 390));
        panelView.setBackground(Color.WHITE);
        panelView.setBorder(titledBorder);
        panelView.repaint();
        panelView.revalidate();
        panelView.setVisible(true);
        userAdminUIFrame.add(panelView);
    }

    // 3b) viewTableConstruction() - Construction of the JTable, JTable type returned as a JScrollPane type, to display search results
    public Component viewTableConstruction(String usernameKeyedIn, String searchStatus){
        ViewUserController viewUserController = new ViewUserController();
        String [] columnTableNames = {"Username", "Password", "User Profile", "Active"};

        if (searchStatus.equals("default")){
            String [][] data = viewUserController.getUserInfo();
            tableViewUsers = new JTable(data, columnTableNames);
        }
        // Search by username or user profile
        else {
            String [][] displaySearchResults = viewUserController.searchByUsername(usernameKeyedIn, searchStatus);
            // Display data in a table format
            tableViewUsers = new JTable(displaySearchResults, columnTableNames);
        }

        JScrollPane sp = new JScrollPane(tableViewUsers);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // 3c) Used to refresh only the JScrollPane for View page
    public void refreshViewTable(String searchResult, String searchStatus){
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
        panelView.add(viewTableConstruction(searchResult, searchStatus),0);
        panelView.revalidate();
        panelView.repaint();
    }


    /* 4. SUSPEND function
    * 4a) displaySuspendPanel() - Display JPanel for User Admin to suspend an account's details
    * 4b) suspendTableConstruction() - Construction of the JTable, JTable type returned as a JScrollPane type
    * 4c) refreshSuspendTable(String usernameKeyedIn) - Used to refresh only the JScrollPane for Suspend page
    */
    // 4a) Method for the User Admin to SUSPEND a user account
    public void displaySuspendPanel(){
        // Border
        titledBorder = new TitledBorder("Suspend a User");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane scrollPane3 = (JScrollPane) suspendTableConstruction();

        // Suspend Button
        buttonSuspendChanges.setPreferredSize(new Dimension(250, 30));
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
                String newActiveStatus = switch (currentActiveStatus) {
                    case "Y" -> "N";
                    case "N" -> "Y";
                    default -> "";
                    // Change active status to the opposite and update the database
                };
                if (new SuspendUserController().suspendUser(selectedUsername, newActiveStatus)){
                    if (newActiveStatus.equals("N")){
                        JOptionPane.showMessageDialog(null, "Account has been successfully suspended!", "Suspend User", JOptionPane.INFORMATION_MESSAGE);
                        refreshSuspendTable();
                    }
                    else if (newActiveStatus.equals("Y"))
                    {
                        JOptionPane.showMessageDialog(null, "Account has been successfully un-suspended!", "Un-suspend User", JOptionPane.INFORMATION_MESSAGE);
                        refreshSuspendTable();
                    }

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
        panelSuspend.setPreferredSize(new Dimension(500, 330));
        panelSuspend.setBackground(Color.WHITE);
        panelSuspend.add(scrollPane3);
        panelSuspend.add(buttonSuspendChanges);
        panelSuspend.setBorder(titledBorder);
        panelSuspend.setVisible(true);
        userAdminUIFrame.add(panelSuspend);
    }

    // 4b) Construction of the JTable, Mouse Click Listener to display an account's details in the text field (JTable type returned as a JScrollPane type)
    public Component suspendTableConstruction(){
        ViewUserController viewUserController = new ViewUserController();
        String [][] data = viewUserController.getUserInfo();
        // Display data in a table format
        String [] columnTableNames = {"Username", "Password","User Profile", "Active"};
        tableSuspendUsers = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableSuspendUsers);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // 4c) Used to refresh only the JScrollPane for Suspend page
    public void refreshSuspendTable(){
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
        panelSuspend.add(suspendTableConstruction(),0);
        panelSuspend.revalidate();
        panelSuspend.repaint();
    }

}