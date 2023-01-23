package Main;

import Entity.Entity;

import java.io.IOException;
import java.util.Random;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventMaster = new Entity(gp);
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

    // SETUP
    public void setDialogue(){
        eventMaster.dialogues[0][0] = "Ouch!";
        eventMaster.dialogues[1][0] = "I press on, despite the spike's injury.";
        eventMaster.dialogues[2][0] = "Spikes hurt.";

        eventMaster.dialogues[4][0] = "Rejuvenate your body, replenish your magic and preserve your progress.\n" +
                "Your game progress is saved!";

        eventMaster.dialogues[5][0] = "Drink from the rejuvenating pool to replenish mana and regain health!";
        eventMaster.dialogues[5][1] = "Refreshing!";

        eventMaster.dialogues[6][0] = "Drink from the rejuvenating pool to regain health!";
        eventMaster.dialogues[6][1] = "Ah, much better!";
    }
    public void checkEvent() throws IOException {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            switch (gp.currentMap) {
                case 0 -> startMap();
                case 1 -> firstMap();
                case 2 -> secondMap();
                case 3 -> thirdMap();
                case 4 -> bossMap();
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

    // EVENTS
    public void spikeHit(int gameState){
        gp.gameState = gameState;

        Random r = new Random();
        int index = r.nextInt(3);
        switch (index) {
            case 0 -> eventMaster.startDialogue(eventMaster, 0);
            case 1 -> eventMaster.startDialogue(eventMaster, 1);
            case 2 -> eventMaster.startDialogue(eventMaster, 2);
        }
        gp.player.life--;
        canTouchEvent = false;
    }
    public void healingPool(int gameState) throws IOException {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(30);
            eventMaster.startDialogue(eventMaster, 4);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            // Whenever you use the event the monsters will respawn
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }
    public void regenerate(int gameState, int map, int col, int row) {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 5);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            eventRect[map][col][row].eventDone = true;
        }
    }
    public void regenerate_health(int gameState, int map, int col, int row) {
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 6);
            gp.player.life += 3;
            eventRect[map][col][row].eventDone = true;
        }
    }
    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;

        canTouchEvent = false;
        gp.playSE(29);
    }
    public void boss_Lyuborge(){
        if (!gp.bossBattleOn){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.lyuborge;
        }
    }

    // MAPS
    public void startMap() throws IOException {
        if (hit(0, 2, 12, "any") || hit(0, 2, 15, "any")
                || hit(0, 9, 14, "any") || hit(0, 30, 19, "any")
                || hit(0, 31, 24, "any") || hit(0, 36, 27, "any")
                || hit(0, 26, 27, "any") || hit(0, 23, 29, "any")
                || hit(0, 27, 31, "any") || hit(0, 25, 33, "any")
                || hit(0, 20, 33, "any") || hit(0, 14, 30, "any")
                || hit(0, 11, 30, "any") || hit(0, 16, 25, "any")
                || hit(0, 14, 23, "any") || hit(0, 7, 22, "any")
                || hit(0, 8, 23, "any") || hit(0, 6, 26, "any")
                || hit(0, 7, 27, "any") || hit(0, 14, 30, "any")
                || hit(0, 11, 22, "any")) {
            spikeHit(gp.dialogueState);
        } else if (hit(0, 44, 14, "up")) {
            healingPool(gp.dialogueState); // SAVE
        } else if (hit(0, 4, 5, "any")) {
            teleport(0, 4, 12);
        } else if (hit(0, 4, 12, "any")) {
            teleport(0, 4, 5);
        } else if (hit(0, 46, 36, "any")) {
            teleport(0, 44, 17);
        } else if (hit(0, 44, 17, "any")) {
            teleport(0, 46, 36);
        } else if ((hit(0, 44, 11, "any"))) {
            teleport(1, 9, 4);
        }
    }
    public void firstMap() throws IOException {
        if (hit(1, 32, 21, "up")) {
            regenerate(gp.dialogueState, 1, 32, 21);
        } else if (hit(1, 42, 44, "up")) {
            healingPool(gp.dialogueState);
        } else if (hit(1, 9, 19, "any")) {
            regenerate_health(gp.dialogueState, 1, 9, 19);
        } else if (hit(1, 38, 33, "any")) {
            regenerate_health(gp.dialogueState, 1, 38, 33);
        } else if (hit(1, 45, 25, "any")) {
            teleport(1, 5, 28);
        } else if (hit(1, 5, 28, "any")) {
            teleport(1, 45, 25);
        } else if (hit(1, 7, 14, "any") || hit(1, 6, 18, "any")
                || hit(1, 4, 19, "any") || hit(1, 37, 3, "any")
                || hit(1, 39, 3, "any") || hit(1, 41, 2, "any")
                || hit(1, 43, 3, "any") || hit(1, 15, 15, "any")
                || hit(1, 12, 18, "any") || hit(1, 13, 20, "any")
                || hit(1, 42, 20, "any") || hit(1, 42, 21, "any")
                || hit(1, 38, 27, "any") || hit(1, 31, 27, "any")
                || hit(1, 21, 13, "any") || hit(1, 21, 11, "any")
                || hit(1, 21, 7, "any") || hit(1, 24, 9, "any")
                || hit(1, 24, 4, "any") || hit(1, 32, 4, "any")

                || hit(1, 27, 27, "any") || hit(1, 14, 28, "any")
                || hit(1, 14, 29, "any") || hit(1, 7, 39, "any")
                || hit(1, 7, 40, "any") || hit(1, 7, 43, "any")
                || hit(1, 7, 44, "any") || hit(1, 44, 24, "any")
                || hit(1, 45, 24, "any") || hit(1, 46, 24, "any")
                || hit(1, 47, 24, "any") || hit(1, 44, 24, "any")
                || hit(1, 44, 25, "any") || hit(1, 44, 26, "any")
                || hit(1, 45, 26, "any") || hit(1, 47, 24, "any")
                || hit(1, 47, 25, "any") || hit(1, 47, 26, "any")

                || hit(1, 14, 46, "any") || hit(1, 15, 46, "any")
                || hit(1, 24, 42, "any") || hit(1, 28, 44, "any")
                || hit(1, 20, 34, "any") || hit(1, 20, 37, "any")
                || hit(1, 22, 34, "any") || hit(1, 22, 37, "any")
                || hit(1, 24, 34, "any") || hit(1, 24, 37, "any")
                || hit(1, 26, 34, "any") || hit(1, 26, 37, "any")
                || hit(1, 28, 34, "any") || hit(1, 28, 37, "any")

                || hit(1, 33, 34, "any") || hit(1, 33, 37, "any")
                || hit(1, 35, 34, "any") || hit(1, 35, 37, "any")
                || hit(1, 37, 34, "any") || hit(1, 37, 37, "any")
                || hit(1, 39, 34, "any") || hit(1, 39, 37, "any")
                || hit(1, 41, 34, "any") || hit(1, 41, 37, "any")
                || hit(1, 43, 34, "any") || hit(1, 43, 37, "any")

                || hit(1, 33, 39, "any")
                || hit(1, 35, 39, "any")
                || hit(1, 37, 39, "any")
                || hit(1, 39, 39, "any")
                || hit(1, 41, 39, "any")
                || hit(1, 43, 39, "any")
        ) {
            spikeHit(gp.dialogueState);
        } else if (hit(1, 42, 45, "any")) {
            teleport(2, 9, 4);
        }
    }
    public void secondMap() throws IOException {
        if (hit(2, 21, 35, "up")) {
            healingPool(gp.dialogueState);
        } else if (hit(2, 5, 3, "any") || hit(2, 11, 5, "any") ||
                hit(2, 13, 6, "any") || hit(2, 23, 38, "any") ||
                hit(2, 18, 33, "any") || hit(2, 33, 21, "any") ||
                hit(2, 35, 21, "any") || hit(2, 46, 32, "any") ||
                hit(2, 34, 33, "any")) {
            spikeHit(gp.dialogueState);
        } else if (hit(2, 13, 36, "any")) {
            teleport(3, 9, 3);
        }
    }
    public void thirdMap() throws IOException {
         if (hit(3, 4, 2, "any")) {
            teleport(3, 26, 2);
        } else if (hit(3, 26, 2, "any")) {
            teleport(3, 4, 2);
        } else if (hit(3, 14, 2, "any")) {
            teleport(3, 43, 2);
        } else if (hit(3, 43, 2, "any")) {
            teleport(3, 14, 2);
        } else if (hit(3, 5, 32, "any")) {
            teleport(3, 8, 32);
        } else if (hit(3, 8, 32, "any")) {
            teleport(3, 5, 32);
        } else if (hit(3, 9, 9, "any") || hit(3, 39, 42, "any")) {
            healingPool(gp.dialogueState);
        } else if (hit(3, 21, 46, "any")) {
            regenerate(gp.dialogueState, 3, 21, 46);
        } else if (hit(3, 24, 7, "any") || hit(3, 28, 7, "any")
                || hit(3, 23, 8, "any") || hit(3, 29, 8, "any")
                || hit(3, 23, 12, "any") || hit(3, 29, 12, "any")
                || hit(3, 24, 13, "any") || hit(3, 28, 13, "any")
                || hit(3, 24, 20, "any") || hit(3, 30, 21, "any")
                || hit(3, 23, 21, "any") || hit(3, 29, 22, "any")
                || hit(3, 21, 23, "any") || hit(3, 29, 27, "any")
                || hit(3, 24, 25, "any") || hit(3, 30, 26, "any")
                || hit(3, 25, 27, "any") || hit(3, 44, 8, "any")
                || hit(3, 25, 24, "any") || hit(3, 42, 10, "any")
                || hit(3, 26, 23, "any") || hit(3, 40, 12, "any")
                || hit(3, 27, 24, "any") || hit(3, 46, 13, "any")
                || hit(3, 26, 25, "any") || hit(3, 41, 20, "any")
                || hit(3, 39, 29, "any") || hit(3, 46, 26, "any")
                || hit(3, 39, 21, "any") || hit(3, 47, 25, "any")
                || hit(3, 40, 24, "any") || hit(3, 44, 30, "any")
                || hit(3, 39, 27, "any") || hit(3, 44, 32, "any")
                || hit(3, 41, 26, "any") || hit(3, 36, 33, "any")
                || hit(3, 42, 22, "any") || hit(3, 36, 35, "any")
                || hit(3, 43, 21, "any") || hit(3, 45, 40, "any")
                || hit(3, 44, 22, "any") || hit(3, 45, 41, "any")
                || hit(3, 43, 23, "any") || hit(3, 44, 43, "any")
                || hit(3, 47, 22, "any") || hit(3, 30, 43, "any")
                || hit(3, 48, 21, "any") || hit(3, 32, 46, "any")
                || hit(3, 40, 24, "any") || hit(3, 32, 39, "any")
                || hit(3, 45, 25, "any") || hit(3, 30, 37, "any")
                || hit(3, 32, 35, "any")
                || hit(3, 26, 33, "any") || hit(3, 6, 20, "any")
                || hit(3, 27, 33, "any") || hit(3, 8, 21, "any")
                || hit(3, 27, 36, "any") || hit(3, 9, 21, "any")
                || hit(3, 26, 40, "any") || hit(3, 12, 20, "any")
                || hit(3, 27, 42, "any") || hit(3, 13, 20, "any")
                || hit(3, 27, 43, "any") || hit(3, 16, 22, "any")
                || hit(3, 26, 47, "any") || hit(3, 14, 24, "any")
                || hit(3, 18, 46, "any") || hit(3, 14, 25, "any")
                || hit(3, 17, 46, "any") || hit(3, 12, 26, "any")
                || hit(3, 5, 47, "any") || hit(3, 8, 27, "any")
                || hit(3, 3, 47, "any") || hit(3, 7, 27, "any")
                || hit(3, 4, 44, "any") || hit(3, 10, 39, "any")
                || hit(3, 4, 43, "any") || hit(3, 7, 38, "any")
                || hit(3, 3, 40, "any") || hit(3, 6, 38, "any")
                || hit(3, 3, 37, "any") || hit(3, 15, 31, "any")
                || hit(3, 5, 35, "any") || hit(3, 15, 32, "any")
                || hit(3, 5, 34, "any") || hit(3, 15, 36, "any")
                || hit(3, 3, 30, "any") || hit(3, 14, 38, "any")
                || hit(3, 3, 29, "any") || hit(3, 16, 41, "any")
                || hit(3, 3, 26, "any") || hit(3, 14, 43, "any")
                || hit(3, 4, 23, "any") || hit(3, 18, 38, "any")
                || hit(3, 4, 22, "any") || hit(3, 20, 39, "any")
                || hit(3, 22, 38, "any") || hit(3, 23, 38, "any")
                || hit(3, 20, 32, "any") || hit(3, 21, 32, "any")
                || hit(3, 21, 33, "any")) {
            spikeHit(gp.dialogueState);
        } else if (hit(3, 39, 48, "down")) {
             teleport(4, 4, 3);
         }
    }
    public void bossMap() throws IOException {
        if (hit(4, 3, 7, "any")) {
            teleport(4, 3, 9);
        } else if (hit(4,4,17, "down")) {
            teleport(4, 4, 19);
        } else if (hit(4, 4, 27, "down")) {
            teleport(4, 4, 29);
        } else if (hit(4, 4, 37, "down")) {
            teleport(4, 4, 39);
        } else if (hit(4, 8, 43, "right")) {
            teleport(4, 10, 43);
        } else if (hit(4, 14, 39, "up")) {
            teleport(4, 14, 37);
        } else if (hit(4, 14, 29, "up")) {
            teleport(4, 14, 27);
        } else if (hit(4, 14, 19, "up")) {
            teleport(4, 14, 17);
        } else if (hit(4, 14, 9, "up")) {
            teleport(4, 14, 7);
        } else if (hit(4, 18, 3, "right")){
            teleport(4, 20, 3);
        } else if (hit(4, 24, 7, "down")){
            teleport(4, 24, 9);
        } else if (hit(4, 24, 17, "down")){
            teleport(4, 24, 19);
        } else if (hit(4, 24, 27, "down")){
            teleport(4, 24, 29);
        } else if (hit(4, 24, 37, "down")){
            teleport(4, 24, 39);
        } else if (hit(4, 28, 43, "right")){
            teleport(4, 30, 43);
        } else if (hit(4, 38, 43, "right")){
            teleport(4, 40, 43);
        } else if (hit(4, 44, 42, "up")){
            teleport(4, 39, 30);

        } else if (hit(4, 2, 14, "any") || hit(4, 4, 14, "any") ||
                hit(4, 6, 14, "any") || hit(4, 5, 15, "any") ||
                hit(4, 4, 24, "any") || hit(4, 1, 25, "any") ||
                hit(4, 7, 25, "any") || hit(4, 3, 31, "any") ||
                hit(4, 4, 31, "any") || hit(4, 5, 31, "any") ||
                hit(4, 2, 33, "any") || hit(4, 3, 33, "any") ||
                hit(4, 2, 35, "any") || hit(4, 3, 35, "any") ||
                hit(4, 3, 40, "any") || hit(4, 3, 41, "any") ||
                hit(4, 5, 40, "any") || hit(4, 5, 41, "any") ||
                hit(4, 3, 43, "any") || hit(4, 4, 43, "any") ||
                hit(4, 5, 43, "any") || hit(4, 1, 45, "any") ||
                hit(4, 2, 45, "any") || hit(4, 3, 45, "any") ||
                hit(4, 4, 45, "any") || hit(4, 12, 35, "any") ||
                hit(4, 16, 35, "any") || hit(4, 16, 34, "any") ||
                hit(4, 15, 34, "any") || hit(4, 13, 34, "any") ||
                hit(4, 13, 33, "any") || hit(4, 15, 33, "any") ||
                hit(4, 11, 20, "any") || hit(4, 12, 21, "any") ||
                hit(4, 15, 21, "any") || hit(4, 16, 21, "any") ||
                hit(4, 11, 15, "any") || hit(4, 12, 15, "any") ||
                hit(4, 13, 15, "any") || hit(4, 15, 15, "any") ||
                hit(4, 15, 14, "any") || hit(4, 11, 11, "any") ||
                hit(4, 11, 10, "any") || hit(4, 12, 4, "any") ||
                hit(4, 16, 4, "any") || hit(4, 21, 2, "any") ||
                hit(4, 24, 3, "any") || hit(4, 26, 3, "any") ||
                hit(4, 22, 4, "any") || hit(4, 25, 4, "any") ||
                hit(4, 21, 5, "any") || hit(4, 23, 5, "any") ||
                hit(4, 26, 5, "any") || hit(4, 22, 11, "any") ||
                hit(4, 24, 11, "any") || hit(4, 26, 11, "any") ||
                hit(4, 23, 12, "any") || hit(4, 25, 12, "any") ||
                hit(4, 22, 13, "any") || hit(4, 24, 13, "any") ||
                hit(4, 26, 13, "any") || hit(4, 21, 23, "any") ||
                hit(4, 23, 23, "any") || hit(4, 25, 23, "any") ||
                hit(4, 27, 23, "any") || hit(4, 25, 30, "any") ||
                hit(4, 26, 30, "any") || hit(4, 26, 31, "any") ||
                hit(4, 27, 31, "any") || hit(4, 27, 32, "any") ||
                hit(4, 22, 40, "any") || hit(4, 21, 45, "any") ||
                hit(4, 39, 29, "any") || hit(4, 38, 29, "any") ||
                hit(4, 37, 28, "any") || hit(4, 36, 28, "any") ||
                hit(4, 35, 28, "any") || hit(4, 42, 28, "any") ||
                hit(4, 43, 28, "any") || hit(4, 44, 28, "any") ||
                hit(4, 40, 27, "any") || hit(4, 41, 27, "any") ||
                hit(4, 38, 23, "any") || hit(4, 40, 23, "any") ||
                hit(4, 41, 21, "any") || hit(4, 37, 21, "any") ||
                hit(4, 38, 20, "any") || hit(4, 40, 20, "any") ||
                hit(4, 38, 17, "any") || hit(4, 40, 17, "any")) {
            spikeHit(gp.dialogueState);

        } else if (hit(4, 6, 5, "any") || hit(4, 15, 44, "any") ||
                hit(4, 47, 43, "any") || hit(4, 38, 9, "any")){
            healingPool(gp.dialogueState);
        } else if (hit(4, 14, 4, "any") || hit(4, 24, 44, "any")){
            regenerate_health(gp.dialogueState,4,14,4);
        } else if (hit(4, 39, 30, "any")){
            boss_Lyuborge();
        }
    }
}
