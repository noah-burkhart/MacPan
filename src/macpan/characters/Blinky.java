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
     */
    public Blinky(Image s, int x, int y, String d) {
        super(s, x, y, d);
    }
    
      /**
     * Full constructor for a Blinky object.
     * @param s - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param xSpeed - the x speed
     * @param ySpeed - the y speed
     * @param d - the direction its facing
     */
    public Blinky(Image s, int x, int y, int xSpeed, int ySpeed, String d){
        super(s, x, y, d);
        this.setXSpeed(xSpeed); //sets the x and y speed
        this.setYSpeed(ySpeed);
        
    }
    
    /**
     * Find the x coordinate blinky should go to 
     * @param pacmanX - pacman's x coordinate
     * @return - returns that x coordinate
     */
    public int chaseAlgorithmX(int pacmanX) {
        return pacmanX;
    }
    
    /**
     * Find the y coordinate blinky should go to 
     * @param pacmanY - pacman's y coordinate
     * @return - returns that y coordinate
     */
    public int chaseAlgorithmY(int pacmanY) {
        return pacmanY;
    }
    
    /**
     * target x coordinate for scatter mode
     * @return - the coordinate blinkly should go to 
     */
    public int scatterPointX() {
        return 600;
    }
    
    /**
     * target y coordinate for scatter mode
     * @return - the coordinate blinkly should go to 
     */
    public int scatterPointY() {
        return 0;
    }
}