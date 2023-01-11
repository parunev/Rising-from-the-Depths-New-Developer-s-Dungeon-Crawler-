package Entity;

import Main.GamePanel;

import java.awt.*;

public class NPC_BigRock extends Entity{
    public static final String npcName = "Big Rock";

    public NPC_BigRock(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 4;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogueSet = -1;

        getOldManImage();
        setDialogue();
    }

    public void getOldManImage(){
        up1 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/bigrock", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){dialogues[0][0] = "It's a giant rock.";}

    public void setAction(){}
    public void update(){}
    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }
    }
}
