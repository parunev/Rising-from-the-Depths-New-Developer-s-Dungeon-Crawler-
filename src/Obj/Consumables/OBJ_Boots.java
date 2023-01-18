package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Boots extends Entity {
    public static final String objName = "Boots";
    public OBJ_Boots(GamePanel gp){
        super(gp);
        name = objName;
        down1 = setup("/Resources/Objects/boots", gp.tileSize, gp.tileSize);
    }
}
