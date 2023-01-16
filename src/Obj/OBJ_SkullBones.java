package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_SkullBones extends Entity {

    GamePanel gp;
    public static final String objName = "Skull Bones";

    public OBJ_SkullBones(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/skullbones", gp.tileSize, gp.tileSize);
        collision = true;

        setDialogues();
    }

    public void setDialogues(){

        dialogues[0][0] = "Agh! A game of dice or dominoes!";
    }

    public void interact(){
            startDialogue(this, 0);
    }
}
