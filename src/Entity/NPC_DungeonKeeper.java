package Entity;

import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_DungeonKeeper extends Entity {

    public NPC_DungeonKeeper(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        dialogueSet = -1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        getDungeonKeeperImages();
        setDialogue();
    }

    public void getDungeonKeeperImages(){
        up1 = setup("/Resources/NPC/dungeonkeeper_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/dungeonkeeper_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/dungeonkeeper_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/dungeonkeeper_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/dungeonkeeper_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/dungeonkeeper_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/dungeonkeeper_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/dungeonkeeper_right2", gp.tileSize, gp.tileSize);
    }

    // Store character dialogues
    public void setDialogue(){
        dialogues[0][0] = "Welcome to the depths of our dungeon, adventurer.\nMay your journey be filled with treasure and glory.";
        dialogues[0][1] = "You may have noticed that the dungeon is rather dark.\nI have a solution. You can find a source of light in the chest.\nIt's a magic lantern that will light your way\nand reveal hidden secrets in the darkness.";
        dialogues[0][2] = "Adventurer, are you ready to proceed to the next level of the dungeon?\nIf so, the ladder will take you deeper into the dungeon.\nRemember, the deeper you go, the greater the challenge and rewards will\nbe. Good luck!";
    }

    public void speak(){
        facePlayer();
        startDialogue(this, 0);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }
    }

    // Character behaviour (AI kinda)
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i <= 25){direction = "up";}
            if (i > 25 && i <= 50){direction = "down";}
            if (i > 50 && i <= 75){direction = "left";}
            if (i > 75){direction = "right";}
            actionLockCounter = 0;
        }
    }
}