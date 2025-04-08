package com.example.project;

//Dot only needs a constructor
public class Dot extends Sprite {
    public Dot(int x, int y) {
        super(x, y);  //constructs a Dot object using a call to the super(constructor of Sprite class)
    }

    // these are the methods that are overridden in the child class.
    // these unique methods would be called instead of the one in the parent class called "Sprite"
    @Override 
    public String getCoords(){ //returns "Dot:"+coordinates
        return "Dot:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Dot:"+row col
        return "Dot:" + super.getRowCol(size);
    }
}
