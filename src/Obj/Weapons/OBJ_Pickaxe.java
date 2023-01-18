package Obj.Weapons;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Pickaxe extends Entity {

    public static final String objName = "Pickaxe";

    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);

        type = type_pickaxe;
        name = objName;
        down1 = setup("/Resources/Objects/pickaxe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nGets the job done!";
        price = 175;
        knockBackPower = 10;
        motionDuration = 10;
        motion2Duration = 20;
    }
}
