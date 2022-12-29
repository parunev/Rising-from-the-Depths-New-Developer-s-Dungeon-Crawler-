package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Chest extends Entity {
    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("/Resources/Objects/chest", gp.tileSize, gp.tileSize);
    }
}
