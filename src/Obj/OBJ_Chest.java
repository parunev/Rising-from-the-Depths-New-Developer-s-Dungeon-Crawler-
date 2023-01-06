package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Chest extends Entity {
    GamePanel gp;
    Entity loot;
    boolean opened = false;
    public OBJ_Chest(GamePanel gp, Entity loot){
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "Chest";
        image = setup("/Resources/Objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/Resources/Objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact(){
        gp.gameState = gp.dialogueState;

        if (!opened){
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You opened a chest and find a ").append(loot.name).append("!");

            if (gp.player.inventory.size() == gp.player.maxInventorySize){
                sb.append("\n... But you cannot carry any more!");
            }else{
                sb.append("\nYou obtained the ").append(loot.name).append("!");
                gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }

            gp.ui.currentDialogue = sb.toString();

        } else {
            gp.ui.currentDialogue = "It's empty!";
        }
    }
}
