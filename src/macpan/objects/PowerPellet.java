/*
 *N Burkhart
 * 12/21/2022
 * Class to represent a pellet object.
 */
package macpan.objects;

import java.awt.Image;

public class PowerPellet extends AbstractConsumable{
      /**
     * Primary constructor for a Power Pellet
     * @param x - the x position
     * @param y - the y position
     */
    public PowerPellet(int x, int y) {
        super(x, y);
        //sprite = pelletImg;
        points = 50;
    }
    
    /**
     * Secondary constructor for a Power Pellet utilizing abstract class.
     *
     * @param sprite - the sprite.
     * @param x - the x position.
     * @param y - the y position.
     */
    public PowerPellet(Image sprite, int x, int y) {
        super(sprite, x, y);
        points = 50; //overrides the points assignment as it can only be 50
    }
    
    /**
     * Secondary constructor for a pellet utilizing abstract class.
     *
     * @param sprite - the sprite.
     * @param x - the x position.
     * @param y - the y position.
     * @param points - the points value.
     */
    public PowerPellet(Image sprite, int x, int y, int points) {
        super(sprite, x, y, points);
        points = 50; //overrides the points assignment as it can only be 50
    }
  
    /**
     * Clones the pellet and makes the clone visible (ignores position).
     * @return - the clone.
     */
    public PowerPellet clone(){
        PowerPellet p = new PowerPellet(sprite, x, y, points);
        return p; //the new pellet
        }
    
    /**
     * Will see if the one PowerPellet is the same as the other in terms of sprite and point value.
     * @param p - the other pellet
     * @return - if they are equal.
     */
    public boolean equals(PowerPellet p){
        return super.equals(p);
    }

    @Override
    /**
     * To string method for the PowerPellet.
     */
    public String toString() {
        return "PowerPellet{" + super.toString() + '}';
    }
    
    
}
