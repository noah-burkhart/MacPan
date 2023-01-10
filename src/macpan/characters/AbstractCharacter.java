/*
 * Jack Luhta
 * December 23, 2022
 * Abstract character class
   This class contains the similar information and methods for all characters, 
   including pacman and the ghosts.
 */
package macpan.characters;

import java.awt.Image;

abstract class AbstractCharacter {
    
    //Instance variables
    protected Image sprite;
    protected static int xPos, yPos, xSpeed, ySpeed;
    protected String direction;
    
    /**
     * Character must be created with a sprite, a position and a direction
     * @param s - Image of sprite
     * @param x - X position of character
     * @param y - Y position of character
     * @param d - Direction of character (north, south, east, west)
     */
    public AbstractCharacter(Image s, int x, int y, String d) {
        sprite = s;
        xPos = x;
        yPos = y;
        direction = d;
    }
    
    /**
     * Move the character up based on it's current y speed
     */
    public void moveUp(int size) {
        yPos = yPos - ySpeed;
    }
    
    /**
     * Move the character down based on it's current y speed
     */
    public void moveDown(int size) {
        yPos = yPos + ySpeed;
    }
    
    /**
     * Move the character right based on it's current x speed
     */
    public void moveRight(int size) {
        xPos = xPos + xSpeed;
    }
    
    /**
     * Move the character left based on it's current x speed
     */
    public void moveLeft(int size) {
        xPos = xPos - xSpeed;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setSprite(Image s) {
        sprite = s;
    }
    
    /**
     * Get the image sprite of the character
     * @return - Sprite image
     */
    public Image getSprite() {
        return sprite;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setXPos(int x) {
        xPos = x;
    }
    
    /**
     * Get the image sprite of the character
     * @return - Sprite image
     */
    public int getXPos() {
        return xPos;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setYPos(int y) {
        yPos = y;
    }
    
    /**
     * Get the image sprite of the character
     * @return - Sprite image
     */
    public int getYPos() {
        return yPos;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setXSpeed(int xS) {
        xSpeed = xS;
    }
    
    /**
     * Get the image sprite of the character
     * @return - Sprite image
     */
    public int getXSpeed() {
        return xSpeed;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setYSpeed(int yS) {
        ySpeed = yS;
    }
    
    /**
     * Get the image sprite of the character
     * @return - Sprite image
     */
    public int getYSpeed() {
        return ySpeed;
    }
    
    /**
     * Set the image sprite of the character
     * @param s - Sprite image
     */
    public void setDirection(String d) {
        direction = d;
    }
    
    public String getDirection() {
        return direction;
    }
    
    public String toString(String m) {
        String message = "";
        message += ("Position: " + xPos + "," + yPos);
        message += ("\nSpeed: " + xSpeed + "," + ySpeed);
        message += ("\nDirection: " + direction);
        message += m;
        return message;
    }
}
