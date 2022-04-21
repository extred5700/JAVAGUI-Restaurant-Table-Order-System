
package Main;

import Main.boundary.*;
import javax.swing.*;
import java.awt.*;

public class SkeletonUI extends JFrame{
    // Variable declaration
    private final JFrame testingUIFrame;
    public SkeletonUI(){
        testingUIFrame = new JFrame("Customer Homepage");
        testingUIFrame.setResizable(false);
        testingUIFrame.setSize (400, 600);
        testingUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testingUIFrame.getContentPane().setLayout(null);



        testingUIFrame.setVisible(true);
    }
}
