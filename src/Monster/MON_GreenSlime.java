package Monster;

import Entity.Entity;
import Main.GamePanel;
import Obj.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defence = 0; // defence is 1 since the player starting damage is 1
        exp = 2; // how much you can get when you kill the monster
        projectile = new OBJ_Rock(gp);

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

        // Slime randomly shoots a rock
        int i = new Random().nextInt(100)+1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    // Monsters react to damage
    public void damageReaction(){
        actionLockCounter = 0;
        // When the monster receive damage it starts moving away from the player
        direction = gp.player.direction;

    }
}
