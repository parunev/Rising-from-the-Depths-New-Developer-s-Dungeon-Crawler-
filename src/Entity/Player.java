package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyH;

    // Where we draw player on the screen
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp); // calling the constructor of the superclass
        this.keyH = keyH;

        // Center
        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        // Player character solid area (you can choose different values)
        solidArea = new Rectangle(8, 16, 32, 32); //x, y, width, height

        // We want to record default values because we're going to change solidArea x,y later
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        // Player starting position on world map
        // You can also type worldX = 1000, it doesn't really matter works fine both ways.
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; //any directions are fine

        // PLAYER STATUS
        maxLife = 6; // 6 life means 3 full hearts
        life = maxLife; // 1 life means half heart, 2 life means 1 heart
    }

    public void getPlayerImage(){
        up1 = setup("/Resources/Player/boy_up_1");
        up2 = setup("/Resources/Player/boy_up_2");
        down1 = setup("/Resources/Player/boy_down_1");
        down2 = setup("/Resources/Player/boy_down_2");
        left1 = setup("/Resources/Player/boy_left_1");
        left2 = setup("/Resources/Player/boy_left_2");
        right1 = setup("/Resources/Player/boy_right_1");
        right2 = setup("/Resources/Player/boy_right_2");
    }

    // This update method gets called 60 times per sec / 60FPS
    public void update(){

        // This If statement checks is any keys are pressed. If so the player moves and if it's not the player stays
        // at one position (etc. the sprites are not updating if we do not press buttons)
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){

            // Direction if statement
            // Based on this direction we check collision
            if (keyH.upPressed){
                direction = "up";
            }else if (keyH.downPressed){
                direction = "down";
            }else if (keyH.leftPressed){
                direction = "left";
            }else if (keyH.rightPressed){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this); // Since this class is subclass of Entity class

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true); // Since this is player the boolean is true
            pickupObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNpc(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            gp.keyH.enterPressed = false;

            // Player image chances in every 10 frames
            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNumber == 1){
                    spriteNumber = 2;
                }else if (spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        // This needs to be outside of key if statement
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void pickupObject(int i){

        // I picked 999 as index but basically any number is fine as long as
        // it's not used by the objects arrays index
        if (i != 999){ // If this index is 999, that means we didn't touched any object but If we did we have touched

        }
    }

    public void interactNpc(int i) {
        if (i != 999){
            if (keyH.enterPressed){ // Dialogue window opens only when you press the Enter key, while NPC collision is happening
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999){
            if (!invincible){
                life--;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {image = up1;}
                if (spriteNumber == 2) {image = up2;}
            }
            case "down" -> {
                if (spriteNumber == 1) {image = down1;}
                if (spriteNumber == 2) {image = down2;}
            }
            case "left" -> {
                if (spriteNumber == 1) {image = left1;}
                if (spriteNumber == 2) {image = left2;}
            }
            case "right" -> {
                if (spriteNumber == 1) {image = right1;}
                if (spriteNumber == 2) {image = right2;}
            }
        }

        // Visual effect, making the player half transparent when he's invincible
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY,null); // image observer

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        //  g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //  g2.setColor(Color.white);
        //  g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }
}
