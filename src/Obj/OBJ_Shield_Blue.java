package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Shield_Blue extends Entity {
    public static final String objName = "Blue Shield";
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/Resources/Objects/shield_blue", gp.tileSize, gp.tileSize);
        defenceValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
        price = 250;
    }
}
