package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/Resources/Objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/Resources/Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Resources/Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/Resources/Objects/heart_blank", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity){
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;

        return true;
    }
}
