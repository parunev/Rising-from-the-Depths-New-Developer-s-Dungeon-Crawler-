package Obj.Consumables;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Health_Potion extends Entity {
    GamePanel gp;
    public static final String objName = "Health Potion";

    public OBJ_Health_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 4;
        down1 = setup("/Resources/Objects/health_potion",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
        price = 25;
        stackable = true;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your life has been recovered by " + value + ".";
    }

    public boolean use(Entity entity){
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(22);

        return true;
    }
}
