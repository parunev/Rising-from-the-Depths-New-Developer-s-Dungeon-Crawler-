package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    // Where we draw player on the screen
    public final int screenX;
    public final int screenY;
    int hasKey = 0; // Indicates how many keys the player has

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
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
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_down_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Player/boy_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // This update method gets called 60 times per sec / 60FPS
    public void update(){

        // This If statement checks is any keys are pressed. If so the player moves and if it's not the player stays
        // at one position (etc. the sprites are not updating if we do not press buttons)
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            // Direction if statement
            // Based on this direction we check collision
            if (keyH.upPressed){
                direction = "up";
            }else if (keyH.downPressed){
                direction = "down";
            }else if (keyH.leftPressed){
                direction = "left";
            }else {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this); // Since this class is subclass of Entity class

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true); // Since this is player the boolean is true
            pickupObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

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
    }

    public void pickupObject(int i){

        // I picked 999 as index but basically any number is fine as long as
        // it's not used by the objects arrays index

        if (i != 999){ // If this index is 999, that means we didn't touched any object but If we did we have touched
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key" -> {
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;// delete the object we just touched
                    System.out.println("Key: " + hasKey);
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null; // door will disappear (we opened it)
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                }
                case "Boots" -> {
                    gp.playSE(2);
                    speed += 2; // boots gives us more speed (power up item), you can make it faster
                    gp.obj[i] = null; // keep in mind faster moving leads to controls becoming difficult
                }
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // image observer
    }
}
