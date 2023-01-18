/*
 * N Burkhart, J Luhta, V He
    December 2022 - January 2023
   
    The main frame for the MacPan Game. All code for movement and animation is divided into
    subsections in the code. Each subsection header explains briefly what is contained in it.
    
    All methods and code is called from the run() method.
    
    Authors of methods will be displayed in the video explination.
    
    Also, a class which allows drawing, because it extends JPanel, by way of the 
    Graphics2D class.

    This panel is repainted regulary by implementing the Runnable interface and 
    running in a Thread. This allows it to be animated.
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
import javax.swing.JButton;
import macpan.characters.Blinky;
import macpan.characters.Clyde;
import macpan.characters.Inky;
import macpan.characters.Pacman;
import macpan.characters.Pinky;      //importing from exterior packages (only in packages for organization)
import macpan.objects.Block;
import macpan.objects.Pellet;
import macpan.objects.Food;
import macpan.objects.PowerPellet;
import macpan.objects.Empty;
import macpan.objects.Thing;

public final class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread animator;
    private final int DELAY = 25, BUFFER_X = 48, BUFFER_Y = 41; //the buffer for each animation in order to centre the gameboard

    private final int px = 26; //the size of each grid spot (26x26 pixels)

    JButton btnBack = new javax.swing.JButton(); //the back button

    /**
     * The default constructor for the game panel
     */
    public GamePanel() {
        loadImage(); //loads the images and the board
        loadBoard();
        setBackground(Color.black);
        

        pacman = new Pacman(3, imgPacUp1, px * 11, px * 11, 2, 2, "right");

        blinky = new Blinky(imgBlinkyUp1, px * 3, px * 1, 2, 2, "right");
        pinky = new Pinky(imgPinkyUp1, px * 19, px * 1, 2, 2, "right");
        inky = new Inky(imgInkyUp1, px * 3, px * 19, 2, 2, "right");            //initializes the ghosts and pacman
        clyde = new Clyde(imgClydeUp1, px * 19, px * 19, 2, 2, "right");

        //attach the keyboard to the panel and give it "focus"
        this.addKeyListener(this);
        this.setFocusable(true);      //allows for keyboard input
        this.requestFocus();
    }

    /**
     * ***************************************************************************************************
     * LOADING IN Includes: - creating images - loading in images - reading from
     * data file to load the game board
     * ***************************************************************************************************
     */
    
    /*
    Thing, the game board itself, utalizes interface called thing(meaning it is a thing/object on the gameboard)
    It includes the classes:
    Block, Empty, Pellet, PowerPellet, Food.
     */
    Thing[][] b = new Thing[23][21];

    //creating image files to be used
    private Image imgPellet, imgPowerPellet, imgFood, imgBlock, imgEmpty;
    private Image imgBlinkyUp1, imgBlinkyUp2, imgBlinkyDown1, imgBlinkyDown2, imgBlinkyLeft1, imgBlinkyLeft2, imgBlinkyRight1, imgBlinkyRight2;
    private Image imgPinkyUp1, imgPinkyUp2, imgPinkyDown1, imgPinkyDown2, imgPinkyLeft1, imgPinkyLeft2, imgPinkyRight1, imgPinkyRight2;
    private Image imgInkyUp1, imgInkyUp2, imgInkyDown1, imgInkyDown2, imgInkyLeft1, imgInkyLeft2, imgInkyRight1, imgInkyRight2;
    private Image imgClydeUp1, imgClydeUp2, imgClydeDown1, imgClydeDown2, imgClydeLeft1, imgClydeLeft2, imgClydeRight1, imgClydeRight2;
    private Image imgPacWhole, imgPacUp1, imgPacUp2, imgPacDown1, imgPacDown2, imgPacLeft1, imgPacLeft2, imgPacRight1, imgPacRight2;
    private Image imgPacDeath1, imgPacDeath2, imgPacDeath3, imgPacDeath4, imgPacDeath5, imgPacDeath6, imgPacDeath7, imgPacDeath8, imgPacDeath9, imgPacDeath10, imgPacDeath11;
    private Image imgScared1, imgScared2, imgScared3, imgScared4, imgEyesUp, imgEyesDown, imgEyesRight, imgEyesLeft;

    private Image imgCherry, imgStrawberry, imgOrange, imgApple, imgMelon, imgGalaxian, imgBell, imgKey;

    /*
     * ***************************************************************************************************
     * LOADING AND RESETTING
            Includes: 
            - Loading images 
            - loading the board  
            - resetting positons and global variables 
            - resetting the game
     * ***************************************************************************************************
     */
    
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

        imgCherry = new ImageIcon("src/macpan/images/Consumables/cherry.png").getImage();
        imgStrawberry = new ImageIcon("src/macpan/images/Consumables/strawberry.png").getImage();
        imgOrange = new ImageIcon("src/macpan/images/Consumables/orange.png").getImage();
        imgApple = new ImageIcon("src/macpan/images/Consumables/apples.png").getImage();
        imgMelon = new ImageIcon("src/macpan/images/Consumables/melon.png").getImage(); //loads the food images in
        imgGalaxian = new ImageIcon("src/macpan/images/Consumables/galaga.png").getImage();
        imgBell = new ImageIcon("src/macpan/images/Consumables/bell.png").getImage();
        imgKey = new ImageIcon("src/macpan/images/Consumables/key.png").getImage();

        imgBlinkyUp1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyUp1.png").getImage();
        imgBlinkyUp2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyUp2.png").getImage();
        imgBlinkyDown1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyDown1.png").getImage();
        imgBlinkyDown2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyDown2.png").getImage();
        imgBlinkyLeft1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyBack1.png").getImage();
        imgBlinkyLeft2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinkyBack2.png").getImage();
        imgBlinkyRight1 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky1.png").getImage();
        imgBlinkyRight2 = new ImageIcon("src/macpan/images/Ghosts/Blinky/blinky2.png").getImage();

        imgPinkyUp1 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyUp1.png").getImage();
        imgPinkyUp2 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyUp2.png").getImage();
        imgPinkyDown1 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyDown1.png").getImage();
        imgPinkyDown2 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyDown2.png").getImage();
        imgPinkyLeft1 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyBack1.png").getImage();
        imgPinkyLeft2 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinkyBack2.png").getImage();
        imgPinkyRight1 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinky1.png").getImage();
        imgPinkyRight2 = new ImageIcon("src/macpan/images/Ghosts/Pinky/pinky2.png").getImage();

        imgInkyUp1 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyUp1.png").getImage();
        imgInkyUp2 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyUp2.png").getImage();
        imgInkyDown1 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyDown1.png").getImage();
        imgInkyDown2 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyDown2.png").getImage();
        imgInkyLeft1 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyBack1.png").getImage();
        imgInkyLeft2 = new ImageIcon("src/macpan/images/Ghosts/Inky/inkyBack2.png").getImage();
        imgInkyRight1 = new ImageIcon("src/macpan/images/Ghosts/Inky/inky1.png").getImage();
        imgInkyRight2 = new ImageIcon("src/macpan/images/Ghosts/Inky/inky2.png").getImage();

        imgClydeUp1 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeUp1.png").getImage();
        imgClydeUp2 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeUp2.png").getImage();
        imgClydeDown1 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeDown1.png").getImage();
        imgClydeDown2 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeDown2.png").getImage();
        imgClydeLeft1 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeBack1.png").getImage();
        imgClydeLeft2 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clydeBack2.png").getImage();
        imgClydeRight1 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clyde1.png").getImage();
        imgClydeRight2 = new ImageIcon("src/macpan/images/Ghosts/Clyde/clyde2.png").getImage();

        //Ghost scared images
        imgScared1 = new ImageIcon("src/macpan/images/Ghosts/scared1.png").getImage();
        imgScared2 = new ImageIcon("src/macpan/images/Ghosts/scared2.png").getImage();
        imgScared3 = new ImageIcon("src/macpan/images/Ghosts/scared3.png").getImage();
        imgScared4 = new ImageIcon("src/macpan/images/Ghosts/scared4.png").getImage();

        //Ghost dead images
        imgEyesUp = new ImageIcon("src/macpan/images/Ghosts/eyesUp.png").getImage();
        imgEyesDown = new ImageIcon("src/macpan/images/Ghosts/eyesDown.png").getImage();
        imgEyesRight = new ImageIcon("src/macpan/images/Ghosts/eyes.png").getImage();
        imgEyesLeft = new ImageIcon("src/macpan/images/Ghosts/eyesBack.png").getImage();

        //pacman images
        imgPacWhole = new ImageIcon("src/macpan/images/Pacman/pacmanWhole.png").getImage();
        imgPacUp1 = new ImageIcon("src/macpan/images/Pacman/pacUp1.png").getImage();
        imgPacUp2 = new ImageIcon("src/macpan/images/Pacman/pacUp2.png").getImage();
        imgPacDown1 = new ImageIcon("src/macpan/images/Pacman/pacDown1.png").getImage();
        imgPacDown2 = new ImageIcon("src/macpan/images/Pacman/pacDown2.png").getImage();
        imgPacLeft1 = new ImageIcon("src/macpan/images/Pacman/pacBack1.png").getImage();
        imgPacLeft2 = new ImageIcon("src/macpan/images/Pacman/pacBack2.png").getImage();
        imgPacRight1 = new ImageIcon("src/macpan/images/Pacman/pacman1.png").getImage();
        imgPacRight2 = new ImageIcon("src/macpan/images/Pacman/pacman2.png").getImage();

        //pacman death
        imgPacDeath1 = new ImageIcon("src/macpan/images/Pacman/die1.png").getImage();
        imgPacDeath2 = new ImageIcon("src/macpan/images/Pacman/die2.png").getImage();
        imgPacDeath3 = new ImageIcon("src/macpan/images/Pacman/die3.png").getImage();
        imgPacDeath4 = new ImageIcon("src/macpan/images/Pacman/die4.png").getImage();
        imgPacDeath5 = new ImageIcon("src/macpan/images/Pacman/die5.png").getImage();
        imgPacDeath6 = new ImageIcon("src/macpan/images/Pacman/die6.png").getImage();
        imgPacDeath7 = new ImageIcon("src/macpan/images/Pacman/die7.png").getImage();
        imgPacDeath8 = new ImageIcon("src/macpan/images/Pacman/die8.png").getImage();
        imgPacDeath9 = new ImageIcon("src/macpan/images/Pacman/die9.png").getImage();
        imgPacDeath10 = new ImageIcon("src/macpan/images/Pacman/die10.png").getImage();
        imgPacDeath11 = new ImageIcon("src/macpan/images/Pacman/die11.png").getImage();

    }

    /**
     * Reads from the file of game board data and creates the game board.
     */
    public void loadBoard() {
        currentPressed = "";
        String type = "";

        try {
            File f = new File("src/macpan/Blocks.data");
            Scanner s = new Scanner(f);  //tries to make a file and a scanner

            for (int y = 0; y < 21; y++) { //Loops through the y axis of the 2D array
                for (int x = 0; x < 23; x++) { //loops through the x

                    type = s.nextLine(); //saves the type of 'thing' from the data file

                    if (type.equals("box")) { //if it is a block
                        b[x][y] = new Block(imgBlock, x * px, y * px);  //set to a block

                    } else if (type.equals("pellet")) { //if it is a pellet 
                        b[x][y] = new Pellet(imgPellet, x * px, y * px);  //set to pellet
                        pelletCount++; //adds to the pellet counter

                    } else if (type.equals("powerPellet")) { //if it is a Power pellet 
                        b[x][y] = new PowerPellet(imgPowerPellet, x * px, y * px);  //set to Power pellet
                        pelletCount++; //adds to the pellet counter

                    } else if (type.equals("food")) { //if it is a Food object 
                        b[x][y] = new Food(imgFood, x * px, y * px);  //set to Food

                    } else { //if not food, it is empty
                        b[x][y] = new Empty(imgEmpty, x * px, y * px);
                    }
                }
            }
        } catch (FileNotFoundException e) { //cacthes file not found
            System.out.println("Error: " + e);
        }
    }

    /**
     * Resets the positions of Pacman and the ghosts
     */
    public void resetPositions() {
        pacDeath = false; //Set pacman to not dead after resetting the positions
        pacman.setXPos(px * 11);
        pacman.setYPos(px * 11);  //reset pacmans position

        blinky.setXPos(px * 3);
        blinky.setYPos(px * 1);
        blinkyCounter = 0;
        blinkyChoice = "right";

        pinky.setXPos(px * 19);
        pinky.setYPos(px * 1);     //resets ghost positions
        pinkyCounter = 0;
        pinkyChoice = "left";

        inky.setXPos(px * 3);
        inky.setYPos(px * 19);
        inkyCounter = 0;
        inkyChoice = "right";

        clyde.setXPos(px * 19);
        clyde.setYPos(px * 19);
        clydeCounter = 0;
        clydeChoice = "left";

        //resets the speeds
        pacman.setXSpeed(2);
        pacman.setYSpeed(2);
        blinky.setXSpeed(2);
        blinky.setYSpeed(2);
        pinky.setXSpeed(2);
        pinky.setYSpeed(2);
        inky.setXSpeed(2);
        inky.setYSpeed(2);
        clyde.setXSpeed(2);
        clyde.setYSpeed(2);

    }

    /**
     * Resets the game to its original state.
     */
    public void resetGame() {
        loadBoard();
        resetPositions();

        pacman.setScore(0);
        pacman.setLives(3);  //resets the score and pacmans lives

        pacmanTick = 0; //resets spawning ticks
        foodTick = 0;

        round = 1;
        conditional = 10000;
        pacDeath = false;
    }

    /*
     * ***************************************************************************************************
    G2D CODE AND FRAMES 
    Includes: 
        - Code for drawing 
        - Code to run the frame
     * ***************************************************************************************************
     */
    
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

    boolean pacDeath; //Boolean for if pacman is dead or not
    long num;
    
    //this method is called only once, when the Thread starts
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        //get the current time
        beforeTime = System.currentTimeMillis();

        int oldScore = 0, currentScore; //used to control adding lives
        
        while (true) { //this loop runs once ever 25 ms (the DELAY)
            num++;
            moveBlinky(); //Move ghosts
            movePinky();
            moveInky();
            moveClyde();
            blinkyCounter += blinky.getXSpeed(); //Counter moves the same amount as the ghost each time
            pinkyCounter += pinky.getXSpeed(); //Counter moves the same amount as the ghost each time
            inkyCounter += inky.getXSpeed(); //Counter moves the same amount as the ghost each time
            clydeCounter += clyde.getXSpeed(); //Counter moves the same amount as the ghost each time

            currentScore = pacman.getScore();  //stores the current score
            checkAddLives(oldScore, currentScore); //checks if we need to add lives
            checkGhostOnPacman();
            
            if (pacman.getLives() == 0) {
                //game over conditions here
                //JOption Pane here
            }
            if (!pacDeath) { //If pacman is not dead, keep running the game, if he is deadeverything stops until the characters are reset
                runPacman(); //Move pacman
                if (pacman.getLives() == 0) {
                    //game over conditions here
                    System.exit(0);
                }
            }
            checkEaten(); //checks to see if anything has been eaten
            foodTick++; //adds to the food tick
            addFood(); //adds food items to the map
  
            checkMapEmpty(); //checks if the user has cleared the board
            
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

    /**
     * Does the actual drawing.
     * @param g - the graphics ob
     */
    private void doDrawing(Graphics g) {
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods

        Graphics2D g2d = (Graphics2D) g;
        for (int i = 2; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                g2d.drawImage(b[i][j].getSprite(), b[i][j].getX() + BUFFER_X, b[i][j].getY() + BUFFER_Y, px, px, Color.black, this);
            }
        }
        g2d.setColor(Color.white);
        g2d.setFont(new java.awt.Font("Monospaced", 1, 17));
        
        g2d.drawString("" + 100000, 100, 40);             //display the highscore
        g2d.drawString("" + pacman.getScore(), 550, 40); //display the score
      
        //draw the ghosts
        g2d.drawImage(blinky.getSprite(), blinky.getXPos() + BUFFER_X, blinky.getYPos() + BUFFER_Y, 25, 25, Color.black, this);
        g2d.drawImage(pinky.getSprite(), pinky.getXPos() + BUFFER_X, pinky.getYPos() + BUFFER_Y, 25, 25, Color.black, this);
        g2d.drawImage(inky.getSprite(), inky.getXPos() + BUFFER_X, inky.getYPos() + BUFFER_Y, 25, 25, Color.black, this);
        g2d.drawImage(clyde.getSprite(), clyde.getXPos() + BUFFER_X, clyde.getYPos() + BUFFER_Y, 25, 25, Color.black, this);
        
        //draw pacman
        g2d.drawImage(pacman.getSprite(), pacman.getXPos() + BUFFER_X, pacman.getYPos() + BUFFER_Y, 25, 25, Color.black, this);

        //draw pacman art at bottom
        
        
        //The Drawing of pacmans lives
        int num = pacman.getLives();
        for (int i = 1; i <= num; i++) { //runs for the number of lives pacman has
            g2d.drawImage(imgPacRight2, 75 + i * px, 590, 21, 21, Color.black, this);
        }
        //drawing what food is avaliable
        g2d.drawImage(findRoundFood().getSprite(), 570, 585, 25, 25, Color.black, this);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 21; j++) {
                g2d.drawImage(b[i][j].getSprite(), b[i][j].getX() + BUFFER_X, b[i][j].getY() + BUFFER_Y, px, px, Color.black, this);
            }
        }
        for (int i = 21; i < 23; i++) {
            for (int j = 0; j < 21; j++) {
                g2d.drawImage(b[i][j].getSprite(), b[i][j].getX() + BUFFER_X, b[i][j].getY() + BUFFER_Y, px, px, Color.black, this);
            }
        }
    }

    /*
     * ***************************************************************************************************
     * ALL PACMAN CODE 
            Includes: 
            - movement 
            - animation 
            - adding points 
            - checking death conditions
            - eating ghosts
            - death behaviour and animation
     * ***************************************************************************************************
     */
    Pacman pacman; //pacman himself

    private String currentPressed = "", oldPressed = ""; //used to control what key was last pressed
    private int pacmanTick = 27; //tick used to control pacmans animation
    private int numGhostEaten = 0;

    @Override
    /**
     * Runs when a key is pressed
     */
    public void keyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();  // Keyboard code for the pressed key.

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { //if key pressed is W meaning UP
            currentPressed = "up";
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { //if key pressed is S meaning DOWN
            currentPressed = "down";
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {//if key pressed is A meaning LEFT
            currentPressed = "left";
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {//if key pressed is D meaning RIGHT
            currentPressed = "right";
        }
    }

    /**
     * Runs all of the PacMan movement and animation methods
     */
    public void runPacman() {
        //represents pacmans position in each grid space. When centred, value will be 0
        int xTop = pacman.getXPos() / px;  //represents pacmans position on the 'grid' (the map array)
        int yTop = pacman.getYPos() / px;

        int xBottom = (pacman.getXPos() + 25) / px;  //represents pacmans position on the 'grid' (the map array)
        int yBottom = (pacman.getYPos() + 25) / px;

        if (oldPressed.equals("right") || oldPressed.equals("down")) { //if the current movement goes off of top positioning, pass through current
            //pass through the top target
            if (currentIsPossible(currentPressed, xTop, yTop)) { //sees if the current wanted direcion is possible
                oldPressed = currentPressed; //change the old one to the new, which runs based off of the old direction it was moving
            }
        } else { //current must already be bottom
            //pass through bottom
            if (currentIsPossible(currentPressed, xBottom, yBottom)) { //sees if the current wanted direcion is possible
                oldPressed = currentPressed; //change the old one to the new, which runs based off of the old direction it was moving
            }
        }
        movePacman(xTop, yTop, xBottom, yBottom); //move pacman
        if (xTop == 0) {
            pacman.setXPos(26 * 21);
        }
        if (xBottom == 22) {
            pacman.setXPos(26 * 1);
        }
        animatePacman(); //animate pacman

    }

    /**
     * Moves PacMan given a certain position on the grid
     *
     * @param xTop - the top x position on the grid
     * @param yTop - the top y position on the grid.
     * @param xBottom - the bottom right x on the grid
     * @param yBottom - the bottom right y on the grid
     */
    public void movePacman(int xTop, int yTop, int xBottom, int yBottom) {
        double inBlockX = (double) (pacman.getXPos() % px);
        double inBlockY = (double) (pacman.getYPos() % px);

        if (inBlockX == 0 && b[xBottom][yBottom - 1] instanceof Block == false && oldPressed.equals("up")) { //If up key is pressed and pacman is in center of space with no block above
            pacman.moveUp(); //Move up
            pacmanTick += 3;
        } else if (inBlockX == 0 && b[xTop][yTop + 1] instanceof Block == false && oldPressed.equals("down")) { //If down key is pressed and pacman is in center of space with no block below
            pacman.moveDown(); //Move down
            pacmanTick += 3;
        } else if (inBlockY == 0 && b[xTop + 1][yTop] instanceof Block == false && oldPressed.equals("right")) { //If right key is pressed and pacman is in center of space with no block to the right
            pacman.moveRight(); //Move right
            pacmanTick += 3; //add 3 every time
        } else if (inBlockY == 0 && b[xBottom - 1][yBottom] instanceof Block == false && oldPressed.equals("left")) { //If left key is pressed and pacman is in center of space with no block to the left
            //System.out.println(b[xGrid - 1][yGrid] instanceof Block);
            pacman.moveLeft(); //move left
            pacmanTick += 3;
            // System.out.println("done");
        }
        //resets pacmanTick
        if (pacmanTick >= 36) {
            pacmanTick = 0;
        }
    }

    /**
     * Will return if the current requested movement is possible
     *
     * @param preffered - the preferred direction to move in
     * @param x - the x position of pacman on the grid
     * @param y - the y position of pacman on the grid
     * @return - if it is possible or not.
     */
    public boolean currentIsPossible(String preffered, int x, int y) {

        if (preffered.equals("up")) {
            return b[x][y - 1] instanceof Block == false; //return if the one above is a block or not
        } else if (preffered.equals("down")) {
            return b[x][y + 1] instanceof Block == false;
        } else if (preffered.equals("left")) {
            return b[x - 1][y] instanceof Block == false;
        } else { //must be right
            return b[x + 1][y] instanceof Block == false;
        }

    }

    /**
     * Animates PacMan based on a tick systems swapping between sprites.
     */
    public void animatePacman() {
        if (oldPressed.equals("up")) { //if it is moving up
            if (pacmanTick <= 12) { //sprite 1
                pacman.setSprite(imgPacUp1);
            } else if (pacmanTick <= 24) { //sprite 2
                pacman.setSprite(imgPacUp2);
            } else {
                pacman.setSprite(imgPacWhole);
            }
        } else if (oldPressed.equals("down")) { //if it is moving down
            if (pacmanTick <= 12) { //sprite 1
                pacman.setSprite(imgPacDown1);
            } else if (pacmanTick <= 24) { //sprite 2
                pacman.setSprite(imgPacDown2);
            } else {
                pacman.setSprite(imgPacWhole);
            }
        } else if (oldPressed.equals("left")) { //if it is moving left
            if (pacmanTick <= 12) { //sprite 1
                pacman.setSprite(imgPacLeft1);
            } else if (pacmanTick <= 24) { //sprite 2
                pacman.setSprite(imgPacLeft2);
            } else {
                pacman.setSprite(imgPacWhole);
            }
        } else { //must be right
            if (pacmanTick <= 12) { //sprite 1
                pacman.setSprite(imgPacRight1);
            } else if (pacmanTick <= 24) { //sprite 2
                pacman.setSprite(imgPacRight2);
            } else {
                pacman.setSprite(imgPacWhole);
            }

        }
    }

    boolean blinkyEaten = false, pinkyEaten = false, inkyEaten = false, clydeEaten = false;
    boolean ghostEaten1 = false, ghostEaten2 = false, ghostEaten3 = false, ghostEaten4 = false;
    
    /**
     * Checks to see if a ghost is on pacman, and result depends on if pacman has powerpellet
     */
    public void checkGhostOnPacman() {
        //Get positions of everyone
        int pacX = pacman.getXPos() / px;
        int pacY = pacman.getYPos() / px;
        int blinkyX = blinky.getXPos() / px;
        int blinkyY = blinky.getYPos() / px;
        int pinkyX = pinky.getXPos() / px;
        int pinkyY = pinky.getYPos() / px;
        int inkyX = inky.getXPos() / px;
        int inkyY = inky.getYPos() / px;
        int clydeX = clyde.getXPos() / px;
        int clydeY = clyde.getYPos() / px;

        if (pacman.isPowerPellet()) { //If pacman ate the powerpellet
            //If pacman hasn't eaten the ghost, set their sprite to the scared animation
            if (!blinkyEaten) { 
                if (powerPelletTick > 100) {
                if (blinkyCounter >= 0 && blinkyCounter < 14 && num%2 == 0) {
                    blinky.setSprite(imgScared1);
                } else if (blinkyCounter >= 14 && blinkyCounter < 27 && num%2 == 0) {
                    blinky.setSprite(imgScared3);
                } else if (blinkyCounter >= 0 && blinkyCounter < 14 && num%2 == 1) {
                    blinky.setSprite(imgScared2);
                } else if (blinkyCounter >= 14 && blinkyCounter < 27 && num%2 == 1){
                    blinky.setSprite(imgScared4);
                }
                } else {
                    if (blinkyCounter >= 0 && blinkyCounter < 3) {
                    blinky.setSprite(imgScared1);
                } else if (blinkyCounter >= 3 && blinkyCounter < 6) {
                    blinky.setSprite(imgScared3);
                } else if (blinkyCounter >= 6 && blinkyCounter < 9) {
                    blinky.setSprite(imgScared2);
                } else if (blinkyCounter >= 9 && blinkyCounter < 12){
                    blinky.setSprite(imgScared4);
                } else if (blinkyCounter >= 12 && blinkyCounter < 15) {
                    blinky.setSprite(imgScared1);
                } else if (blinkyCounter >= 15 && blinkyCounter < 18) {
                    blinky.setSprite(imgScared3);
                } else if (blinkyCounter >= 18 && blinkyCounter < 21) {
                    blinky.setSprite(imgScared2);
                } else {
                    blinky.setSprite(imgScared4);
                }
                }
            } else { //If they have been eaten, set their image to just eyes
                blinky.setSprite(imgEyesUp);
            }
            if (!pinkyEaten) {
                                if (powerPelletTick > 100) {
                    if (pinkyCounter >= 0 && pinkyCounter < 14 && num % 2 == 0) {
                        pinky.setSprite(imgScared1);
                    } else if (pinkyCounter >= 14 && pinkyCounter < 27 && num % 2 == 0) {
                        pinky.setSprite(imgScared3);
                    } else if (pinkyCounter >= 0 && pinkyCounter < 14 && num % 2 == 1) {
                        pinky.setSprite(imgScared2);
                    } else if (pinkyCounter >= 14 && pinkyCounter < 27 && num % 2 == 1) {
                        pinky.setSprite(imgScared4);
                    }
                } else {
                    if (pinkyCounter >= 0 && pinkyCounter < 3) {
                        pinky.setSprite(imgScared1);
                    } else if (pinkyCounter >= 3 && pinkyCounter < 6) {
                        pinky.setSprite(imgScared3);
                    } else if (pinkyCounter >= 6 && pinkyCounter < 9) {
                        pinky.setSprite(imgScared2);
                    } else if (pinkyCounter >= 9 && pinkyCounter < 12) {
                        pinky.setSprite(imgScared4);
                    } else if (pinkyCounter >= 12 && pinkyCounter < 15) {
                        pinky.setSprite(imgScared1);
                    } else if (pinkyCounter >= 15 && pinkyCounter < 18) {
                        pinky.setSprite(imgScared3);
                    } else if (pinkyCounter >= 18 && pinkyCounter < 21) {
                        pinky.setSprite(imgScared2);
                    } else {
                        pinky.setSprite(imgScared4);
                    }
                }
            } else {
                pinky.setSprite(imgEyesUp);
            }
            if (!inkyEaten) {
                                if (powerPelletTick > 100) {
                    if (inkyCounter >= 0 && inkyCounter < 14 && num % 2 == 0) {
                        inky.setSprite(imgScared1);
                    } else if (inkyCounter >= 14 && inkyCounter < 27 && num % 2 == 0) {
                        inky.setSprite(imgScared3);
                    } else if (inkyCounter >= 0 && inkyCounter < 14 && num % 2 == 1) {
                        inky.setSprite(imgScared2);
                    } else if (inkyCounter >= 14 && inkyCounter < 27 && num % 2 == 1) {
                        inky.setSprite(imgScared4);
                    }
                } else {
                    if (inkyCounter >= 0 && inkyCounter < 3) {
                        inky.setSprite(imgScared1);
                    } else if (inkyCounter >= 3 && inkyCounter < 6) {
                        inky.setSprite(imgScared3);
                    } else if (inkyCounter >= 6 && inkyCounter < 9) {
                        inky.setSprite(imgScared2);
                    } else if (inkyCounter >= 9 && inkyCounter < 12) {
                        inky.setSprite(imgScared4);
                    } else if (inkyCounter >= 12 && inkyCounter < 15) {
                        inky.setSprite(imgScared1);
                    } else if (inkyCounter >= 15 && inkyCounter < 18) {
                        inky.setSprite(imgScared3);
                    } else if (inkyCounter >= 18 && inkyCounter < 21) {
                        inky.setSprite(imgScared2);
                    } else {
                        inky.setSprite(imgScared4);
                    }
                }
            } else {
                inky.setSprite(imgEyesUp);
            }
            if (!clydeEaten) {
                                if (powerPelletTick > 100) {
                    if (clydeCounter >= 0 && clydeCounter < 14 && num % 2 == 0) {
                        clyde.setSprite(imgScared1);
                    } else if (clydeCounter >= 14 && clydeCounter < 27 && num % 2 == 0) {
                        clyde.setSprite(imgScared3);
                    } else if (clydeCounter >= 0 && clydeCounter < 14 && num % 2 == 1) {
                        clyde.setSprite(imgScared2);
                    } else if (clydeCounter >= 14 && clydeCounter < 27 && num % 2 == 1) {
                        clyde.setSprite(imgScared4);
                    }
                } else {
                    if (clydeCounter >= 0 && clydeCounter < 3) {
                        clyde.setSprite(imgScared1);
                    } else if (clydeCounter >= 3 && clydeCounter < 6) {
                        clyde.setSprite(imgScared3);
                    } else if (clydeCounter >= 6 && clydeCounter < 9) {
                        clyde.setSprite(imgScared2);
                    } else if (clydeCounter >= 9 && clydeCounter < 12) {
                        clyde.setSprite(imgScared4);
                    } else if (clydeCounter >= 12 && clydeCounter < 15) {
                        clyde.setSprite(imgScared1);
                    } else if (clydeCounter >= 15 && clydeCounter < 18) {
                        clyde.setSprite(imgScared3);
                    } else if (clydeCounter >= 18 && clydeCounter < 21) {
                        clyde.setSprite(imgScared2);
                    } else {
                        clyde.setSprite(imgScared4);
                    }
                }
            } else {
                clyde.setSprite(imgEyesUp);
            }
            if (powerPelletTick > 0) { //While the powerpellet is active
                System.out.println(powerPelletTick);
                powerPelletTick--; //Make the time left decrease
                if (numGhostEaten == 1 && !ghostEaten1) { //If total number of ghosts eaten is 1, and the 200 points haven't been scored yet
                    pacman.addScore(200); //Add 200 points
                    ghostEaten1 = true; //Make it unable to score 200 again while this powerpellet is active
                } else if (numGhostEaten == 2 && !ghostEaten2) { //If total number of ghosts eaten is 2, and the 400 points haven't been scored yet
                    pacman.addScore(400); //Add 400 points
                    ghostEaten2 = true; //Make it unable to score 400 again while this powerpellet is active
                } else if (numGhostEaten == 3 && !ghostEaten3) { //If total number of ghosts eaten is 3, and the 800 points haven't been scored yet
                    pacman.addScore(800); //Add 800 points
                    ghostEaten3 = true; //Make it unable to score 800 again while this powerpellet is active
                } else if (numGhostEaten == 4 && !ghostEaten4) { //If total number of ghosts eaten is 4, and the 1600 points haven't been scored yet
                    pacman.addScore(1600); //Add 1600 points
                    ghostEaten4 = true; //Make it unable to score 1600 again while this powerpellet is active
                } else {
                }

                //If Blinky is on pacman and he hasn't been eaten yet
                if (pacX == blinkyX && pacY == blinkyY && blinkyEaten == false) {
                    numGhostEaten++; //Add to total ghosts eaten
                    blinkyEaten = true; //Set them as being eaten
                }
                //If pinky is on pacman and she hasn't been eaten yet
                if (pacX == pinkyX && pacY == pinkyY && pinkyEaten == false) {
                    numGhostEaten++; //Add to total ghosts eaten
                    pinkyEaten = true; //Set them as being eaten
                }
                //If inky is on pacman and he hasn't been eaten yet
                if (pacX == inkyX && pacY == inkyY && inkyEaten == false) {
                    numGhostEaten++; //Add to total ghosts eaten
                    inkyEaten = true; //Set them as being eaten
                }
                //If clyde is on pacman and he hasn't been eaten yet
                if (pacX == clydeX && pacY == clydeY && clydeEaten == false) {
                    numGhostEaten++; //Add to total ghosts eaten
                    clydeEaten = true; //Set them as being eaten
                }
            } else { //If powerpellet has run out
                //Reset all variables and set pacman to not being in powerpellet form
                ghostEaten1 = false;
                ghostEaten2 = false;
                ghostEaten3 = false;
                ghostEaten4 = false;
                numGhostEaten = 0;
                blinkyEaten = false;
                pinkyEaten = false;
                inkyEaten = false;
                clydeEaten = false;
                pacman.setPowerPellet(false);
            }
        } else { //If pacman is not in power pellet form
            //And he is touched by a ghost
            if (pacX == blinkyX && pacY == blinkyY || pacX == pinkyX && pacY == pinkyY || pacX == inkyX && pacY == inkyY || pacX == clydeX && pacY == clydeY) {
                //Stop all ghosts
                pacman.setXSpeed(0);
                pacman.setYSpeed(0);
                blinky.setXSpeed(0);
                blinky.setYSpeed(0);
                pinky.setXSpeed(0);
                pinky.setYSpeed(0);
                inky.setXSpeed(0);
                inky.setYSpeed(0);
                clyde.setXSpeed(0);
                clyde.setYSpeed(0);
                pacmanTick++; //Run the pacman tick for the death animation
                pacDeath = true; //Set pacman as dead
                behaveDead(); //Run the behave dead method
            }
        }
    }

    /**
     * Animates pacmans death then resets position
     */
    public void behaveDead() {
        if (pacmanTick <= 10) { //sprite 1
            pacman.setSprite(imgPacWhole);
        } else if (pacmanTick <= 20) { //sprite 2
            pacman.setSprite(imgPacDeath1);
        } else if (pacmanTick <= 30) {
            pacman.setSprite(imgPacDeath2);
        } else if (pacmanTick <= 40) { //sprite 3
            pacman.setSprite(imgPacDeath3);
        } else if (pacmanTick <= 50) {
            pacman.setSprite(imgPacDeath4);
        } else if (pacmanTick <= 60) { //sprite 4
            pacman.setSprite(imgPacDeath5);
        } else if (pacmanTick <= 70) {
            pacman.setSprite(imgPacDeath6);
        } else if (pacmanTick <= 80) { //sprite 5
            pacman.setSprite(imgPacDeath7);
        } else if (pacmanTick <= 90) {
            pacman.setSprite(imgPacDeath8);
        } else if (pacmanTick <= 100) { //sprite 2
            pacman.setSprite(imgPacDeath9);
        } else if (pacmanTick <= 110) {
            pacman.setSprite(imgPacDeath10);
        } else if (pacmanTick <= 120) {
            pacman.setSprite(imgPacDeath11);
        } else if (pacmanTick >= 180) { //no longer dead
            //gap in tick is used to leave the death animation running for longer before it returns to the regular game.
            pacman.setSprite(imgPacWhole);
            pacman.setLives(pacman.getLives() - 1);
            resetPositions();
        }
    }

    /*
     POINT SCORING AND CONSUMABLES
     */
    private int foodTick = 0; //used to control food spawning
    private int powerPelletTick = 0; //Used to control how long pacman has the powerpellet

    /**
     * Checks to see if Pacman is on a consumable, then adds points and erase
     */
    public void checkEaten() {
        int x = (pacman.getXPos() + 13) / px;  //represents pacmans position on the 'grid' (the map array)
        int y = (pacman.getYPos() + 13) / px; //mid level of pacman

        if (b[x][y] instanceof Pellet == true) {
            pelletCount--; //takes away from the total pellet count
            pacman.addScore(((Pellet) (b[x][y])).getPoints()); //adds the score of the pellet to pacmans score
            b[x][y] = new Empty(imgEmpty, x * px, y * px); //sets the old space to empty

        } else if (b[x][y] instanceof Food == true) { //if the occupied space is food
            pacman.addScore(((Food) (b[x][y])).getPoints()); //adds the score of the pellet to pacmans score
            b[x][y] = new Empty(imgEmpty, x * px, y * px); //sets the old space to empty

        } else if (b[x][y] instanceof PowerPellet == true) { //if the occupied space is a power pellet
            //additional power pellet code here
            pelletCount--; //takes away from the total pellet count
            pacman.setPowerPellet(true); //notifies that it now has a power pellet state.
            powerPelletTick = 400;
            pacman.addScore(((PowerPellet) (b[x][y])).getPoints()); //adds the score of the pellet to pacmans score
            b[x][y] = new Empty(imgEmpty, x * px, y * px); //sets the old space to empty
        }
    }

    
    /*
     * ***************************************************************************************************
     * CHECKING AND BACKGROUND CODE 
            Includes: 
            - adding food 
            - finding round food 
            - checking if the map is empty 
            - adding lives
     * ***************************************************************************************************
     */
    
    /**
     * Decides when to add a food to the board in order to score more points
     */
    public void addFood() {
        //the second conditon controls how long until spawing another piece of food

        if (pacman.getScore() > 500 && foodTick >= 600) { //if pacman has eaten at least 50 pellets:

            boolean run = true; //controls while loop

            while (run) {
                int x = (int) (Math.random() * 12) + 5; //creates a random x coordinate on the grid
                int y = (int) (Math.random() * 20) + 1;  //creates a random y coordinate on the grid

                if (b[x][y] instanceof Empty == true) { //if that spot is empty
                    run = false; //stop looking for a spot
                    Food f = findRoundFood(); //gets the current rounds food
                    b[x][y] = new Food(f.getSprite(), x * px, y * px, f.getPoints());
                    foodTick = 0; //reset the food tick
                }
            }
        }
    }

    private int round = 1; //controls what round the game is on, rounds switch every time the board is cleared

    /**
     * Will find what food item must be used given the round
     *
     * @return - the food item for the round
     */
    public Food findRoundFood() {
        Food f; //set the spot to a piece of food;
        if (round == 1) {
            f = new Food(imgCherry, 0, 0, 100); //set the spot to a piece of food 
        } else if (round == 2) {
            f = new Food(imgStrawberry, 0, 0, 300); //set the spot to a piece of food         
        } else if (round == 3) {
            f = new Food(imgOrange, 0, 0, 500); //set the spot to a piece of food      
        } else if (round == 4) {
            f = new Food(imgApple, 0, 0, 700); //set the spot to a piece of food   
        } else if (round == 5) {
            f = new Food(imgMelon, 0, 0, 1000); //set the spot to a piece of food 
        } else if (round == 6) {
            f = new Food(imgGalaxian, 0, 0, 2000); //set the spot to a piece of food 
        } else if (round == 7) {
            f = new Food(imgBell, 0, 0, 3000); //set the spot to a piece of food 
        } else { //must be 8
            f = new Food(imgKey, 0, 0, 5000); //set the spot to a piece of food 
        }
        return f;
    }

    private int pelletCount = 0; //used to remember win condition

    /**
     * Will check to see if Pacman has cleared the map
     */
    public void checkMapEmpty() {
        if (pelletCount == 0) { //if all pellets have been consumed
            //put sound effects here if were doing this
            round++; //adds to the round
            if(round == 9){ //check to see if the round is impossible
                round = 1; //if impossible, bring back to cherry
            }

            resetPositions();
            loadBoard(); //reset the board and fill it again

        }
    }
    
    private int conditional = 10000; //controls what amount of points pacman has to reach to gain a life
   
    /**
     * Checks to see if PacMan can gain a new life
     * @param oldS - the old score
     * @param newS - the new score
     */
    public void checkAddLives(int oldS, int newS){
        if(newS >= conditional && oldS < conditional){ //score has gone over the 10k increment
            if(pacman.getLives() < 3){ //if pacman has less than the maximum lives
                pacman.setLives(pacman.getLives()+1); //adds a life
            }
            conditional += 10000; //sets a new conditon to meet
        }
    }

    /*
     * ***************************************************************************************************
     * ALL GHOST CODE 
            Includes: 
            - animation 
            - movement 
            - power pellet activated movement of ghosts
     * ***************************************************************************************************
     */
    Blinky blinky;
    Pinky pinky;
    Inky inky;   //creates the ghosts
    Clyde clyde;

    private boolean blinkyUp = true, blinkyDown = true, blinkyLeft = true, blinkyRight = true; //Booleans for checking backward movement
    private boolean pinkyUp = true, pinkyDown = true, pinkyLeft = true, pinkyRight = true; //Booleans for checking backward movement
    private boolean inkyUp = true, inkyDown = true, inkyLeft = true, inkyRight = true; //Booleans for checking backward movement
    private boolean clydeUp = true, clydeDown = true, clydeLeft = true, clydeRight = true; //Booleans for checking backward movement

    private int blinkyCounter = px, pinkyCounter = px, inkyCounter = px, clydeCounter = px;
    private String blinkyPossible = "", pinkyPossible = "", inkyPossible = "", clydePossible = "";
    private String blinkyChoice = "", pinkyChoice = "", inkyChoice = "", clydeChoice = "";

    /**
     * Move Blinky by making a decision every time the counter has reached PX
     * (when the ghost is in the middle block)
     */
    public void moveBlinky() {
        //If blinkyCounter has reached the tile width (meaning the ghost has moved enough to get to the middle of the block)
        //Then find the blinkyPossible moves
        if (blinkyCounter == px) {
            blinkyCounter = 0; //Reset the blinkyCounter keeping track of how far the ghost has moved
            blinkyPossible = ""; //Reset the blinkyPossible moves to nothing
            if (b[(blinky.getXPos() / px)][blinky.getYPos() / px - 1] instanceof Block == false && blinkyUp) { //if it can move UP
                blinkyPossible += "up "; //Add "up" to blinkyPossible moves
            }
            if (b[blinky.getXPos() / px][blinky.getYPos() / px + 1] instanceof Block == false && blinkyDown) { //if it can move DOWN
                blinkyPossible += "down "; //Add "down" to blinkyPossible moves
            }
            if (b[blinky.getXPos() / px + 1][blinky.getYPos() / px] instanceof Block == false && blinkyRight) { //if it can move RIGHT
                blinkyPossible += "right "; //Add "right" to blinkyPossible moves
            }
            if (b[blinky.getXPos() / px - 1][blinky.getYPos() / px] instanceof Block == false && blinkyLeft) { //if it can move LEFT
                blinkyPossible += "left"; //Add "left" to blinkyPossible move
            }

            blinkyChoice = makeChoice(blinkyPossible); //Make the blinkyChoice for where to go
        }

        if (blinkyChoice.equals("up")) { //If blinkyChoice selected is up
            if (blinkyCounter > 0 && blinkyCounter < 14) {
                blinky.setSprite(imgBlinkyUp1); //Set the image to up
            } else {
                blinky.setSprite(imgBlinkyUp2); //Set the image to up but different
            }
            blinky.moveUp(); //Move the ghost up the same amount as it's speed
            blinkyDown = false; //Ghost can no longer go down (backwards)
            //But can go all other directions assuming there isn't a block in the way
            blinkyUp = true;
            blinkyRight = true;
            blinkyLeft = true;
        } else if (blinkyChoice.equals("down")) { //If blinkyChoice selected is down
            if (blinkyCounter > 0 && blinkyCounter < 14) {
                blinky.setSprite(imgBlinkyDown1); //Set the image to down
            } else {
                blinky.setSprite(imgBlinkyDown2); //Set the image to down but different
            }
            blinky.moveDown(); //Move the ghost down the same amount as it's speed
            blinkyUp = false; //Ghost can no longer go up (backwards)
            //But can go all other directions assuming there isn't a block in the way
            blinkyDown = true;
            blinkyRight = true;
            blinkyLeft = true;
        } else if (blinkyChoice.equals("right")) { //If blinkyChoice selected is right
            if (blinkyCounter > 0 && blinkyCounter < 14) {
                blinky.setSprite(imgBlinkyRight1); //Set the image to right
            } else {
                blinky.setSprite(imgBlinkyRight2); //Set the image to right but different
            }
            blinky.moveRight(); //Move the ghost right the same amount as it's speed
            blinkyLeft = false; //Ghost can no longer go left (backwards)
            //But can go all other directions assuming there isn't a block in the way
            blinkyUp = true;
            blinkyRight = true;
            blinkyDown = true;
        } else {
            //must be left
            if (blinkyCounter > 0 && blinkyCounter < 14) {
                blinky.setSprite(imgBlinkyLeft1); //Set the image to left
            } else {
                blinky.setSprite(imgBlinkyLeft2); //Set the image to left but different
            } //Set the image to left
            blinky.moveLeft(); //Move the ghost left the same amount as it's speed
            blinkyRight = false; //Ghost can no longer go right (backwards)
            //But can go all other directions assuming there isn't a block in the way
            blinkyUp = true;
            blinkyDown = true;
            blinkyLeft = true;
        }
        if (blinky.getXPos() / 26 == 0) {
            blinky.setXPos(26 * 21 - 1);
        }
        if (blinky.getXPos() / 26 == 21) {
            blinky.setXPos(26 * 1);
        }
    }

    /**
     * Move Pinky by making a decision every time the counter has reached PX
     * (when the ghost is in the middle block)
     */
    public void movePinky() {
        //If pinkyCounter has reached the tile width (meaning the ghost has moved enough to get to the middle of the block)
        //Then find the pinkyPossible moves
        if (pinkyCounter == px) {
            pinkyCounter = 0; //Reset the pinkyCounter keeping track of how far the ghost has moved
            pinkyPossible = ""; //Reset the pinkyPossible moves to nothing
            if (b[(pinky.getXPos() / px)][pinky.getYPos() / px - 1] instanceof Block == false && pinkyUp) { //if it can move UP
                pinkyPossible += "up "; //Add "up" to pinkyPossible moves
            }
            if (b[pinky.getXPos() / px][pinky.getYPos() / px + 1] instanceof Block == false && pinkyDown) { //if it can move DOWN
                pinkyPossible += "down "; //Add "down" to pinkyPossible moves
            }
            if (b[pinky.getXPos() / px + 1][pinky.getYPos() / px] instanceof Block == false && pinkyRight) { //if it can move RIGHT
                pinkyPossible += "right "; //Add "right" to pinkyPossible moves
            }
            if (b[pinky.getXPos() / px - 1][pinky.getYPos() / px] instanceof Block == false && pinkyLeft) { //if it can move LEFT
                pinkyPossible += "left"; //Add "left" to pinkyPossible move
            }

            pinkyChoice = makeChoice(pinkyPossible); //Make the pinkyChoice for where to go
        }

        if (pinkyChoice.equals("up")) { //If pinkyChoice selected is up
            if (pinkyCounter > 0 && pinkyCounter < 14) {
                pinky.setSprite(imgPinkyUp1); //Set the image to up
            } else {
                pinky.setSprite(imgPinkyUp2); //Set the image to up but different
            }
            pinky.moveUp(); //Move the ghost up the same amount as it's speed
            pinkyDown = false; //Ghost can no longer go down (backwards)
            //But can go all other directions assuming there isn't a block in the way
            pinkyUp = true;
            pinkyRight = true;
            pinkyLeft = true;
        } else if (pinkyChoice.equals("down")) { //If pinkyChoice selected is down
            if (pinkyCounter > 0 && pinkyCounter < 14) {
                pinky.setSprite(imgPinkyDown1); //Set the image to down
            } else {
                pinky.setSprite(imgPinkyDown2); //Set the image to down but different
            }
            pinky.moveDown(); //Move the ghost down the same amount as it's speed
            pinkyUp = false; //Ghost can no longer go up (backwards)
            //But can go all other directions assuming there isn't a block in the way
            pinkyDown = true;
            pinkyRight = true;
            pinkyLeft = true;
        } else if (pinkyChoice.equals("right")) { //If pinkyChoice selected is right
            if (pinkyCounter > 0 && pinkyCounter < 14) {
                pinky.setSprite(imgPinkyRight1); //Set the image to right
            } else {
                pinky.setSprite(imgPinkyRight2); //Set the image to right but different
            }
            pinky.moveRight(); //Move the ghost right the same amount as it's speed
            pinkyLeft = false; //Ghost can no longer go left (backwards)
            //But can go all other directions assuming there isn't a block in the way
            pinkyUp = true;
            pinkyRight = true;
            pinkyDown = true;
        } else {
            //must be left
            if (pinkyCounter > 0 && pinkyCounter < 14) {
                pinky.setSprite(imgPinkyLeft1); //Set the image to left
            } else {
                pinky.setSprite(imgPinkyLeft2); //Set the image to left but different
            } //Set the image to left
            pinky.moveLeft(); //Move the ghost left the same amount as it's speed
            pinkyRight = false; //Ghost can no longer go right (backwards)
            //But can go all other directions assuming there isn't a block in the way
            pinkyUp = true;
            pinkyDown = true;
            pinkyLeft = true;
        }
        if (pinky.getXPos() / 26 == 0) {
            pinky.setXPos(26 * 21 - 1);
        }
        if (pinky.getXPos() / 26 == 21) {
            pinky.setXPos(26 * 1);
        }
    }

    /**
     * Move Inky by making a decision every time the counter has reached PX
     * (when the ghost is in the middle block)
     */
    public void moveInky() {
        //If inkyCounter has reached the tile width (meaning the ghost has moved enough to get to the middle of the block)
        //Then find the inkyPossible moves
        if (inkyCounter == px) {
            inkyCounter = 0; //Reset the inkyCounter keeping track of how far the ghost has moved
            inkyPossible = ""; //Reset the inkyPossible moves to nothing
            if (b[(inky.getXPos() / px)][inky.getYPos() / px - 1] instanceof Block == false && inkyUp) { //if it can move UP
                inkyPossible += "up "; //Add "up" to inkyPossible moves
            }
            if (b[inky.getXPos() / px][inky.getYPos() / px + 1] instanceof Block == false && inkyDown) { //if it can move DOWN
                inkyPossible += "down "; //Add "down" to inkyPossible moves
            }
            if (b[inky.getXPos() / px + 1][inky.getYPos() / px] instanceof Block == false && inkyRight) { //if it can move RIGHT
                inkyPossible += "right "; //Add "right" to inkyPossible moves
            }
            if (b[inky.getXPos() / px - 1][inky.getYPos() / px] instanceof Block == false && inkyLeft) { //if it can move LEFT
                inkyPossible += "left"; //Add "left" to inkyPossible move
            }

            inkyChoice = makeChoice(inkyPossible); //Make the inkyChoice for where to go
        }

        if (inkyChoice.equals("up")) { //If inkyChoice selected is up
            if (inkyCounter > 0 && inkyCounter < 14) {
                inky.setSprite(imgInkyUp1); //Set the image to up
            } else {
                inky.setSprite(imgInkyUp2); //Set the image to up but different
            }
            inky.moveUp(); //Move the ghost up the same amount as it's speed
            inkyDown = false; //Ghost can no longer go down (backwards)
            //But can go all other directions assuming there isn't a block in the way
            inkyUp = true;
            inkyRight = true;
            inkyLeft = true;
        } else if (inkyChoice.equals("down")) { //If inkyChoice selected is down
            if (inkyCounter > 0 && inkyCounter < 14) {
                inky.setSprite(imgInkyDown1); //Set the image to down
            } else {
                inky.setSprite(imgInkyDown2); //Set the image to down but different
            }
            inky.moveDown(); //Move the ghost down the same amount as it's speed
            inkyUp = false; //Ghost can no longer go up (backwards)
            //But can go all other directions assuming there isn't a block in the way
            inkyDown = true;
            inkyRight = true;
            inkyLeft = true;
        } else if (inkyChoice.equals("right")) { //If inkyChoice selected is right
            if (inkyCounter > 0 && inkyCounter < 14) {
                inky.setSprite(imgInkyRight1); //Set the image to right
            } else {
                inky.setSprite(imgInkyRight2); //Set the image to right but different
            }
            inky.moveRight(); //Move the ghost right the same amount as it's speed
            inkyLeft = false; //Ghost can no longer go left (backwards)
            //But can go all other directions assuming there isn't a block in the way
            inkyUp = true;
            inkyRight = true;
            inkyDown = true;
        } else {
            //must be left
            if (inkyCounter > 0 && inkyCounter < 14) {
                inky.setSprite(imgInkyLeft1); //Set the image to left
            } else {
                inky.setSprite(imgInkyLeft2); //Set the image to left but different
            } //Set the image to left
            inky.moveLeft(); //Move the ghost left the same amount as it's speed
            inkyRight = false; //Ghost can no longer go right (backwards)
            //But can go all other directions assuming there isn't a block in the way
            inkyUp = true;
            inkyDown = true;
            inkyLeft = true;
        }
        if (inky.getXPos() / 26 == 0) {
            inky.setXPos(26 * 21 - 1);
        }
        if (inky.getXPos() / 26 == 21) {
            inky.setXPos(26 * 1);
        }
    }

    /**
     * Move Clyde by making a decision every time the counter has reached PX
     * (when the ghost is in the middle block)
     */
    public void moveClyde() {
        //If clydeCounter has reached the tile width (meaning the ghost has moved enough to get to the middle of the block)
        //Then find the clydePossible moves
        if (clydeCounter == px) {
            clydeCounter = 0; //Reset the clydeCounter keeping track of how far the ghost has moved
            clydePossible = ""; //Reset the clydePossible moves to nothing
            if (b[(clyde.getXPos() / px)][clyde.getYPos() / px - 1] instanceof Block == false && clydeUp) { //if it can move UP
                clydePossible += "up "; //Add "up" to clydePossible moves
            }
            if (b[clyde.getXPos() / px][clyde.getYPos() / px + 1] instanceof Block == false && clydeDown) { //if it can move DOWN
                clydePossible += "down "; //Add "down" to clydePossible moves
            }
            if (b[clyde.getXPos() / px + 1][clyde.getYPos() / px] instanceof Block == false && clydeRight) { //if it can move RIGHT
                clydePossible += "right "; //Add "right" to clydePossible moves
            }
            if (b[clyde.getXPos() / px - 1][clyde.getYPos() / px] instanceof Block == false && clydeLeft) { //if it can move LEFT
                clydePossible += "left"; //Add "left" to clydePossible move
            }

            // System.out.println(clydePossible);
            clydeChoice = makeChoice(clydePossible); //Make the clydeChoice for where to go
        }

        if (clydeChoice.equals("up")) { //If clydeChoice selected is up
            if (clydeCounter > 0 && clydeCounter < 14) {
                clyde.setSprite(imgClydeUp1); //Set the image to up
            } else {
                clyde.setSprite(imgClydeUp2); //Set the image to up but different
            }
            clyde.moveUp(); //Move the ghost up the same amount as it's speed
            clydeDown = false; //Ghost can no longer go down (backwards)
            //But can go all other directions assuming there isn't a block in the way
            clydeUp = true;
            clydeRight = true;
            clydeLeft = true;
        } else if (clydeChoice.equals("down")) { //If clydeChoice selected is down
            if (clydeCounter > 0 && clydeCounter < 14) {
                clyde.setSprite(imgClydeDown1); //Set the image to down
            } else {
                clyde.setSprite(imgClydeDown2); //Set the image to down but different
            }
            clyde.moveDown(); //Move the ghost down the same amount as it's speed
            clydeUp = false; //Ghost can no longer go up (backwards)
            //But can go all other directions assuming there isn't a block in the way
            clydeDown = true;
            clydeRight = true;
            clydeLeft = true;
        } else if (clydeChoice.equals("right")) { //If clydeChoice selected is right
            if (clydeCounter > 0 && clydeCounter < 14) {
                clyde.setSprite(imgClydeRight1); //Set the image to right
            } else {
                clyde.setSprite(imgClydeRight2); //Set the image to right but different
            }
            clyde.moveRight(); //Move the ghost right the same amount as it's speed
            clydeLeft = false; //Ghost can no longer go left (backwards)
            //But can go all other directions assuming there isn't a block in the way
            clydeUp = true;
            clydeRight = true;
            clydeDown = true;
        } else {
            //must be left
            if (clydeCounter > 0 && clydeCounter < 14) {
                clyde.setSprite(imgClydeLeft1); //Set the image to left
            } else {
                clyde.setSprite(imgClydeLeft2); //Set the image to left but different
            } //Set the image to left
            clyde.moveLeft(); //Move the ghost left the same amount as it's speed
            clydeRight = false; //Ghost can no longer go right (backwards)
            //But can go all other directions assuming there isn't a block in the way
            clydeUp = true;
            clydeDown = true;
            clydeLeft = true;
        }
        if (clyde.getXPos() / 26 == 0) {
            clyde.setXPos(26 * 21 - 1);
        }
        if (clyde.getXPos() / 26 == 21) {
            clyde.setXPos(26 * 1);
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
