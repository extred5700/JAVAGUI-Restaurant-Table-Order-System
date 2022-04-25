package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingPage extends JFrame{
    /* Variable declaration */
    private JFrame startingPageFrame;
    private final JLabel labelTopText = new JLabel("Who's Eating?");
    // Buttons
    private final JButton buttonStaff = new JButton("Staff");
    private final JButton buttonCustomer = new JButton("Customer");


    public StartingPage(){
        startingPageFrame = new DisplayPage("Homepage");

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

