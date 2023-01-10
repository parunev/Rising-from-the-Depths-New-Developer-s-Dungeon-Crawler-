package Data;

import Entity.Entity;
import Main.GamePanel;
import Obj.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        // You have to be really precise about these names otherwise it won't parse anything
        // If you change an item name in their class you need to change the name here too
        // Not really flexible solution, but it works for now
        return switch (itemName) {
            case "Woodcutter's Axe" -> new OBJ_Axe(gp);
            case "Boots" -> new OBJ_Boots(gp);
            case "Key" -> new OBJ_Key(gp);
            case "Lantern" -> new OBJ_Lantern(gp);
            case "Red Potion" -> new OBJ_Potion_Red(gp);
            case "Blue Shield" -> new OBJ_Shield_Blue(gp);
            case "Wood Shield" -> new OBJ_Shield_Wood(gp);
            case "Normal Sword" -> new OBJ_Sword_Normal(gp);
            case "Tent" -> new OBJ_Tent(gp);
            case "Door" -> new OBJ_Door(gp);
            case "Chest" -> new OBJ_Chest(gp);
            default -> null;
        };
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
        DataStorage ds = new DataStorage();

        // PLAYER STATS
        ds.level = gp.player.level;
        ds.maxLife = gp.player.maxLife;
        ds.life = gp.player.life;
        ds.maxMana = gp.player.maxMana;
        ds.mana = gp.player.mana;
        ds.strength = gp.player.strength;
        ds.dexterity = gp.player.dexterity;
        ds.exp = gp.player.exp;
        ds.nextLevelExp = gp.player.nextLevelExp;
        ds.coin = gp.player.coin;

        // PLAYER INVENTORY
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            ds.itemNames.add(gp.player.inventory.get(i).name);
            ds.itemAmount.add(gp.player.inventory.get(i).amount);
        }

        // PLAYER EQUIPMENT
        ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
        ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

        // OBJECTS ON MAP
        ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
        ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
        ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
        ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
        ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

        for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[mapNum][i] == null){ // Empty
                    ds.mapObjectNames[mapNum][i] = "NA";
                } else {
                    ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                    ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                    ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                    // If the object has loot
                    if (gp.obj[mapNum][i].loot != null){
                        ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                    }

                    ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                }
            }
        }

        // Write the DataStorage object
        oos.writeObject(ds);
    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

        // Read the DatStorage object
        DataStorage ds = (DataStorage) ois.readObject();

        // PLAYER STATS
        gp.player.level = ds.level;
        gp.player.maxLife = ds.maxLife;
        gp.player.life = ds.life;
        gp.player.maxMana = ds.maxMana;
        gp.player.mana = ds.mana;
        gp.player.strength = ds.strength;
        gp.player.dexterity = ds.dexterity;
        gp.player.exp = ds.exp;
        gp.player.nextLevelExp = ds.nextLevelExp;
        gp.player.coin = ds.coin;

        // PLAYER INVENTORY
        gp.player.inventory.clear();
        for (int i = 0; i < ds.itemNames.size(); i++) {
            gp.player.inventory.add(getObject(ds.itemNames.get(i)));
            gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
        }

        // PLAYER EQUIPMENT
        gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
        gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
        gp.player.getAttack();
        gp.player.getDefence();
        gp.player.getAttackImage();

        // OBJECTS ON MAP
        for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

            for (int i = 0; i < gp.obj[1].length; i++) {
                if (ds.mapObjectNames[mapNum][i].equals("NA")){
                    gp.obj[mapNum][i] = null;
                } else {
                    gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                    gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                    gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];

                    if (ds.mapObjectLootNames[mapNum][i] != null){
                        gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                    }

                    gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                    if (gp.obj[mapNum][i].opened){
                        gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                    }
                }
            }
        }
    }
}
