/*
 * Jack Luhta
 * December 23, 2022
 * Blinky class, extends abstract ghost 
 */
package macpan.characters;

import java.awt.Image;

public class Blinky extends AbstractGhost implements Ghost{
    /**
     * Blinky must be created with a sprite, a position, a direction, and whether or not they're in the spawn box
     * @param s - Image sprite
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param d - direction ghost is facing
     * @param iB - whether or not the ghost is in the box
     */
    public Blinky(Image s, int x, int y, String d, boolean iB) {
        super(s, x, y, d, iB);
    }
    
    public int chaseAlgorithmX(int pacmanX) {
        return pacmanX;
    }
    
    public int chaseAlgorithmY(int pacmanY) {
        return pacmanY;
    }
    
    public int scatterPointX() {
        return 600;
    }
    
    public int scatterPointY() {
        return 0;
    }
}
