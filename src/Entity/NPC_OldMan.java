package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getOldManImage();
        setDialogue();
    }

    public void getOldManImage(){
        up1 = setup("/Resources/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    // Store character dialogues
    public void setDialogue(){
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
    }

    // Character behaviour (AI kinda)
    public void setAction(){

        // For every 120 frames the NPC will move. This can be adjusted.
        actionLockCounter++;
        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // if the bound is just 100 it becomes 0 to 99, that's why I added +1

            if (i <= 25){ // 25% of the time it goes up
                direction = "up";
            }
            if (i > 25 && i <= 50){ // 25% of the time it goes down
                direction = "down";
            }
            if (i > 50 && i <= 75){ // 25% of the time it goes left
                direction = "left";
            }
            if (i > 75){ // 25% of the time it goes right
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    // We still keep this subclass method because maybe we will want to add a specific stuff later
    // Example: You have special items and different dialogue starts... etc.
    // Makes customization easier
    public void speak(){
        super.speak();
    }
}
