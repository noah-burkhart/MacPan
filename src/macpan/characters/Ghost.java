/*
  * Jack Luhta
  * December 22, 2022
  * Ghost Interface
 */
package macpan.characters;

public interface Ghost {

    //Get the state the ghost is on, 1-4
    // *Chase mode, goes to pacman, (dependent on type of ghost)
    // *Scatter mode, move to repective corner of map
    // *Scared mode, pac man has eaten power pellet
    // *Eaten mode, goes back to base after being eaten
    public int getState();

    //Set the state the ghost is on, 1-4
    // *Chase mode, goes to pacman, (dependent on type of ghost)
    // *Scatter mode, move to repective corner of map
    // *Scared mode, pac man has eaten power pellet
    // *Eaten mode, goes back to base after being eaten
    public void setState(int state);
    
    //Check if ghost is in spawn box or not
    public boolean getInBox();
    
    //Set the ghost as being in the box or not
    public void setInBox(boolean box);
    
    //Get the x coordinate target point on the gameboard 2d array for the ghost to move to
    public int getTargetPointX();
    
    //Set the x coordinate new target point on the gameboard 2d array for the ghost to move to
    public void setTargetPointX(int point);
    
    //Get the y coordinate target point on the gameboard 2d array for the ghost to move to
    public int getTargetPointY();
    
    //Set the y coordinate new target point on the gameboard 2d array for the ghost to move to
    public void setTargetPointY(int point);
    
    //Output the String representation of the class
    public String toString(String m);
}
