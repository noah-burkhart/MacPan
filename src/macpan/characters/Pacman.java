/*
 * V. He
 * Jan 5th, 2023
 */
package macpan.characters;

import java.awt.Image; 

public class Pacman extends AbstractCharacter{
    
    /**
     * Primary Constructor for PacMan
     * He must have:
     * @param s - a sprite
     * @param x - x position
     * @param y - y position
     * @param d - the direction he's facing
     */
    public Pacman(Image s, int x, int y, String d) {
        super(s, x, y, d);
    }
    
}