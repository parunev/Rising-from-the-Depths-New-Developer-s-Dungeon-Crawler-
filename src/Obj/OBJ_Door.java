package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Door extends Entity {
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/Resources/Objects/door");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
