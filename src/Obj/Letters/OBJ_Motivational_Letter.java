package Obj.Letters;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Motivational_Letter extends Entity {
    GamePanel gp;
    public static final String objName = "Letter";

    public OBJ_Motivational_Letter(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/Resources/Objects/letter2", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA letter";

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = """
                Do not let the darkness and fear consume you,
                fight on with valor and bravery. Remember why you started this journey
                and let that be your guiding light. The victory is within reach, do not
                give up now. Stay strong, stay determined, and emerge victorious.""";

        dialogues[1][0] = """
                Though the path ahead may be uncertain and the monsters formidable,
                do not falter. Your strength, courage and determination will guide you.
                Remember, the end justifies the means and the victory is so close.
                Keep fighting, and do not give up hope. Triumph awaits you.""";

        dialogues[2][0] = """
                In the face of adversity, let your determination be your guide and
                your strength be your weapon. The monsters may be powerful but
                remember the reason why you began this journey. Keep moving forward,
                the victory is within reach. Believe in yourself and you will emerge victory.""";
    }

    public boolean use(Entity entity){
        switch (gp.currentMap){
            case 2: startDialogue(this, 0);
            case 3: startDialogue(this, 1);
            case 4: startDialogue(this, 2);
        }
        return true;
    }
}
