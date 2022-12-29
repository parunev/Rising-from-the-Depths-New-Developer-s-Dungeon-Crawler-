package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setup("/Resources/Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Resources/Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/Resources/Objects/heart_blank", gp.tileSize, gp.tileSize);
    }
}
