package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantOwner.GenerateReportController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

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

        // Logout button on the top of the GUI
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.setPreferredSize(new Dimension(200, 30));
        buttonLogout.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonLogout.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonLogout.setBackground(Color.WHITE);
        // Button LOGOUT on click listener
        buttonLogout.addActionListener(e -> {
            ownerUIFrame.dispose();
            ownerUIFrame.setVisible(false);
            new StaffLoginPage();
        });
        ownerUIFrame.add(buttonLogout);

        // Display respective labels, fields and buttons
        displayGenerateDataPanel();

        ownerUIFrame.setVisible(true);
    }

    /* 1. GENERATE REPORT/DATA function
     * 1a) void displayGenerateDataPanel() - Display JPanel for Restaurant Owner to display the data generation functions
     * 1b) Component generateDataTableConstruction(String [][] data) - Construction of the JTable (JTable type returned as a JScrollPane type)
     * 1c) void generateDataButton_Onclick() - GENERATE DATA button actions
     */

    // 1a) Method for Restaurant Owner to display the data generation functions
    public void displayGenerateDataPanel(){
        // Border
        TitledBorder titledBorder = new TitledBorder("Generate Data");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));

        // Table Construction called in method, converted to a JScrollPane
        String [][] defaultTableValues = {{"", "", ""}};
        JScrollPane scrollPane3 = (JScrollPane) generateDataTableConstruction(defaultTableValues);
        panelReport.add(scrollPane3);

        // Radio Buttons
        JRadioButton [] radioButtons = {radioAvgSpend, radioFreqVisit, radioFoodPreference};
        String [] actionCommand = {"Average Spend", "Frequency of Visits", "Food Preference"};
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
        radioButtons[0].setSelected(true);

        // Generate Data Button
        buttonGenerateData.setPreferredSize(new Dimension(200, 30));
        buttonGenerateData.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        buttonGenerateData.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        buttonGenerateData.setBackground(Color.WHITE);
        buttonGenerateData.addActionListener(e -> generateDataButton_Onclick()); // Button on click listener
        panelReport.add(buttonGenerateData);

        // Add components to the JPanel
        panelReport.setPreferredSize(new Dimension(490, 460));
        panelReport.setBackground(Color.WHITE);
        panelReport.setBorder(titledBorder);
        panelReport.setVisible(true);
        ownerUIFrame.add(panelReport);
    }

    // 1b) Construction of the JTable (JTable type returned as a JScrollPane type)
    public Component generateDataTableConstruction(String [][] data){
        // Display data in a table format
        String [] columnTableNames = {"Daily", "Weekly", "Monthly"};
        // Table
        JTable tableGenerateData = new JTable(data, columnTableNames);
        JScrollPane generatedDataScrollPane = new JScrollPane(tableGenerateData);
        generatedDataScrollPane.setPreferredSize(new Dimension(470, 200)); // width then height

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
        panelReport.add(generatedDataScrollPane, 0);
        panelReport.revalidate();
        panelReport.repaint();

        return generatedDataScrollPane;
    }

    // 1c) GENERATE DATA button actions
    public void generateDataButton_Onclick(){
        String radioButtonSelected = buttonGroup.getSelection().getActionCommand();
        GenerateReportController generateReportController = new GenerateReportController();
        String [][] data = generateReportController.getReport(radioButtonSelected);
        // Refresh Table
        generateDataTableConstruction(data);
    }

}
