package Main.boundary.StaffUI;

import Main.boundary.StaffLoginPage;
import Main.controller.RestaurantStaff.StaffViewController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RestaurantStaffPageUI extends JFrame {
    /* Variable declaration */
    private final JFrame staffUIFrame = new JFrame("Restaurant Staff Homepage");
    private TitledBorder titledBorder;

    /* Buttons for top of the GUI*/
    private final JButton buttonLogout = new JButton("Logout");
    private final JButton buttonEdit = new JButton("Edit");
    private final JButton buttonSearch = new JButton("Search");
    private final JButton buttonView = new JButton("View");
    private final JButton buttonDelete = new JButton("Delete");

    /* 1. EDIT Function */
    private final JPanel panelEditOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
    private JTable tableEditOrder;

    public RestaurantStaffPageUI(String usernameLoggedIn){
        staffUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffUIFrame.getContentPane().setLayout(new FlowLayout());
        staffUIFrame.setSize(520, 705);
        staffUIFrame.setResizable(false);
        staffUIFrame.setLocationRelativeTo(null); // Window will display in the middle of the screen
        staffUIFrame.getContentPane().setBackground(Color.WHITE);

        // Display Staff's Username
        JLabel labelTopHeader = new JLabel("You are currently logged in as: " + usernameLoggedIn, JLabel.CENTER);
        labelTopHeader.setPreferredSize(new Dimension(500, 30));
        labelTopHeader.setBorder(new LineBorder(Color.WHITE));
        labelTopHeader.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        staffUIFrame.add(labelTopHeader);

        // Add buttons & functions for the top of the GUI
        displayStaffUserButton();


        /* Button Function for Administrator */
        // 1. EDIT function
        displayEditOrder();
        panelEditOrder.setVisible(true);


        staffUIFrame.setVisible(true);
    }

    /* Universal GUI Functions
     * displayAdminUserButtons() - Method to display the buttons on top of the screen
     * ActionListener topButtonsListener - Button Listener for the buttons on top of the screen
     * displayTitledBorder(JPanel panel) - Construction and display of the Titled Border
     * getTransactionHistory(ArrayList<ArrayList<String>> arrayListTransactionHistory) - Convert arraylist (Containing all transaction history) to a 2D array
     */
    public void displayStaffUserButton(){
        JButton [] myArray = {buttonLogout, buttonEdit, buttonSearch, buttonView, buttonDelete};
        for (JButton jButton : myArray) {
            jButton.setPreferredSize(new Dimension(95, 30));
            jButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
            jButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            jButton.setBackground(Color.WHITE);
            jButton.addActionListener(topButtonsListener);
            staffUIFrame.add(jButton);
        }
    }

    // Top buttons listener
    ActionListener topButtonsListener = e ->{
        JButton buttonPressed = (JButton)e.getSource();
        String action = buttonPressed.getText();
        switch(action){
            case "Logout":
                staffUIFrame.dispose();
                staffUIFrame.setVisible(false);
                new StaffLoginPage();
                break;
            case "Edit":
                dispose();
                panelEditOrder.setVisible(true);
                break;
            case "View":
                dispose();
                panelEditOrder.setVisible(false);
                break;
            case "Search":
                dispose();
                panelEditOrder.setVisible(false);
                break;
            case "Delete":
                dispose();
                panelEditOrder.setVisible(false);
                break;
        }
    };

    // Method to construct and display of the Titled Border
    public void displayTitledBorder(JPanel panel){
        titledBorder = new TitledBorder("Edit Customer Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        panel.setBorder(titledBorder);
    }

    // Method to store the respective array list into a 2D array
    public String[][] getTransactionHistory(ArrayList<ArrayList<String>> arrayListTransactionHistory){
        String [] transaction_id = arrayListTransactionHistory.get(0).toArray(new String[0]);
        String [] table_num = arrayListTransactionHistory.get(1).toArray(new String[0]);
        String [] date = arrayListTransactionHistory.get(2).toArray(new String[0]);
        String [] phone_num = arrayListTransactionHistory.get(3).toArray(new String[0]);
        String [] total_price = arrayListTransactionHistory.get(4).toArray(new String[0]);
        String [] isPaid = arrayListTransactionHistory.get(5).toArray(new String[0]);

        String [][] arrayAllTransactions = new String[transaction_id.length][arrayListTransactionHistory.size()];
        for (int i = 0; i < transaction_id.length; i++){
            arrayAllTransactions[i][0] = transaction_id[i];
            arrayAllTransactions[i][1] = table_num[i];
            arrayAllTransactions[i][2] = date[i];
            arrayAllTransactions[i][3] = phone_num[i];
            arrayAllTransactions[i][4] = total_price[i];
            arrayAllTransactions[i][5] = isPaid[i];
        }
        return arrayAllTransactions;
    }


    /* 1a. CREATE function
    * 1a) displayEditOrder method - Display JPanel for Restaurant Staff to edit orders
    * 1b) displayTableConstruction() method - Construction of the JTable, Mouse Click Listener to display all transactions (JTable type returned as a JScrollPane type)
    */
    // 1a) Method to display JPanel for Restaurant Staff to edit orders
    public void displayEditOrder(){
        displayTitledBorder(panelEditOrder); // Display titled border

        // Table Construction called in method, converted to a JScrollPane
        JScrollPane editOrderScrollPane = (JScrollPane) displayTableConstruction();






        // Add components to the JPanel
        panelEditOrder.setPreferredSize(new Dimension(500, 490));
        panelEditOrder.setBackground(Color.WHITE);
        panelEditOrder.add(editOrderScrollPane);
        staffUIFrame.add(panelEditOrder);
        panelEditOrder.setVisible(true);;
    }

    // 1b) Method to construction of the JTable, Mouse Click Listener to display all transactions (JTable type returned as a JScrollPane type)
    public Component displayTableConstruction(){
        StaffViewController staffViewController = new StaffViewController();
        ArrayList<ArrayList<String>> arrayListOrders = staffViewController.displayOrders();
        String [][] data =getTransactionHistory(arrayListOrders);
        // Display data in a table format
        String [] columnTableNames = {"Order ID", "Transaction ID", "Item ID", "Quantity", "Price", "Fulfilled"};
        tableEditOrder = new JTable(data, columnTableNames);
        JScrollPane sp = new JScrollPane(tableEditOrder);
        sp.setPreferredSize(new Dimension(485, 200)); // width then height
        return sp;
    }




}
