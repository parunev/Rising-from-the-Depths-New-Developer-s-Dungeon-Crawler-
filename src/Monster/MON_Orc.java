package Monster;

import Entity.Entity;
import Main.GamePanel;
import Obj.Consumables.OBJ_Coin;
import Obj.Consumables.OBJ_Heart;
import Obj.Consumables.OBJ_Mana_Crystal;
import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp;

    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defence = 2;
        exp = 10;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motionDuration = 40;
        motion2Duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        up1 = setup("/Resources/Monsters/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Monsters/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Monsters/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Monsters/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Monsters/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Monsters/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Monsters/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Monsters/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage(){
        attackUp1 = setup("/Resources/Monsters/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/Resources/Monsters/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/Resources/Monsters/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/Resources/Monsters/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/Resources/Monsters/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/Resources/Monsters/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/Resources/Monsters/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/Resources/Monsters/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
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

        // Check if it attacks
        if (!attacking){
            // If you want to make the monster more aggressive pass a smaller rate number
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
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
