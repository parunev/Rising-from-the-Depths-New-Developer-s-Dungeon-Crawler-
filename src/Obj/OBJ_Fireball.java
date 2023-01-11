package Obj;

import Entity.Entity;
import Entity.Projectile;
import Main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public static final String objName = "Fireball";

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 5; // how fast the fireball will fly
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 5;
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

    // PARTICLE COLOR/SIZE/SPEED/LIFE
    public Color getParticleColor(){
        return new Color(240,50,0);
    }
    public int getParticleSize(){
        return 7;
    }
    public int getParticleSpeed(){
        return 1;
    }
    public int getParticleMaxLife(){
        return 20;
    }
}