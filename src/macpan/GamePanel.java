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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import macpan.characters.Blinky;

public class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;
    Block[][] b = new Block[21][19];

    File bImg = new File("src/macpan/images/Ghosts/Blinky/blinky1.png");
    BufferedImage imgBlinky;
    File pImg = new File("src/macpan/images/Ghosts/Pinky/pinky1.png");
    BufferedImage imgPinky;
    File iImg = new File("src/macpan/images/Ghosts/Inky/inky1.png");
    BufferedImage imgInky;
    File cImg = new File("src/macpan/images/Ghosts/Clyde/clyde1.png");
    BufferedImage imgClyde;

    Blinky blinky;
    //Pinky pinky;
    //Inky inky;
    //Clyde clyde;

    // default constructor
    public GamePanel() {
        readFile(b);
        try {
            bImg = new File("src/macpan/images/Ghosts/Blinky/blinky1.png");
            imgBlinky = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgBlinky = ImageIO.read(bImg);
            pImg = new File("src/macpan/images/Ghosts/Pinky/pinky1.png");
            imgPinky = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgPinky = ImageIO.read(pImg);
            iImg = new File("src/macpan/images/Ghosts/Inky/inky1.png");
            imgInky = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgInky = ImageIO.read(iImg);
            cImg = new File("src/macpan/images/Ghosts/Clyde/clyde1.png");
            imgClyde = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgClyde = ImageIO.read(cImg);
            System.out.println("Ghost Reading Complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        blinky = new Blinky(imgBlinky, 250, 200, "east", true);
        //pinky = new Pinky(imgPinky, 30, 0, "east", true);
        //inky = new Inky(imgInky, 60, 0, "east", true);
        //clyde = new Clyde(imgClyde, 90, 0, "east", true);
        blinky.setXSpeed(1);
        blinky.setYSpeed(2);
    }

    public static void readFile(Block[][] b) {
        try { //to read the file
            File f = new File("src/MacPan/blocks.data"); //read in the file
            Scanner s = new Scanner(f); //create scanner for file

            String imageLink = "";
            BufferedImage img = null;
            File imgF = null;

            for (int i = 0; i < 21; i++) { //Loop the size of the array to fill every spot
                for (int j = 0; j < 19; j++) {
                    String imgLink = s.nextLine();
                    try {
                        imgF = new File(imgLink); //image file path
                        img = new BufferedImage(27, 27, BufferedImage.TYPE_INT_ARGB);
                        img = ImageIO.read(imgF);
                        System.out.println("Reading complete.");
                    } catch (IOException e) {
                        System.out.println("Error: " + e);
                    }
                    b[i][j] = new Block(img, j * 27 + 25, i * 27 + 100);
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
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 19; j++) {
                g2d.drawImage(b[i][j].sprite, b[i][j].xPos, b[i][j].yPos, 27, 27, Color.black, this);
            }
        }
        g2d.drawImage(blinky.getSprite(), blinky.getXPos(), blinky.getYPos(), 25, 25, Color.black, this);
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

        int counter = 0;
        while (true) { //this loop runs once ever 25 ms (the DELAY)
            counter += DELAY;
            if (counter < 2500) {
                blinky.setXSpeed(1);
                bImg = new File("src/macpan/images/Ghosts/Blinky/blinky1.png");
                try {
                    imgBlinky = ImageIO.read(bImg);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                blinky.setSprite(imgBlinky);
                blinky.moveRight();
            } else if (counter < 5000) {
                blinky.setYSpeed(1);
                bImg = new File("src/macpan/images/Ghosts/Blinky/blinkyDown1.png");
                try {
                    imgBlinky = ImageIO.read(bImg);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                blinky.setSprite(imgBlinky);
                blinky.moveDown();
            } else if (counter < 7500) {
                blinky.setXSpeed(1);
                bImg = new File("src/macpan/images/Ghosts/Blinky/blinky1.png");
                try {
                    imgBlinky = ImageIO.read(bImg);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                blinky.setSprite(imgBlinky);
                blinky.moveLeft();
            } else if (counter < 10000) {
                blinky.setYSpeed(1);
                bImg = new File("src/macpan/images/Ghosts/Blinky/blinkyUp1.png");
                try {
                    imgBlinky = ImageIO.read(bImg);
                } catch (IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                blinky.setSprite(imgBlinky);
                blinky.moveUp();
            }
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
