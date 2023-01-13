package Main;

import Entity.Entity;
import Obj.*;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        // You have to be really precise about these names otherwise it won't parse anything
        // If you change an item name in their class you need to change the name here too
        // Not really flexible solution, but it works for now
        return switch (itemName) {
            case OBJ_Axe.objName -> new OBJ_Axe(gp);
            case OBJ_BlueHeart.objName -> new OBJ_BlueHeart(gp);
            case OBJ_Boots.objName -> new OBJ_Boots(gp);
            case OBJ_Chest.objName -> new OBJ_Chest(gp);
            case OBJ_Coin_Bronze.objName -> new OBJ_Coin_Bronze(gp);
            case OBJ_Door.objName -> new OBJ_Door(gp);
            case OBJ_Door_Iron.objName -> new OBJ_Door_Iron(gp);
            case OBJ_Fireball.objName -> new OBJ_Fireball(gp);
            case OBJ_Heart.objName -> new OBJ_Heart(gp);
            case OBJ_Key.objName -> new OBJ_Key(gp);
            case OBJ_Lantern.objName -> new OBJ_Lantern(gp);
            case OBJ_Mana_Crystal.objName -> new OBJ_Mana_Crystal(gp);
            case OBJ_Pickaxe.objName -> new OBJ_Pickaxe(gp);
            case OBJ_Potion_Red.objName -> new OBJ_Potion_Red(gp);
            case OBJ_Rock.objName -> new OBJ_Rock(gp);
            case OBJ_Shield_Blue.objName -> new OBJ_Shield_Blue(gp);
            case OBJ_Shield_Wood.objName -> new OBJ_Shield_Wood(gp);
            case OBJ_Sword_Normal.objName -> new OBJ_Sword_Normal(gp);
            case OBJ_Tent.objName -> new OBJ_Tent(gp);
            default -> null;
        };
    }
}
