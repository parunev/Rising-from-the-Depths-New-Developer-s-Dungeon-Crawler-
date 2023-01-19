package Obj;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class OBJ_SkullBones extends Entity {

    GamePanel gp;
    public static final String objName = "Skull Bones";

    public OBJ_SkullBones(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/skullbones", gp.tileSize, gp.tileSize);

        solidArea.x = 4;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
        collision = true;

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "Skulls and bones, silent testimony...";
        dialogues[1][0] = "Fallen warriors, forgotten...";
        dialogues[2][0] = "Remains of battle, haunting...";
        dialogues[3][0] = "Bones litter the ground...";
        dialogues[4][0] = "Death's remnants, a grim reminder...";
    }

    public void interact() {
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
