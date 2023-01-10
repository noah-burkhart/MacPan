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
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import macpan.characters.Blinky;
import macpan.objects.Block;
import macpan.objects.Pellet;
import macpan.objects.Food;
//import macpan.objects.PowerPellet;

import macpan.objects.Thing;

public class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25;
    Thing[][] b = new Thing[19][21];

    //creating image files to be used
    private Image imgPellet, imgPowerPellet, imgFood, imgBlock;
    private Image imgBlinky, imgPinky, imgInky, imgClyde;

    Blinky blinky;
    //Pinky pinky;
    //Inky inky;
    //Clyde clyde;

    // default constructor
    public GamePanel() {
        loadImage();
        loadBoard();

        blinky = new Blinky(imgBlinky, 156, 156, "east", true);
        //pinky = new Pinky(imgPinky, 30, 0, "east", true);
        //inky = new Inky(imgInky, 60, 0, "east", true);
        //clyde = new Clyde(imgClyde, 90, 0, "east", true);
        blinky.setXSpeed(2);
        blinky.setYSpeed(2);
    }

    public void loadImage() {
        //loads images that are unchanging
        imgBlock = new ImageIcon("src/macpan/images/JFrames/box.png").getImage();
        imgPellet = new ImageIcon("src/macpan/images/Consumables/smallPellet.png").getImage();
        imgPowerPellet = new ImageIcon("src/macpan/images/Consumables/powerPellet.png").getImage();
        imgFood = new ImageIcon("src/macpan/images/Consumables/cherry.png").getImage();

        imgBlinky = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky1.png").getImage();
        imgPinky = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinky1.png").getImage();
        imgInky = new ImageIcon("src/macpan/images/Ghosts/Inky/inky1.png").getImage();
        imgClyde = new ImageIcon("src/macpan/images/Ghosts/Clyde/clyde1.png").getImage();
    }

    /**
     * Reads from the file of game board data and creates the game board.
     */
    public void loadBoard() {
        String type = "";

        try {
            File f = new File("src/macpan/Blocks.data");
            Scanner s = new Scanner(f);  //tries to make a file and a scanner

            for (int y = 0; y < 21; y++) { //Loops through the y axis of the 2D array
                for (int x = 0; x < 19; x++) { //loops through the x

                    type = s.nextLine(); //saves the type of 'thing' from the data file

                    if (type.equals("box")) { //if it is a block
                        b[x][y] = new Block(imgBlock, x * 26, y * 26);  //set to a block

                    } else if (type.equals("pellet")) { //if it is a pellet 
                        b[x][y] = new Pellet(imgPellet, x * 26, y * 26);  //set to pellet

                    } else if (type.equals("powerPellet")) { //if it is a Power pellet 
                        //b[x][y] = new PowerPellet(imgPowerPellet, x, y);  //set to Power pellet

                    } else if (type.equals("food")) { //if it is a Food object 
                        b[x][y] = new Food(imgFood, x, y);  //set to Food
                    } else {
                        b[x][y] = new Block(null, x * 26, y * 26);
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
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 21; j++) {
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
           
            if (blinky.getXPos() % 26 == 0 && blinky.getYPos() % 26 == 0) {
                num = (int) (Math.random() * 4 + 1);
            }
            switch (num) {
                case 1:
                    blinky.moveUp();
                    break;
                case 2:
                    blinky.moveRight();
                    break;
                case 3:
                    blinky.moveDown();
                    break;
                default:
                    blinky.moveLeft();
                    break;
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
