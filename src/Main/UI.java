package Main;

// Handles all the on-screen User Interface
// So we can display messages, item icons etc.

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp){
        this.gp = gp;
        // Instantiating object in game loop is not recommended. Consuming time and resource.
        arial_40 = new Font("Arial", Font.PLAIN, 40); // font name, font style, font size
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // PLAY STATE
        if (gp.gameState == gp.playState){

        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }


    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    private void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40; // next line will be displayed below the first line
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210); // creates an RGB Color; Fourth number is Alpha value (adjusting opacity)
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // Defines the width of outlines of graphics which are rendered with a Graphics2D
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }


    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

        return gp.screenWidth / 2 - length / 2;
    }
}
