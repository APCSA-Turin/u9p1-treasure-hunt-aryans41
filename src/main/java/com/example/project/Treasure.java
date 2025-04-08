package com.example.project;

//only needs a constructor
public class Treasure extends Sprite { //child of Sprite
    public Treasure(int x, int y) { //constructs a Dot object using a call to the super(constructor of Sprite class)
        super(x, y);
    }

    // these are the methods that are overridden in the child class.
    // these unique methods would be called instead of the one in the parent class called "Sprite"
    @Override 
    public String getCoords(){ //returns "Treasure:"+coordinates
        return "Treasure:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Treasure:"+row col
        return "Treasure:" + super.getRowCol(size);
    }
}