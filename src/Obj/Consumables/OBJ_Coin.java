package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Coin extends Entity {
    GamePanel gp;
    public static final String objName = "Coin";
    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/Resources/Objects/coin_image", gp.tileSize - 10, gp.tileSize - 10);
    }

    public boolean use(Entity entity){
        gp.playSE(1);
        gp.ui.addMessage("$$$");
        switch (gp.currentMap) {
            case 0 -> gp.player.coin += value;
            case 1 -> gp.player.coin += value + 2;
            case 3 -> gp.player.coin += value + 3;
            case 4 -> gp.player.coin += value + 4;
        }

        return true;
    }
}
