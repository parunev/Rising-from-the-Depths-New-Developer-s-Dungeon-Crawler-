package Monster;

import Entity.Entity;
import Main.GamePanel;
import Obj.Consumables.OBJ_Coin;
import Obj.Consumables.OBJ_Heart;
import Obj.Consumables.OBJ_Mana_Crystal;
import Obj.OBJ_Rock;

import java.util.Random;

public class MON_Swampy extends Entity {
    GamePanel gp;
    public MON_Swampy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Swampy";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defence = 0; // defence is 1 since the player starting damage is 1
        exp = 1; // how much you can get when you kill the monster
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        checkCurrentMap();
        getImage();
    }

    public void checkCurrentMap() {
        switch (gp.currentMap) {
            case 3 -> {
                maxLife = 8;
                life = maxLife;
                attack = 6;
                defence = 1;
            }
            case 4 -> {
                maxLife = 10;
                life = maxLife;
                attack = 7;
            }
        }
    }

    public void getImage(){
        // Using two images (down1, down2) for all directions
        up1 = setup("/Resources/Monsters/swampy_image_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Monsters/swampy_image_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Monsters/swampy_image_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Monsters/swampy_image_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Monsters/swampy_image_3", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Monsters/swampy_image_4", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Monsters/swampy_image_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Monsters/swampy_image_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        if (onPath){
            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            // If the player is nearby the monster, monster gets aggro(chasing) - adjustable
            checkStartChasingOrNot(gp.player, 5, 100);

            // For every 120 frames the NPC will move. This can be adjusted. Gets a random direction
            getRandomDirection(120);
        }
    }

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
            dropItem(new OBJ_Coin(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Mana_Crystal(gp));
        }
    }
}
