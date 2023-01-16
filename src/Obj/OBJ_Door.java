package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Door extends Entity {
    GamePanel gp;
    public static final String objName = "Door";
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/Resources/Objects/door", gp.tileSize, gp.tileSize);
        image2 = setup("/Resources/Objects/door_open", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You need a key to open this.";
    }

    public void interact(){

        if (!opened){
            startDialogue(this, 0);
        } else {
            down1 = image2;
        }
    }
}
