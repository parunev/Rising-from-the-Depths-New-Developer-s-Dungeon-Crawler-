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

        dialogues[1][0] = """
                The path ahead is treacherous, but with determination
                and a little bit of coins, you may just find the key to unlock the door
                to the next level. Gather as many coins as you can, and use them wisely
                to purchase the key that will open the door to your destiny.""";

        dialogues[2][0] = """
                Brave adventurer, heed my advice. The boss ahead is fierce,
                but with proper guard and parry techniques, victory can be yours.
                Do not forget to stock up on potions from the merchant
                before entering the battle. Good luck on your quest!""";
    }

    public boolean use(Entity entity){
        switch (gp.currentMap) {
            case 2 -> startDialogue(this, 0);
            case 3 -> startDialogue(this, 1);
            case 4 -> startDialogue(this, 2);
        }
        return true;
    }
}
