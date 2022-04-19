package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminLogin extends JFrame{
    // Variable Declaration
    private final JFrame adminLoginFrame;
    public JLabel labelID = new JLabel("Login ID: ");
    public JTextField loginField = new JTextField();
    public JLabel labelPassword = new JLabel("Password: ");
    public JPasswordField passwordField = new JPasswordField();

    public JRadioButton radioAdmin = new JRadioButton("Administrator");
    public JRadioButton radioOwner = new JRadioButton("Owner");
    public JRadioButton radioManager = new JRadioButton("Manager");
    public JRadioButton radioStaff = new JRadioButton("Staff");
    public JButton buttonLogin = new JButton("Log In");
    public JButton buttonReturn = new JButton("Return");

    public adminLogin(){
        adminLoginFrame = new JFrame("Administrator Log In");
        adminLoginFrame.setResizable(false);
        adminLoginFrame.setSize (400, 600);
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.getContentPane().setLayout(null);

        // Label on the top
        JLabel adminPageLabel = new JLabel("Actors");
        adminPageLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        adminPageLabel.setBounds(95, 25, 200, 40);
        adminLoginFrame.getContentPane().add(adminPageLabel);


        /* Login & Password Fields */
        // Login field
        labelID.setBounds(30, 100, 200, 40);
        loginField.setBounds(95, 100, 200, 40);
        adminLoginFrame.getContentPane().add(labelID);
        adminLoginFrame.getContentPane().add(loginField);
        // Password field
        labelPassword.setBounds(30, 150, 200, 40);
        passwordField.setBounds(95, 150, 200, 40);
        adminLoginFrame.getContentPane().add(labelPassword);
        adminLoginFrame.getContentPane().add(passwordField);


        /* Radio Button Group for actors */
        // Administrator
        radioAdmin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioAdmin.setBounds(95, 210, 150, 20);
        adminLoginFrame.getContentPane().add(radioAdmin);
        radioAdmin.setActionCommand("Admin");

        // Restaurant Owner
        radioOwner.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioOwner.setBounds(95, 240, 150, 20);
        adminLoginFrame.getContentPane().add(radioOwner);
        radioOwner.setActionCommand("Owner");

        // Restaurant Owner
        radioManager.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioManager.setBounds(95, 270, 150, 20);
        adminLoginFrame.getContentPane().add(radioManager);
        radioManager.setActionCommand("Manager");

        // Restaurant Staff
        radioStaff.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioStaff.setBounds(95, 300, 150, 20);
        adminLoginFrame.getContentPane().add(radioStaff);
        radioStaff.setActionCommand("Staff");

        // Radio Button Group
        ButtonGroup jobTypeButtonGroup = new ButtonGroup();
        jobTypeButtonGroup.add(radioAdmin);
        jobTypeButtonGroup.add(radioOwner);
        jobTypeButtonGroup.add(radioManager);
        jobTypeButtonGroup.add(radioStaff);


        // Login button
        buttonLogin.setBounds(70, 330, 250, 40);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if radio button is selected then display ERROR MESSAGE if necessary
                if (jobTypeButtonGroup.getSelection() == null){
                    JOptionPane.showMessageDialog(null, "Please select a specific job type.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else{
                   String actorSelected = jobTypeButtonGroup.getSelection().getActionCommand(); // get String value of the type of actor selected
                   switch (actorSelected){
                       // Following codes are strictly for TESTING
                       case "Admin":
                           JOptionPane.showMessageDialog(null, "1 " + actorSelected);
                           break;
                       case "Owner":
                           JOptionPane.showMessageDialog(null, "2 " + actorSelected);
                           break;
                       case "Manager":
                           JOptionPane.showMessageDialog(null, "3 " + actorSelected);
                           break;
                       case "Staff":
                           JOptionPane.showMessageDialog(null, "4 " + actorSelected);
                           break;
                   } // end of switch statements
                } // end of else statements
            } // end of actionPerformed method
        });
        adminLoginFrame.getContentPane().add(buttonLogin);

        // Return button
        buttonReturn.setBounds(70, 380, 250, 40);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminLoginFrame.setVisible(false);
                new startingPage();
            } // end of actionPerformed method
        });
        adminLoginFrame.getContentPane().add(buttonReturn);


        adminLoginFrame.setVisible(true);
    }
}