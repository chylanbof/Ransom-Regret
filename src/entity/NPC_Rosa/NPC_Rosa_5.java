package entity.NPC_Rosa;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class NPC_Rosa_5 extends Entity {
    // Final triste del juego


    public NPC_Rosa_5(GamePanel gp) {

        super(gp);

        type = type_npc;
        direction = "down";
        speed = 0; // hola
        getImage();
        if (gp.player != null) {
            setDialogue(gp.player.characterType);
        }
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        dialogueSet = 0;

    }

    public void getImage() {

        up1 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/Rosa/tarta", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(String characterType) {

        dialogues[0][0] = "Desafortunadamente El Creador De Este Juego No Tuvo Tiempo A Terminar El\nJuego.";
        dialogues[0][1] = "Originalmente El Plan Era Meter Un Jefe Final Chulisimo Y Una Cinematica\nGuapisima.";
        dialogues[0][2] = "Hasta Iba A Meter Una Discoteca.";
        dialogues[0][3] = "Sin Embargo, Este Mensaje Esta Siendo Escrito A Las 5 De La Madrugada Y\nRealisticamente Ya No Hay Tiempo.";
        dialogues[0][4] = "Disfruta De Este Final Soso.";
        dialogues[0][5] = "Quizas En Un Futuro Este Juego Se Expanda Pero Ya Es Suficiente.";
        dialogues[0][6] = "Buenas Noches";

    }

    public void speak() {
        gp.stopMusic();
        System.out.println("hablando");
        facePlayer();
        setDialogue(gp.player.characterType);
        startDialogue(this, dialogueSet);


        gp.gameState = gp.winState;
        gp.playMusic(48);
        }
    }
