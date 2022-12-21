/*
 *N Burkhart
 * Abstract class to represent all consumable objects
 */
package macpan.objects;

import java.awt.Image;

/**
 *
 * @author nobur7839
 */
abstract class  AbstractConsumable {
    
    //attributes
    private Image sprite;
    private int x, y, points;
    
    /**
     * General constructor for all consumable objects.
     * @param sprite - the sprite
     * @param xPos - the x position
     * @param yPos - the y position
     * @param points - the points value it holds.
     */
    public AbstractConsumable(Image sprite, int xPos, int yPos, int points){
        this.sprite = sprite;
        x = xPos;
        y = yPos;                  //sets values
        this.points = points;
    }

    //getters and setters
    
    /**
     * Gets the Image file of the sprite
     * @return - the Image
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Sets the sprite of the object
     * @param sprite - the new sprite
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets the x-position of the object
     * @return - the x position
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position of the object
     * @param x - the x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y position of the object
     * @return - the y position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of the object
     * @param y - the new y position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the point value
     * @return - the value
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the point value
     * @param points - the new point value
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    //behaviours
    
    /**
     * Eats the item making the space empty
     * @return - the point value of the consumable eaten.
     */
    private int eat(){
        int value = points; //temporarily stores the point score to be returned
        sprite = null; //gets rid of the srpite
        points = 0; //erases  point value
        //by erasing sprite and point value, it will still occupy the space as a blank space, but adds no point value. Can be overwritten.
        return value;
    }

    @Override
    /**
     * General to-string method, must add title and ending variables to it.
     */
    public String toString() {
        return "sprite=" + sprite + ", x=" + x + ", y=" + y + ", points=" + points;
    }
    
    /**
     * Compares the point value and sprite to see if they are the same.
     * @param o - the other object
     * @return - if they are the same
     */
    public boolean equals(AbstractConsumable o){
        return sprite == o.getSprite() && points == o.points;
    }
    
    
    
    
    
}
