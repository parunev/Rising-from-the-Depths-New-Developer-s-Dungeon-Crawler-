package TileInteractive;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class IT_DestructibleWall extends InteractiveTile{

    GamePanel gp;

    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/Resources/Tiles_Interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity){
        return entity.currentWeapon.type == type_pickaxe;
    }

    public void playSe(){
        gp.playSE(19);
    }

    public InteractiveTile getDestroyedForm(){
        return null;
    }


    // WE CHOOSE COLOR, SIZE, SPEED AND MAX LIFE OF THIS DRY TREE PARTICLE
    public Color getParticleColor(){
        return new Color(65,65,65);
    }
    public int getParticleSize(){
        return 6;
    }
    public int getParticleSpeed(){ // how fast the particle can fly
        return 1;
    }
    public int getParticleMaxLife(){
        return 20;
    }
}
