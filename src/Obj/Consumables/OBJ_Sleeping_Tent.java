package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sleeping_Tent extends Entity {
    GamePanel gp;
    public static final String objName = "The Adventurer's Haven";

    public OBJ_Sleeping_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/Resources/Objects/tent", gp.tileSize, gp.tileSize);
        description = "["+ name +"]\nPortable dungeon shelter,\nto regain health and mana.";
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
