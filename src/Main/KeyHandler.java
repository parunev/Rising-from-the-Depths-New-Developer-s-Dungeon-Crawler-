package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// The listener interface for receiving keyboard events (keystrokes)
public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    boolean checkDrawTime = false; // DEBUG

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the integer keyCode associated with the key in this event

        if (gp.gameState == gp.titleState){ // TITLE STATE
            titleState(code);
        } else if (gp.gameState == gp.playState){ // PLAY STATE
            playState(code);
        } else if (gp.gameState == gp.pauseState){ // PAUSE STATE
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState){ // DIALOGUE STATE
            dialogueState(code);
        } else if (gp.gameState == gp.characterState){ // CHARACTER STATE
            characterState(code);
        }
    }

    public void titleState(int code){
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
            switch (gp.ui.commandNum){
                case 0:
                    gp.gameState = gp.playState;
                   // gp.playMusic(0);
                    break;
                case 1:
                    // add later
                    break;
                case 2:
                    System.exit(0);
                    break;
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
        }
        if (code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        // DEBUG
        if (code == KeyEvent.VK_T){
            checkDrawTime = !checkDrawTime;
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code){
        if (code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W){
            if (gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A){
            if (gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S){
            if (gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D){
            if (gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
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
    }
}
