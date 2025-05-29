package main;

import entity.Entity;

import java.awt.*;
import java.util.Random;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "¡Te caiste en un agujero!";

        eventMaster.dialogues[1][0] = "Bebes 4 litros de agua...";
        eventMaster.dialogues[1][1] = "¡Y te curaste al maximo!";


        eventMaster.dialogues[2][0] = "¡Te tropezaste con una bonita piedra!";

        eventMaster.dialogues[3][0] = "El portal te ha llevado a otro lugar...";

        eventMaster.dialogues[4][0] = "Ya no hay vuelta atrás.";
    }
    public void checkEvent() {

        //mira si el personaje esta 1 tile lejos del ultimo evento
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent) {
            if(hit(0,4, 7, "any")) {damagePit(gp.dialogueState);}
            else if(hit(0,24, 39, "any")) {healingPool(gp.dialogueState);}
            else if(hit(0,25, 39, "any")) {healingPool(gp.dialogueState);}
            else if(hit(0,3, 7, "any" )) {portal2(gp.dialogueState);}
            else if(hit(0,43, 45, "any" )) {portal1(gp.dialogueState);}
            else if(hit(0,46, 24, "any" )) {damageStone(0,46, 24, gp.dialogueState);}
            //CAMION
            else if(hit(0,20,19,"any")) {teleport(1,25,24, gp.indoor); gp.stopMusic(); gp.playMusic(29);}
            else if(hit(1,25,24,"any")) {teleport(0,20,19, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            //PRIMERA TIENDA
            else if(hit(0,3,0,"any")) {teleport(2,23,23, gp.indoor); gp.stopMusic(); gp.playMusic(30);}
            else if(hit(2,23,24,"any")) {teleport(0,3,1, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            else if(hit(2,23,19, "up")) {speak(gp.npc[2][0]);}
            //SALA DEL OBJETO
            else if(hit(0,43,49,"any")) {teleport(3,48,26, gp.fullDark); gp.stopMusic(); gp.playMusic(36);}
            else if(hit(3,48,24,"any")) {teleport(0,43,47, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            //Zona Transitoria
            else if(hit(0,49,24, "any")) {teleport(4, 4,24, gp.dungeon); gp.stopMusic();}
            else if(hit(4,2,24, "any")) {teleport(0, 47,24, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            else if(hit(4,2,23, "any")) {teleport(0, 47,24, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            else if(hit(4,2,22, "any")) {teleport(0, 47,24, gp.indoor); gp.stopMusic(); gp.playMusic(0);}
            else if(hit(4,17,23, "any")) {teleport(5, 9,40, gp.dungeon); gp.stopMusic(); gp.playMusic(47);}
            else if(hit(5,9,41, "any")) {teleport(4, 17,24, gp.dungeon); gp.stopMusic();}
            else if(hit(5, 20, 22, "any")) {teleport(6,23,47, gp.dungeon); gp.stopMusic(); gp.playMusic(17);}
            else if(hit(6, 24, 47, "any")) {
                if(isLocker2Active()) {
                    teleport(5, 21, 22, gp.dungeon);
                    gp.stopMusic();
                    gp.playMusic(47);
                } else {
                    mensajeOminoso(gp.dialogueState);
                    canTouchEvent = false;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            else if(hit(6, 29, 26, "any")) {teleport(6,26,13, gp.dungeon); gp.stopMusic();}
            else if(hit(6, 26, 11, "any")) {jefe1();} //jefe

        }


        }


    public void mensajeOminoso(int gameState) {
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 4);
    }


    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }


        return hit;
    }

    public void portal1(int gameState) {
        gp.gameState = gameState;
        Random rand = new Random();
        int soundEffect = rand.nextInt(2) == 0 ? 13 : 14;
        gp.playSE(soundEffect);
        gp.player.worldX = gp.tileSize*3;
        gp.player.worldY = gp.tileSize*8;
        eventMaster.startDialogue(eventMaster, 3);

    }
    public void portal2(int gameState) {
        gp.gameState = gameState;
        Random rand = new Random();
        int soundEffect = rand.nextInt(2) == 0 ? 13 : 14;
        gp.playSE(soundEffect);
        gp.player.worldX = gp.tileSize*43;
        gp.player.worldY = gp.tileSize*46;
        eventMaster.startDialogue(eventMaster, 3);

    }


    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.playSE(8);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        canTouchEvent = false;
    }

    public void damageStone(int map, int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.playSE(8);
        eventMaster.startDialogue(eventMaster, 2);
        gp.player.life -= 2;
        eventRect[map][col][row].eventDone = true;
    }


    public void healingPool(int gameState) {

        if(gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            long currentMusicPosition = gp.music.getPosition();
            gp.music.pause();
            gp.playSE(12);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            new Thread(() -> {
                try {
                    Thread.sleep(2300);
                    gp.music.setPosition(currentMusicPosition);
                    gp.music.resume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    public void teleport(int map, int col, int row, int area) {
        gp.currentMap = map;
        gp.currentArea = area;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
        gp.playSE(28);
    }
    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public boolean isLocker2Active() {
        if (gp.obj[6] != null) {
            for (Entity e : gp.obj[6]) {
                if (e != null && "Cerradura2".equals(e.name)) {
                    return true; // Aún está presente
                }
            }
        }
        return false; // No está en el mapa
    }
    public void jefe1() {

        if(!gp.bossBattleOn) {
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.jefe1;
        }
    }
}
