package Main.boundary;

import Main.boundary.StaffUI.*;
import Main.controller.Staff.LoginController;
import Main.controller.UserAdmin.AdminCreateController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Main.boundary.StartingPage.displayBannerImage;

public class StaffLoginPage extends JFrame{
    /* Variable declaration */
    private final JFrame staffLoginFrame = new JFrame("Staff Login Page");
    // STAFF
    // Labels
    private final JLabel labelID = new JLabel("Login ID: ");
    private final JLabel labelPassword = new JLabel("Password: ");
    // Text Fields
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    // Staff Choices
    private final Choice staffType = new Choice();

    public StaffLoginPage(){
        // JFrame properties
        staffLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffLoginFrame.getContentPane().setLayout(null);
        staffLoginFrame.setSize(520, 705);
        staffLoginFrame.setResizable(false);
        staffLoginFrame.setLocationRelativeTo(null); // Window will pop out in the middle of the screen
        staffLoginFrame.getContentPane().setBackground(Color.WHITE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        staffLoginFrame.getContentPane().add(displayBannerImage);

        /* Login, Password Label and Text Field */
        displayLoginFields();

        // Display Staff Choices
        displayStaffChoices();

        /* BUTTON DESIGN */
        // Login button
        // Button
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(40, 460, 200, 40);
        buttonLogin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogin.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonLogin.setBackground(Color.WHITE);
        // Return button
        JButton buttonReturn = new JButton("Return");
        buttonReturn.setBounds(270, 460, 200, 40);
        buttonReturn.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonReturn.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonReturn.setBackground(Color.WHITE);
        // Add buttons & functions
        buttonLogin.addActionListener(buttonListener);
        buttonReturn.addActionListener(buttonListener);
        staffLoginFrame.getContentPane().add(buttonLogin);
        staffLoginFrame.getContentPane().add(buttonReturn);

        staffLoginFrame.setVisible(true);
    }

    // end of actionPerformed
    // Button Listener
    ActionListener buttonListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action) {
            case "Login" -> {
                // Get ID, Password & Staff Type
                String staffUsername = loginField.getText();
                String staffPassword = passwordField.getText();
                String staffProfile = staffType.getSelectedItem();  // Check which dropdown list's item has been selected
                LoginController loginController = new LoginController(); // Controller Object to establish DB connection, attempt to login the user
                if (loginController.validateLogin(staffUsername, staffPassword, staffProfile)) {
                    loginSuccess(staffProfile);
                }
                // If login fails
                else {
                    JOptionPane.showMessageDialog(null, "Invalid account!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Return" -> {
                dispose();
                staffLoginFrame.setVisible(false);
                new StartingPage();
            }
        } // end of switch for BUTTONS statements
    };

    // Method to display Login & Password Text Fields
    public void displayLoginFields(){
        // Login ID
        labelID.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelID.setBounds(100, 250, 200, 40);
        loginField.setBounds(200, 250, 200, 40);
        staffLoginFrame.getContentPane().add(labelID);
        staffLoginFrame.getContentPane().add(loginField);
        // Password
        labelPassword.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelPassword.setBounds(100, 300, 200, 40);
        passwordField.setBounds(200, 300, 200, 40);
        staffLoginFrame.getContentPane().add(labelPassword);
        staffLoginFrame.getContentPane().add(passwordField);
    } // end of method displayLoginFields

    // Method to display Staff Log In Choices
    public void displayStaffChoices(){
        AdminCreateController adminCreateController = new AdminCreateController();
        String [] arrayAllProfiles = adminCreateController.getArrayOfProfiles();
        staffType.removeAll();
        if (staffType.getItemCount() != arrayAllProfiles.length){
            for (String arrayAllProfile : arrayAllProfiles) {
                staffType.add(arrayAllProfile);
            }
        }
        staffType.setBounds(200, 360, 200, 20);
        staffLoginFrame.getContentPane().add(staffType);
    } // end of method displayStaffChoices

    // Method to display the respective profile UI
    public void loginSuccess(String profile){
        dispose();
        staffLoginFrame.setVisible(false);
        switch (profile) {
            case "user admin" -> new UserAdminPageUI(); // Display Administrator UI
            case "restaurant owner" -> new OwnerPageUI();
            case "restaurant manager" -> new ManagerPageUI();
            case "restaurant staff" -> new RestaurantStaffPageUI();
            case "default" -> System.out.println(1); // For Testing
        }
    } // end of method loginSuccess
}
