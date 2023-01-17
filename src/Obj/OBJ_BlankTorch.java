package Obj;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class OBJ_BlankTorch extends Entity {

    GamePanel gp;
    public static final String objName = "Torch";

    public OBJ_BlankTorch(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/blank_torch", gp.tileSize - 10, gp.tileSize - 10);
        collision = true;

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "Torch burns low, danger nears..";
        dialogues[1][0] = "Torch gutters, shadows deepen...";
        dialogues[2][0] = "Torch flickers, hope diminishes..";
        dialogues[3][0] = "Torchlight fails, darkness closes in...";
    }

    public void interact(){
        Random r = new Random();
        int index = r.nextInt(4);
        switch (index) {
            case 0 -> startDialogue(this, 0);
            case 1 -> startDialogue(this, 1);
            case 2 -> startDialogue(this, 2);
            case 3 -> startDialogue(this, 3);
        }
    }
}
