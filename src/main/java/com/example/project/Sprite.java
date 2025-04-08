package com.example.project;

// This is the Parent class that extends the Player, Treasure, Trophy, Dot, and Enemy Objects
public class Sprite {
    private int x, y; 

    public Sprite(int x, int y) {  //constructs a Sprite object with a specified x,y position 
        this.x = x;
        this.y = y;
    }

    /* These are the getter methods for the x and y instance variables. These methods can be accessed by an object of Sprite
    of an object of its child classes */

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //setter methods for the x,y instance variables. These can be acceseed by the Sprite class and its child classes as well
    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }


    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        int column = x; //column of a sprite in the Cartesian plane
        int row = size - y - 1; //row of a Sprite in the Cartesian plane
        return "[" + row + "]" + "[" + column + "]";
    }

    //These methods only return the row or column of an object with a specific x-y instance variables 
    public int getRow(int size) {
        return size - y - 1;
    }

    public int getColumn(int size) {
        return x;
    }

    //These methods checks if a object can move in several direction based on its position on grid
    //The size parameter represents the size of a square grid
    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) {
            if (getY() == size - 1) { //can it move up or no? it must be below the top-most row (size - 1)
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("a")) { //can it move left or no? (it must be to the right to the leftest column)
            if (getX() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("s")) { //can it move down or no? (it must be above the the most bottomest row (size - 1))
            if (getY() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("d")) {
            if (getX() == size - 1) { //can it move right or no? it must be to the left of the most rightest column (size - 1)
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }
}
