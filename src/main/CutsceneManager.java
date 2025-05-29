package main;

import entity.PlayerDummy;
import monster.MON_Boss;
import object.OBJ_Boss_Door;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    public final int NA = 0;
    public final int jefe1 = 1;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case jefe1:
                scene_jefe1();
                break;
        }
    }

    public void scene_jefe1() {

        if (scenePhase == 0) {

            gp.playMusic(50);
            gp.bossBattleOn = true;

            for (int i = 0; i < gp.obj[1].length; i++) {

                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_Boss_Door(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 26;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 12;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(4);
                    break;
                }
            }
            for (int i = 0; i < gp.npc[1].length; i++) {

                if (gp.obj[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;


            scenePhase++;
        }
        if (scenePhase == 1) {

            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize * 6) {
                scenePhase++;
            }
        }
        if (scenePhase == 2) {

            //Buscar al jefe
            for (int i = 0; i < gp.monster[1].length; i++) {

                if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(MON_Boss.monName)) {

                    ((MON_Boss) gp.monster[gp.currentMap][i]).initDialogueFromPlayer();

                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {

            // El jefe habla
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 4) {

            //Volver al jugador

            //Buscar al dummy

            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == PlayerDummy.npcName) {
                    //Restaurar la posicion del jugador
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    //Borrar el dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;

            // Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
        }
    }
}
