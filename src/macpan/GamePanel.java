/*
 * N Burkhart, J Luhta, V He
    December 2022 - January 2023
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
import macpan.objects.PowerPellet;
import macpan.objects.Empty;

import macpan.objects.Thing;

public final class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private final int DELAY = 25, BUFFER_X = 48, BUFFER_Y = 41;
    Thing[][] b = new Thing[19][21]; //the images
    final int size = 26; //the size of each grid space (26x26 px)

    int[][] gridX = new int[19][21]; //parallel to images, holds the position the images are in on the X axis
    int[][] gridY = new int[19][21]; //parallel to images, holds the position the images are in on the Y axis

    //creating image files to be used
    private Image imgPellet, imgPowerPellet, imgFood, imgBlock, imgEmpty;
    private Image imgBlinky, imgPinky, imgInky, imgClyde;

    Blinky blinky;
    //Pinky pinky;
    //Inky inky;
    //Clyde clyde;

    // default constructor
    public GamePanel() {
        loadImage();
        loadBoard();
        setBackground(Color.black);
        blinky = new Blinky(imgBlinky, 26 * 18, 26 * 9, "east", true);
        //pinky = new Pinky(imgPinky, 30, 0, "east", true);
        //inky = new Inky(imgInky, 60, 0, "east", true);
        //clyde = new Clyde(imgClyde, 90, 0, "east", true);
        blinky.setXSpeed(2);
        blinky.setYSpeed(2);
    }

    /**
     * Loads the images and stores them for use.
     */
    public void loadImage() {
        //loads images that are unchanging
        imgBlock = new ImageIcon("src/macpan/images/JFrames/box.png").getImage();
        imgEmpty = new ImageIcon("src/macpan/images/JFrames/empty.png").getImage();
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
                        b[x][y] = new Block(imgBlock, x * size, y * size);  //set to a block

                    } else if (type.equals("pellet")) { //if it is a pellet 
                        b[x][y] = new Pellet(imgPellet, x * size, y * size);  //set to pellet

                    } else if (type.equals("powerPellet")) { //if it is a Power pellet 
                        b[x][y] = new PowerPellet(imgPowerPellet, x * size, y * size);  //set to Power pellet

                    } else if (type.equals("food")) { //if it is a Food object 
                        b[x][y] = new Food(imgFood, x * size, y * size);  //set to Food

                    } else { //if not food, it is empty
                        b[x][y] = new Empty(imgEmpty, x * size, y * size);
                    }
                    gridX[x][y] = x * size; //stores the x cooridnate at the image position
                    gridY[x][y] = y * size; //stores the y coordinate at the image position (both used for pacman interaction)
                }
            }
        } catch (FileNotFoundException e) { //cacthes file not found
            System.out.println("Error: " + e);
        }
    }
    
    public void moveGhosts() {
        int skippedNums[] = new int[3];
            for (int i = 0; i < skippedNums.length; i++) {
                skippedNums[i] = 0;
            }
            if (blinky.getXPos() % 26 == 0 && blinky.getYPos() % 26 == 0) {
                if (b[blinky.getXPos() / 26][blinky.getYPos() / 26 - 1] instanceof Block) {
                    up = false;
                    skippedNums[0] = 1;
                }
                if (b[blinky.getXPos() / 26][blinky.getYPos() / 26 + 1] instanceof Block) {
                    down = false;
                    skippedNums[0] = 1;
                }
                if (b[blinky.getXPos() / 26 + 1][blinky.getYPos() / 26] instanceof Block) {
                    right = false;
                    skippedNums[0] = 1;
                }
                if (b[blinky.getXPos() / 26 - 1][blinky.getYPos() / 26] instanceof Block) {
                    left = false;
                    skippedNums[0] = 1;
                }
                num = (int) (Math.random() * counter);
            }
        }
        imgBlinky = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyUp1.png").getImage();

        blinky.setSprite(imgBlinky);

        blinky.moveUp(size);
    }
}
else if (num == 2 && right) {
                if (b[blinky.getXPos()/26 + 1][blinky.getYPos()/26] instanceof Block == false) {
                left = false;
                up = true;
                down = true;
                right = true;
                imgBlinky = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky1.png").getImage();
                blinky.setSprite(imgBlinky);
                blinky.moveRight(size);
            }
            } else if (num == 3 && down) {
                if (b[blinky.getXPos()/26][blinky.getYPos()/26 + 1] instanceof Block == false) {
                up = false;
                down = true;
                right = true;
                left = true;
                imgBlinky = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyDown1.png").getImage();
                blinky.setSprite(imgBlinky);
                blinky.moveDown(size);
                }
            } else if (num == 4 && left){
                if (b[blinky.getXPos()/26 - 1][blinky.getYPos()/26] instanceof Block == false) {
                right = false;
                up = true;
                down = true;
                left = true;
                imgBlinky = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyBack1.png").getImage();
                blinky.setSprite(imgBlinky);
                blinky.moveLeft(size);
                }
            }
    }

    //does the actual drawing
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods

        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 21; j++) {
                g2d.drawImage(b[i][j].getSprite(), b[i][j].getX() + BUFFER_X, b[i][j].getY() + BUFFER_Y, 26, 26, Color.black, this);
            }
        }
        g2d.drawImage(blinky.getSprite(), blinky.getXPos() + BUFFER_X, blinky.getYPos() + BUFFER_Y, 25, 25, Color.black, this);
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
        int counter;
        boolean up = true, down = true, left = true, right = true;

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
