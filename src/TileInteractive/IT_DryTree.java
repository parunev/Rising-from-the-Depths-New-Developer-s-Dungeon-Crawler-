package TileInteractive;

import Entity.Entity;
import Main.GamePanel;

public class IT_DryTree extends InteractiveTile{
    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/Resources/Tiles_Interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3; // How much times you have to hit the tree until you cut it
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
}
