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
     */
    public Clyde(Image s, int x, int y, String d) {
        super(s, x, y, d);
    }
    
     /**
     * Full constructor for a Clyde object.
     * @param s - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param xSpeed - the x speed
     * @param ySpeed - the y speed
     * @param d - the direction its facing
     */
    public Clyde(Image s, int x, int y, int xSpeed, int ySpeed, String d){
        super(s, x, y, d);
        this.setXSpeed(xSpeed); //sets the x and y speed
        this.setYSpeed(ySpeed);
        
    }
    
    public double distanceBetween(int pacmanX, int pacmanY) {
        double distance;
        distance = (Math.pow((getXPos() - pacmanX), 2)) + (Math.pow((getYPos() - pacmanY), 2));
        return distance;
    }
    
    public double angleTo(int pacmanX, int pacmanY) {
        double angle;
        double slope = (getYPos() - pacmanY)/(getXPos() - pacmanX);
        angle = Math.atan(slope);
        return angle;
    }
    
    public int chaseAlgorithmX(int pacmanX, int pacmanY) {
        if ((distanceBetween(pacmanX, pacmanY) * Math.cos(angleTo(pacmanX, pacmanY))) < (8 * 18) * Math.cos(angleTo(pacmanX, pacmanY))) {
            return scatterPointX();
        }
        return pacmanX;
    }
    
    public int chaseAlgorithmY(int pacmanY, int pacmanX) {
        if ((distanceBetween(pacmanX, pacmanY) * Math.cos(angleTo(pacmanX, pacmanY))) < (8 * 18) * Math.cos(angleTo(pacmanX, pacmanY))) {
            return scatterPointY();
        }
        return pacmanY;
    }
    
    /**
     * target x coordinate for scatter mode
     * @return - the coordinate clyde should go to 
     */
    public int scatterPointX() {
        return 0;
    }
    
    /**
     * target y coordinate for scatter mode
     * @return - the coordinate clyde should go to 
     */
    public int scatterPointY() {
        return 800;
    }
}