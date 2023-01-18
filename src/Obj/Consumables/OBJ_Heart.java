package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity {
    GamePanel gp;
    public static final String objName = "Heart";
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 2;
        down1 = setup("/Resources/Objects/heart_full", gp.tileSize - 10, gp.tileSize - 10);
        image = setup("/Resources/Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Resources/Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/Resources/Objects/heart_blank", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity){
        gp.playSE(22);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;

        return true;
    }
}
