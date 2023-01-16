package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Bones extends Entity {

    GamePanel gp;
    public static final String objName = "Bones";

    public OBJ_Bones(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/bones", gp.tileSize, gp.tileSize);
        collision = true;

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "Remains of a fallen warrior. Maybe...";
    }

    public void interact(){
        startDialogue(this, 0);
    }
}

