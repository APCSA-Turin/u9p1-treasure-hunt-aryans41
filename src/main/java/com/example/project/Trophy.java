package com.example.project;

// only needs a constructor
public class Trophy extends Treasure { //child of treasure
    public Trophy(int x, int y){ //constructs a Dot object using a call to the super(constructor of Treasure class)
        super(x, y);
    }

    // these are the methods that are overridden in the child class.
    // these unique methods would be called instead of the one in the parent class called "Sprite"
    @Override 
    public String getCoords(){ //returns "Trophy:"+coordinates
        return "Trophy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Trophy:"+row col
        return "Trophy:" + super.getRowCol(size);
    }
}
