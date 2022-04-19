/*
* Contains the following:
* 1. After keying in the table number (customerLogin.java), successfully "logged" into the customer account (after validation)
* 2. Browse Menu
* 3. Placing an order (Quantity, preference on food?)
* 4. Change order
* 5. Cancel order
* 6. Making payment
*
* Notes:
* 1. Browse + Placing an order at the same time
* 2. Optional - Create a textbox at the checkout page & DB extra column to record customers preference on how their food should be prepared
* */
package Main.boundary;

import javax.swing.*;

public class customerUI extends JFrame{
    // Variable declaration
    private JFrame customerUIFrame;
    public JButton buttonMenu = new JButton("Menu");
    public JButton buttonEditOrder = new JButton("Edit Order");
    public JButton buttonPayment = new JButton("Payment");

    public customerUI(){
        customerUIFrame = new JFrame("Customer Homepage");
        customerUIFrame.setResizable(false);
        customerUIFrame.setSize (400, 600);
        customerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerUIFrame.getContentPane().setLayout(null);

        // Button to browse Menu
        buttonMenu.setBounds(30, 100, 200, 40);
        customerUIFrame.getContentPane().add(buttonMenu);

        customerUIFrame.setVisible(true);
    }
}
