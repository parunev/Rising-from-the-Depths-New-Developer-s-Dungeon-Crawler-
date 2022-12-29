package Main;

import Entity.NPC_OldMan;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    // What object you want to place on the map and on what coordinates
    public void setObject(){
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21; // col
        gp.npc[0].worldY = gp.tileSize * 21; // row
    }
}
