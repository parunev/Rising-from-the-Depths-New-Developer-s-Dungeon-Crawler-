package Obj;

import Entity.Projectile;
import Main.GamePanel;

public class OBJ_Rock extends Projectile {

    GamePanel gp;

    public OBJ_Rock(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Projectiles/rock_down_1", gp.tileSize, gp.tileSize);
    }
}
