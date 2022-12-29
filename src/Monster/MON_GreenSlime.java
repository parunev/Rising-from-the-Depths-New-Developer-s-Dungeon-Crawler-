package Monster;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    // Loading and scaling
    public void getImage(){
        // Using two images (down1, down2) for all directions
        up1 = setup("/Resources/Monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Monsters/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Monsters/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    // Setting Slime behaviour
    public void setAction(){
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

    // Monsters react to damage
    public void damageReaction(){
        actionLockCounter = 0;
        // When the monster receive damage it starts moving away from the player
        direction = gp.player.direction;

    }
}
