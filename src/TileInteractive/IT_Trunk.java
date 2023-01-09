package TileInteractive;

import Main.GamePanel;

public class IT_Trunk extends InteractiveTile {
    GamePanel gp;

    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/Resources/Tiles_Interactive/trunk", gp.tileSize, gp.tileSize);

        // We need to set the solidArea to 0, so you can walk through the trunks, otherwise the player will get stuck
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
