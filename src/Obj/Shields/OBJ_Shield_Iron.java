package Obj.Shields;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Iron extends Entity {
    public static final String objName = "Iron Bulwark";
    public OBJ_Shield_Iron(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/Resources/Objects/iron_bulwark_shield", gp.tileSize, gp.tileSize);
        defenceValue = 2;
        description = "[" + name + "]\nUnyielding and Reliable \nProtection";
        price = 250;
    }
}
