package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite {  //child  of Sprite
    
    public Enemy(int x, int y) { //constructs a Enemy object using a call to the super(constructor of Sprite class)
        super(x, y);
    }

    // the methods below should override the super class 
    // these are the methods that are overridden in the child class.
    // these unique methods would be called instead of the one in the parent class called "Sprite"
    @Override 
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}