/*
 * Jack Luhta
 * December 25, 2022
 * Class storing the visuals for each block in the game frame
*/

package macpan;

import javax.swing.ImageIcon;

public class Block {
    ImageIcon sprite;
    int xPos;
    int yPos;
    boolean full;
    
    public Block(ImageIcon s, int x, int y) {
        sprite = s;
        xPos = x;
        yPos = y;
    }
    
    public Block(ImageIcon s, int x, int y, boolean f) {
        sprite = s;
        xPos = x;
        yPos = y;
        full = f;
    }
}
