/*
 * V. He
 * Jan 5th, 2023
 */
package macpan.characters;

import java.awt.Image; 

public class Pacman extends AbstractCharacter{
    
    private boolean powerPellet;
    
    /**
     * Primary Constructor for PacMan
     * He must have:
     * @param s - a sprite
     * @param x - x position
     * @param y - y position
     * @param d - the direction he's facing
     */
    public Pacman(Image s, int x, int y, String d) {
        super(s, x, y, d);
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