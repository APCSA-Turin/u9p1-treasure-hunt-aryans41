package com.example.project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestGrid2 {
    
    private Grid grid;
    private Player player;
    private Enemy enemy;
    private Enemy enemy2;
    private Treasure treasure;
    private Treasure treasure2;
    private Trophy trophy;

    @BeforeEach
    public void setUp() {
        int size = 6; // Grid size set to 6
        grid = new Grid(size);
        player = new Player(0, 0);  // (x=0, y=5) -> row =  = 5, col = 0
        enemy = new Enemy(3, 3);    // (x=3, y=3) -> row =  = 2, col = 3
        enemy2 = new Enemy(4, 4);   // (x=4, y=4) -> row =  = 1, col = 4
        treasure2 = new Treasure(2, 1); // (x=2, y=1) -> row = 4, col = 2
        treasure = new Treasure(1, 0); // (x=1, y=0) -> row = 5, col = 1
        trophy = new Trophy(5, 5); // (x=5, y=5) -> row = 0, col = 5

        // Visual Grid (rows x columns):
        // Row 0: [ ][ ][ ][ ][ ][W]
        // Row 1: [ ][ ][ ][ ][E][ ]
        // Row 2: [ ][ ][ ][E][ ][ ]
        // Row 3: [ ][ ][ ][ ][ ][ ]
        // Row 4: [ ][T][ ][ ][ ][ ]
        // Row 5: [P][T][ ][ ][ ][ ]
        //         0  1  2  3  4  5

        // Place objects on the grid using the translated positions
        grid.placeSprite(player);
        grid.placeSprite(enemy);
        grid.placeSprite(enemy2);
        grid.placeSprite(treasure);
        grid.placeSprite(treasure2);
        grid.placeSprite(trophy);
    }


    @Test
    public void testPlayerInteractWithTreasureUpdatesGridAndWIN() {
             // Visual Grid (rows x columns):
        // Row 0: [ ][ ][ ][ ][ ][W]
        // Row 1: [ ][ ][ ][ ][E][ ]
        // Row 2: [ ][ ][ ][E][ ][ ]
        // Row 3: [ ][ ][ ][ ][ ][ ]
        // Row 4: [ ][T][ ][ ][ ][ ]
        // Row 5: [P][T][ ][ ][ ][ ]
        //         0  1  2  3  4  5

        // Move the player to the treasure's position
        player.interact(6,"d",2,treasure);
        player.move("d"); //[5][1]
        grid.placeSprite(player);

        assertEquals(1,player.getTreasureCount(), "Player should have collected a treasure (1)");
        assertEquals(player, grid.getGrid()[5][1],"Player shold be at [5][1]");

        player.interact(6,"w",2,treasure2);
        player.move("w"); //[4][1]
        grid.placeSprite(player);

        assertEquals(2,player.getTreasureCount(), "Player should have collected a treasure (2)");
        assertEquals(player, grid.getGrid()[4][1],"Player shold be at [4][1]");


        for(int i = 0;i<4;i++){
            player.move("w"); //[0][1]
        }

        for(int i = 0;i<3;i++){
            player.move("d");//[0][4]
        }

        

        player.interact(6,"d",2,trophy);
        player.move("d"); 
        grid.placeSprite(player); //[0][5]
        
        assertTrue(player.getWin(), "Player should win after interacting with trophy");
        assertEquals(player, grid.getGrid()[0][5], "Player should be at [0][5]");
    }
} 