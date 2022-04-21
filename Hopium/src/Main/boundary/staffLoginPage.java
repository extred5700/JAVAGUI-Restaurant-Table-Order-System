package Main.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class staffLoginPage extends JFrame{
    /* Variable declaration */
    private final JFrame staffLoginFrame = new JFrame("Staff Login Page");
    private final Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();      // For banner
    private final JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);   // For banner

    /* --- STAFF ---
     * Buttons
     * Labels
     * Text Fields
     * Radio Button
     */
    // Labels
    private final JLabel labelID = new JLabel("Login ID: ");
    private final JLabel labelPassword = new JLabel("Password: ");
    // Text Fields
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    // Radio Buttons
    public JRadioButton radioAdmin = new JRadioButton("Administrator");
    private final JRadioButton radioOwner = new JRadioButton("Owner");
    private final JRadioButton radioManager = new JRadioButton("Manager");
    private final JRadioButton radioStaff = new JRadioButton("Staff");
    private final ButtonGroup jobTypeButtonGroup = new ButtonGroup();

    // Buttons
    private final JButton buttonLogin = new JButton("Log In");
    private final JButton buttonReturn = new JButton("Return");

    public staffLoginPage(){
        staffLoginFrame.getContentPane().setBackground(Color.WHITE);
        staffLoginFrame.setSize (520, 705);
        staffLoginFrame.getContentPane().setLayout(null);
        staffLoginFrame.setLocationRelativeTo(null); // Window will popout in the middle of the screen
        staffLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Banner Image
        displayBannerImage.setBounds(0, 0, 505, 200);
        staffLoginFrame.getContentPane().add(displayBannerImage);

        /* Login, Password Label and Text Field */
        // Login ID
        labelID.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelID.setBounds(100, 250, 200, 40);
        loginField.setBounds(200, 250, 200, 40);
        staffLoginFrame.getContentPane().add(labelID);
        staffLoginFrame.getContentPane().add(loginField);
        // Password
        labelPassword.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        labelPassword.setBounds(100, 300, 200, 40);
        passwordField.setBounds(200, 300, 200, 40);
        staffLoginFrame.getContentPane().add(labelPassword);
        staffLoginFrame.getContentPane().add(passwordField);

        // Display Radio Buttons
        displayStaffRadioButtons();

        // Login button
        buttonLogin.setBounds(40, 490, 200, 40);
        buttonLogin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogin.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonLogin.setBackground(Color.WHITE);
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
        staffLoginFrame.getContentPane().add(buttonLogin);
        // Return button
        buttonReturn.setBounds(270, 490, 200, 40);
        buttonReturn.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonReturn.setBorder(BorderFactory.createLineBorder(Color.RED,1));
        buttonReturn.setBackground(Color.WHITE);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                staffLoginFrame.setVisible(false);
                new startingPage();
            } // end of actionPerformed method
        });
        staffLoginFrame.getContentPane().add(buttonReturn);



        staffLoginFrame.setVisible(true);
    }

    /*
    Method to display radio buttons
    add radio button to a radio button group
    */
    public void displayStaffRadioButtons(){
        // Administrator
        radioAdmin.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioAdmin.setBounds(200, 360, 200, 20);
        staffLoginFrame.getContentPane().add(radioAdmin);
        radioAdmin.setActionCommand("Admin");

        // Restaurant Owner
        radioOwner.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioOwner.setBounds(200, 390, 200, 20);
        staffLoginFrame.getContentPane().add(radioOwner);
        radioOwner.setActionCommand("Owner");

        // Restaurant Manager
        radioManager.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioManager.setBounds(200, 420, 200, 20);
        staffLoginFrame.getContentPane().add(radioManager);
        radioManager.setActionCommand("Manager");

        // Restaurant Staff
        radioStaff.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        radioStaff.setBounds(200, 450, 200, 20);
        staffLoginFrame.getContentPane().add(radioStaff);
        radioStaff.setActionCommand("Staff");

        // Radio Button Group
        jobTypeButtonGroup.add(radioAdmin);
        jobTypeButtonGroup.add(radioOwner);
        jobTypeButtonGroup.add(radioManager);
        jobTypeButtonGroup.add(radioStaff);

    } // end of radioButtonForStaff method
}
