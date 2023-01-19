package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
    boolean showDebugText = false; // DEBUG
    public boolean godModeOn = false; // DEBUG

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState){ // TITLE STATE
            try {
                titleState(code);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (gp.gameState == gp.playState){ // PLAY STATE
            playState(code);
        } else if (gp.gameState == gp.pauseState){ // PAUSE STATE
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState){ // DIALOGUE STATE
            dialogueState(code);
        } else if (gp.gameState == gp.characterState){ // CHARACTER STATE
            characterState(code);
        } else if (gp.gameState == gp.optionsState) { // OPTIONS STATE
            optionsState(code);
        } else if (gp.gameState == gp.gameOverState) { // GAME OVER STATE
            try {
                gameOverState(code);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (gp.gameState == gp.tradeState) { // TRADE STATE
            tradeState(code);
        } else if (gp.gameState == gp.mapState) { // MAP STATE
            mapState(code);
        }
    }

    public void titleState(int code) throws IOException, ClassNotFoundException {
        if (code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER){
            switch (gp.ui.commandNum) {
                case 0 -> {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                case 1 -> {
                    gp.saveLoad.load();
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                case 2 -> System.exit(0);
            }
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
            gp.playSE(27);
        }
        if (code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
            gp.playSE(27);
        }
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X){
            gp.map.miniMapOn = !gp.map.miniMapOn;
        }
        if (code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }

        // DEBUG INFO
        if (code == KeyEvent.VK_T){
            showDebugText = !showDebugText;
        }
        if (code == KeyEvent.VK_R){
            switch (gp.currentMap) {
                case 0 -> gp.tileM.loadMap("/Resources/Maps/worldV3.txt", 0);
                case 1 -> gp.tileM.loadMap("/Resources/Maps/interior01.txt", 1);
            }
        }
        // DEBUG GOD MODE
        if (code == KeyEvent.VK_G){
            godModeOn = !godModeOn;
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
            gp.playSE(28);
        }
    }

    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER){
            // Maybe the text requires multiple pages to display, so we cannot just return to the playState
            // everytime we press enter
            enterPressed = true;
        }
    }

    public void characterState(int code){
        if (code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        playerInventory(code);
        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }

    public void optionsState(int code){
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
            gp.playSE(28);
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = switch (gp.ui.subState) {
            case 0 -> 4;
            case 2 -> 1;
            default -> 0;
        };

        if (code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 0 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 1 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNum == 0 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 1 && gp.se.volumeScale < 5){
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }

    }

    public void gameOverState(int code) throws IOException, ClassNotFoundException {
        if (code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 0){ // retry
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
            }else if (gp.ui.commandNum == 1){ // load
                gp.saveLoad.load();
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }else if (gp.ui.commandNum == 2){ // quit
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }

    public void tradeState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if (gp.ui.subState == 0){
            if (code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1){
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2){
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }

    public void mapState(int code){
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.playState;
        }
    }


    public void playerInventory(int code){
        if (code == KeyEvent.VK_W){
            if (gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A){
            if (gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S){
            if (gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D){
            if (gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
    }
    public void npcInventory(int code){
        if (code == KeyEvent.VK_W){
            if (gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A){
            if (gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S){
            if (gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D){
            if (gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }
}
