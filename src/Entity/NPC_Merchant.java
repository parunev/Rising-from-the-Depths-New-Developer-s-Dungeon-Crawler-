package Entity;

import Main.GamePanel;
import Obj.*;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getOldManImage();
        setDialogue();
        setItems();
    }

    public void getOldManImage(){
        up1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    // Store character dialogues
    public void setDialogue(){
        dialogues[0][0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
        dialogues[1][0] = "Come again, hehe!";
        dialogues[2][0] = "You need more coins to buy that!";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Tent(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }

    public void speak(){
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
