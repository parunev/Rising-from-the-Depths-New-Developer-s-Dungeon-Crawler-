package Obj;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObject{

    public OBJ_Boots(){
        name = "Boots";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Objects/boots.png")));
        }catch (
                IOException e){
            e.printStackTrace();
        }
    }
}
