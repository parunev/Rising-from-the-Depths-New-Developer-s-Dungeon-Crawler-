package Main;

import Entity.Entity;
import Obj.*;
import Obj.Consumables.*;
import Obj.Letters.OBJ_Final_Letter;
import Obj.Letters.OBJ_Hint_Letter;
import Obj.Letters.OBJ_Motivational_Letter;
import Obj.Shields.OBJ_Shield_Iron;
import Obj.Shields.OBJ_Shield_Oak;
import Obj.Weapons.OBJ_Axe;
import Obj.Weapons.OBJ_Pickaxe;
import Obj.Weapons.OBJ_Sword_Rusty;
import Obj.Weapons.OBJ_Sword_Steel;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){
        return switch (itemName) {
            case OBJ_Axe.objName -> new OBJ_Axe(gp);
            case OBJ_BlankCandle.objName -> new OBJ_BlankCandle(gp);
            case OBJ_BlankTorch.objName -> new OBJ_BlankTorch(gp);
            case OBJ_BlueHeart.objName -> new OBJ_BlueHeart(gp);
            case OBJ_Bones.objName -> new OBJ_Bones(gp);
            case OBJ_Boots.objName -> new OBJ_Boots(gp);
            case OBJ_Chest.objName -> new OBJ_Chest(gp);
            case OBJ_Coin.objName -> new OBJ_Coin(gp);
            case OBJ_Door.objName -> new OBJ_Door(gp);
            case OBJ_Door_Iron.objName -> new OBJ_Door_Iron(gp);
            case OBJ_Fireball.objName -> new OBJ_Fireball(gp);
            case OBJ_Heart.objName -> new OBJ_Heart(gp);
            case OBJ_Key.objName -> new OBJ_Key(gp);
            case OBJ_Final_Letter.objName -> new OBJ_Final_Letter(gp);
            case OBJ_Motivational_Letter.objName -> new OBJ_Motivational_Letter(gp);
            case OBJ_Hint_Letter.objName -> new OBJ_Hint_Letter(gp);
            case OBJ_Mana_Crystal.objName -> new OBJ_Mana_Crystal(gp);
            case OBJ_Mana_Potion.objName -> new OBJ_Mana_Potion(gp);
            case OBJ_Pickaxe.objName -> new OBJ_Pickaxe(gp);
            case OBJ_Health_Potion.objName -> new OBJ_Health_Potion(gp);
            case OBJ_Rock.objName -> new OBJ_Rock(gp);
            case OBJ_Shield_Iron.objName -> new OBJ_Shield_Iron(gp);
            case OBJ_Shield_Oak.objName -> new OBJ_Shield_Oak(gp);
            case OBJ_SkullBones.objName -> new OBJ_SkullBones(gp);
            case OBJ_Speed_Up_Potion.objName -> new OBJ_Speed_Up_Potion(gp);
            case OBJ_Strength_Up_Potion.objName -> new OBJ_Strength_Up_Potion(gp);
            case OBJ_Sword_Rusty.objName -> new OBJ_Sword_Rusty(gp);
            case OBJ_Sword_Steel.objName -> new OBJ_Sword_Steel(gp);
            case OBJ_Sleeping_Tent.objName -> new OBJ_Sleeping_Tent(gp);
            default -> null;
        };
    }
}
