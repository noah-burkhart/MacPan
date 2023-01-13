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
     * Find the x coordinate blinky should go to when
     * @param pacmanX
     * @return 
     */
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
