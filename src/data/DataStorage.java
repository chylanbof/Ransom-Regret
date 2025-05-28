package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    // Stats del jugador
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strenght;
    int dexterirty;
    int exp;
    int nextLevelExp;
    int coin;
    public String characterType;

    // Inventario del jugador
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // Objetos en el mapa
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];


}
