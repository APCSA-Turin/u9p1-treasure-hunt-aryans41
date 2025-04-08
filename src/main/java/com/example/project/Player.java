package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite  {
    //a player has unique attributes like treasureCount, numLives, and win
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2
        super(x, y); //constructs a Dot object using a call to the super(constructor of Sprite class)
        //assigns default values to the player's unique attributes
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    //returns the unique instance variables(attributes) of the player class different from the Sprite class
    public int getTreasureCount(){
        return treasureCount;
    }
   
    public int getLives() {
        return numLives;
    }

    public boolean getWin(){
        return win;
    }

    //allows for the setting or modification of the unique instance variables(attributes) of the player class
    public void setLives(int val) {
        numLives = val;
    }

    public void setWin(boolean b) {
        win = b;
    }

    // these are the methods that are overridden in the child class.
    // these unique methods would be called instead of the one in the parent class called "Sprite"
    @Override
    public String getCoords(){ //returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }


    @Override
    public String getRowCol(int size){ //return "Player:"+row col
        return "Player:" + super.getRowCol(size);
    }
   
    @Override
    //move method should override parent class, sprite
    //this method moves a player a given direction based on its previous position(x,y value) and updates those attributes
    public void move(String direction) { //move the (x,y) coordinates of the player
            if (direction.equals("w")) { //up so the y-val has to increment by 1
                setY(getY() + 1);
            }
            if (direction.equals("s")) { //down so the y-val has to decrement by 1
                setY(getY() - 1);
            }
            if (direction.equals("a")) { //left so the x-val has to decrement by 1
                setX(getX() - 1);
            }
            if (direction.equals("d")) { //right so the y-val has to increment by 1
                setX(getX() + 1);
            }
        }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to
    //numTreasures is the total treasures at the beginning of the game
        if(isValid(size,direction)){ //before interaction, it must ensure that that iteraction of movement from the player is valid based on the grid size and direction
            if(obj instanceof Enemy){
                numLives--;    //is the object that the player is interacting with is a ememy, then player loses a life
            } else if (obj instanceof Treasure){ //it the object is a treasure and also a trophy such that he has already gained the max amount of tresures, then the player can obtain the trophy and win
                if(obj instanceof Trophy){
                    if(treasureCount==numTreasures){  
                        win = true;
                    }
                } else {
                    treasureCount++; //if the player still doesn't have max treasures, then treasure count simply increments
                }
            }
        }
    }
   
    //this overrides the isValid method in the Sprite class
    //utilizes the x-y instance variables to determine if the Player can move or not
    @Override 
    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) { //can move up if below the toppest row
            if (getY() < size - 1) {
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("a")) {
            if (getX() > 0) { //can move left is to the right of the lefest column
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("s")) {
            if (getY() > 0) { //can move down if above the bottomest row
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("d")) {
            if (getX() < size - 1) { //can move right if to the left of the most rightest column
                return true;
            } else {
                return false;
            }
        }
        return false; //returns false for all invalid calls to the method
    }
}

