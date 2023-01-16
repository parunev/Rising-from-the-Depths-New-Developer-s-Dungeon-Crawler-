package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    // not player but entity because we will use this method to check not only player collision but also Monster and NPC as well
    // check if hitting solid tile or not
    public void checkTile(Entity entity){

        // based on these coordinates we will find out their column and row numbers
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        // we only need to check 2 tiles for each direction
        int tileNum1, tileNum2;

        // Use a temporal direction when it's being knock-backed
        String direction = entity.direction;
        if (entity.knockBack){
            direction = entity.knockBackDirection;
        }

        // we predict where the player will be after he moved
        switch (direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityTopRow];

                // if one of them or both are true, the player is hitting a solid tile, so he cannot move this direction
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    // In this method we check if player is hitting any object and if he is we return the index of the object,
    // so we can process the reaction accordingly.
    public int checkObject(Entity entity, boolean player){ // we're going to check if this entity is player or not

        int index = 999;

        // Use a temporal direction when it's being knock-backed
        String direction = entity.direction;
        if (entity.knockBack){
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < gp.obj[1].length ; i++) {
            if (gp.obj[gp.currentMap][i] != null){

                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Since object solid area x and y is set to 0 the latter part doesn't add anything
                // But it's included so the code still works even if you set specific values in each object

                // Get the objects solid area position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                // Simulating entity's movement and check where it will be after it moved.
                // Rectangle class has a beautiful method called "Intersects"
                // this method automatically check if two rectangles are colliding or not
                switch (direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }

                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                    if (gp.obj[gp.currentMap][i].collision) { // solid or not
                        entity.collisionOn = true;
                    }
                    if (player) { // we get the index and return it
                        index = i;
                    } // non-player characters cannot pickup objects
                }

                // We reset entity and object solid area otherwise the x and y keeps increasing
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // NPC or Monster collision
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        // Use a temporal direction when it's being knock-backed
        String direction = entity.direction;
        if (entity.knockBack){
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[1].length ; i++) {
            if (target[gp.currentMap][i] != null){

                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Since object solid area x and y is set to 0 the latter part doesn't add anything
                // But it's included so the code still works even if you set specific values in each object

                // Get the objects solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                // Simulating entity's movement and check where it will be after it moved.
                // Rectangle class has a beautiful method called "Intersects"
                // this method automatically check if two rectangles are colliding or not
                switch (direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    // If the intersected target is equal to this entity then collision doesn't happen
                    // This way we can avoid this entity to include itself as a collision target
                    if (target[gp.currentMap][i] != entity){
                        entity.collisionOn = true;
                    }
                    index = i;
                }
                // We reset entity and object solid area otherwise the x and y keeps increasing
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // Similar to other checks but we don't scan array, and we don't return any indexes
    public boolean checkPlayer(Entity entity){

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
