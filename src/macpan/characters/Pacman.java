/*
 * V. He
 * Jan 5th, 2023
 * Pacman class 
 */
package macpan.characters;

import java.awt.Image; 

public class Pacman extends AbstractCharacter{
    
    private boolean powerPellet;
    private int lives;
    private int score;
    
    /**
     * Primary Constructor for PacMan (from AbstractCharacter)
     * He must have:
     * @param s - a sprite
     * @param x - x position
     * @param y - y position
     * @param d - the direction he's facing
     */
    public Pacman(Image s, int x, int y, String d) {
        super(s, x, y, d);
        lives = 3;
        score = 0;
    }

    /**
     * Secondary Constructor for PacMan
     * @param lives - number of lives 
     * @param s - pacman sprite
     * @param x - x position
     * @param y - y position
     * @param d - direction he is facing
     */
    public Pacman(int lives, Image s, int x, int y, String d) {
        super(s, x, y, d);
        this.lives = lives;
        score = 0;
    }
    
    /**
     * Full constructor for a PacMan object.
     * @param lives - the amound of lives he has
     * @param s - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param xSpeed - the x speed
     * @param ySpeed - the y speed
     * @param d - the direction its facing
     */
    public Pacman(int lives, Image s, int x, int y, int xSpeed, int ySpeed, String d){
        super(s, x, y, d);
        this.setXSpeed(xSpeed); //sets the x and y speed
        this.setYSpeed(ySpeed);
        this.lives = lives; //sets the lives
        score = 0;
        
    }

    /**
     * Accessor method for lives
     * @return - number of lives pacman has
     */
    public int getLives() {
        return lives;
    }

    /**
     * Mutator method for pacman lives
     * @param lives - number of lives pacman has
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gets the score of Pacman
     * @return - the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of Pacman
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * Adds to the score.
     * @param score 
     */
    public void addScore(int score){
        this.score += score; //adds to the score
    }
    
   
    /**
     * Accessor method for power pellet variable
     * @return - if pacman ate a power pellet or not
     */
    public boolean isPowerPellet() {
        return powerPellet;
    }

    /**
     * Mutator Method for power pellet boolean
     * @param powerPellet - did pacman consume a power pellet? true or false
     */
    public void setPowerPellet(boolean powerPellet) {
        this.powerPellet = powerPellet;
    }
}