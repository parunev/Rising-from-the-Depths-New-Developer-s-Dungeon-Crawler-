package TileInteractive;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    // OVERRIDE METHODS
    public boolean isCorrectItem(Entity entity){return false;}
    public void playSe(){}
    public InteractiveTile getDestroyedForm(){return null;}

    public void update(){
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            g2.drawImage(down1, screenX, screenY, null);
        }
    }
}
