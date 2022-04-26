package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerLoginPage extends JFrame {
    /* Variable declaration */
    private JFrame customerLoginFrame;
    private final JLabel labelTopText = new JLabel("Enter table number to view menu");
    // CUSTOMER
    // Table Number Label & Text Field
    private final JLabel labelTableNumber = new JLabel("Table Number: ");
    private final JTextField fieldTableNumber = new JTextField();
    // Button
    private final JButton buttonLogin = new JButton("View Menu");
    private final JButton buttonReturn = new JButton("Return");

    public CustomerLoginPage() {
        customerLoginFrame = new DisplayPage("Customer Login Page");

        // Header label
        labelTopText.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelTopText.setBounds(140, 120, 240, 210);
        customerLoginFrame.getContentPane().add(labelTopText);

        // Customer Table Number
        displayTableNumField(); // Display Table Number Field
        // Ensure user only can type in numbers/integers
        fieldTableNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        /* BUTTON DESIGN */
        // Login Button
        buttonLogin.setBounds(40, 320, 200, 40);
        buttonLogin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogin.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonLogin.setBackground(Color.WHITE);
        // Return Button
        buttonReturn.setBounds(270, 320, 200, 40);
        buttonReturn.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonReturn.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonReturn.setBackground(Color.WHITE);
        // Add buttons & functions
        buttonLogin.addActionListener(buttonListener);
        buttonReturn.addActionListener(buttonListener);
        customerLoginFrame.getContentPane().add(buttonLogin);
        customerLoginFrame.getContentPane().add(buttonReturn);
    }

    // Button Listener
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            String action = buttonPressed.getText();
            switch (action) {
                case "View Menu":
                    // Check whether table number is valid
                    if (fieldTableNumber.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter a table number.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    // Once table number has been validated, proceed to next JFrame to display Customer Menu, payment, etc..
                    else{
                        int tableNumber = Integer.parseInt(fieldTableNumber.getText());
                        dispose();
                        customerLoginFrame.setVisible(false);
                        new CustomerPageUI(tableNumber);
                    }
                    break;
                case "Return":
                    dispose();
                    customerLoginFrame.setVisible(false);
                    new StartingPage();
                    break;
            } // end of switch for BUTTONS statements
        } // end of actionPerformed
    };

    // Method to display the text field for the Customer to key in their TABLE NUMBER
    public void displayTableNumField() {
        labelTableNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelTableNumber.setBounds(90, 250, 200, 40);
        fieldTableNumber.setBounds(200, 250, 200, 40);
        customerLoginFrame.getContentPane().add(labelTableNumber);
        customerLoginFrame.getContentPane().add(fieldTableNumber);
    }
}
