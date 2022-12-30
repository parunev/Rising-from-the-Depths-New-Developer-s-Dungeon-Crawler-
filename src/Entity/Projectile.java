package Entity;

import Main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife; // Reset the life to the max value everytime you shoot it
    }
    public void update(){

        if (user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            if (monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, attack);
                alive = false; // If the projectile hits a monster, it dies (disappears)
            }
        }
        if (user != gp.player){

        }
        // Just like other NPCs or monsters, projectiles move based on its speed and direction
        switch (direction) {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }

        // Once you shoot a projectile it gradually loses its life
        // and when it hits zero the projectile disappears otherwise it flies forever
        // Since projectile life is 80 it means after 80frames it disappears
        life--;
        if (life <= 0){
            alive = false;
        }

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