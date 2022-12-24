/*
 * Jack Luhta
 * December 23, 2022
 * Clyde class, extends abstract ghost 
 */
package macpan.characters;

import java.awt.Image;



public class Clyde extends AbstractGhost implements Ghost {
    /**
     * Clyde must be created with a sprite, a position, a direction, and whether or not they're in the spawn box
     * @param s - Image sprite
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param d - direction ghost is facing
     * @param iB - whether or not the ghost is in the box
     */
    public Clyde(Image s, int x, int y, String d, boolean iB) {
        super(s, x, y, d, iB);
    }
    
    public int chaseAlgorithmX(int pacmanX) {
        //if clyde is within an 8 tile radius, set target point ot scatter point, otherwise, same as blinky
    }
    
    public int chaseAlgorithmY(int pacmanY) {
        //if clyde is within an 8 tile radius, set target point ot scatter point, otherwise, same as blinky
    }
    
    public int scatterPointX() {
        return 0;
    }
    
    public int scatterPointY() {
        return 800;
    }
}
