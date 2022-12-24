/*
 * Jack Luhta
 * December 23, 2022
 * Abstract ghost class
   This class contains the similar information and methods between all ghosts
 */
package macpan.characters;

import java.awt.Image;

public abstract class AbstractGhost extends AbstractCharacter implements Ghost {
    
    //Instance variables=
    protected int targetPointX, targetPointY, state;
    protected boolean inBox;
    protected long scatterTime, chaseTime, scaredTime, boxTime;
    
    /**
     * Ghost must be created with a sprite, a position, a direction, and whether or not they're in the spawn box
     * @param s - Image sprite
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param d - direction ghost is facing
     * @param iB - whether or not the ghost is in the box
     */
    public AbstractGhost(Image s, int x, int y, String d, boolean iB) {
        super(s, x, y, d);
        inBox = iB;
    }
    
    /**
     * Set the state the ghost is in
     * @param s - one of 4 different states
     */
    public void setState(int s) {
        state = s;
    }
    
    /**
     * Get the state the ghost is in
     * @return - the state the ghost is in
     */
    public int getState() {
        return state;
    }
    
    /**
     * Set whether or not the ghost is in the spawn box
     * @param iB - Boolean in box or not
     */
    public void setInBox(boolean iB) {
        inBox = iB;
    }
    
    /**
     * Get whether or not the ghost is in the spawn box
     * @return - Boolean in box or not
     */
    public boolean getInBox() {
        return inBox;
    }
    
    /**
     * Set the target x coordinate point on game board
     * @param x - x coordinate
     */
    public void setTargetPointX(int x) {
        targetPointX = x;
    }
    
    /**
     * Get the target x coordinate point on the game board
     * @return - the x coordinate
     */
    public int getTargetPointX() {
        return targetPointX;
    }
    
    /**
     * Set the target y coordinate point on game board
     * @param y - y coordinate
     */
    public void setTargetPointY(int y) {
        targetPointX = y;
    }
    
    /**
     * Get the target y coordinate point on the game board
     * @return - the y coordinate
     */
    public int getTargetPointY() {
        return targetPointY;
    }
    
    /**
     * Set the time length of the scatter state
     * @param t - time length
     */
    public void setScatterTime(long t) {
        scatterTime = t;
    }
    
    /**
     * Get the time length of the scatter state
     * @return - time length
     */
    public long getScatterTime() {
        return scatterTime;
    }
    
    /**
     * Set the time length of the chase state
     * @param t - time length
     */
    public void setChaseTime(long t) {
        chaseTime = t;
    }
    
    /**
     * Get the time length of the chase state
     * @return - time length
     */
    public long getChaseTime() {
        return chaseTime;
    }
    
    /**
     * Set the time length of the scared state
     * @param t - time length
     */
    public void setScaredTime(long t) {
        scaredTime = t;
    }
    
    /**
     * Get the time length of the scared state
     * @return - time length
     */
    public long getScaredTime() {
        return scaredTime;
    }
}
