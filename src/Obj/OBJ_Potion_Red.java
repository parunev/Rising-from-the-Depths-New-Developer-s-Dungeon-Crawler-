package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    int value = 5;
    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/Resources/Objects/potion_red",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n"
                + "Your life has been recovered by " + value + ".";
        entity.life += value;

        if (gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }

        gp.playSE(2);
    }
}
