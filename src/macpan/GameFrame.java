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
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 700;
        c.ipadx = 600;
        //add a custom JPanel to draw on
        add(new GamePanel(), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 20;
        c.ipadx = 200;
        btnBack.setBackground(Color.black);
        btnBack.setFont(new java.awt.Font("Monospaced", 1, 20));
        btnBack.setForeground(Color.yellow);
        btnBack.setText("Back");
        btnBack.setLocation(600, 600);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(true);
                setVisible(false);
                
            }
        }
        );
        add(btnBack, c);
        //set the size of the window
        setSize(600, 800);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
