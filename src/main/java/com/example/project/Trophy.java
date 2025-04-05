package com.example.project;

// only needs a constructor
public class Trophy extends Treasure { //child of treasure
    public Trophy(int x, int y){
        super(x, y);
    }

    @Override 
    public String getCoords(){ //returns "Trophy:"+coordinates
        return "Trophy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Trophy:"+row col
        return "Trophy:" + super.getRowCol(size);
    }
}
