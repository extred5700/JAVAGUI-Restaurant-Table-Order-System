// Put in phone number

package Main.boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class customerLogin extends JFrame{
    // Variable Declaration
    private final JFrame customerLoginFrame;
    public JLabel tableNumberLabel = new JLabel("Table Number: ");
    public JTextField tableNumberField = new JTextField();
    public JButton buttonLogin = new JButton("Proceed to view menu");
    public JButton buttonReturn = new JButton("Return");

    public customerLogin(){
        customerLoginFrame = new JFrame("Customer Login");
        customerLoginFrame.setResizable(false);
        customerLoginFrame.setSize (400, 600);
        customerLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerLoginFrame.getContentPane().setLayout(null);

        // Enter customer's table number
        tableNumberLabel.setBounds(30, 100, 200, 40);
        tableNumberField.setBounds(130, 100, 200, 40);
        // Limit the text field to accept only NUMBERS
        tableNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
                    e.consume();
                }
            }
        });
        customerLoginFrame.getContentPane().add(tableNumberLabel);
        customerLoginFrame.getContentPane().add(tableNumberField);

        // "Login" for customers
        buttonLogin.setBounds(70, 330, 250, 40);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if text field is EMPTY
                if (tableNumberField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a table number.", "Error!", JOptionPane.ERROR_MESSAGE);
                } // end of if statements
                else{
                    int customerTableNum = Integer.parseInt(tableNumberField.getText()); // convert customer table number to Integer
                    JOptionPane.showMessageDialog(null, customerTableNum); // testing
                    // Statements to check whether table number is valid/taken

                } // end of else statements
            } // end of actionPerformed method
        });
        customerLoginFrame.getContentPane().add(buttonLogin);

        // Return button
        buttonReturn.setBounds(70, 380, 250, 40);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                customerLoginFrame.setVisible(false);
                new startingPage();
            }
        });
        customerLoginFrame.getContentPane().add(buttonReturn);


        customerLoginFrame.setVisible(true);
    }
}