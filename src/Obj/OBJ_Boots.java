package Obj;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObject{

    GamePanel gp;
    public OBJ_Boots(GamePanel gp){

        this.gp = gp;
        name = "Boots";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Objects/boots.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
