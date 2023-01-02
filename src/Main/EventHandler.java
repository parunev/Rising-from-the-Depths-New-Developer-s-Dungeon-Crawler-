package Main;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;

    // Setting some kind of margin and make it so if an event happened it won't happen again
    // until player character move away from the event rectangle by one tile distance
    // preventing the event from happening repeatedly
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        // Event Rectangle on every single tile basically
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            // 2x2 rectangle (middle of the tile)
            // The reason we chose it small it's because we want the event to be triggered when the player go a bit
            // further into the tile
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row++;
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
            if (hit(27, 16, "right")){ // Map column, row and direction
                damagePit(27,16, gp.dialogueState);
            }
            // Drinking water event
            if (hit(23,12,"up")){
                healingPool(23, 12, gp.dialogueState);
            }
            // Teleport event
            if (hit(19,16, "left")){
                teleport(19, 16, gp.dialogueState);
            }
        }
    }

    // This is the method that checks the event collision
    // Works pretty similar to object collision
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        // Getting players current solidArea positions
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        // Getting eventRect solidArea positions
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        // Using intersects method again to check if players is colliding
        // Checking if player's solidArea is colliding with eventRect's solidArea
        // eventDone - the event only happens when it's false
        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
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
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState){
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

    public void teleport(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;
        eventRect[col][row].eventDone = true;
    }
}
