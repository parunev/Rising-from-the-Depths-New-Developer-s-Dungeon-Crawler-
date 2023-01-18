package Obj.Shields;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Oak extends Entity {
    public static final String objName = "Oak Defender";
    public OBJ_Shield_Oak(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/Resources/Objects/oak_defender_shield", gp.tileSize, gp.tileSize);
        defenceValue = 1;
        description = "[" + name + "]\nMade by oak wood.";
        price = 35;
    }
}
