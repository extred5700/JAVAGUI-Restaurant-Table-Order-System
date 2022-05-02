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
    DefaultTableModel model;

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
     * isplayTitledBorder(JPanel panel) - Construction and display of the Titled Border
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

    public void displayTitledBorder(JPanel panel){
        titledBorder = new TitledBorder("Edit Customer Order");
        titledBorder.setBorder(new LineBorder(Color.BLACK));
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        panel.setBorder(titledBorder);
    }

    /* 1a. CREATE function
    * 1a) displayEditOrder function - Display JPanel for Restaurant Staff to edit orders
    */
    public void displayEditOrder(){
        displayTitledBorder(panelEditOrder); // Display titled border

        // Display Table
        JScrollPane editOrderScrollPane = new JScrollPane();
        panelEditOrder.add(editOrderScrollPane);


        tableEditOrder = new JTable();
        String [] column = {"Order ID", "Transaction ID", "Item ID", "Quantity", "Price", "Fulfilled"};
        Object [] row = new Object[column.length];
        tableEditOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int getSelectedRow = tableEditOrder.getSelectedRow();
            }
        });
        tableEditOrder.setDefaultEditor(Object.class, null);
        editOrderScrollPane.setViewportView(tableEditOrder);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        tableEditOrder.setModel(model);
        model = (DefaultTableModel) tableEditOrder.getModel();
        tableEditOrder.getTableHeader().setReorderingAllowed(false);

        StaffViewController staffViewController = new StaffViewController();
        ArrayList<ArrayList<String>> arrayListOrders = staffViewController.displayOrders();
        getOrderTable(arrayListOrders, row);




        // Add components to the JPanel
        panelEditOrder.setPreferredSize(new Dimension(500, 490));
        panelEditOrder.setBackground(Color.WHITE);
        staffUIFrame.add(panelEditOrder);
        panelEditOrder.setVisible(true);;
    }

    public void getOrderTable(ArrayList<ArrayList<String>> arrayListOrders, Object[] row){
        for (int i = 0; i < arrayListOrders.size(); i++){
            for (int j = 0; j < arrayListOrders.get(i).size(); j++){
                String value = arrayListOrders.get(i).get(j);
                if (j < 6){
                    row[j] = value;
                }
                if (j == 5){
                    model.addRow(row);
                }
            }
        }
    }




}
