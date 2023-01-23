package Monster;

import Entity.Entity;
import Main.GamePanel;
import Obj.Consumables.OBJ_Coin;
import Obj.Consumables.OBJ_Heart;
import Obj.Consumables.OBJ_Mana_Crystal;
import Obj.OBJ_Rock;

import java.util.Random;

public class MON_Muddy extends Entity {

    GamePanel gp;

    public MON_Muddy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Muddy";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 7;
        defence = 0;
        exp = 3;
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
                maxLife = 9;
                life = maxLife;
                attack = 8;
                defence = 1;
            }
            case 4 -> {
                maxLife = 10;
                life = maxLife;
                attack = 9;
            }
        }
    }

    public void getImage(){
        up1 = setup("/Resources/Monsters/muddy_image_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Monsters/muddy_image_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Monsters/muddy_image_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Monsters/muddy_image_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Monsters/muddy_image_3", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Monsters/muddy_image_4", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Monsters/muddy_image_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Monsters/muddy_image_2", gp.tileSize, gp.tileSize);
    }


    public void setAction(){
        if (onPath){
            checkStopChasingOrNot(gp.player, 10, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            checkShootOrNot(200, 30); // shoot a rock
        } else {
            checkStartChasingOrNot(gp.player, 5, 100);
            getRandomDirection(120);
        }
    }

    public void damageReaction(){
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i < 50){dropItem(new OBJ_Coin(gp));}
        if (i >= 50 && i < 75){dropItem(new OBJ_Heart(gp));}
        if (i >= 75 && i < 100){dropItem(new OBJ_Mana_Crystal(gp));}
    }
}