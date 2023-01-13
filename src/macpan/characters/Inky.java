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
     */
    public Inky(Image s, int x, int y, String d) {
        super(s, x, y, d);
    }
    
     /**
     * Full constructor for a Inky object.
     * @param s - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param xSpeed - the x speed
     * @param ySpeed - the y speed
     * @param d - the direction its facing
     */
    public Inky(Image s, int x, int y, int xSpeed, int ySpeed, String d){
        super(s, x, y, d);
        this.setXSpeed(xSpeed); //sets the x and y speed
        this.setYSpeed(ySpeed);
        
    }
    
    
//    public int chaseAlgorithmX(int pacmanX, Blinky b) {
//        int pivotPointX = pacmanX;
//        int targetX;
//        if (Pacman.getDirection().equals("east") || Pacman.getDirection().equals("west")) {
//            pivotPointX += (2 * 18);
//        }
//        targetX = pivotPointX + (pivotPointX - b.getXPos());
//        return targetX;
//    }
//    
//    public int chaseAlgorithmY(int pacmanY, Blinky b) {
//        int pivotPointY = pacmanY;
//        int targetY;
//        if (Pacman.getDirection().equals("north") || Pacman.getDirection().equals("south")) {
//            pivotPointY += (2 * 18);
//        }
//        targetY = pivotPointY + (pivotPointY - b.getYPos());
//        return targetY;
//    }
    
    /**
     * target x coordinate for scatter mode
     * @return - the coordinate inky should go to 
     */
    public int scatterPointX() {
        return 600;
    }
    
    /**
     * target y coordinate for scatter mode
     * @return - the coordinate inky should go to 
     */
    public int scatterPointY() {
        return 800;
    }
}