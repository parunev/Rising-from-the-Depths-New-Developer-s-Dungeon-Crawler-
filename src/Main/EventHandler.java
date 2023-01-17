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
        eventMaster.dialogues[0][0] = "Ouch!!!";
        eventMaster.dialogues[1][0] = "Rejuvenate your body, replenish your magic and preserve your progress.\n" +
                "Your game progress is saved!";
        eventMaster.dialogues[2][0] = "Drink from the rejuvenating pool to replenish mana and regain health!";
        eventMaster.dialogues[2][1] = "Refreshing!";
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
            // MAP0
            if (hit(0,2, 12, "any") || hit(0,2, 15, "any")
                    || hit(0,9, 14, "any") || hit(0,30, 19, "any")
                    || hit(0,31, 24, "any") || hit(0,36, 27, "any")
                    || hit(0,26, 27, "any") || hit(0,23, 29, "any")
                    || hit(0,27, 31, "any") || hit(0,25, 33, "any")
                    || hit(0,20, 33, "any") || hit(0,14, 30, "any")
                    || hit(0,11, 30, "any") || hit(0,16, 25, "any")
                    || hit(0,14, 23, "any") || hit(0,7, 22, "any")
                    || hit(0,8, 23, "any")  || hit(0,6, 26, "any")
                    || hit(0,7, 27, "any")  || hit(0,14, 30, "any")
                    || hit(0,11, 22, "any")){
                spikeHit(gp.dialogueState);
            }
            else if (hit(0,44,14,"up")){
                healingPool(gp.dialogueState); // SAVE
            }
            else if (hit(0, 4, 5, "any")) {
                teleport(0, 4, 12, gp.dungeon);
            }
            else if (hit(0, 4, 12, "any")) {
                teleport(0, 4, 5, gp.dungeon);
            }
            else if (hit(0, 46, 36, "any")) {
                teleport(0, 44, 17, gp.dungeon);
            }
            else if (hit(0, 44, 17, "any")) {
                teleport(0, 46, 36, gp.dungeon);
            }

            // MAP 1
            else if (hit(0, 44, 11, "any")) {
                teleport(1, 9, 4, gp.dungeon);
            }
            else if (hit(1, 28, 20, "up") || (hit(1, 30, 21, "up")
                    || (hit(1, 32, 21, "up")))){
                regenerate(gp.dialogueState);
            }
            else if (hit(1,42,44,"up")){
                healingPool(gp.dialogueState);
            }
            else if (hit(1, 45, 25, "any")){
                teleport(1,5,28, gp.dungeon);
            }
            else if (hit(1, 5, 28, "any")){
                teleport(1,45,25, gp.dungeon);
            }
            else if (hit(1,7, 14, "any")    || hit(1,6, 18, "any")
                    || hit(1,4, 19, "any")  || hit(1,37, 3, "any")
                    || hit(1,39, 3, "any")  || hit(1,41, 2, "any")
                    || hit(1,43, 3, "any")  || hit(1,15, 15, "any")
                    || hit(1,12, 18, "any") || hit(1,13, 20, "any")
                    || hit(1,42, 20, "any") || hit(1,42, 21, "any")
                    || hit(1,38, 27, "any") || hit(1,31, 27, "any")
                    || hit(1,21, 13, "any") || hit(1,21, 11, "any")
                    || hit(1,21, 7, "any")  || hit(1,24, 9, "any")
                    || hit(1,24, 4, "any")  || hit(1,32, 4, "any")

                    || hit(1,27, 27, "any")  || hit(1,14, 28, "any")
                    || hit(1,14, 29, "any")  || hit(1,7, 39, "any")
                    || hit(1,7, 40, "any")   || hit(1,7, 45, "any")
                    || hit(1,7, 44, "any")   || hit(1,44, 24, "any")
                    || hit(1,45, 24, "any")  || hit(1,46, 24, "any")
                    || hit(1,47, 24, "any")  || hit(1,44, 24, "any")
                    || hit(1,44, 25, "any")  || hit(1,44, 26, "any")
                    || hit(1,45, 26, "any")  || hit(1,47, 24, "any")
                    || hit(1,47, 25, "any")  || hit(1,47, 26, "any")

                    || hit(1,14, 46, "any")  || hit(1,15, 46, "any")
                    || hit(1,24, 42, "any")  || hit(1,28, 44, "any")
                    || hit(1,20, 34, "any")  || hit(1,20, 37, "any")
                    || hit(1,22, 34, "any")  || hit(1,22, 37, "any")
                    || hit(1,24, 34, "any")  || hit(1,24, 37, "any")
                    || hit(1,26, 34, "any")  || hit(1,26, 37, "any")
                    || hit(1,28, 34, "any")  || hit(1,28, 37, "any")

                    || hit(1,33, 34, "any")  || hit(1,33, 37, "any")
                    || hit(1,35, 34, "any")  || hit(1,35, 37, "any")
                    || hit(1,37, 34, "any")  || hit(1,37, 37, "any")
                    || hit(1,39, 34, "any")  || hit(1,39, 37, "any")
                    || hit(1,41, 34, "any")  || hit(1,41, 37, "any")
                    || hit(1,43, 34, "any")  || hit(1,43, 37, "any")

                    || hit(1,33, 39, "any")
                    || hit(1,35, 39, "any")
                    || hit(1,37, 39, "any")
                    || hit(1,39, 39, "any")
                    || hit(1,41, 39, "any")
                    || hit(1,43, 39, "any")
                    ){

                spikeHit(gp.dialogueState);
            }
        }
    }

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

    public void spikeHit(int gameState){
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

    public void regenerate(int gameState) {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 2);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
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
}
