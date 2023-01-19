package Entity;

import Main.GamePanel;
import Obj.Consumables.*;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        getOldManImage();
        setDialogue();
        setItems();
    }

    public void update(){}

    public void getOldManImage(){
        up1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
    }

    // Store character dialogues
    public void setDialogue(){
        dialogues[0][0] = "Well, well, well. What do we have here?\nA brave adventurer looking to stock up on supplies?\nI've got just what you need. Take a look at my wares\n, I guarantee you won't be disappointed.";
        dialogues[1][0] = "Farewell, adventurer!";
        dialogues[2][0] = "You need more coins to buy that!";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems(){
            inventory.add(new OBJ_Health_Potion(gp));
            inventory.add(new OBJ_Mana_Potion(gp));
            inventory.add(new OBJ_Key(gp));
            inventory.add(new OBJ_Speed_Up_Potion(gp));
            inventory.add(new OBJ_Strength_Up_Potion(gp));
            inventory.add(new OBJ_Sleeping_Tent(gp));
    }

    public void speak(){
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
