package main;

import entity.NPC_Billson;
import entity.NPC_Bradney;
import entity.NPC_Guardarin;
import entity.NPC_Rosa.*;
import monster.*;
import object.*;
import tile_interactive.IT_BreakableRock;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject (String characterType) {

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*5;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.obj[mapNum][i] = new OBJ_Burger(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*42;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*48;
        gp.obj[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*47;
        gp.obj[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*43;
        gp.obj[mapNum][i].worldY = gp.tileSize*49;

        mapNum = 1;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*20;
        gp.obj[mapNum][i].worldY = gp.tileSize*24;

        mapNum = 3;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        if(characterType.equals("chico")) {
            gp.obj[mapNum][i].setLoot(new OBJ_Bean_Can(gp));
        }
        if (characterType.equals("chica")) {
            gp.obj[mapNum][i].setLoot(new OBJ_Noob_Magic(gp));
        }
        gp.obj[mapNum][i].worldX = gp.tileSize*39;
        gp.obj[mapNum][i].worldY = gp.tileSize*33;
        mapNum = 5;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Burger(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*8;
        gp.obj[mapNum][i].worldY = gp.tileSize*17;
        i++;
        gp.obj[mapNum][i] = new OBJ_Dime(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*9;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*40;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Locker(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Boss_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*34;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Clip(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*27;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boss_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*21;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;


    }
    public void setNPC() {
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Bradney(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*25;
        gp.npc[mapNum][i].worldY = gp.tileSize*25;
        i++;
        gp.npc[mapNum][i] = new NPC_Rosa(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*16;
        gp.npc[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.npc[mapNum][i] = new NPC_Rosa_2(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*11;
        gp.npc[mapNum][i].worldY = gp.tileSize*3;
        i++;
        gp.npc[mapNum][i] = new NPC_Rosa_3(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*48;
        gp.npc[mapNum][i].worldY = gp.tileSize*23;
        i++;
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Guardarin(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*22;
        i++;
        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Billson(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*17;
        i++;
        mapNum = 5;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Guardarin(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*8;
        gp.npc[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.npc[mapNum][i] = new NPC_Rosa_4(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*13;
        gp.npc[mapNum][i].worldY = gp.tileSize*16;
        mapNum = 6;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Rosa_5(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*24;
        gp.npc[mapNum][i].worldY = gp.tileSize*28;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*47;
        gp.monster[mapNum][i].worldY = gp.tileSize*47;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*47;
        gp.monster[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.monster[mapNum][i] = new MON_Vigilux(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.monster[mapNum][i] = new MON_Farty(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*7;
        gp.monster[mapNum][i].worldY = gp.tileSize*34;
        i++;
        gp.monster[mapNum][i] = new MON_Farty(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*41;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;


        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*18;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*11;
        gp.monster[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*16;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*21;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*14;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*20;

        mapNum = 4;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*14;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        mapNum = 5;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*16;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*40;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*14;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*9;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*33;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;
        gp.monster[mapNum][i] = new MON_Farty(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*14;
        i++;
        gp.monster[mapNum][i] = new MON_Wheelliam(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*18;
        i++;
        gp.monster[mapNum][i] = new MON_Xlime_Rojo(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*9;
        i++;
    }
    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_BreakableRock(gp,16,3);i++;
        gp.iTile[mapNum][i] = new IT_BreakableRock(gp,16,4);i++;
        gp.iTile[mapNum][i] = new IT_BreakableRock(gp,31,3);i++;
        gp.iTile[mapNum][i] = new IT_BreakableRock(gp,31,4);i++;

        mapNum = 1;
    }

}
