/*
 * Jack Luhta
 * December 25, 2022
 * Class storing the visuals for each block in the game frame
*/

package macpan;

import java.awt.Image;

public class Block {
    Image sprite;
    int xPos;
    int yPos;
    boolean full;
    boolean empty;
    
    public Block(Image s, int x, int y, boolean e) {
        sprite = s;
        xPos = x;
        yPos = y;
        empty = e;
    }
    
    public Block(Image s, int x, int y, boolean e, boolean f) {
        sprite = s;
        xPos = x;
        yPos = y;
        empty = e;
        full = f;
    }
    
    public boolean getEmpty() {
        return empty;
    }
    
    public void setEmpty(boolean e) {
        empty = e;
    }
}
