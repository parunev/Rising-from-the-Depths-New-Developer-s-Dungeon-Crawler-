package Main;

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

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 8;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Lantern(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 3;
        gp.obj[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.obj[mapNum][i] = new OBJ_SkullBones(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 6;
        gp.obj[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.obj[mapNum][i] = new OBJ_SkullBones(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 8;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.obj[mapNum][i] = new OBJ_SkullBones(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 2;
        i++;

        gp.obj[mapNum][i] = new OBJ_SkullBones(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 19;
        gp.obj[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bones(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 42;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;

        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 3;
        i++;

        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 2;
    }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        // MAP 0
         gp.npc[mapNum][i] = new NPC_DungeonKeeper(gp);
         gp.npc[mapNum][i].worldX = gp.tileSize * 3; // col
         gp.npc[mapNum][i].worldY = gp.tileSize * 3; // row
         i++;

        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 31; // col
        gp.npc[mapNum][i].worldY = gp.tileSize * 2; // row

        // MAP 1
       // mapNum = 1;
       // gp.npc[mapNum][i] = new NPC_Merchant(gp);
       // gp.npc[mapNum][i].worldX = gp.tileSize * 12;
       // gp.npc[mapNum][i].worldY = gp.tileSize * 7;

        // MAP 2
       // setDungeonRocks();
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

    public void setMonster(){
        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 9;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 13;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 5;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 16;
        gp.monster[mapNum][i].worldY = gp.tileSize * 26;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 47;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 41;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.monster[mapNum][i] = new MON_Swampy(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
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
}