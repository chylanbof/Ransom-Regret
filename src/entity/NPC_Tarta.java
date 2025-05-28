package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_Tarta extends Entity {
    // Final triste del juego


    public NPC_Tarta(GamePanel gp) {

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

        dialogues[0][0] = "Felicidades Shinji.";

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
