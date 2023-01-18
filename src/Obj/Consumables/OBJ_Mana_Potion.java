package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Mana_Potion extends Entity {

    GamePanel gp;
    public static final String objName = "Mana Potion";

    public OBJ_Mana_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 1;
        down1 = setup("/Resources/Objects/mana_potion",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nRecovers your mana by " + value + ".";
        price = 35;
        stackable = true;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your mana has been recovered by " + value + ".";
    }

    public boolean use(Entity entity){
        gp.playSE(22);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;

        return true;
    }
}
