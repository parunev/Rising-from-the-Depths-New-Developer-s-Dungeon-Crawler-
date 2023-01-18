package Obj.Letters;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Hint_Letter extends Entity {
    GamePanel gp;
    public static final String objName = "Hint";

    public OBJ_Hint_Letter(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/Resources/Objects/letter3", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA hint";

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = """
                Hark adventurer! Your journey through the dungeon has been long and
                treacherous, but it has also been rewarding. Remember, the equipment
                you have gathered can be sold for coins. Keep your eyes open for
                opportunities to acquire new weapons. Good luck and safe travels!""";
    }

    public boolean use(Entity entity){
        if (gp.currentMap == 2){
            startDialogue(this, 0);
        }
        return true;
    }
}
