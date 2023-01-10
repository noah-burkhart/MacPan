/*
 * Jack Luhta
 * December 25, 2022
 * 
 */
package macpan;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class GameFrame extends JFrame {

    MenuFrame mainWindow;
    
    //constructor
    public GameFrame(MenuFrame m) {
        //create the User interface
        initUI();
        mainWindow = m;
    }

    //create the custom JFrame
    private void initUI() {        
        //set title of the JFrame
        setTitle("MACPAN");
        //add a custom JPanel to draw on
        add(new GamePanel());
        //set the size of the window
        setSize(600, 800);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
