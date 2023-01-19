package Obj.Letters;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Final_Letter extends Entity {
    GamePanel gp;
    public static final String objName = "Final Words";

    public OBJ_Final_Letter(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/Resources/Objects/letter", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nTo my love.";

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = """
                My dearest love, As I write these final words, I can feel the life slowly
                slipping away from me. I fought valiantly against the monsters of this
                dungeon, but in the end, it was not enough. I am filled with regret that
                I will not be able to hold you one last time...""";

        dialogues[1][0] = """
                To my family, I regret that I will not be able to see your
                faces again, but know that I fought and died for our kingdom
                and its people. I hope my sacrifice will not be in vain
                and that my legacy will live on...\s""";
    }

    public boolean use(Entity entity){
        if (gp.currentMap == 2){
            startDialogue(this, 0);
        }
        if (gp.currentMap == 3){
            startDialogue(this, 1);
        }
        return true;
    }
}
