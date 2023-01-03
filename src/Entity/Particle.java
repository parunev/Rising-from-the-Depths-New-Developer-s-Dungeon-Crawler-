package Entity;

import Main.GamePanel;

import java.awt.*;

public class Particle extends Entity{

    Entity generator; // the entity that produces the particle
    Color color;
    int size;
    int xd;
    int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);

        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        int offset = (gp.tileSize/2) - size/2;

        // If we're cutting a drytree somewhere this is going to be the particle generator
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }

    public void update(){
        life--;

        // If the particle life is the half of its maxLife or less it adds to the yd value
        // making the particle look like it's falling to the ground
        if (life < maxLife/3){
            yd++;
        }

        worldX += xd * speed;
        worldY += yd * speed;

        if (life == 0){
            alive = false;
        }
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size); // rectangle as particle, sprite can also be used with g2.drawImage
    }
}
