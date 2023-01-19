package Entity;

import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_DungeonKeeper extends Entity {

    public NPC_DungeonKeeper(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        dialogueSet = -1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        getDungeonKeeperImages();
        setDialogue();
    }

    public void getDungeonKeeperImages(){
        up1 = setup("/Resources/NPC/dungeonkeeper_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/NPC/dungeonkeeper_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/NPC/dungeonkeeper_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/NPC/dungeonkeeper_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/NPC/dungeonkeeper_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/NPC/dungeonkeeper_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/NPC/dungeonkeeper_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/NPC/dungeonkeeper_right2", gp.tileSize, gp.tileSize);
    }

    // Store character dialogues
    public void setDialogue(){
        // MAP 0
        dialogues[0][0] = "Welcome to the depths of our dungeon, adventurer.\nMay your journey be filled with treasure and glory.";
        dialogues[0][1] = "Are you ready to proceed to the next level of the dungeon?\nIf so, the ladder will take you deeper into the dungeon.\nRemember, the deeper you go, the greater the challenge and rewards will\nbe. Good luck!";

        // MAP 1
        dialogues[1][0] = """
                Ah, welcome back adventurer!\s
                You've made it to the second floor of the dungeon, I see.\s
                Be careful, this floor is known for its traps and more powerful monsters.\s
                Good luck!""";

        // MAP 2
        dialogues[2][0] = "Welcome, adventurer, you have reached level 3 of the dungeon\n, let me tell you the story of this place before you proceed.\n";
        dialogues[2][1] = "The dungeon was once a place of mystery and legend.\nStories of powerful artifacts and untold riches drew\nwarriors and adventurers from all over the land to test\ntheir skills and claim the treasures within.";
        dialogues[2][2] = "However, as the years passed, the dungeon became known\nas a place of DEATH!";
        dialogues[2][3] = "The final chamber of the dungeon is said to be guarded by\na powerful boss, a demon lord who has never been defeated\nMany brave warriors have ventured into the dungeon\n, but none have ever returned from the final chamber.";
        dialogues[2][4] = "The dungeon was eventually abandoned, the secrets and treasures\nlocked away for eternity. The stories of the dungeon and the demon lord\nhave been passed down through the generations, becoming nothing more\nthan a cautionary tale for the brave.";
        dialogues[2][5] = "Now, it's your turn to take up the challenge and venture into\nthe dungeon to face the demon lord. You will have to venture deep into\nthe dungeon, fighting your way through hordes of monsters,\nuncovering ancient treasures and powerful artifacts, and finally";
        dialogues[2][6] = ", facing the demon lord in the final chamber. Will you be able to defeat\nthe demon lord and claim the ultimate treasure, or will you fall like\nso many before you? Only time will tell.";
        dialogues[2][7] = "This dungeon level appears to be abandoned by monsters, with only the\npresence of fallen soldiers and various story-telling objects scattered\nthroughout. It's possible there may be new discoveries to be made.";

        // MAP 3
        dialogues[3][0] = "Welcome, brave adventurer, to the fourth level of the dungeon.";
        dialogues[3][1] = "You have journeyed far and overcome many challenges to reach this point.\nKnow that many before you have failed, but you have persevered.";
        dialogues[3][2] = "The trials ahead will be even greater\n,but I have faith in your strength and determination.\nMay you find what you seek and emerge victorious.";
    }

    public void speak(){
        super.speak();
        onPath = true;
        facePlayer();
        switch (gp.currentMap) {
            case 0 -> startDialogue(this, 0);
            case 1 -> startDialogue(this, 1);
            case 2 -> startDialogue(this, 2);
            case 3 -> startDialogue(this, 3);
        }
    }

    // Character behaviour (AI kinda)
    public void setAction() {
        if (gp.currentMap == 2){
            direction = "right";
            speed = 0;
            if (onPath){
                speed = 1;
                searchPath(18, 35);
            }
        }

        if (gp.currentMap != 2){
            actionLockCounter++;
            if (actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1;
                if (i <= 25){direction = "up";}
                if (i > 25 && i <= 50){direction = "down";}
                if (i > 50 && i <= 75){direction = "left";}
                if (i > 75){direction = "right";}
                actionLockCounter = 0;
            }
        }
    }
}