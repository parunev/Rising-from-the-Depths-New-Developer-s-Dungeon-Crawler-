package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Obj.OBJ_Shield_Wood;
import Obj.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyH;

    // Where we draw player on the screen
    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;

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

        // Player attack area
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){

        // Player starting position on world map
        // You can also type worldX = 1000, it doesn't really matter works fine both ways.
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; //any directions are fine

        // PLAYER STATUS
        level = 1;
        maxLife = 6; // 6 life means 3 full hearts
        life = maxLife; // 1 life means half heart, 2 life means 1 heart
        strength = 1; // The more strength he has, the more damage he gives
        dexterity = 1; // The more dexterity he has, the less damage he receives
        exp = 0;
        nextLevelExp = 5; // how much exp is needed for next level
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon
        defence = getDefence(); // The total defence value is decided by dexterity and shield
    }

    public int getAttack(){
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence(){
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImage(){
        up1 = setup("/Resources/Player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/Resources/Player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/Resources/Player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/Resources/Player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/Resources/Player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/Resources/Player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/Resources/Player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/Resources/Player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/Resources/Player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    // This update method gets called 60 times per sec / 60FPS
    public void update(){
        if (attacking){
            attacking();

            // This If statement checks is any keys are pressed. If so the player moves and if it's not the player stays
            // at one position (etc. the sprites are not updating if we do not press buttons)
        }else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){

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

            if (keyH.enterPressed && !attackCanceled){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
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

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5){ // showing attacking pic 1 during the first 5 frames
            spriteNumber = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25){ // showing pic 2 during the next  6-25 frames
            spriteNumber = 2;

            // Save the current world x and y, solid area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's world x/y for the attack area
            // Based on players direction we offset player world y or x by his attack area
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // Restore his original position and the solid area after checking collision
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25){ // reset
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickupObject(int i){

        // I picked 999 as index but basically any number is fine as long as
        // it's not used by the objects arrays index
        if (i != 999){ // If this index is 999, that means we didn't touched any object but If we did we have touched

        }
    }

    public void interactNpc(int i) {
        if (gp.keyH.enterPressed){
            if (i != 999){// Dialogue window opens only when you press the Enter key, while NPC collision is happening
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999){
            if (!invincible){
                gp.playSE(6);
                life--;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if (i != 999){
            if (!gp.monster[i].invincible){
                gp.playSE(5);
                gp.monster[i].life--;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if (!attacking){
                    if (spriteNumber == 1) {image = up1;}
                    if (spriteNumber == 2) {image = up2;}
                }
                if (attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) {image = attackUp1;}
                    if (spriteNumber == 2) {image = attackUp2;}
                }
            }
            case "down" -> {
                if (!attacking){
                    if (spriteNumber == 1) {image = down1;}
                    if (spriteNumber == 2) {image = down2;}
                }
                if (attacking){
                    if (spriteNumber == 1) {image = attackDown1;}
                    if (spriteNumber == 2) {image = attackDown2;}
                }
            }
            case "left" -> {
                if (!attacking){
                    if (spriteNumber == 1) {image = left1;}
                    if (spriteNumber == 2) {image = left2;}
                }
                if (attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) {image = attackLeft1;}
                    if (spriteNumber == 2) {image = attackLeft2;}
                }
            }
            case "right" -> {
                if (!attacking){
                    if (spriteNumber == 1) {image = right1;}
                    if (spriteNumber == 2) {image = right2;}
                }
                if (attacking){
                    if (spriteNumber == 1) {image = attackRight1;}
                    if (spriteNumber == 2) {image = attackRight2;}
                }
            }
        }

        // Visual effect, making the player half transparent when he's invincible
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null); // image observer

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        //  g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //  g2.setColor(Color.white);
        //  g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }
}
