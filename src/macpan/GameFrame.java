/*
 * Jack Luhta
 * December 25, 2022
 * 
 */
package macpan;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends javax.swing.JFrame {

    JButton btnBack = new javax.swing.JButton();
    private Image icon  = new ImageIcon("src/macpan/images/Consumables/cherry.png").getImage();
    
    //constructor
    public GameFrame(MenuFrame m) {
        //create the User interface
        initUI(m);
    }

    //create the custom JFrame
    private void initUI(MenuFrame m) {        
        //set title of the JFrame
        setTitle("MacPan");
        setIconImage(icon);
        
        //add a custom JPanel to draw on
        add(new GamePanel(m, this));
        //set the size of the window
        setSize(700, 800);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
