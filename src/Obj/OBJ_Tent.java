package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Tent extends Entity {
    GamePanel gp;

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("/Resources/Objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent]\nYou can sleep until\nnew morning.";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity){

        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1); // this tent image

        // Setting this to true means the item will disappear after usage
        // Personally I think it's better to be set to true because otherwise the nighttime will not make any sense
        return true;
    }
}
