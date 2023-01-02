package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Mana_Crystal extends Entity {

    GamePanel gp;

    public OBJ_Mana_Crystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("/Resources/Objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Resources/Objects/manacrystal_blank",gp.tileSize,gp.tileSize);
    }
}
