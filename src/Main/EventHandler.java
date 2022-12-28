package Main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        // 2x2 rectangle (middle of the tile)
        // The reason we chose it small it's because we want the event to be triggered when the player go a bit
        // further into the tile
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){

        // Damage pit event
        if (hit(27, 16, "right")){ // Map column, row and direction
            damagePit(gp.dialogueState);
        }
        // Drinking water event
        if (hit(23,12,"up")){
            healingPool(gp.dialogueState);
        }
        // Teleport event
        if (hit(19,16, "left")){
            teleport(gp.dialogueState);
        }
    }

    // This is the method that checks the event collision
    // Works pretty similar to object collision
    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        // Getting players current solidArea positions
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        // Getting eventRect solidArea positions
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        // Using intersects method again to check if players is colliding
        // Checking if player's solidArea is colliding with eventRect's solidArea
        if (gp.player.solidArea.intersects(eventRect)){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        // After checking the collision reset the solidArea x and y
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
    }

    public void healingPool(int gameState){
        if (gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. \nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;
    }
}
