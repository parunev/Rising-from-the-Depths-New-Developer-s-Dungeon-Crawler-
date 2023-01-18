package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Speed_Up_Potion extends Entity {

    GamePanel gp;
    public static final String objName = "Speed Up Potion";

    public OBJ_Speed_Up_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 1;
        down1 = setup("/Resources/Objects/speedUp",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIncreasing your speed.";
        price = 125;
        stackable = true;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your speed has been boosted by " + value + ".";
    }

    public boolean use(Entity entity){
        startDialogue(this, 0);
        entity.speed += value;
        gp.playSE(24);

        return true;
    }
}
