/*
 * Jack Luhta
 * December 25, 2022
 * 
 */
package macpan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends javax.swing.JFrame {

    MenuFrame mainWindow;
    JButton btnBack = new javax.swing.JButton();
    
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
        setSize(700, 800);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
