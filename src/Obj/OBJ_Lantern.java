package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Lantern extends Entity {
    public static final String objName = "Lantern";
    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("/Resources/Objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]\nIlluminates your \nsurroundings.";
        price = 200;
        lightRadius = 250;
    }
}
