package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/Resources/Objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;

    }
}
