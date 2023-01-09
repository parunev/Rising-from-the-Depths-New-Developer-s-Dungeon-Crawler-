package TileInteractive;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class IT_DryTree extends InteractiveTile{
    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/Resources/Tiles_Interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 1; // How much times you have to hit the tree until you cut it // Temporary putting health to 1
    }

    // If your weapon is Axe you can cut the tree, otherwise you can't
    public boolean isCorrectItem(Entity entity){
        return entity.currentWeapon.type == type_axe;
    }

    public void playSe(){
        gp.playSE(11);
    }

    // When you cut the tree, a trunk of a tree will appear
    public InteractiveTile getDestroyedForm(){
        return new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
    }


    // WE CHOOSE COLOR, SIZE, SPEED AND MAX LIFE OF THIS DRY TREE PARTICLE
    public Color getParticleColor(){
        return new Color(65,50,30);
    }
    public int getParticleSize(){
        return 6; // 6 pixels
    }
    public int getParticleSpeed(){ // how fast the particle can fly
        return 1;
    }
    public int getParticleMaxLife(){ // this indicates how long this particle lasts
        return 20;
    }
}
