package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffLoginPage extends JFrame{
    /* Variable declaration */
    private final JFrame staffLoginFrame;
    // STAFF
    // Labels
    private final JLabel labelID = new JLabel("Login ID: ");
    private final JLabel labelPassword = new JLabel("Password: ");
    // Text Fields
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    // Staff Choices
    private final Choice staffType = new Choice();

    // Button
    private final JButton buttonLogin = new JButton("Login");
    private final JButton buttonReturn = new JButton("Return");

    public StaffLoginPage(){
        staffLoginFrame = new DisplayPage("Staff Login Page"); // Display Frame & Banner Image

        /* Login, Password Label and Text Field */
        displayLoginFields();

        // Display Staff Choices
        displayStaffChoices();

        /* BUTTON DESIGN */
        // Login button
        buttonLogin.setBounds(40, 460, 200, 40);
        buttonLogin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogin.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonLogin.setBackground(Color.WHITE);
        // Return button
        buttonReturn.setBounds(270, 460, 200, 40);
        buttonReturn.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonReturn.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonReturn.setBackground(Color.WHITE);
        // Add buttons & functions
        buttonLogin.addActionListener(buttonListener);
        buttonReturn.addActionListener(buttonListener);
        staffLoginFrame.getContentPane().add(buttonLogin);
        staffLoginFrame.getContentPane().add(buttonReturn);
    }

    // Button Listener
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton)e.getSource();
            String action = buttonPressed.getText();
            switch (action){
                case "Login":
                    String roleSelected = staffType.getSelectedItem();
                    // Check which dropdown list's item has been selected
                    switch(roleSelected){
                        case "Administrator":
                            JOptionPane.showMessageDialog(null, "1");
                            break;
                        case "Restaurant Owner":
                            JOptionPane.showMessageDialog(null, "2");
                            break;
                        case "Restaurant Manager":
                            JOptionPane.showMessageDialog(null, "3");
                            break;
                        case "Restaurant Staff":
                            JOptionPane.showMessageDialog(null, "4");
                            break;
                    } // end of switch for CHOICE statements
                    break;
                case "Return":
                    dispose();
                    staffLoginFrame.setVisible(false);
                    new StartingPage();
                    break;
            } // end of switch for BUTTONS statements
        } // end of actionPerformed
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
    }

    // Method to display Staff Log In Choices
    public void displayStaffChoices(){
        staffType.add("Administrator");
        staffType.add("Restaurant Owner");
        staffType.add("Restaurant Manager");
        staffType.add("Restaurant Staff");
        staffType.setBounds(200, 360, 200, 20);
        staffLoginFrame.getContentPane().add(staffType);
    }
}
