package entity.NPC_Rosa;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bean_Can;

import java.awt.*;

public class NPC_Rosa_4 extends Entity {
    // random de mazmorra


    public NPC_Rosa_4(GamePanel gp) {

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

        up1 = setup("/npc/Rosa/rosa1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/Rosa/rosa2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/Rosa/rosa1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Rosa/rosa2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/Rosa/rosa1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/Rosa/rosa2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/Rosa/rosa1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/Rosa/rosa2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(String characterType) {

        dialogues[0][0] = "NO SE COMO LLEGUE AQUI.";
        dialogues[0][1] = "AYUDA.";

    }

    public void speak() {
        facePlayer();
        setDialogue(gp.player.characterType);
        startDialogue(this,dialogueSet);
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }
    }
}