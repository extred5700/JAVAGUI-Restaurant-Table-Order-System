package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerPageUI extends JFrame {
    /* Variable declaration */
    private final JFrame managerUIFrame = new JFrame("Restaurant Manager Homepage");
    private TitledBorder titledBorder;

    // Buttons
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonCreate = new JButton("Create");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonSearch = new JButton("Search");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonDelete = new JButton("Delete");

    public ManagerPageUI(String usernameLoggedIn){
        managerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerUIFrame.getContentPane().setLayout(new FlowLayout());
        managerUIFrame.setSize(520, 705);
        managerUIFrame.setResizable(false);
        managerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        managerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Manager's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        managerUIFrame.add(labelTopHeader);

        // Add buttons & functions
        displayManagerUserButtons();

        managerUIFrame.setVisible(true);
    }

    // Method to display buttons for Restaurant Manager
    public void displayManagerUserButtons(){
        JButton [] myArray = {buttonLogout, buttonCreate, buttonEdit, buttonSearch, buttonView, buttonDelete};
        for (JButton jButton : myArray) {
            jButton.setPreferredSize(new Dimension(78, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            managerUIFrame.add(jButton);
        }
    }

    // Top buttons listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action){
            case "Logout":
                managerUIFrame.dispose();
                managerUIFrame.setVisible(false);
                new StaffLoginPage();
                break;
            case "Create":
                break;
            case "Edit":
                break;
            case "Search":
                break;
            case "Delete":
                break;

        }
    };

}
