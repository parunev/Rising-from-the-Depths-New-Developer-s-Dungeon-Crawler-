package Obj;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/Resources/Objects/potion_red",gp.tileSize, gp.tileSize);
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
        gp.playSE(2);

        return true;
    }
}
