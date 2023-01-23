package Main;

import Entity.PlayerDummy;
import Monster.MON_Lyuborge;
import Obj.OBJ_BlueHeart;

import java.awt.*;
import java.util.Objects;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    // Scene Number
    public final int NA = 0;
    public final int lyuborge = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;

        endCredit = """
                As the adventure comes to a close, the developer of the game \n
                would like to express his gratitude for joining us on this journey.\040
                It has been an incredible experience creating this game and bringing
                
                the story of the small boy and his battle against the skeleton lord to life.
                
                Creating this game was no easy task, it was a long journey \n
                filled with challenges and obstacles, but it was all worth it in the end.\040
                
                I am so proud of the final product and I hope you have \n
                enjoyed playing it as much as I enjoyed creating it.
                I would like to extend my heartfelt thanks to everyone \n
                who has been a part of this project. I couldn't have done it without you.

                Thank you for playing
                
                , and I hope you'll join us on our next adventure!""";
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch (sceneNum){
            case lyuborge -> scene_Lyuborge();
            case ending -> scene_ending();
        }
    }

    public void scene_Lyuborge(){
        if (scenePhase == 0){
            gp.bossBattleOn = true;

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

            if (gp.player.worldY < gp.tileSize * 14){
                scenePhase++;
            }
        }

        if (scenePhase == 2){

            // Search the boss
            for (int i = 0; i <gp.monster[1].length ; i++) {

                if (gp.monster[gp.currentMap][i] != null
                        && gp.monster[gp.currentMap][i].name.equals(MON_Lyuborge.monName)){

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
            gp.playMusic(20);
        }
    }

    public void scene_ending(){
        if (scenePhase == 0){
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if (scenePhase == 1){
            // Display dialogues
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 2){
            // Play the fanfare
            gp.playSE(4);
            scenePhase++;
        }
        if (scenePhase == 3){
            // Wait until the sound effect ends
            if (counterReached(300)){ // 5 seconds
                scenePhase++;
            }
        }
        if (scenePhase == 4){
            // The screen gets darker
            alpha += 0.005f;
            if (alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);

            if (alpha == 1f){
                alpha = 0f;
                scenePhase++;
            }
        }
        if (scenePhase == 5){
            drawBlackBackground(1f);

            alpha += 0.005f;
            if (alpha > 1f){
                alpha = 1f;
            }

            String text = """
                    The small blue boy faced the skeleton lord with courage, his sword in hand.
                    With skill and determination, he defeated the towering skeleton lord and saved the kingdom.\s
                    A hero was born.""";

            drawString(alpha, 22f, 200, text, 70);

            if (counterReached(600)){
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if (scenePhase == 6){
            drawBlackBackground(1f);

            drawString(1f, 120f, gp.screenHeight/2, "BLUEPRINT GAME", 40);

            if (counterReached(480)){
                scenePhase++;
            }
        }
        if (scenePhase == 7){
            drawBlackBackground(1f);

            y = gp.screenHeight / 2;
            drawString(1f, 22f, y,  endCredit, 40);

            if (counterReached(480)){
                scenePhase++;
            }
        }
        if (scenePhase == 8){
            drawBlackBackground(1f);

            // Scrolling the credit
            y--;
            drawString(1f , 22f, y, endCredit, 40);
        }
    }

    public boolean counterReached(int target){
        boolean counterReached = false;
        counter++;

        if (counter > target){
            counterReached = true;
            counter = 0;
        }

        return counterReached;
    }

    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")){
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
