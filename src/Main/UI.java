package Main;

// Handles all the on-screen User Interface
// So we can display messages, item icons etc.

import Obj.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        // Instantiating object in game loop is not recommended. Consuming time and resource.
        arial_40 = new Font("Arial", Font.PLAIN, 40); // font name, font style, font size
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Again we prepare the image before the game loop starts
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        if (gameFinished){
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // returns the lenght of the text
            // Center of the screen
            x = gp.screenWidth/2 - textLength/2; // this way the text will be aligned
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.BLUE);
            text = " Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null; // This stops the thread, meaning we are stopping the game. We finished it.
        }else{
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null); // key image
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // PLAY TIME
            playTime += (double) 1/60; // 1/60 seconds in every loop
            g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);

            // MESSAGE
            if (messageOn){
                g2.setFont(g2.getFont().deriveFont(30F)); // deriveFont accepts float
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                // When the counter hits a certain number, we stop displaying the text
                if (messageCounter > 120) { // 120Frames - After 2 seconds the msg disappears
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
