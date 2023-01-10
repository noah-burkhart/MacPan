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
     * Accessor 
     * @return 
     */
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    

    public boolean isPowerPellet() {
        return powerPellet;
    }

    public void setPowerPellet(boolean powerPellet) {
        this.powerPellet = powerPellet;
    }
    
    
//    public int state(boolean powerPellet) {
//        if (powerPellet == true) {
//            return 3;
//        } else {
//            return AbstractGhost.getState();
//        }
//    }
}
