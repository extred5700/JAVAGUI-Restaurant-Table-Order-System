package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startingPage extends JFrame{
    /* Variable declaration */
    private final JFrame startingPageFrame = new JFrame("Homepage");
    private final Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();  // For banner
    private final JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);    // For banner
    private final JLabel labelSlogan = new JLabel("Who's Eating?");
    // Buttons
    private final JButton buttonStaff = new JButton("Staff");
    private final JButton buttonCustomer = new JButton("Customer");


    public startingPage(){
        startingPageFrame.getContentPane().setBackground(Color.WHITE);
        startingPageFrame.setSize (520, 705);
        startingPageFrame.getContentPane().setLayout(null);
        startingPageFrame.setLocationRelativeTo(null); // Window will popout in the middle of the screen
        startingPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        startingPageFrame.getContentPane().add(displayBannerImage);

        // Header Label
        labelSlogan.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelSlogan.setBounds(200, 120, 200, 210);
        startingPageFrame.getContentPane().add(labelSlogan);

        /* BUTTON DESIGN */
        // Staff Button
        buttonStaff.setBounds(20, 250, 200, 40);
        buttonStaff.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonStaff.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonStaff.setBackground(Color.WHITE);
        startingPageFrame.getContentPane().add(buttonStaff);
        // Customer Button
        buttonCustomer.setBounds(280, 250, 200, 40);
        buttonCustomer.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonCustomer.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonCustomer.setBackground(Color.WHITE);
        startingPageFrame.getContentPane().add(buttonCustomer);

        /* BUTTON FUNCTION */
        // Staff Button
        buttonStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                startingPageFrame.setVisible(false);
                new staffLoginPage();
            } // end of "Staff" Button method action
        });
        // Customer Button
        buttonCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                startingPageFrame.setVisible(false);
                new customerLoginPage();
            }
        });


        startingPageFrame.setVisible(true);
    }
}

