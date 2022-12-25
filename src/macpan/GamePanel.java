/*
 * B Cutten
    May 2022
    A class which allows drawing, because it extends JPanel, by way of the 
    Graphics2D class
    This panel is repainted regulary by implementing the Runnable interface and 
    running in a Thread. This allows it to be animated
 */
package macpan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;
    Block[][] b = new Block[32][28];

    // default constructor
    public GamePanel() {
        readFile(b);
    }

    public static void readFile(Block[][] b) {
        try { //to read the file
            File f = new File("src/MacPan/blocks.data"); //read in the file
            Scanner s = new Scanner(f); //create scanner for file

            String imageLink = "";
            int x, y;
            ImageIcon img = null;

            for (int i = 0; i < 32; i++) { //Loop the size of the array to fill every spot
                for (int j = 0; j < 28; j++) {
                    String imgLink = s.nextLine();
                    x = Integer.parseInt(s.nextLine());
                    y = Integer.parseInt(s.nextLine());
                    img = new ImageIcon(imgLink);
                    b[i][j] = new Block(img, x, y);
                }
            }

        } catch (FileNotFoundException e) { //if file is not read properly
            System.out.println(e);
        }
    }

    //does the actual drawing
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
    }

    //overrides paintComponent in JPanel class
    //performs custom painting
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        doDrawing(g);
    }

    //update the position of the ball
    //this method is called after the JPanel is added to the JFrame
    //we can perform start up tasks here
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    //this method is called only once, when the Thread starts
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        //get the current time
        beforeTime = System.currentTimeMillis();

        while (true) { //this loop runs once ever 25 ms (the DELAY)
            repaint();

            //calculate how much time has passed since the last call
            //this allows smooth updates and our ball will move at a constant speed (as opposed to being dependent on processor availability)
            timeDiff = System.currentTimeMillis() - beforeTime;

            //calculate how much time to wait before the next call
            sleep = DELAY - timeDiff;

            //always wait at least 2 ms
            if (sleep < 0) {
                sleep = 2;
            }

            //try to actually wait
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                //threads can be interrupted from other threads
                JOptionPane.showMessageDialog(this, "Thread interrupted: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            //get the new current time
            beforeTime = System.currentTimeMillis();
        }
    }
}
