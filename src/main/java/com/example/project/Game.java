package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;
    private Scanner scanner;

    public Game(int size){ //the constructor should call initialize() and play() 
        //creates a game object and initializes the Grid and calls play method
        this.scanner = new Scanner(System.in);
        this.size = size;
        this.grid = new Grid(size);
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { //write your game logic here
        boolean toPlay = true; //does the player want to play?
        while(toPlay && player.getLives() > 0) { //continue as long as player wants to play and has lives
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            System.out.println();
            grid.display(); //displays the grid
            //displays the stats: lives, corrdinates, num of treasures
            System.out.println("Number of Lives: " + player.getLives());
            System.out.println("Player Coordinate: " + player.getCoords());
            System.out.println("Player Coordinate: " + player.getRowCol(size));
            System.out.println("Number of treasures needed: " + treasures.length);
            System.out.println("Number of treasures acquired: " + player.getTreasureCount());
            //asks the player to enter a direction for the player object to move
            System.out.println("Enter direction (w/a/s/d): ");
            //stores the input in a string variable called direction
            String direction = scanner.nextLine().toLowerCase();
            if (direction.equals("w") || direction.equals("a") || 
               direction.equals("s") || direction.equals("d")) {
                if(player.isValid(size, direction)) { //if the direction is valid and the player can move then move the player
                    //moves the player based on direction by altering its x-y coordinates
                    int newX = player.getX();
                    int newY = player.getY();
                    if(direction.equals("w")) {
                        newY++;
                    }
                    if(direction.equals("a")) {
                        newX--;
                    }
                    if(direction.equals("s")) { 
                        newY--;
                    }
                    if(direction.equals("d")) {
                        newX++;
                    } 
                    //checks the sprite already located in the direction the player is moving(ex enemy or treasure)
                    Sprite sprite = grid.getGrid()[size-1-newY][newX];
                    player.interact(size, direction, treasures.length, sprite); //interacts appropriately with the sprite
                    if(sprite instanceof Treasure && !(sprite instanceof Trophy)) { //just treasure
                        player.move(direction); //player object moves and the player is placed in that new postion 
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Dot){
                        player.move(direction); //player object moves and is placed in the new position simply
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Enemy){
                        player.move(direction); //player onject moves and is placed in the position
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Trophy) {
                        player.move(direction); //player object moves and is placed in the position
                        grid.placeSprite(player, direction);
                        if(player.getWin()){ //since player is at trophy, we have to check if player wins 
                            clearScreen(); //if so display the win and set toPlay to false
                            grid.win();
                            toPlay = false;
                        } else {
                            System.out.println("Collect more treasures!"); //not sufficient treasures acquired
                        }
                    }
                }
            }
        }   
        if(player.getLives() <= 0) {
            clearScreen();
            //stores the recent position of the player
            int column = player.getColumn(size);
            int row = player.getRow(size);
            grid.gameover(row, column); //if the player loses all lives aka lives at 0 then the loss is displayed through the gameover method
        }
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        //creates a player placed on the bottom left, the trophy that is on the top-left and places them on the grid
        player = new Player(0, 0);
        trophy = new Trophy(0, size - 1); 
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        if (size == 6) { //this is for the hard method with a smaller challenging grid
            player.setLives(1); //player only has 1 live
            treasures = new Treasure[6]; //there are more treasures to aquire
            //creates and places the treasurs on the grid
            treasures[0] = new Treasure(1, 3);
            treasures[1] = new Treasure(3, 1);
            treasures[2] = new Treasure(4, 4);
            treasures[3] = new Treasure(2, 5);
            treasures[4] = new Treasure(5, 0);
            treasures[5] = new Treasure(2, 2);
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);
            grid.placeSprite(treasures[2]);
            grid.placeSprite(treasures[3]);
            grid.placeSprite(treasures[4]);
            grid.placeSprite(treasures[5]);
            // there are more enemies
            //creates and places the enemies on the grid 
            enemies = new Enemy[6];
            enemies[0] = new Enemy(1,1);
            enemies[1] = new Enemy(3, 3);
            enemies[2] = new Enemy(0, 4);
            enemies[3] = new Enemy(2, 0);
            enemies[4] = new Enemy(4, 2);
            enemies[5] = new Enemy(2, 4);
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);
            grid.placeSprite(enemies[2]);
            grid.placeSprite(enemies[3]);
            grid.placeSprite(enemies[4]);
            grid.placeSprite(enemies[5]);
        } else if (size == 10) { //medium difficulty a little larger grid, some obstances
            player.setLives(2); //2 lives
            treasures = new Treasure[4];
            //creates 4 treasures and places them on the grid
            treasures[0] = new Treasure(8, 2);
            treasures[1] = new Treasure(4, 5);
            treasures[2] = new Treasure(5, 8);
            treasures[3] = new Treasure(1, 6);
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);
            grid.placeSprite(treasures[2]);
            grid.placeSprite(treasures[3]);
            //creates 4 enemies and places them on the grid
            enemies = new Enemy [4];
            enemies[0] = new Enemy(3,6);
            enemies[1] = new Enemy(3, 3);
            enemies[2] = new Enemy(0, 8);
            enemies[3] = new Enemy(8, 6);
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);
            grid.placeSprite(enemies[2]);
            grid.placeSprite(enemies[3]);
        } else if (size == 14) { //easy mode, large grid, few things to aquire less enemies
            treasures = new Treasure[2]; //only two treasures to get
            enemies = new Enemy [2]; //only 2 enemies
            player.setLives(2); //two lives
            //creates the enemies and treasures and places them on the grid
            treasures[0] = new Treasure(2, 10);
            treasures[1] = new Treasure(6, 12);
            enemies[0] = new Enemy(5, 4);
            enemies[1] = new Enemy(10, 9);
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);
        }
    }

    public static void mainMethod() {
        //asks the user the difficulty that they would like to play in and creates a game object corresponding to that difficulty and its grid size
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please indicate the difficulty that you would like to play in. 1 for hard, 2 for medium, 3 for easy"); 
        int difficulty = scanner.nextInt(); 
        if (difficulty == 1) {
            Game myGame = new Game(6);
        } else if (difficulty == 2) {
            Game myGame = new Game(10);
        } else if (difficulty == 3) {
            Game myGame = new Game(14);
        }
    }

    public static void main(String[] args) {
        boolean again = true; //does the player desire to play again
        Scanner scanner = new Scanner(System.in);
        mainMethod(); //difficuly and creates the game object
        while (again == true) { //while player wants to play, repeat. the player can change the difficulty
            System.out.println("Would you like to play again? y(yes) or n(no)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("y")) {
                mainMethod();
            } else if (response.equals("n")) {
                again = false;
                System.out.println("Thanks for Playing!"); //again is false player does not want to play anymore
            } else {
                System.out.println("INVALID RESPONSE!"); // a response other than a "y" or "n"
            }
        }
        scanner.close(); 
    }
}