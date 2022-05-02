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
    private TitledBorder titledBorder;

    // Buttons
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonGenerateData = new JButton("Generate Data");

    /* Text Fields */
    private final JLabel labelDaily = new JLabel("Daily", SwingConstants.CENTER);
    private final JLabel labelWeekly = new JLabel("Weekly", SwingConstants.CENTER);
    private final JLabel labelMonthly = new JLabel("Monthly", SwingConstants.CENTER);
    private final JTextField fieldDaily = new JTextField(10);
    private final JTextField fieldWeekly = new JTextField(10);
    private final JTextField fieldMonthly = new JTextField(10);

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
        JButton [] buttons = {buttonLogout, buttonGenerateData};
        constructButtons(buttons);
        ownerUIFrame.add(buttonLogout);

        // Display respective labels, fields and buttons
        displayGenerateDataFields();

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
                    GenerateReportController generateReportController = new GenerateReportController();
                    String [] generatedReport = generateReportController.getReport(radioButtonSelected);
                    displayGeneratedResults(generatedReport);
                }

                break;
        }
    };

    // Method for Restaurant Owner to display the data generation functions
    public void displayGenerateDataFields(){
        // Border
        titledBorder = new TitledBorder("Generate Data");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Display Average Spend
        JLabel [] labels = {labelDaily, labelWeekly, labelMonthly};
        JTextField [] textFields = {fieldDaily, fieldWeekly, fieldMonthly};
        JRadioButton [] radioButtons = {radioAvgSpend, radioFreqVisit, radioFoodPreference};
        String [] actionCommand = {"Average Spend", "Frequency of Visits", "Food Preference"};
        constructAndAddAvgSpendFields(labels, textFields, radioButtons, actionCommand);

        // Generate Data Button
        panelReport.add(buttonGenerateData);

        // Add components to the JPanel
        panelReport.setPreferredSize(new Dimension(490, 500));
        panelReport.setBackground(Color.WHITE);
        panelReport.setBorder(titledBorder);
        panelReport.setVisible(true);
        ownerUIFrame.add(panelReport);
    }

    // Method for to construct & display labels, textfields and button
    public void constructAndAddAvgSpendFields(JLabel [] labels, JTextField [] textFields, JRadioButton [] radioButtons, String [] actionCommand){
        // Labels
        for (JLabel jLabel : labels){
            jLabel.setPreferredSize(new Dimension(110, 15));
            jLabel.setBorder(new LineBorder(Color.WHITE));
            jLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            panelReport.add(jLabel);
        }

        // Text Fields
        for (JTextField jTextField : textFields){
            //jTextField.setEnabled(false);
            jTextField.setForeground(Color.BLACK);
            panelReport.add(jTextField);
        }

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

    // Method to display the calculated and generated results into the JTextFields
    public void displayGeneratedResults(String [] generatedReport){
        fieldDaily.setText(generatedReport[0]);
        fieldWeekly.setText(generatedReport[1]);
        fieldMonthly.setText(generatedReport[2]);
    }
}
