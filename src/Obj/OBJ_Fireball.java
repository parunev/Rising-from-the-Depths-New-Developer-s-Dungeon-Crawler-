package Obj;

import Entity.Entity;
import Entity.Projectile;
import Main.GamePanel;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5; // how fast the fireball will fly
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/Resources/Projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Projectiles/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Projectiles/fireball_right_2", gp.tileSize, gp.tileSize);
    }
    public boolean hasResource(Entity user){
        return user.mana >= useCost;
    }

    public void subtractResource(Entity user){
        user.mana -= useCost;
    }
}
