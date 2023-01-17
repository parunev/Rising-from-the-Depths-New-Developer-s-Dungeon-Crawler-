package Obj;

import Entity.Entity;
import Main.GamePanel;

import java.util.Random;

public class OBJ_BlankCandle extends Entity {
    GamePanel gp;
    public static final String objName = "Candle";

    public OBJ_BlankCandle(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/Resources/Objects/blank_candle", gp.tileSize-14, gp.tileSize-14);

        solidArea.x = 4;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 10;
        solidArea.height = 10;
        collision = true;

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "Glimmer of hope fades..";
        dialogues[1][0] = "Candle flickers in darkness...";
        dialogues[2][0] = "Light fades, darkness persists..";
        dialogues[3][0] = "Hope dims, darkness looms...";

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
