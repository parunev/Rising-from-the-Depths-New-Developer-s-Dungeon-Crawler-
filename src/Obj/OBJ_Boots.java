package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gp){
        super(gp);
        name = "Boots";
        down1 = setup("/Resources/Objects/boots");
    }
}
