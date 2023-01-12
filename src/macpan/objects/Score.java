/*
 * V. He
 * A Score class to hold the score (int) and the initials of user (string) in one object
 * Jan 11th, 2023
 */
package macpan.objects;

import java.util.Objects;

public class Score {
    private int value;
    private String name;

    /**
     * Primary Constructor for a score
     * @param value - int value of score
     * @param name - initials of player (3 characters)
     */
    public Score(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Accessor Method for value
     * @return - the value of score
     */
    public int getValue() {
        return value;
    }

    /**
     * Accessor Method for Name
     * @return - the initials of player
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator Method for score value
     * @param value - value of score
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Mutator Method for player name
     * @param name - player's initials 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString method for Score
     * @return - all of score's information
     */
    @Override
    public String toString() {
        return "Score{" + "value=" + value + ", name=" + name + '}';
    }

    /**
     * equals method for score
     * @param obj - another score
     * @return - true or false if they are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (this.value != other.value) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }   
    
    @Override
    public Score clone() {
        Score s = new Score(value, name);
        return s;
    }
}