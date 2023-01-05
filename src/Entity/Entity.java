package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

// Stores variables that will be used in Player, Monster and NPC classes.
public class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2
            , attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48); // with this class we can create invisible or abstract rectangle used for collision
    public Rectangle attackArea = new Rectangle(0,0,0,0); // entities attack area
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];

    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNumber = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    // CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    // ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenceValue;
    public String description = "";
    public int useCost; // how much mana it costs to shoot a projectile
    public int price; // item price

    // TYPE
    public int type; // 0 = player, 1 = NPC, 2 = monster
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction() {}

    public void damageReaction(){}

    public void speak() {
        // If there is no text we go back to index zero preventing a NullPointer
        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    public void use(Entity entity){}

    public void checkDrop(){}

    public void dropItem(Entity droppedItem){
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster worldX
                gp.obj[gp.currentMap][i].worldY = worldY; //
                break;
            }
        }
    }

    // BLUEPRINTS, we always override them
    public Color getParticleColor(){
        return null;
    }
    public int getParticleSize(){
        return 0;
    }
    public int getParticleSpeed(){
        return 0;
    }
    public int getParticleMaxLife(){
        return 0;
    }
    //

    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        // Last two parameters xd, yd means which direction this particle will move to
        // The direction and speed of the particle can be adjusted
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife,-2, -1); // top left
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife,2, -1); // top right
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife,-2, 1); // down left
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife,2, 1); // down right
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void update(){
        // We created this method in Old Man class too and if the subclass has the same method it takes a priority
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this); // going to pass the Old Man class as Entity
        gp.cChecker.checkObject(this, false); // checking if the npc is hitting doors maybe
        gp.cChecker.checkEntity(this, gp.npc); // collision happens between monsters and NPCs
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile); // collision happens between monsters and interactive tiles
        boolean contactPlayer = gp.cChecker.checkPlayer(this); // checking if the npc is hitting player

        // If this class is monster, and we contacted player we deal damage
        if (this.type == type_monster && contactPlayer){
            damagePlayer(attack);
        }

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
        if (spriteCounter > 24){
            if (spriteNumber == 1){
                spriteNumber = 2;
            }else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack){
        if (!gp.player.invincible){ // we can give damage
            gp.playSE(6);

            int damage = attack - gp.player.defence;
            if (damage < 0){
                damage = 0;
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath +".png")));
            image = uTool.scaleImage(image, width, height);

        }catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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

            // Monster HP bar
            if (type == 2 && hpBarOn){

                double oneScale = (double) gp.tileSize/maxLife;
                double hpBarValue = oneScale * life; // bar length

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                // The hp bar will not disappear as long we engage
                hpBarCounter++;
                if (hpBarCounter > 600){ // 10 seconds
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }
            if (dying){
                //dying animation, like blinking effect
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1F);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5; // adjust time from here

        // BLINKING DYING ANIMATION
        if (dyingCounter <= i){changeAlpha(g2,0f);}
        if (dyingCounter > i && dyingCounter <= i * 2){changeAlpha(g2,1f);}
        if (dyingCounter > i * 2 && dyingCounter <= i * 3){changeAlpha(g2,0f);}
        if (dyingCounter > i * 3 && dyingCounter <= i * 4){changeAlpha(g2,1f);}
        if (dyingCounter > i * 4 && dyingCounter <= i * 5){changeAlpha(g2,0f);}
        if (dyingCounter > i * 5 && dyingCounter <= i * 6){changeAlpha(g2,1f);}
        if (dyingCounter > i * 6 && dyingCounter <= i * 7){changeAlpha(g2,0f);}
        if (dyingCounter > i * 7 && dyingCounter <= i * 8){changeAlpha(g2,1f);}
        if (dyingCounter > i * 8){
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
}
