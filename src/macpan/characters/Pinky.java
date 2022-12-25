/*
 * Jack Luhta
 * December 23, 2022
 * Pinky class, extends abstract ghost 
 */
package macpan.characters;

import java.awt.Image;



public class Pinky extends AbstractGhost implements Ghost {
    /**
     * Pinky must be created with a sprite, a position, a direction, and whether or not they're in the spawn box
     * @param s - Image sprite
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param d - direction ghost is facing
     * @param iB - whether or not the ghost is in the box
     */
    public Pinky(Image s, int x, int y, String d, boolean iB) {
        super(s, x, y, d, iB);
    }
    
    public int chaseAlgorithmX(int pacmanX) {
        if (Pacman.getDirection().equals("east") || Pacman.getDirection().equals("west")) {
            pacmanX = pacmanX + (4*18);
        }
        return pacmanX;
    }
    
    public int chaseAlgorithmY(int pacmanY) {
        if (Pacman.getDirection().equals("north") || Pacman.getDirection().equals("south")) {
            pacmanY = pacmanY + (4*18);
        }
        return pacmanY;
    }
    
    public int scatterPointX() {
        return 0;
    }
    
    public int scatterPointY() {
        return 0;
    }
}
