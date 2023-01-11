package TileInteractive;

import Main.GamePanel;

public class IT_MetalPlate extends InteractiveTile{
    GamePanel gp;
    public static final String itName = "Metal Plate";

    public IT_MetalPlate(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        name = itName;
        down1 = setup("/Resources/Tiles_Interactive/metalplate", gp.tileSize, gp.tileSize);

        // We need to set the solidArea to 0, so you can walk through the trunks, otherwise the player will get stuck
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
