package com.example.project;

//Dot only needs a constructor
public class Dot extends Sprite {
    public Dot(int x, int y) {
        super(x, y);
    }

    @Override 
    public String getCoords(){ //returns "Dot:"+coordinates
        return "Dot:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Dot:"+row col
        return "Dot:" + super.getRowCol(size);
    }
}
