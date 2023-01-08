package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Obj.OBJ_Fireball;
import Obj.OBJ_Key;
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
    public boolean lightUpdated = false;

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
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues(){

        // Player starting position on world map
        // You can also type worldX = 1000, it doesn't really matter works fine both ways.
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down"; //any directions are fine

        // PLAYER STATUS
        level = 1;
        maxLife = 6; // 6 life means 3 full hearts
        maxMana = 4;
        mana = maxMana;
        life = maxLife; // 1 life means half heart, 2 life means 1 heart
        strength = 1; // The more strength he has, the more damage he gives
        dexterity = 1; // The more dexterity he has, the less damage he receives
        exp = 0;
        nextLevelExp = 5; // how much exp is needed for next level
        coin = 500;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon
        defence = getDefence(); // The total defence value is decided by dexterity and shield
    }

    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }

    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
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

        if (currentWeapon.type == type_sword){
            attackUp1 = setup("/Resources/Player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/Resources/Player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/Resources/Player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/Resources/Player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/Resources/Player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/Resources/Player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/Resources/Player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/Resources/Player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
        }

        if (currentWeapon.type == type_axe){
            attackUp1 = setup("/Resources/Player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/Resources/Player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/Resources/Player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/Resources/Player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/Resources/Player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/Resources/Player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/Resources/Player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/Resources/Player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
        }
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

            // CHECK INTERACTIVE TILE COLLISION
            gp.cChecker.checkEntity(this, gp.iTile);

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

        // Seconds condition means that you cannot shoot another projectile if there's already one alive
        // This means you can shoot only one at a time
        if (gp.keyH.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 30 && projectile.hasResource(this)){

            // SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST(MANA)
            projectile.subtractResource(this);

            // CHECK VACANCY
            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;

            gp.playSE(10);
        }

        // This needs to be outside of key if statement
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if (life > maxLife){
            life = maxLife;
        }
        if (mana > maxMana){
            mana = maxMana;
        }
        if (life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1; // prevents you from resetting the game immediately
            gp.stopMusic();
            gp.playSE(12);
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
            damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);
            
            int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

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

            if (gp.obj[gp.currentMap][i].type == type_pickupOnly){ // PICKUP ONLY ITEMS
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null; // DON'T FORGET THIS

            }else if (gp.obj[gp.currentMap][i].type == type_obstacle){
                if (keyH.enterPressed){
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }

            } else{ // INVENTORY ITEMS
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i])){ // check if inventory is full
                    gp.playSE(1);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                }else {
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null; // DON'T FORGET THIS
            }
        }
    }

    public void interactNpc(int i) {
        if (gp.keyH.enterPressed){
            if (i != 999){// Dialogue window opens only when you press the Enter key, while NPC collision is happening
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999){
            if (!invincible && !gp.monster[gp.currentMap][i].dying){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defence;
                if (damage < 0){
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack, int knockBackPower){
        if (i != 999){
            if (!gp.monster[gp.currentMap][i].invincible){
                gp.playSE(5);

                if (knockBackPower > 0){
                    knockBack(gp.monster[gp.currentMap][i], knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defence;
                if (damage < 0){
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void knockBack(Entity entity, int knockBackPower){
        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;

    }

    public void damageInteractiveTile(int i){
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible
                && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible){

            gp.iTile[gp.currentMap][i].playSe();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // Generate particles
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int i){
        if (i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }

    public void checkLevelUp(){
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            dexterity = getDefence();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }

            if (selectedItem.type == type_shield){
                currentShield = selectedItem;
                defence = getDefence();
            }

            if (selectedItem.type == type_light){
                if (currentLight == selectedItem){
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }

            if (selectedItem.type == type_consumable){
                if (selectedItem.use(this)){
                    if (selectedItem.amount > 1){
                        selectedItem.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    // Whenever we get an item we call this method and pass the item name and scan our inventory
    // and check if the same item is already in our inventory, and if you have the same item
    // return the index of the item
    // This method also can be used when you want to check if player has a certain quest item etc.
    public int searchItemInInventory(String itemName){
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }

        return itemIndex;
    }

    public boolean canObtainItem(Entity item){
        boolean canObtain = false;

        // CHECK IF ITEM IS STACKABLE
        if (item.stackable){
            int index = searchItemInInventory(item.name);

            if (index != 999){ // If we already have the same item
                inventory.get(index).amount++;
                canObtain = true;

            } else { // New item so we need to check vacancy
                if (inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else { // NOT STACKABLE so check vacancy
            if (inventory.size() != maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }

        return canObtain;
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
