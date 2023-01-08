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
    public boolean onPath = false;
    public boolean knockBack = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int knockBackCounter = 0;

    // CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
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
    public Entity currentLight;
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
    public int knockBackPower;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius; // radius of the light circle, in-case you want to add various light items

    // TYPE
    public int type; // 0 = player, 1 = NPC, 2 = monster
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    // BLUEPRINTS
    public int getLeftX(){return worldX + solidArea.x;}
    public int getRightX(){return worldX + solidArea.x + solidArea.width;}
    public int getTopY(){return worldY + solidArea.y;}
    public int getBottomY(){return worldY + solidArea.y + solidArea.height;}
    public int getCol(){return (worldX + solidArea.x) / gp.tileSize;}
    public int getRow(){return (worldY + solidArea.y) / gp.tileSize;}

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

    public void interact(){}

    public boolean use(Entity entity){return false;}

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

    public void checkCollision(){
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
    }

    public void update(){
        if (knockBack){
            checkCollision();
            if (collisionOn){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }else {
                switch (gp.player.direction){ // knocking back to where the player is facing
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

                knockBackCounter++;
                if (knockBackCounter == 10){ // the more you increase the number the bigger the knock-back distance
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }

        }else{
            // We created this method in Old Man class too and if the subclass has the same method it takes a priority
            setAction();

            checkCollision();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
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

    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow);

        // If it returns true this means we found the path, so we can guide this entity to the goal
        if (gp.pFinder.search()){
            // Next worldX and worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            // Based on the current NPCs position, find out the relative direction of the next node

            // If these conditions are matched the entity can go to the direction
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                // left or right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX){
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX){
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX){
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX){
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn){
                    direction = "right";
                }
            }

            // If goal is reached, stop the search
            // If you want the NPC to follow the player the next lines should be disabled(comments)
            // Because if the player is the goal whenever he touches us, or we touch him the goal is reached and
            // the NPC stops following us

             int nextCol = gp.pFinder.pathList.get(0).col;
             int nextRow = gp.pFinder.pathList.get(0).row;

             if (nextCol == goalCol && nextRow == goalRow){
                 onPath = false;
             }
        }
    }

    public int getDetected(Entity user, Entity[][] target, String targetName){
        int index = 999;

        // Check the surrounding objects
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up" -> nextWorldY = user.getTopY() - user.speed;
            case "down" -> nextWorldY = user.getBottomY() + user.speed;
            case "left" -> nextWorldX = user.getLeftX() - user.speed;
            case "right" -> nextWorldX = user.getRightX() + user.speed;
        }

        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length ; i++) {
            if (target[gp.currentMap][i] != null){
                if (target[gp.currentMap][i].getCol() == col &&
                target[gp.currentMap][i].getRow() == row &&
                target[gp.currentMap][i].name.equals(targetName)){

                    index = i;
                    break;
                }
            }
        }

        return index;
    }
}
