package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Mana_Crystal extends Entity {
    GamePanel gp;
    public static final String objName = "Mana Crystal";

    public OBJ_Mana_Crystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/Resources/Objects/manacrystal_full",gp.tileSize - 10,gp.tileSize - 10);
        image = setup("/Resources/Objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Resources/Objects/manacrystal_blank",gp.tileSize,gp.tileSize);
    }

    public boolean use(Entity entity){
        gp.playSE(22);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;

        return true;
    }
}
