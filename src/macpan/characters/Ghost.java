/*
Jack Luhta
December 22, 2022
Ghost Interface
 */
package macpan.characters;

public interface Ghost {

    //Move the vehicle position in the lot
    public void move(int x, int y);

    //Get x position of vehicle
    public int getXPos();

    //Get y position of vehicle
    public int getYPos();

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

    //Get the cost of the vehicle
    public double getCost();

    //Set the cost of the vehicle
    //Return false if the cost is not valid
    public boolean setCost(double cost);

    //Get make of vehicle
    public String getMake();

    //Set make of vehicle
    public void setMake(String make);

    //Get model of vehicle
    public String getModel();

    //Set model of vehicle
    public void setModel(String model);

    //Get color of vehicle
    public Color getColor();

    //Set color of vehicle
    public void setColor(Color c);

    //Draw the vehicle
    public void draw(StandardPen p);

    //Get a string of all the vehicle information
    public String toString();
}
