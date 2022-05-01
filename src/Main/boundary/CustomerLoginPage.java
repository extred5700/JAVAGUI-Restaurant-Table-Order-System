package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Main.boundary.StartingPage.displayBannerImage;

public class CustomerLoginPage extends JFrame {
    /* Variable declaration */
    private final JFrame customerLoginFrame = new JFrame("Customer Login Page");
    // CUSTOMER
    // Table Number Label & Text Field
    private final JLabel labelTableNumber = new JLabel("Table Number: ");
    private final JTextField fieldTableNumber = new JTextField();

    public CustomerLoginPage() {
        // JFrame Properties
        customerLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerLoginFrame.getContentPane().setLayout(null);
        customerLoginFrame.setSize(520, 705);
        customerLoginFrame.setResizable(false);
        customerLoginFrame.setLocationRelativeTo(null); // Window will pop out in the middle of the screen
        customerLoginFrame.getContentPane().setBackground(Color.WHITE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        customerLoginFrame.getContentPane().add(displayBannerImage);


        // Header label
        JLabel labelTopText = new JLabel("Enter table number to view menu");
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
        // Button
        JButton buttonLogin = new JButton("View Menu");
        buttonLogin.setBounds(40, 320, 200, 40);
        buttonLogin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogin.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonLogin.setBackground(Color.WHITE);
        // Return Button
        JButton buttonReturn = new JButton("Return");
        buttonReturn.setBounds(270, 320, 200, 40);
        buttonReturn.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonReturn.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonReturn.setBackground(Color.WHITE);
        // Add buttons & functions
        buttonLogin.addActionListener(buttonListener);
        buttonReturn.addActionListener(buttonListener);
        customerLoginFrame.getContentPane().add(buttonLogin);
        customerLoginFrame.getContentPane().add(buttonReturn);

        customerLoginFrame.setVisible(true);
    }

    // end of actionPerformed
    // Button Listener
    ActionListener buttonListener = e -> {
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
