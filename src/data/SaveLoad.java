package data;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strenght = gp.player.strenght;
            ds.dexterirty = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;


            // Inventario
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            //Equipamiento
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
            ds.characterType = gp.player.characterType;

            //Objetos en el mapa
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {

                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            //Write the DataStorage object
            oos.writeObject(ds);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Save exception");
        }
    }

    public void load() {
        if (gp.player != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

                // Leer el objeto DataStorage
                DataStorage ds = (DataStorage) ois.readObject();
                gp.player = new Player(gp, gp.keyH, ds.characterType);
                gp.player.setCharacter(ds.characterType);


                // Jugador
                gp.player.level = ds.level;
                gp.player.maxLife = ds.maxLife;
                gp.player.life = ds.life;
                gp.player.maxMana = ds.maxMana;
                gp.player.mana = ds.mana;
                gp.player.strenght = ds.strenght;
                gp.player.dexterity = ds.dexterirty;
                gp.player.exp = ds.exp;
                gp.player.nextLevelExp = ds.nextLevelExp;
                gp.player.coin = ds.coin;
                gp.player.characterType = ds.characterType;
                gp.player.cargarPartida(gp.player.characterType);

                // Inventario
                gp.player.inventory.clear();
                for (int i = 0; i < ds.itemNames.size(); i++) {
                    Entity item = gp.eGenerator.getObject(ds.itemNames.get(i));
                    if (item != null) {
                        item.amount = ds.itemAmounts.get(i);
                        gp.player.inventory.add(item);
                    }
                }

                // Equipamiento
                gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
                gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
                gp.player.getAttackImage();
                gp.player.getGuardImage();
                gp.player.getAttack();
                gp.player.getDefense();

                // Objetos en el mapa
                for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                    // Inicializa la fila del array si es null
                    if (gp.obj[mapNum] == null) {
                        gp.obj[mapNum] = new Entity[ds.mapObjectNames[mapNum].length];
                    }

                    for (int i = 0; i < ds.mapObjectNames[mapNum].length; i++) {

                        if (ds.mapObjectNames[mapNum][i] == null || ds.mapObjectNames[mapNum][i].equals("NA")) {
                            gp.obj[mapNum][i] = null;
                        } else {
                            Entity obj = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                            if (obj != null) {
                                obj.worldX = ds.mapObjectWorldX[mapNum][i];
                                obj.worldY = ds.mapObjectWorldY[mapNum][i];

                                if (ds.mapObjectLootNames != null && ds.mapObjectLootNames[mapNum][i] != null) {
                                    obj.setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                                }

                                obj.opened = ds.mapObjectOpened[mapNum][i];
                                if (obj.opened) {
                                    obj.down1 = obj.image2;
                                }

                                gp.obj[mapNum][i] = obj;
                            } else {
                                gp.obj[mapNum][i] = null;
                            }
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Load exception");
                e.printStackTrace();
            }
        }

    }
}