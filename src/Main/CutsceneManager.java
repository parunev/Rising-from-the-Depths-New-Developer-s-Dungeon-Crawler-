package Main;

import Entity.PlayerDummy;
import Monster.MON_Skeletonlord;
import Obj.OBJ_Door_Iron;

import java.awt.*;
import java.util.Objects;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    // Scene Number
    public final int NA = 0;
    public final int skeletonLord = 1;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        if (sceneNum == skeletonLord) {
            scene_skeletonLord();
        }
    }

    public void scene_skeletonLord(){
        if (scenePhase == 0){
            gp.bossBattleOn = true;

            // Shut the iron door
            for (int i = 0; i < gp.obj[1].length; i++) {

                if (gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true; // only available during the boss fight
                    gp.playSE(20);
                    break;
                }
            }

            // Search a vacant slot for the dummy
            for (int i = 0; i < gp.npc[1].length ; i++) {
                if (gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1){
            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize * 16){
                scenePhase++;
            }
        }

        if (scenePhase == 2){

            // Search the boss
            for (int i = 0; i <gp.monster[1].length ; i++) {

                if (gp.monster[gp.currentMap][i] != null
                        && gp.monster[gp.currentMap][i].name.equals(MON_Skeletonlord.monName)){

                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 3){
            // The boss speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 4){

            // Return to the player

            // Search the dummy
            for (int i = 0; i < gp.npc[1].length; i++) {

                if (gp.npc[gp.currentMap][i] != null
                        && Objects.equals(gp.npc[gp.currentMap][i].name, PlayerDummy.npcName)){

                    // Restore the players position
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    // Delete the dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }

            // Drawing the player
            gp.player.drawing = true;

            // Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            // Change the music
            gp.stopMusic();
            gp.playMusic(21);
        }
    }
}
