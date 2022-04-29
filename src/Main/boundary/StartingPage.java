package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingPage extends JFrame{
    /* Variable declaration */
    private JFrame startingPageFrame = new JFrame("Hopium Restaurant");
    static final Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();      // For banner
    static final JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);   // For banner
    private final JLabel labelTopText = new JLabel("Who's Eating?");
    // Buttons
    private final JButton buttonStaff = new JButton("Staff");
    private final JButton buttonCustomer = new JButton("Customer");


    public StartingPage(){
        // JFrame properties
        startingPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startingPageFrame.getContentPane().setLayout(null);
        startingPageFrame.setSize(520, 705);
        startingPageFrame.setResizable(false);
        startingPageFrame.setLocationRelativeTo(null); // Window will popout in the middle of the screen
        startingPageFrame.getContentPane().setBackground(Color.WHITE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        startingPageFrame.getContentPane().add(displayBannerImage);

        // Header Label
        labelTopText.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelTopText.setBounds(200, 120, 200, 210);
        startingPageFrame.getContentPane().add(labelTopText);

        /* BUTTON DESIGN */
        // Staff Button
        buttonStaff.setBounds(20, 250, 200, 40);
        buttonStaff.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonStaff.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonStaff.setBackground(Color.WHITE);
        // Customer Button
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

    // Button Listener
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton)e.getSource();
            String action = buttonPressed.getText();
            switch(action){
                case "Staff":
                    dispose();
                    startingPageFrame.setVisible(false);
                    new StaffLoginPage();
                    break;
                case "Customer":
                    dispose();
                    startingPageFrame.setVisible(false);
                    new CustomerLoginPage();
                    break;
            } // end of switch statements
        } // end of actionPerformed
    };
}

