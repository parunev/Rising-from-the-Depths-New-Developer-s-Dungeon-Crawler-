package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Obj.*;
import Obj.Shields.OBJ_Shield_Oak;
import Obj.Weapons.OBJ_Sword_Rusty;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    }

    public void setDefaultValues(){

        // START POS
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        gp.currentMap = 0;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6; // 6 = 3 full
        maxMana = 4;
        mana = maxMana;
        life = maxLife; // 1 = half, 2 = full
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 50;
        currentWeapon = new OBJ_Sword_Rusty(gp);
        currentShield = new OBJ_Shield_Oak(gp);
        currentLight = null;
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defence = getDefence();

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
    }

    public void setDefaultPositions(){
        gp.currentMap = 0;
        worldX = gp.tileSize * 4;
        worldY = gp.tileSize * 3;
        direction = "down";
    }

    public void setDialogue(){
        dialogues[0][0] = "You are level " + level + " now!\n"
                + "You feel stronger!";
    }

    public void restoreStatus(){
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }

    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motionDuration = currentWeapon.motionDuration;
        motion2Duration = currentWeapon.motion2Duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence(){
        return defence = dexterity + currentShield.defenceValue;
    }

    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }

        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }

        return currentShieldSlot;
    }

    public void getImage() {
        up1 = setup("/Resources/Player/p_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Player/p_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Player/p_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Player/p_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Player/p_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Player/p_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Player/p_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Player/p_right_2", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image){
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void getAttackImage(){

        if (currentWeapon.type == type_sword && currentWeapon.name.equals("Rusty Sword")){
            attackUp1 = setup("/Resources/Player/rusty_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/Resources/Player/rusty_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/Resources/Player/rusty_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/Resources/Player/rusty_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/Resources/Player/rusty_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/Resources/Player/rusty_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/Resources/Player/rusty_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/Resources/Player/rusty_attack_right_2", gp.tileSize*2, gp.tileSize);
        } else if (currentWeapon.type == type_sword && currentWeapon.name.equals("Steel Sword")){
            attackUp1 = setup("/Resources/Player/steel_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/Resources/Player/steel_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/Resources/Player/steel_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/Resources/Player/steel_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/Resources/Player/steel_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/Resources/Player/steel_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/Resources/Player/steel_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/Resources/Player/steel_attack_right_2", gp.tileSize*2, gp.tileSize);
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
        if (currentWeapon.type == type_pickaxe){
            attackUp1 = setup("/Resources/Player/boy_pick_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/Resources/Player/boy_pick_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/Resources/Player/boy_pick_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/Resources/Player/boy_pick_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/Resources/Player/boy_pick_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/Resources/Player/boy_pick_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/Resources/Player/boy_pick_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/Resources/Player/boy_pick_right_2", gp.tileSize*2, gp.tileSize);
        }
    }

    public void getGuardImage(){
        switch (currentShield.name) {
            case "Oak Defender" -> {
                guardUp = setup("/Resources/Player/oak_guard_up", gp.tileSize, gp.tileSize);
                guardDown = setup("/Resources/Player/oak_guard_down", gp.tileSize, gp.tileSize);
                guardLeft = setup("/Resources/Player/oak_guard_left", gp.tileSize, gp.tileSize);
                guardRight = setup("/Resources/Player/oak_guard_right", gp.tileSize, gp.tileSize);
            }
            case "Iron Bulwark" -> {
                guardUp = setup("/Resources/Player/iron_guard_up", gp.tileSize, gp.tileSize);
                guardDown = setup("/Resources/Player/iron_guard_down", gp.tileSize, gp.tileSize);
                guardLeft = setup("/Resources/Player/iron_guard_left", gp.tileSize, gp.tileSize);
                guardRight = setup("/Resources/Player/iron_guard_right", gp.tileSize, gp.tileSize);
            }
        }
    }

    // This update method gets called 60 times per sec / 60FPS
    public void update() throws IOException {

        if (knockBack) {
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);

            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (knockBackDirection) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

                knockBackCounter++;
                if (knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
        }else if (attacking){
            attacking();

        } else if (keyH.spacePressed){
            guarding = true;
            guardCounter++;

        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){
            // This If statement checks is any keys are pressed. If so the player moves and if it's not the player stays
            // at one position (etc. the sprites are not updating if we do not press buttons)

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
            guarding = false;
            guardCounter = 0;

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
                transparent = false;
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
        if (!keyH.godModeOn){
            if (life <= 0){
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1; // prevents you from resetting the game immediately
                gp.stopMusic();
                gp.playSE(12);
            }
        }
    }

    public void pickupObject(int i){

        // I picked 999 as index but basically any number is fine as long as
        // it's not used by the objects arrays index
        if (i != 999){ // If this index is 999, that means we didn't touched any object but If we did we have touched

            if (gp.obj[gp.currentMap][i].type == type_pickupOnly){ // PICKUP ONLY ITEMS
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null; // DON'T FORGET THIS

            } else if (gp.obj[gp.currentMap][i].type == type_obstacle){
                if (keyH.enterPressed){
                    opened = true;
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }

            } else { // INVENTORY ITEMS
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
        if (i != 999){ // Dialogue window opens only when you press the Enter key, while NPC collision is happening
            if (gp.keyH.enterPressed) {
                attackCanceled = true;
                gp.npc[gp.currentMap][i].speak();
            }

            gp.npc[gp.currentMap][i].move(direction);
        }
    }

    public void contactMonster(int i) {
        if (i != 999){
            if (!invincible && !gp.monster[gp.currentMap][i].dying){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defence;
                if (damage < 1){
                    damage = 1;
                }

                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower){
        if (i != 999){
            if (!gp.monster[gp.currentMap][i].invincible){
                gp.playSE(5);

                if (knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

                if (gp.monster[gp.currentMap][i].offBalance){
                    attack *= 5;
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
            nextLevelExp = nextLevelExp * 6;
            maxLife += 2;
            if (level % 5 == 0){
                maxMana++;
            }
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            setDialogue();
            startDialogue(this, 0);
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == type_shield){
                currentShield = selectedItem;
                defence = getDefence();
                getGuardImage();
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

        Entity newItem = gp.eGenerator.getObject(item.name);

        // CHECK IF ITEM IS STACKABLE
        if (newItem.stackable){
            int index = searchItemInInventory(newItem.name);

            if (index != 999){ // If we already have the same item
                inventory.get(index).amount++;
                canObtain = true;

            } else { // New item so we need to check vacancy
                if (inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        } else { // NOT STACKABLE so check vacancy
            if (inventory.size() != maxInventorySize){
                inventory.add(newItem);
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
                if (guarding){
                    image = guardUp;
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
                if (guarding){
                    image = guardDown;
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
                if (guarding){
                    image = guardLeft;
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
                if (guarding){
                    image = guardRight;
                }
            }
        }

        // Visual effect, making the player half transparent when he's invincible
        if (transparent){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        if (drawing){
            g2.drawImage(image, tempScreenX, tempScreenY,null); // image observer
        }

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
