package Obj;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{

    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Objects/chest.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
