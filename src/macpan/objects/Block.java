/*
 * Noah Burkhart
 * January 9, 2023
 * Class storing the visuals for each block or empty space in the gameframe
*/

package macpan.objects;

import java.awt.Image;

public class Block {
    
    //attributes
    public Image sprite;
    public int x;
    public int y;
    
    /**
     * General constructor for a block
     * @param sprite - the sprite
     * @param x -the x position
     * @param y - the y position
     */
    public Block(Image sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the image
     * @return - the image
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Sets the image
     * @param sprite - the new image
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets the x position
     * @return - the position
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position
     * @param x - the new x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y position
     * @return - the position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position
     * @param y - the new y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    /**
     * Sends attributes to a string
     */
    public String toString() {
        return "Block{" + "sprite=" + sprite + ", x=" + x + ", y=" + y + '}';
    }
    
    //no equals class needed as all blocks are going to be the same
    
    /**
     * Clones all attributes of a block
     * @return - the block.
     */
    public Block clone(){
        Block b = new Block(sprite, x, y); //creates a clone at the same spot with the same sprite.
        return b; //returns the clone
    }
    
    }
    

