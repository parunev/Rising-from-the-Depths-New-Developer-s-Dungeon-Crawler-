package Entity;

import Main.GamePanel;

public class PlayerDummy extends Entity{

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);

        name = npcName;
        getImage();
    }

    public void getImage() {
        up1 = setup("/Resources/Player/p_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Resources/Player/p_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Resources/Player/p_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Resources/Player/p_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Resources/Player/p_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Resources/Player/p_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Resources/Player/p_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Resources/Player/p_right_2", gp.tileSize, gp.tileSize);
    }
}
