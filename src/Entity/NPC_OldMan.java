package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getOldManImage();
    }

    public void getOldManImage(){
        up1 = setup("/Resources/NPC/oldman_up_1");
        up2 = setup("/Resources/NPC/oldman_up_2");
        down1 = setup("/Resources/NPC/oldman_down_1");
        down2 = setup("/Resources/NPC/oldman_down_2");
        left1 = setup("/Resources/NPC/oldman_left_1");
        left2 = setup("/Resources/NPC/oldman_left_2");
        right1 = setup("/Resources/NPC/oldman_right_1");
        right2 = setup("/Resources/NPC/oldman_right_2");
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
}
