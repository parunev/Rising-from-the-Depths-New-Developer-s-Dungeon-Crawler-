package Obj;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject{

    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "Door";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Objects/door.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
