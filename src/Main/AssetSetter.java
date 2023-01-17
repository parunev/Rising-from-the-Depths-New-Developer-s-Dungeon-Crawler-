package Main;

import Entity.Entity;
import Entity.NPC_DungeonKeeper;
import Entity.NPC_Merchant;
import Monster.*;
import Obj.*;

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

        objSetter(new OBJ_Bones(gp), mapNum, i, 3, 5);i++;
        objSetter(new OBJ_Door(gp), mapNum, i, 31, 5);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 6, 5);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 8, 14);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 27, 2);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 19, 31);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 42, 13);i++;
        objSetter(new OBJ_Coin(gp), mapNum, i, 34, 3);i++;
        objSetter(new OBJ_Coin(gp), mapNum, i, 33, 2);

        // MAP 1
        mapNum = 1;
        i = 0;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 8, 3);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 13, 7);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 7, 15);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 16, 20);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 26, 21);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 42, 22);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 48, 23);i++;
        objSetter(new OBJ_SkullBones(gp), mapNum, i, 33, 38);i++;

        objSetter(new OBJ_Bones(gp), mapNum, i, 43, 33);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 19, 38);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 7, 41);i++;
        objSetter(new OBJ_Bones(gp), mapNum, i, 26, 42);i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 43, 36);
        gp.obj[mapNum][i].setLoot(new OBJ_Shield_Blue(gp));
        i++;

        objSetter(new OBJ_Chest(gp), mapNum, i, 35, 2);
        gp.obj[mapNum][i].setLoot(new OBJ_Sleeping_Tent(gp));
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
       // setDungeonRocks();
    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;

        monsterSetter(new MON_Swampy(gp), mapNum, i, 9, 22);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 13, 22);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 5, 25);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 16, 26);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 15, 31);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 21, 33);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 47, 33);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 41, 36);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 39, 33);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 34, 27);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 27, 27);i++;
        monsterSetter(new MON_Swampy(gp), mapNum, i, 32, 24);

        // MAP 1
        mapNum = 1;
        i = 0;

        monsterSetter(new MON_Muddy(gp), mapNum, i, 2, 14);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 3, 17);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 5, 19);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 14, 19); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 15, 16); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 21, 12); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 23, 4);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 34, 4);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 39, 2);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 40, 13); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 36, 20); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 42, 19); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 35, 27); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 23, 27); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 14, 30); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 25, 34); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 21, 37); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 29, 38); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 34, 34); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 36, 37); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 42, 39); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 7, 42);  i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 26, 43); i++;
        monsterSetter(new MON_Muddy(gp), mapNum, i, 30, 46);
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