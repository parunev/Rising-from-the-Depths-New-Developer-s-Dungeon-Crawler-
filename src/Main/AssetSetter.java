package Main;

import Entity.Entity;
import Entity.NPC_DungeonKeeper;
import Entity.NPC_Merchant;
import Monster.*;
import Obj.*;
import Obj.Consumables.*;
import Obj.Letters.OBJ_Final_Letter;
import Obj.Letters.OBJ_Hint_Letter;
import Obj.Letters.OBJ_Motivational_Letter;
import Obj.Shields.OBJ_Shield_Iron;
import Obj.Weapons.OBJ_Sword_Steel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        int i = 0;

        // MAP 0
        objSetter(new OBJ_Chest(gp), mapNum, i, 8, 21);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        i++;

        objSetter(new OBJ_Bones(gp), mapNum, i, 3, 5);i++; objSetter(new OBJ_Door(gp), mapNum, i, 31, 5);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 6, 5);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 8, 14);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 27, 2);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 19, 31);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 42, 13);i++; objSetter(new OBJ_Coin(gp), mapNum, i, 34, 3);i++;
        objSetter(new OBJ_Coin(gp), mapNum, i, 33, 2);

        // MAP 1
        mapNum = 1;
        i = 0;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 8, 3);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 13, 7);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 7, 15);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 16, 20);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 26, 21);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 42, 22);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 48, 23);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 33, 38);i++;

        objSetter(new OBJ_Bones(gp), mapNum, i, 43, 33);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 19, 38);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 7, 41);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 26, 42);i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 43, 36);
        gp.obj[mapNum][i].setLoot(new OBJ_Strength_Up_Potion(gp));
        i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 35, 2);
        gp.obj[mapNum][i].setLoot(new OBJ_Sleeping_Tent(gp));

        // MAP 2
        mapNum = 2;
        i = 0;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 11, 7);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 6, 8);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 13, 13);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 22, 15);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 23, 25);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 27, 21);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 37, 21);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 32, 34);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 23, 33);i++;

        objSetter(new OBJ_Bones(gp), mapNum, i, 25, 18);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 12, 8);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 5, 9);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 23, 26);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 46, 28);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 34, 38);i++;

        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 26, 20);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 22, 20);i++;

        objSetter(new OBJ_BlankCandle(gp), mapNum, i, 33, 37);i++; objSetter(new OBJ_BlankCandle(gp), mapNum, i, 47, 23);i++;
        objSetter(new OBJ_BlankCandle(gp), mapNum, i, 33, 23);i++; objSetter(new OBJ_BlankCandle(gp), mapNum, i, 55, 23);i++;

        objSetter(new OBJ_Final_Letter(gp), mapNum, i, 12, 13); i++;
        objSetter(new OBJ_Motivational_Letter(gp), mapNum, i, 29, 24); i++;
        objSetter(new OBJ_Hint_Letter(gp), mapNum, i, 46, 36); i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 17, 13);
        gp.obj[mapNum][i].setLoot(new OBJ_Sword_Steel(gp));
        i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 22, 34);
        gp.obj[mapNum][i].setLoot(new OBJ_Shield_Iron(gp));

        // MAP 3
        mapNum = 3;
        i = 0;

        objSetter(new OBJ_Door(gp), mapNum, i, 9, 45);i++;
        objSetter(new OBJ_Door(gp), mapNum, i, 39, 45);i++;
        objSetter(new OBJ_Chest(gp), mapNum, i, 9, 44);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        i++;

        objSetter(new OBJ_SkullBones(gp), mapNum, i, 22, 8);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 29, 11);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 25, 15);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 45, 26);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 39, 20);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 48, 20);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 13, 26);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 3, 25);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 3, 20);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 27, 47);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 19, 38);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 45, 43);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 45, 37);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 45, 6);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 40, 11);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 48, 11);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 22, 20);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 30, 22);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 21, 24);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 5, 33);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 8, 38);i++; objSetter(new OBJ_SkullBones(gp), mapNum, i, 3, 46);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 36, 32);i++;

        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 22, 19);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 22, 25);i++;
        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 9, 25);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 11, 37);i++;
        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 7, 45);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 23, 45);i++;
        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 33, 41);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 42, 41);i++;
        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 38, 31);i++; objSetter(new OBJ_BlankTorch(gp), mapNum, i, 35, 22);i++;

        objSetter(new OBJ_Bones(gp), mapNum, i, 41, 27);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 48, 22);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 32, 41);i++; objSetter(new OBJ_Bones(gp), mapNum, i, 27, 37);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 11, 32);i++;

        objSetter(new OBJ_Motivational_Letter(gp), mapNum, i, 41, 37); i++;
        objSetter(new OBJ_Final_Letter(gp), mapNum, i, 15, 27); i++;
        objSetter(new OBJ_Hint_Letter(gp), mapNum, i, 36, 43);

        // MAP 4
        mapNum = 4;
        i = 0;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 5, 3); i++;objSetter(new OBJ_Bones(gp), mapNum, i, 4, 2); i++;
        objSetter(new OBJ_BlankCandle(gp), mapNum, i, 1, 2); i++;objSetter(new OBJ_BlankTorch(gp), mapNum, i, 2, 9); i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 4, 32); i++;objSetter(new OBJ_BlankTorch(gp), mapNum, i, 16, 43); i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 17, 40); i++;objSetter(new OBJ_BlankTorch(gp), mapNum, i, 14, 23); i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 17, 14); i++;objSetter(new OBJ_BlankTorch(gp), mapNum, i, 13, 3); i++;
        objSetter(new OBJ_BlankTorch(gp), mapNum, i, 15, 3); i++;objSetter(new OBJ_SkullBones(gp), mapNum, i, 22, 5); i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 26, 4); i++;objSetter(new OBJ_Bones(gp), mapNum, i, 24, 12); i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 27, 33); i++;objSetter(new OBJ_BlankTorch(gp), mapNum, i, 23, 43); i++;
        objSetter(new OBJ_Final_Letter(gp), mapNum, i, 16, 45); i++;objSetter(new OBJ_Motivational_Letter(gp), mapNum, i, 13, 5); i++;
        objSetter(new OBJ_Hint_Letter(gp), mapNum, i, 23, 45); i++;
        objSetter(new OBJ_Chest(gp), mapNum, i, 7, 40);
        gp.obj[mapNum][i].setLoot(new OBJ_Strength_Up_Potion(gp));i++;
        objSetter(new OBJ_Chest(gp), mapNum, i, 13, 23);
        gp.obj[mapNum][i].setLoot(new OBJ_Speed_Up_Potion(gp)); i++;
        objSetter(new OBJ_Door_Iron(gp), mapNum, i, 39, 12);
    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        // MAP 0
        npcSetter(new NPC_DungeonKeeper(gp), mapNum, i, 3, 3);i++;
        npcSetter(new NPC_Merchant(gp), mapNum, i, 31, 2);

        // MAP 1
        mapNum = 1;
        i = 0;

        npcSetter(new NPC_DungeonKeeper(gp), mapNum, i, 12, 6);i++;
        npcSetter(new NPC_Merchant(gp), mapNum, i, 5, 26);

        // MAP 2
        mapNum = 2;
        i = 0;
        npcSetter(new NPC_DungeonKeeper(gp), mapNum, i, 8, 5);

        // MAP 3
        mapNum = 3;
        i = 0;
        npcSetter(new NPC_DungeonKeeper(gp), mapNum, i, 10, 4);i++;
        npcSetter(new NPC_Merchant(gp),mapNum, i, 10, 32);

        // MAP 4
        mapNum = 4;
        i = 0;
        npcSetter(new NPC_DungeonKeeper(gp), mapNum, i, 2, 3);i++;
        npcSetter(new NPC_Merchant(gp),mapNum, i, 44, 45);

    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;

        monsterSetter(new MON_Swampy(gp), mapNum, i, 9, 22);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 13, 22);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 5, 25);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 16, 26);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 15, 31);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 21, 33);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 47, 33);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 41, 36);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 39, 33);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 34, 27);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 27, 27);i++; monsterSetter(new MON_Swampy(gp), mapNum, i, 32, 24);

        // MAP 1
        mapNum = 1;
        i = 0;

        monsterSetter(new MON_Muddy(gp), mapNum, i, 2, 14); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 3, 17);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 5, 19); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 14, 19); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 15, 16); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 21, 12); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 23, 4); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 34, 4);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 39, 2); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 40, 13); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 36, 20); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 42, 19); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 35, 27); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 23, 27); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 14, 30); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 25, 34); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 21, 37); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 29, 38); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 34, 34); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 36, 37); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 42, 39); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 7, 42);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 26, 43); i++; monsterSetter(new MON_Muddy(gp), mapNum, i, 30, 46);

        // MAP 3
        mapNum = 3;
        i = 0;

        monsterSetter(new MON_Muddy(gp), mapNum, i,24, 8); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,28,8); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,23, 9); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,40,9); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,23,11); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,47,11); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,24,12); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,41,12); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,27,13); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,46,8); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,28,12); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,43,9); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,29,9);  i++;

        monsterSetter(new MON_Swampy(gp), mapNum, i,21, 20); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,31,20); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,23, 22); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,27,23); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,25,24); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,29,25); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,22,26); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,31,27); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,26,26); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,44,23); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,38,21); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,47,24); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,38,23); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,46,27); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,38,27); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,43,27); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,40,26); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,47,20); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,42,23); i++;

        monsterSetter(new MON_Small_Orc(gp), mapNum, i,5,26); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,15,20); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,4,27); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,15,39); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,4,21); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,15,46); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,11,20); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,26,46);

        // BOSS MAP
        mapNum = 4;
        i = 0;

        monsterSetter(new MON_Muddy(gp), mapNum, i,1, 14); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,1, 22); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,5, 13); i++;  monsterSetter(new MON_Swampy(gp), mapNum, i,6, 22); i++;
        monsterSetter(new MON_Bat(gp), mapNum, i,4, 15); i++; monsterSetter(new MON_Bat(gp), mapNum, i,4, 25); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,1, 34); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,2, 41); i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i,6, 33); i++; monsterSetter(new MON_Swampy(gp), mapNum, i,5, 45); i++;
        monsterSetter(new MON_Bat(gp), mapNum, i,4, 35); i++; monsterSetter(new MON_Bat(gp), mapNum, i,7, 42); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,11, 34); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,11, 23); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i,16, 33); i++; monsterSetter(new MON_Muddy(gp), mapNum, i,16, 23); i++;
        monsterSetter(new MON_Bat(gp), mapNum, i,13, 30); i++; monsterSetter(new MON_Bat(gp), mapNum, i,13, 20); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,11, 14); i++;monsterSetter(new MON_Muddy(gp), mapNum, i,15, 12); i++;
        monsterSetter(new MON_Bat(gp), mapNum, i,15, 10); i++;

        monsterSetter(new MON_Small_Orc(gp), mapNum, i,27, 3); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,24, 5); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,22, 12); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,27, 13); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,22, 23); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,26, 23); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,27, 30); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,21, 33); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,26, 35); i++; monsterSetter(new MON_Small_Orc(gp), mapNum, i,35, 43); i++;
        monsterSetter(new MON_Small_Orc(gp), mapNum, i,37, 43); i++;

        monsterSetter(new MON_Lyuborge(gp), mapNum, i, 38, 14);
    }
    public void setInteractiveTiles(){
        int mapNum = 0;
        int i = 0;

//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21);i++;
//
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 18, 40);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 40);i++;
//
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25, 27);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 27);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 28);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 29);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 31);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 31);i++;
//        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 30);
//
//        mapNum = 2;
//        i = 0;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 31);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 32);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 34);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 22);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 24);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 18);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 19);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 20);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 21);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 13);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 14);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 28);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 30, 28);i++;
//        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 32, 28);i++;
//
//        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 22);i++;
//        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 8, 17);i++;
//        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 39, 31);
    }
    public void setDungeonRocks(){
        int mapNum = 2;
        int i = 0;

        // gp.npc[mapNum][i] = new NPC_BigRock(gp);
        // gp.npc[mapNum][i].worldX = gp.tileSize * 20;
        // gp.npc[mapNum][i].worldY = gp.tileSize * 25;

        // gp.npc[mapNum][i] = new NPC_BigRock(gp);
        // gp.npc[mapNum][i].worldX = gp.tileSize * 11;
        // gp.npc[mapNum][i].worldY = gp.tileSize * 19;
        // i++;

        // gp.npc[mapNum][i] = new NPC_BigRock(gp);
        // gp.npc[mapNum][i].worldX = gp.tileSize * 23;
        // gp.npc[mapNum][i].worldY = gp.tileSize * 14;
    }

    public void monsterSetter(Entity entity, int mapNum, int index, int col, int row){
        gp.monster[mapNum][index] = entity;
        gp.monster[mapNum][index].worldX = gp.tileSize * col;
        gp.monster[mapNum][index].worldY = gp.tileSize * row;
    }
    public void npcSetter(Entity entity, int mapNum, int index, int col, int row){
        gp.npc[mapNum][index] = entity;
        gp.npc[mapNum][index].worldX = gp.tileSize * col;
        gp.npc[mapNum][index].worldY = gp.tileSize * row;
    }
    public void objSetter(Entity entity, int mapNum, int index, int col, int row){
        gp.obj[mapNum][index] = entity;
        gp.obj[mapNum][index].worldX = gp.tileSize * col;
        gp.obj[mapNum][index].worldY = gp.tileSize * row;
    }
}