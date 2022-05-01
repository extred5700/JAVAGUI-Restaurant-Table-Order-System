package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartingPage extends JFrame{
    /* Variable declaration */
    private final JFrame startingPageFrame = new JFrame("Hopium Restaurant");
    static final Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();      // For banner
    static final JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);   // For banner


    public StartingPage(){
        // JFrame properties
        startingPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startingPageFrame.getContentPane().setLayout(null);
        startingPageFrame.setSize(520, 705);
        startingPageFrame.setResizable(false);
        startingPageFrame.setLocationRelativeTo(null); // Window will pop out in the middle of the screen
        startingPageFrame.getContentPane().setBackground(Color.WHITE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        startingPageFrame.getContentPane().add(displayBannerImage);

        // Header Label
        JLabel labelTopText = new JLabel("Who's Eating?");
        labelTopText.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelTopText.setBounds(200, 120, 200, 210);
        startingPageFrame.getContentPane().add(labelTopText);

        /* BUTTON DESIGN */
        // Staff Button
        // Buttons
        JButton buttonStaff = new JButton("Staff");
        buttonStaff.setBounds(20, 250, 200, 40);
        buttonStaff.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonStaff.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonStaff.setBackground(Color.WHITE);
        // Customer Button
        JButton buttonCustomer = new JButton("Customer");
        buttonCustomer.setBounds(280, 250, 200, 40);
        buttonCustomer.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonCustomer.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonCustomer.setBackground(Color.WHITE);
        // Add buttons & functions
        buttonStaff.addActionListener(buttonListener);
        buttonCustomer.addActionListener(buttonListener);
        startingPageFrame.getContentPane().add(buttonStaff);
        startingPageFrame.getContentPane().add(buttonCustomer);

        startingPageFrame.setVisible(true);
    }

    // end of actionPerformed
    // Button Listener
    ActionListener buttonListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action) {
            case "Staff" -> {
                dispose();
                startingPageFrame.setVisible(false);
                new StaffLoginPage();
            }
            case "Customer" -> {
                dispose();
                startingPageFrame.setVisible(false);
                new CustomerLoginPage();
            }
        } // end of switch statements
    };
}

