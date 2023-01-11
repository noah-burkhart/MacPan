/*
 * V. He
 * Jan 5th, 2023
 */
package macpan.characters;

import java.awt.Image; 

public class Pacman extends AbstractCharacter{
    
    private boolean powerPellet;
    private int lives;
    
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