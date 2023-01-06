package Monster;

import Entity.Entity;
import Main.GamePanel;
import Obj.OBJ_Coin_Bronze;
import Obj.OBJ_Heart;
import Obj.OBJ_Mana_Crystal;
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
        attack = 2;
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

    // Currently using this method for the second aggro/attack condition
    // The game gets pretty hard if that's enabled - adjustable
    public void update(){
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        // If the player is nearby the monster, monster gets aggro - adjustable
        if (!onPath && tileDistance < 10){

            int i = new Random().nextInt(100) + 1;
            if (i > 50){ // 50% of the time it becomes aggro - adjustable
                onPath = true;
            }
        }

        // If the tile distance is more than 14 tiles the monsters stop being aggro - adjustable
        if (onPath && tileDistance > 14){
            onPath = false;
        }
    }

    // Setting Slime behaviour
    public void setAction(){
        if (onPath){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

            // Slime randomly shoots a rock
            int i = new Random().nextInt(200)+1;
            if (i > 197 && !projectile.alive && shotAvailableCounter == 30){
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
        }else{
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

    // Monsters react to damage
    public void damageReaction(){
        actionLockCounter = 0;
        // When the monster receive damage it starts moving away from the player
        // direction = gp.player.direction;
        onPath = true; // Instantly becomes aggro when player attacks it
    }

    public void checkDrop(){

        // CAST A DIE
        int i = new Random().nextInt(100)+1;

        // SET THE MONSTER DROP - 50% of the time the monster drops a bronze coin and 2x25% for mana or heart
        if (i < 50){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Mana_Crystal(gp));
        }
    }
}
