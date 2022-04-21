package Main.boundary;

import javax.swing.*;
import java.awt.*;

public class customerLoginPage extends JFrame{
    /* Variable declaration */
    private final JFrame customerLoginFrame = new JFrame("Customer Login Page");
    private final Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();  // For banner
    private final JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);    // For banner

    /* --- CUSTOMER ---
     * Buttons
     * Label
     * Text Fields
     */
    // Label
    private final JLabel labelTableNumber = new JLabel("Table Number: ");
    // Buttons
    private final JButton buttonMenu = new JButton("Staff");

    public customerLoginPage(){
        customerLoginFrame.getContentPane().setBackground(Color.WHITE);
        customerLoginFrame.setSize (520, 705);
        customerLoginFrame.getContentPane().setLayout(null);
        customerLoginFrame.setLocationRelativeTo(null); // Window will popout in the middle of the screen
        customerLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        customerLoginFrame.getContentPane().add(displayBannerImage);

        // Choice for Customers option
        Choice customerActions = new Choice();
        customerActions.add("Browse Menu");
        customerActions.add("Edit Order");
        customerActions.add("View Order");
        customerActions.add("Payment");
        customerActions.setBounds(10, 200, 485, 150);
        customerLoginFrame.add(customerActions);


        customerLoginFrame.setVisible(true);
    }
}
