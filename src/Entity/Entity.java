package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Stores variables that will be used in Player, Monster and NPC classes.
public class Entity {

    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea; // with this class we can create invisible or abstract rectangle used for collision
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
