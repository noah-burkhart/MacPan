/*
 * N Burkhart
 * 12/22/2022
 * Object made to represent food in the game
*/
package macpan.objects;

import java.awt.Image;


public class Food extends AbstractConsumable{
    
    /**
     * General constructor for food items.
     * @param x - the x position
     * @param y - the y position
     */
    public Food(int x, int y){
        super(x, y);
        //sprite = foodImg;
        points = 50; //assigns the point value for the food
    }
    
    /**
     * Constructor for food with described variables
     * @param sprite - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param points - the points it is worth
     */
    public Food(Image sprite, int x, int y, int points){
        super(sprite, x, y, points);
    }
    
    /**
     * Clones the food object ignoring location
     * @return - the clone
     */
    public Food clone(){
        Food o = new Food(0, 0);
        o.setPoints(points);  //clones points and sprite.
        o.setSprite(sprite);
        o.visible = true; //makes it visible by default
        return o; //the new food
    }
    
    /**
     * Will see if the food is equal to the other in terms of its sprite and its point value.
     * @param o - the other food object
     * @return - if they are equal
     */
    public boolean equals(Food o){
        return super.equals(o);
    }

    @Override
    /**
     * Puts the attributes into a string
     */
    public String toString() {
        return "Food{" + super.toString() + '}';
    }
    
    
}
