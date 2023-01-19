package Obj;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class OBJ_Bones extends Entity {

    GamePanel gp;
    public static final String objName = "Bones";

    public OBJ_Bones(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/bones", gp.tileSize, gp.tileSize);

        solidArea.x = 4;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;
        collision = true;

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "Bones of the fallen, a warning...";
        dialogues[1][0] = "Remains of battle, a chilling sight..";
        dialogues[2][0] = "Skeletal remains, a monster's feast...";
        dialogues[3][0] = "Bones of the fallen, a grim tale..";
        dialogues[4][0] = "Skeletons litter the ground, a monster's legacy...";
    }

    public void interact(){
        Random r = new Random();
        int index = r.nextInt(5);
        switch (index) {
            case 0 -> startDialogue(this, 0);
            case 1 -> startDialogue(this, 1);
            case 2 -> startDialogue(this, 2);
            case 3 -> startDialogue(this, 3);
            case 4 -> startDialogue(this, 4);
        }
    }
}

