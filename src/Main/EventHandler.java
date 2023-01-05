package Main;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;

    // Setting some kind of margin and make it so if an event happened it won't happen again
    // until player character move away from the event rectangle by one tile distance
    // preventing the event from happening repeatedly
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

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
    }

    public void checkEvent(){

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
            // Teleport event
            else if (hit(0,19,16, "left")){
                teleport(0,19, 16, gp.dialogueState);
            }
            // Hut event
            else if (hit(0,10,39,"any")){
                teleportHut(1, 12, 13);
            }
            // Leave Hut event
            else if (hit(1,12,13,"any")){
                teleportHut(0, 10, 39);
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
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
        canTouchEvent = false;
    }

    public void healingPool(int gameState){
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drink the water. \nYour life and mana has been recovered.";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            // Whenever you use the event the monsters will respawn
            gp.aSetter.setMonster();
        }
    }

    public void teleport(int map,int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;
        eventRect[map][col][row].eventDone = true;
    }

    public void teleportHut(int map, int col, int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;


        canTouchEvent = false;
        gp.playSE(13);
    }
}
