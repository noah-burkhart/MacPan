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
import macpan.objects.Block;
import macpan.objects.Pellet;

import macpan.objects.Thing;

public class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;
    Thing[][] b = new Thing[21][19];

    File blocks = new File("src/macpan/images/Consumables/pellet.png");
    BufferedImage imgBlocks;
    File pellet = new File("src/macpan/images/Consumables/pellet.png");
    BufferedImage imgPellet;
    File powerPellet = new File("src/macpan/images/Consumables/powerpellet.png");
    BufferedImage imgPowerPellet;
    File food = new File("src/macpan/images/Consumables/food.png");
    BufferedImage imgFood;
    
    
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
        try {
            imgBlocks = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgBlocks = ImageIO.read(blocks);
            imgPellet = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgPellet = ImageIO.read(pellet);
            imgPowerPellet = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgPowerPellet = ImageIO.read(powerPellet);
            imgFood = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            imgFood = ImageIO.read(food);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        blinky = new Blinky(imgBlinky, 156, 156, "east", true);
        //pinky = new Pinky(imgPinky, 30, 0, "east", true);
        //inky = new Inky(imgInky, 60, 0, "east", true);
        //clyde = new Clyde(imgClyde, 90, 0, "east", true);
        blinky.setXSpeed(2);
        blinky.setYSpeed(2);
    }

    
    /**
     * Reads from the file of game board data and creates the game board.
     * @param b - the board.
     */
    public static void readFile(Thing[][] b) {
        String type = "";

        try {
            File f = new File("src/Blocks.data");
            Scanner s = new Scanner(f);  //tries to make a file and a scanner

            for (int y = 0; y < 21; y++) { //Loops through the y axis of the 2D array
                for (int x = 0; x < 19; x++) { //loops through the x
                    
                    type = s.nextLine(); //saves the type of 'thing' from the data file

                    if (type.equals("block")) { //if it is a block
                        b[x][y] = new Block(imgBlocks, x, y);  //set to a block

                    } else if (type.equals("pellet")) { //if it is a pellet 
                        b[x][y] = new Pellet(imgPellet, x, y);  //set to pellet

                    } else if (type.equals("powerPellet")) { //if it is a Power pellet 
                        b[x][y] = new PowerPellet(imgPowerPellet, x, y);  //set to Power pellet
                        
                    }else if(type.equals("food")){ //if it is a Food object 
                       b[x][y] = new Food(imgFood, x, y);  //set to Food
                    }
                }
            }
            
        } catch (FileNotFoundException e) { //cacthes file not found
            System.out.println("Error: " + e);
        }
    }

    //does the actual drawing
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 19; j++) {
                g2d.drawImage(b[i][j].getSprite(), b[i][j].getX(), b[i][j].getY(), 26, 26, Color.black, this);
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

        int num = 2;

        while (true) { //this loop runs once ever 25 ms (the DELAY)
            bImg = new File("src/macpan/images/Ghosts/Blinky/blinky1.png");
            try {
                imgBlinky = ImageIO.read(bImg);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            blinky.setSprite(imgBlinky);
            if (blinky.getXPos() % 26 == 0 && blinky.getYPos() % 26 == 0) {
                num = (int) (Math.random() * 4 + 1);
            }
            if (num == 1) {
                blinky.moveUp();
            } else if (num == 2) {
                blinky.moveRight();
            } else if (num == 3) {
                blinky.moveDown();
            } else {
                blinky.moveLeft();
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
