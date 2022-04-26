package Main.boundary;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPageUI extends JFrame{
    /* Variable declaration */
    private JFrame customerUIFrame = new JFrame("Customer Homepage");
    private final JLabel labelTableNumber;// = new JLabel("Table Number " + tableNumber, JLabel.CENTER);
    // Buttons
    private final JButton buttonMenu = new JButton("View Menu");
    private final JButton buttonEdit = new JButton("Edit Order");
    private final JButton buttonView = new JButton("View Order");
    private final JButton buttonPayment = new JButton("Payment");

    public CustomerPageUI(int tableNumber){
        customerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerUIFrame.getContentPane().setLayout(new FlowLayout());
        customerUIFrame.setSize(520, 705);
        customerUIFrame.setResizable(false);
        customerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        customerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Table Number
        labelTableNumber = new JLabel("Table Number " + tableNumber, JLabel.CENTER);
        labelTableNumber.setPreferredSize(new Dimension(500, 30));
        labelTableNumber.setBorder(new LineBorder(Color.BLACK));
        labelTableNumber.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        customerUIFrame.add(labelTableNumber);

        // Add buttons & functions
        displayCustomerButtons();



        customerUIFrame.setVisible(true);
    }

    // Method to display buttons for Customer
    public void displayCustomerButtons(){
        JButton [] myArray = {buttonMenu, buttonEdit, buttonView, buttonPayment};
        for (int i = 0; i < 4; i++){
            myArray[i].setPreferredSize(new Dimension(120, 30));
            myArray[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            myArray[i].setBorder(BorderFactory.createLineBorder(Color.RED,1));
            myArray[i].setBackground(Color.WHITE);
            myArray[i].addActionListener(topButtonsListener);
            customerUIFrame.add(myArray[i]);
        }
    }

    // Button Listener
    ActionListener topButtonsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton)e.getSource();
            String action = buttonPressed.getText();
            System.out.println(action);
            switch(action){
                case "View Menu":
                    viewMenu();
                    break;
                case "Edit Order":
                    break;
                case "View Order":
                    break;
                case "Payment":
                    break;
            } // end of switch statements
        } // end of actionPerformed
    };

    // View Menu
    public void viewMenu(){
        JLabel label1 = new JLabel("Success");
        customerUIFrame.add(label1);
        customerUIFrame.setVisible(true);
    }
}