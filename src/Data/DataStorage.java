package Data;

import java.io.Serializable;
import java.util.ArrayList;

// Implementing Serializable interface, so you can read and write the DataStorage object
// This class itself can be writable and readable
public class DataStorage implements Serializable {

    // PLAYER STATS
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    // PLAYER POS
    int currentPlayerX;
    int currentPlayerY;
    int currentPlayerMap;

    // PLAYER INVENTORY
    // Since you can only read variables and not classes in serialized object we declare two ArrayList holding their names and amounts
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // OBJECTS ON MAP
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootNames;  // Loot names, so if it's a treasure chest we record what's inside it
    boolean[][] mapObjectOpened; // Record if the chest is already opened or not
}
