package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startingPage extends JFrame{
    // Variable Declaration
    private final JFrame startingPageFrame;
    public JButton buttonAdmin = new JButton("Admin");          // For admin, Owner, Manager, Staff
    public JButton buttonCustomer = new JButton("Customer");    // For Customer ONLY

    public startingPage(){
        startingPageFrame = new JFrame("Hopium Restaurant");
        startingPageFrame.setResizable(false);
        startingPageFrame.setSize (400, 600);
        startingPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startingPageFrame.getContentPane().setLayout(null);


        // Restaurant name at the top
        JLabel restaurantLabel = new JLabel("Hopium Restaurant");
        restaurantLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        restaurantLabel.setBounds(95, 25, 200, 40);
        startingPageFrame.getContentPane().add(restaurantLabel);


        /*Selection of actors */
        // Admin Button
        buttonAdmin.setBounds(95, 300, 200, 40);
        buttonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                startingPageFrame.setVisible(false);
                new adminLogin();
            }
        });
        // Customer Button
        buttonCustomer.setBounds(95, 350, 200, 40);
        buttonCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                startingPageFrame.setVisible(false);
                new customerLogin();
            }
        });


        startingPageFrame.getContentPane().add(buttonAdmin);
        startingPageFrame.getContentPane().add(buttonCustomer);
        startingPageFrame.setVisible(true);
    }
}
