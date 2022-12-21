/*
 *N Burkhart
 * 12/21/2022
 * Class to represent a pellet object.
 */
package macpan.objects;

import java.awt.Image;

/**
 *
 * @author nobur7839
 */
public class Pellet extends AbstractConsumable {

    /**
     * Primary constructor for a pellet
     * @param x - the x position
     * @param y - the y position
     */
    public Pellet(int x, int y) {
        super(x, y);
        //sprite = pelletImg;
        points = 10;
    }

    /**
     * Secondary constructor for a pellet utilizing abstract class.
     *
     * @param sprite - the sprite.
     * @param x - the x position.
     * @param y - the y position.
     * @param points - the points value.
     */
    public Pellet(Image sprite, int x, int y, int points) {
        super(sprite, x, y, points);
        points = 10; //overrides the points assignment as it can only be 10
    }

    /**
     * Clones the pellet and makes the clone visible (ignores position).
     * @return - the clone.
     */
    public Pellet clone(){
        Pellet p = new Pellet(0, 0); //creates a new pellet.
        p.setPoints(points);  //clones points and sprite.
        p.setSprite(sprite);
        p.visible = true; //makes it visible by default
        return p; //the new pellet
        }
    
    /**
     * Will see if the one pellet is the same as the other in terms of sprite and point value.
     * @param p - the other pellet
     * @return - if they are equal.
     */
    public boolean equals(Pellet p){
        return super.equals(p);
    }

    @Override
    /**
     * To string method for the pellet.
     */
    public String toString() {
        return "Pellet{" + super.toString() + '}';
    }
    
    
}
