package Main;

import Entity.Entity;

import java.io.IOException;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;

    // Setting some kind of margin and make it so if an event happened it won't happen again
    // until player character move away from the event rectangle by one tile distance
    // preventing the event from happening repeatedly
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventMaster = new Entity(gp);

        // Event Rectangle on every single tile basically
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            // 2x2 rectangle (middle of the tile)
            // The reason we chose it small it's because we want the event to be triggered when the player go a bit
            // further into the tile
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row++;

                if (row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue(){
        eventMaster.dialogues[0][0] = "You fall into a pit!";
        eventMaster.dialogues[1][0] = """
                    You drink the water.\s
                    Your life and mana has been recovered.
                    (The progress has been saved)""";

        eventMaster.dialogues[1][1] = "Refreshing!";
    }

    public void checkEvent() throws IOException {

        //Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent){
            // Damage pit event
            if (hit(0,27, 16, "right")){ // Map, Map column, Map row and direction
                damagePit(gp.dialogueState);
            }
            // Drinking water event
            else if (hit(0,23,12,"up")){
                healingPool(gp.dialogueState);
            }
            // To the Merchant
            else if (hit(0,10,39,"any")){
                teleport(1, 12, 13, gp.indoor);
            }
            // To leave the Merchant
            else if (hit(1,12,13,"any")){
                teleport(0, 10, 39, gp.outside);
            }
            // Speak to the merchant
            else if (hit(1,12,9,"up")){
                speak(gp.npc[1][0]);
            }
            // Entering the dungeon
            else if (hit(0, 12, 9, "any")) {
                teleport(2, 9, 41, gp.dungeon);
            }
            // Leaving the dungeon
            else if (hit(2, 9, 41, "any")){
                teleport(0, 12, 9, gp.outside);
            }
            // To the second dungeon floor
            else if (hit(2, 8, 7, "any")){
                teleport(3, 26, 41, gp.dungeon);
            }
            // To the first dungeon floor
            else if (hit(3, 26, 41, "any")){
                teleport(2, 8, 7, gp.dungeon);
            }
        }
    }

    // This is the method that checks the event collision
    // Works pretty similar to object collision
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;
        if (map == gp.currentMap){
            // Getting players current solidArea positions
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            // Getting eventRect solidArea positions
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            // Using intersects method again to check if players is colliding
            // Checking if player's solidArea is colliding with eventRect's solidArea
            // eventDone - the event only happens when it's false
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    // Based on this information we check the distance between character and last event
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            // After checking the collision reset the solidArea x and y
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life--;
        canTouchEvent = false;
    }

    public void healingPool(int gameState) throws IOException {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            // Whenever you use the event the monsters will respawn
            gp.aSetter.setMonster();

            gp.saveLoad.save();
        }
    }

    public void teleport(int map, int col, int row, int area){
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;

        canTouchEvent = false;
        gp.playSE(13);
    }

    public void speak(Entity entity){
        if (gp.keyH.enterPressed){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
}
