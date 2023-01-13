package Monster;

import Data.Progress;
import Entity.Entity;
import Main.GamePanel;
import Obj.OBJ_Coin_Bronze;
import Obj.OBJ_Door_Iron;
import Obj.OBJ_Heart;
import Obj.OBJ_Mana_Crystal;

import java.util.Random;

public class MON_Skeletonlord extends Entity {

    GamePanel gp;
    public static final String monName = "Skeleton Lord";

    public MON_Skeletonlord(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defence = 2;
        exp = 50;
        knockBackPower = 5;
        sleep = true;

        int size = gp.tileSize * 5;

        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motionDuration = 25;
        motion2Duration = 50;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage(){
        int i = 5;

        if (!inRage){
            up1 = setup("/Resources/Monsters/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/Resources/Monsters/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/Resources/Monsters/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/Resources/Monsters/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/Resources/Monsters/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/Resources/Monsters/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/Resources/Monsters/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/Resources/Monsters/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }

        if (inRage){
            up1 = setup("/Resources/Monsters/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/Resources/Monsters/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/Resources/Monsters/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/Resources/Monsters/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/Resources/Monsters/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/Resources/Monsters/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/Resources/Monsters/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/Resources/Monsters/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }

    public void getAttackImage(){
        int i = 5;

        if (!inRage){
            attackUp1 = setup("/Resources/Monsters/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/Resources/Monsters/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/Resources/Monsters/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/Resources/Monsters/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/Resources/Monsters/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/Resources/Monsters/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/Resources/Monsters/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/Resources/Monsters/skeletonlord_attack_right_2", gp.tileSize * i *  2, gp.tileSize * i);
        } else {
            attackUp1 = setup("/Resources/Monsters/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/Resources/Monsters/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/Resources/Monsters/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/Resources/Monsters/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/Resources/Monsters/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/Resources/Monsters/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/Resources/Monsters/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/Resources/Monsters/skeletonlord_phase2_attack_right_2", gp.tileSize * i *  2, gp.tileSize  * i);
        }

    }

    public void setDialogue(){
        dialogues[0][0] = "No one can steal my treasure!";
        dialogues[0][1] = "You will die here!";
        dialogues[0][2] = "WELCOME TO YOUR DOOM!";
    }

    public void setAction(){
        if (!inRage && life < maxLife/2){
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
            motionDuration -= 5;
            motion2Duration -= 10;
        }

        if (getTileDistance(gp.player) < 10){ // 10 tiles distance between player and skeleton
            moveTowardPlayer(60); // Every 60 frames the Skeleton checks the player position and maybe change the direction
        } else {
            getRandomDirection(120);
        }

        if (!attacking){
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
        }
    }

    public void damageReaction(){
        actionLockCounter = 0;
    }

    public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;

        // Restore the previous music
        gp.stopMusic();
        gp.playMusic(17);

        // Remove the iron door
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                gp.playSE(20);
                gp.obj[gp.currentMap][i] = null;
            }
        }

        int i = new Random().nextInt(100)+1;

        if (i < 50){dropItem(new OBJ_Coin_Bronze(gp));}
        if (i >= 50 && i < 75){dropItem(new OBJ_Heart(gp));}
        if (i >= 75 && i < 100){dropItem(new OBJ_Mana_Crystal(gp));}
    }
}