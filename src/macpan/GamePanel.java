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
import java.awt.event.*;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import macpan.characters.Blinky;
import macpan.characters.Pacman;
import macpan.objects.Block;
import macpan.objects.Pellet;
import macpan.objects.Food;
import macpan.objects.PowerPellet;
import macpan.objects.Empty;

import macpan.objects.Thing;

public final class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread animator;
    private final int DELAY = 25, BUFFER_X = 48, BUFFER_Y = 41;
    Thing[][] b = new Thing[19][21]; //the images
    final int size = 26; //the size of each grid space (26x26 px)
    int counter = size;
    String possible = "";
    String choice = "";

    int[][] gridX = new int[19][21]; //parallel to images, holds the position the images are in on the X axis
    int[][] gridY = new int[19][21]; //parallel to images, holds the position the images are in on the Y axis

    //creating image files to be used
    private Image imgPellet, imgPowerPellet, imgFood, imgBlock, imgEmpty;
    private Image imgBlinkyUp1, imgBlinkyUp2, imgBlinkyDown1, imgBlinkyDown2, imgBlinkyLeft1, imgBlinkyLeft2, imgBlinkyRight1, imgBlinkyRight2, imgPinky, imgInky, imgClyde, imgPacman;

    Pacman pacman;
    Blinky blinky;
    //Pinky pinky;
    //Inky inky;
    //Clyde clyde;

    // default constructor
    public GamePanel() {
        loadImage();
        loadBoard();
        setBackground(Color.black);
        pacman = new Pacman(3, imgPacman, 26 * 10, 26 * 13, 2, 2, "right");
        blinky = new Blinky(imgBlinkyUp1, 26 * 14, 26 * 9, "right", true);
        //pinky = new Pinky(imgPinky, 30, 0, "east", true);
        //inky = new Inky(imgInky, 60, 0, "east", true);
        //clyde = new Clyde(imgClyde, 90, 0, "east", true);
        blinky.setXSpeed(2);
        blinky.setYSpeed(2);

        //attach the keyboard to the panel and give it "focus"
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
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

        imgBlinkyUp1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyUp1.png").getImage();
        imgBlinkyUp2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyUp2.png").getImage();
        imgBlinkyDown1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyDown1.png").getImage();
        imgBlinkyDown2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyDown2.png").getImage();
        imgBlinkyLeft1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyBack1.png").getImage();
        imgBlinkyLeft2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyBack2.png").getImage();
        imgBlinkyRight1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky1.png").getImage();
        imgBlinkyRight2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky2.png").getImage();

        imgPinky = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinky1.png").getImage();
        imgInky = new ImageIcon("src/macpan/images/Ghosts/Inky/inky1.png").getImage();
        imgClyde = new ImageIcon("src/macpan/images/Ghosts/Clyde/clyde1.png").getImage();

        imgPacman = new ImageIcon("src/macpan/images/Pacman/pacmanWhole.png").getImage();
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
        g2d.drawImage(pacman.getSprite(), pacman.getXPos() + BUFFER_X, pacman.getYPos() + BUFFER_Y, 25, 25, Color.black, this);

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
            moveBlinky(); //Move ghost
            movePacman();
            counter += blinky.getXSpeed(); //Counter moves the same amount as the ghost each time

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

    public void movePacman() {
        switch (keyPressed) {
            case "up" -> {
                pacman.setDirection("up");
                pacman.moveUp();
            }
            case "down" -> {
                pacman.setDirection("down");
                pacman.moveDown();
            }
            case "left" -> {
                pacman.setDirection("left");
                pacman.moveLeft();
            }
            default -> {
                //must be right
                pacman.setDirection("right");
                pacman.moveRight();
            }
        }

    }

    private String keyPressed = ""; //used to control what key was last pressed

    @Override
    public void keyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();  // Keyboard code for the pressed key.

        if (key == KeyEvent.VK_W) { //if key pressed is W meaning UP
            System.out.println("w");
            keyPressed = "up";
        } else if (key == KeyEvent.VK_S) { //if key pressed is S meaning DOWN
            System.out.println("s");
            keyPressed = "down";
        } else if (key == KeyEvent.VK_A) {//if key pressed is A meaning LEFT
            System.out.println("a");
            keyPressed = "left";
        } else if (key == KeyEvent.VK_D) {//if key pressed is D meaning RIGHT
            System.out.println("d");
            keyPressed = "right";
        }
    }

    boolean up = true, down = true, left = true, right = true; //Booleans for checking backward movement

    /**
     * Move the ghost by making a decision every time the counter has reached 26
     * (when the ghost is in the middle block)
     */
    public void moveBlinky() {
        //If counter has reached the tile width (meaning the ghost has moved enough to get to the middle of the block)
        //Then find the possible moves
        if (counter == 26) {
            counter = 0; //Reset the counter keeping track of how far the ghost has moved
            possible = ""; //Reset the possible moves to nothing
            if (b[(blinky.getXPos() / 26)][blinky.getYPos() / 26 - 1] instanceof Block == false && up) { //if it can move UP
                possible += "up "; //Add "up" to possible moves
            }
            if (b[blinky.getXPos() / 26][blinky.getYPos() / 26 + 1] instanceof Block == false && down) { //if it can move DOWN
                possible += "down "; //Add "down" to possible moves
            }
            if (b[blinky.getXPos() / 26 + 1][blinky.getYPos() / 26] instanceof Block == false && right) { //if it can move RIGHT
                possible += "right "; //Add "right" to possible moves
            }
            if (b[blinky.getXPos() / 26 - 1][blinky.getYPos() / 26] instanceof Block == false && left) { //if it can move LEFT
                possible += "left"; //Add "left" to possible move
            }

            choice = makeChoice(possible); //Make the choice for where to go
        }

        if (choice.equals("up")) { //If choice selected is up
            if (counter > 0 && counter < 14) {
                blinky.setSprite(imgBlinkyUp1); //Set the image to up
            } else {
                blinky.setSprite(imgBlinkyUp2); //Set the image to up but different
            }
            blinky.moveUp(); //Move the ghost up the same amount as it's speed
            down = false; //Ghost can no longer go down (backwards)
            //But can go all other directions assuming there isn't a block in the way
            up = true;
            right = true;
            left = true;
        } else if (choice.equals("down")) { //If choice selected is down
            if (counter > 0 && counter < 14) {
                blinky.setSprite(imgBlinkyDown1); //Set the image to down
            } else {
                blinky.setSprite(imgBlinkyDown2); //Set the image to down but different
            }
            blinky.moveDown(); //Move the ghost down the same amount as it's speed
            up = false; //Ghost can no longer go up (backwards)
            //But can go all other directions assuming there isn't a block in the way
            down = true;
            right = true;
            left = true;
        } else if (choice.equals("right")) { //If choice selected is right
            if (counter > 0 && counter < 14) {
                blinky.setSprite(imgBlinkyRight1); //Set the image to right
            } else {
                blinky.setSprite(imgBlinkyRight2); //Set the image to right but different
            }
            blinky.moveRight(); //Move the ghost right the same amount as it's speed
            left = false; //Ghost can no longer go left (backwards)
            //But can go all other directions assuming there isn't a block in the way
            up = true;
            right = true;
            down = true;
        } else {
            //must be left
            if (counter > 0 && counter < 14) {
                blinky.setSprite(imgBlinkyLeft1); //Set the image to left
            } else {
                blinky.setSprite(imgBlinkyLeft2); //Set the image to left but different
            } //Set the image to left
            blinky.moveLeft(); //Move the ghost left the same amount as it's speed
            right = false; //Ghost can no longer go right (backwards)
            //But can go all other directions assuming there isn't a block in the way
            up = true;
            down = true;
            left = true;
        }

    }

    /**
     * Makes the choice between all the possible moves on where to go
     *
     * @param main - The string list of moves
     * @return - The chosen string for movement
     */
    public String makeChoice(String main) {
        String[] options = main.split(" "); //Create an array of the options, splitting the spaces
        if (options.length == 1) { //If theres only one option
            return options[0]; //Return that option
        } else { //If there is two or three options
            int randomNum = (int) (Math.random() * options.length); //generates a random number selecting any of the multiple choices it can move to
            return options[randomNum]; //Return the option randomly picked
        }
    }

    /*
    Abstract methods for key listener class
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //
    }

}
