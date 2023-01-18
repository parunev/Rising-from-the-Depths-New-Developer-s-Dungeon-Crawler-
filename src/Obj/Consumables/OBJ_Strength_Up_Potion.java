package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Strength_Up_Potion extends Entity {
    GamePanel gp;
    public static final String objName = "Strength Up Potion";

    public OBJ_Strength_Up_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 1;
        down1 = setup("/Resources/Objects/strengthUp",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIncrease your strength.";
        price = 135;
        stackable = true;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your strength has been boosted by " + value + ".";
    }

    public boolean use(Entity entity){
        startDialogue(this, 0);
        entity.strength += value;
        gp.playSE(23);

        return true;
    }
}
