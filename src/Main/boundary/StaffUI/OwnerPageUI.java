package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantOwner.GenerateReportController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OwnerPageUI extends JFrame {
    /* Variable declaration */
    private final JFrame ownerUIFrame = new JFrame("Restaurant Owner Homepage");

    private final JButton buttonGenerateData = new JButton("Generate Data");

    // Radio buttons
    private final JRadioButton radioAvgSpend = new JRadioButton("Average Spend Per Visit");
    private final JRadioButton radioFreqVisit = new JRadioButton("Frequency/Patterns of Visits");
    private final JRadioButton radioFoodPreference = new JRadioButton("Dish/Drink Preference");
    private final ButtonGroup buttonGroup = new ButtonGroup();

    // JPanel
    private final JPanel panelReport = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));


    public OwnerPageUI(String usernameLoggedIn){
        ownerUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ownerUIFrame.getContentPane().setLayout(new FlowLayout());
        ownerUIFrame.setSize(520, 705);
        ownerUIFrame.setResizable(false);
        ownerUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        ownerUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Manager's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        ownerUIFrame.add(labelTopHeader);

        // Add buttons & functions
        JButton buttonLogout = new JButton("Logout");
        JButton [] buttons = {buttonLogout, buttonGenerateData};
        constructButtons(buttons);
        ownerUIFrame.add(buttonLogout);

        // Display respective labels, fields and buttons
        displayGenerateDataPanel();

        ownerUIFrame.setVisible(true);
    }

    // Method to display buttons for Restaurant Owner
    public void constructButtons(JButton [] buttons){
        for (JButton jButton : buttons){
            jButton.setPreferredSize(new Dimension(200, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
        }
    }

    // Buttons listener
    ActionListener topButtonsListener = e -> {
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch (action){
            case "Logout":
                ownerUIFrame.dispose();
                ownerUIFrame.setVisible(false);
                new StaffLoginPage();
                break;
            case "Generate Data":
                if (buttonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a radio button!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String radioButtonSelected = buttonGroup.getSelection().getActionCommand();
                    // Table
                    refreshEditTable(radioButtonSelected);
                }

                break;
        }
    };

    // Method for Restaurant Owner to display the data generation functions
    public void displayGenerateDataPanel(){
        // Border
        TitledBorder titledBorder = new TitledBorder("Generate Data");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Radio Buttons
        JRadioButton [] radioButtons = {radioAvgSpend, radioFreqVisit, radioFoodPreference};
        String [] actionCommand = {"Average Spend", "Frequency of Visits", "Food Preference"};
        constructAndAddAvgSpendFields(radioButtons, actionCommand);

        // Generate Data Button
        panelReport.add(buttonGenerateData);

        // Add components to the JPanel
        panelReport.setPreferredSize(new Dimension(490, 500));
        panelReport.setBackground(Color.WHITE);
        panelReport.setBorder(titledBorder);
        panelReport.setVisible(true);
        ownerUIFrame.add(panelReport);
    }

    // Method to construct and add Radio Buttons to the JPanel
    public void constructAndAddAvgSpendFields(JRadioButton [] radioButtons, String [] actionCommand){
        // Radio Buttons
        for (int i = 0; i < radioButtons.length; i++){
            radioButtons[i].setBackground(Color.WHITE);
            radioButtons[i].setAlignmentX(SwingConstants.CENTER);
            radioButtons[i].setAlignmentY(SwingConstants.CENTER);
            radioButtons[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            radioButtons[i].setPreferredSize(new Dimension(240, 15));
            radioButtons[i].setActionCommand(actionCommand[i]);
            buttonGroup.add(radioButtons[i]);
            panelReport.add(radioButtons[i]);
        }
    }

    // Construction of the JTable (JTable type returned as a JScrollPane type)
    public Component displayDataTableConstruction(String radioButtonSelected){
        GenerateReportController generateReportController = new GenerateReportController();
        String [][] data = generateReportController.getReport(radioButtonSelected);
        // Display data in a table format
        String [] columnTableNames = {"Daily", "Weekly", "Monthly"};
        // Table
        JTable tableGenerateData = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableGenerateData);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }

    // Method to refresh the Table data
    public void refreshEditTable(String radioButtonSelected){
        //Get the components in the panel
        Component[] componentList = panelReport.getComponents();
        //Loop through the components
        for(Component c : componentList){
            //Find the components you want to remove
            if(c instanceof JScrollPane){
                //Remove it
                panelReport.remove(c);
            }
        }
        panelReport.add(displayDataTableConstruction(radioButtonSelected));
        panelReport.revalidate();
        panelReport.repaint();
    }
}
