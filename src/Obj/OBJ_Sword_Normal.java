package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public static final String objName = "Normal Sword";
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/Resources/Objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        motionDuration = 5;
        motion2Duration = 25;
    }
}
