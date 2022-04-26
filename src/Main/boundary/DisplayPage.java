package Main.boundary;

import javax.swing.*;
import java.awt.*;

public class DisplayPage extends JFrame{
    public DisplayPage(String nameOfFrame){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setSize(520, 705);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Window will popout in the middle of the screen
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle(nameOfFrame);

        // Display Banner Image
        Image logoImage = new ImageIcon("src/Main/boundary/Images/Logo-Red.png").getImage();      // For banner
        JLabel displayBannerImage = new JLabel(new ImageIcon(logoImage),SwingConstants.CENTER);   // For banner
        displayBannerImage.setBounds(0, 0, 505, 200);
        this.add(displayBannerImage);

        this.setVisible(true);
    }
}
