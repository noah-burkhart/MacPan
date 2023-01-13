/*
 * Noah Burkhart
 * January 9th, 2023
 * Interface for all objects on the gameboard, this being blocks, pellets and food.
 */
package macpan.objects;

import java.awt.Image;

public interface Thing {
    
    public int getX();
    public void setX(int x);  //getter and setter for x position
    
    public int getY();
    public void setY(int y); //getter and setter for y position
    
    public Image getSprite();
    public void setSprite(Image sprite);  //getter and setter for the sprite
    
    public String toString(); //toString method must be included
}