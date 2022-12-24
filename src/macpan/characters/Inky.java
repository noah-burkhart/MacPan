/*
 * Jack Luhta
 * December 23, 2022
 * Inky class, extends abstract ghost 
 */
package macpan.characters;

import java.awt.Image;



public class Inky extends AbstractGhost implements Ghost {
    /**
     * Inky must be created with a sprite, a position, a direction, and whether or not they're in the spawn box
     * @param s - Image sprite
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param d - direction ghost is facing
     * @param iB - whether or not the ghost is in the box
     */
    public Inky(Image s, int x, int y, String d, boolean iB) {
        super(s, x, y, d, iB);
    }
    
    public int chaseAlgorithmX(int pacmanX) {
        //int pivotPointX;
        //int targetX;
        //if (Pacman.getDirection().equals("east") || Pacman.getDirection().equals("west")) {
        //    pivotPointX = pacmanX + (2 * 18);
        //}
        //targetX = pivotPointX + (pivotPointX - Blinky.getXPos());
        //return targetY;
    }
    
    public int chaseAlgorithmY(int pacmanY) {
        //int pivotPointY;
        //int targetY;
        //if (Pacman.getDirection().equals("north") || Pacman.getDirection().equals("south")) {
        //    pivotPointY = pacmanY + (2 * 18);
        //}
        //targetY = pivotPointY + (pivotPointY - Blinky.getYPos());
        //return targetY;
    }
    
    public int scatterPointX() {
        return 600;
    }
    
    public int scatterPointY() {
        return 800;
    }
}
