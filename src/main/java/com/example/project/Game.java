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
        boolean toPlay = true;
        while(toPlay && player.getLives() > 0) {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            System.out.println();
            grid.display();
            System.out.println("Number of Lives: " + player.getLives());
            System.out.println("Player Coordinate: " + player.getCoords());
            System.out.println("Player Coordinate: " + player.getRowCol(size));
            System.out.println("Number of treasures needed: " + treasures.length);
            System.out.println("Number of treasures acquired: " + player.getTreasureCount());
            System.out.println("Enter direction (w/a/s/d): ");
            String direction = scanner.nextLine().toLowerCase();
            if (direction.equals("w") || direction.equals("a") || 
               direction.equals("s") || direction.equals("d")) {
                if(player.isValid(size, direction)) {
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
                    Sprite sprite = grid.getGrid()[size-1-newY][newX];
                    player.interact(size, direction, treasures.length, sprite);
                    if(sprite instanceof Treasure && !(sprite instanceof Trophy)) {
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Dot){
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Enemy){
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Trophy) {
                        player.move(direction);
                        grid.placeSprite(player, direction);
                        if(player.getWin()){
                            clearScreen();
                            grid.win();
                            toPlay = false;
                        } else {
                            System.out.println("Collect more treasures!");
                        }
                    }
                }
            }
        }   
        if(player.getLives() <= 0) {
            clearScreen();
            int column = player.getColumn(size);
            int row = player.getRow(size);
            grid.gameover(row, column);
        }
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        player = new Player(0, 0);
        trophy = new Trophy(0, size - 1); 
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        if (size == 6) {
            player.setLives(1);
            treasures = new Treasure[6];
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
        } else if (size == 10) {
            player.setLives(2);
            treasures = new Treasure[4];
            treasures[0] = new Treasure(8, 2);
            treasures[1] = new Treasure(4, 5);
            treasures[2] = new Treasure(5, 8);
            treasures[3] = new Treasure(1, 6);
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);
            grid.placeSprite(treasures[2]);
            grid.placeSprite(treasures[3]);
            enemies = new Enemy [4];
            enemies[0] = new Enemy(3,6);
            enemies[1] = new Enemy(3, 3);
            enemies[2] = new Enemy(0, 8);
            enemies[3] = new Enemy(8, 6);
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);
            grid.placeSprite(enemies[2]);
            grid.placeSprite(enemies[3]);
        } else if (size == 14) {
            treasures = new Treasure[2];
            enemies = new Enemy [2];
            player.setLives(2);
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
        boolean again = true;
        Scanner scanner = new Scanner(System.in);
        mainMethod();
        while (again == true) {
            System.out.println("Would you like to play again? y(yes) or n(no)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("y")) {
                mainMethod();
            } else if (response.equals("n")) {
                again = false;
                System.out.println("Thanks for Playing!");
            } else {
                System.out.println("INVALID RESPONSE!");
            }
        }
        scanner.close(); 
    }
}