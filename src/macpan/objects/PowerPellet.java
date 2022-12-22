/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macpan.objects;

import java.awt.Image;

/**
 *
 * @author nobur7839
 */
public class PowerPellet extends AbstractConsumable{
    
    /**
     * General constructor for PowerPellet items.
     *
     * @param x - the x position
     * @param y - the y position
     */
    public PowerPellet(int x, int y) {
        super(x, y);
        //sprite = foodImg;
        points = 50; //assigns the point value for the food
    }

    /**
     * Constructor for PowerPellet with described variables
     *
     * @param sprite - the sprite
     * @param x - the x position
     * @param y - the y position
     * @param points - the points it is worth
     */
    public PowerPellet(Image sprite, int x, int y, int points) {
        super(sprite, x, y, points);
    }
}
