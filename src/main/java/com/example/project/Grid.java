package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size; //sets the instance variable size to the parameter value
        grid = new Sprite[size][size]; // creates a 2D Sprite Grid with square dimensions based on value of side
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(col, size - 1 - row); //sets and iterates through the 2D array to set each value of the grid to a Dot object at first
                //col is the x-value of the dot object'
                //size - 1 - row is the y-value of the dot object
            }
        } 
    }

    //returns the instance variables of the Grid class which are the size and the grid itself
    public int getSize() {
        return size;
    }

    public Sprite[][] getGrid(){
        return grid;
    }

    //places a sprite(in the parameter) to spot on the grid
    //accesses the row and column of the sprite using methods in the sprite class and sets that position of the array to that specifc Sprite object
    public void placeSprite(Sprite s){ //place sprite in new spot
      int row = s.getRow(size);
      int col = s.getColumn(size); 
      grid[row][col] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
            //creates two varialbes oldXVal and oldYVal and temporarily assigns them to the value of -1
            int oldXVal = -1;
            int oldYVal = -1;
            // Move the sprite based on direction
            if (direction.equals("d")) {
                oldXVal = s.getX() - 1; //moving right so old x-val decrements 
                oldYVal = s.getY();
            } else if (direction.equals("w")) {
                oldXVal = s.getX();
                oldYVal = s.getY() - 1; //moving up so old y value decrements 
            } else if (direction.equals("a")) {
                oldXVal = s.getX() + 1;
                oldYVal = s.getY(); //moving left so old x-value increments
            } else if (direction.equals("s")) {
                oldXVal = s.getX();
                oldYVal = s.getY() + 1; //moving down so old y value decrements 
            }
            // Place the sprite in its new position and replaces it old position with a dot
            grid[size - 1 - oldYVal][oldXVal] = new Dot(oldXVal, oldYVal);
            placeSprite(s);
        }


    public void display() { //print out the current grid to the screen 
        for(int i = 0; i < grid.length; i++){ //iterates through row
            for(int j = 0; j < grid[0].length; j++){ //iterates through column of the grid 
                if(grid[i][j] instanceof Enemy){ //is a emeny object 
                    System.out.print("🐉"); //enemy emoji
                }else if(grid[i][j] instanceof Player){ //is a player object
                    System.out.print("👦"); //player emoji
                }else if(grid[i][j] instanceof Dot){ //is a dot object
                    System.out.print("⬜"); //dot emoji
                }else if(grid[i][j] instanceof Treasure &&  !(grid[i][j] instanceof Trophy)){ //is only a treasure object
                    System.out.print("🌈"); //treasure emoji
                }else if(grid[i][j] instanceof Trophy){ //is a trophy object
                    System.out.print("🏆"); //trophy emoji
                }
            }
            System.out.println();
        }
    }
    
    //r and c represent the x-y positon of the player when player lost
    public void gameover(int r, int c){ //use this method to display a loss
        String[][] lose = new String[grid.length][grid.length]; //creates a new arrayti display loss
        for (int i = 0; i < grid.length; i++) { //sets each value of the 2D array to 💀
            for (int j = 0; j < grid[0].length; j++) {
                lose[i][j] = "💀";
            }
        }
        lose[r][c] = "👦"; //the position where the player was previously at has the person/player emoji
        for (int row = 0; row < lose.length; row++) {
            for (int column = 0; column < lose[0].length; column++) {
                System.out.print(lose[row][column]);
            }
            System.out.println(); //prints out the lost array
        }
        System.out.println("You lose!"); //prints out the message that the player lost
    }

    public void win(){ //use this method to display a win 
        String[][] win = new String[grid.length][grid.length]; //creates a new array with same size as grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                win[i][j] = "🏆"; //sets each val to trophy emoji
            }
        }
        win[0][0] = "👦"; //sets the upper-left index to player since is the last player where the trophy also is
        for (int row = 0; row < win.length; row++) {
            for (int column = 0; column < win[0].length; column++) {
                System.out.print(win[row][column]); // prints out the new array
            }
            System.out.println();
        }
        System.out.println("You win!"); //displays the message that the player has won!
    }
}