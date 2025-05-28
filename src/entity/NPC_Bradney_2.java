package entity;

import main.GamePanel;
import object.OBJ_Clip;

import java.awt.*;
import java.util.Random;

public class NPC_Bradney_2 extends Entity {

    private boolean hasGivenItem = false;


    public NPC_Bradney_2(GamePanel gp) {

        super(gp);
        voiceSE = 44;

        type = type_npc;
        direction = "down";
        speed = 0;
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

        dialogueSet = -1;



    }

    public void getImage() {

        down1 = setup("/npc/Bradney/Bradney_Down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/Bradney/Bradney_Down_1",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/Bradney/Bradney_Right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/Bradney/Bradney_Right_1",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/Bradney/Bradney_Left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/Bradney/Bradney_Left_1",gp.tileSize,gp.tileSize);
        up1 = setup("/npc/Bradney/Bradney_Up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/Bradney/Bradney_Up_1",gp.tileSize,gp.tileSize);
    }

    public void setDialogue(String characterType) {


        if(characterType.equals("chico")) {
            dialogues[0][0] = "Muy bien chavalote, te has hecho el tutorial.";
        }
        else if(characterType.equals("chica")) {
            dialogues[0][0] = "Muy bien chavalota, te has hecho el tutorial.";
        }
        dialogues[0][1] = "¿Pero sabes que viene luego del tutorial?";
        dialogues[0][2] = "Una buena patada en el culo.";
        if(characterType.equals("chico")) {
            dialogues[0][3] = "Si estas preparado, abre la cerradura con esta cosa que te voy a dar.";
        }
        else if(characterType.equals("chica")) {
            dialogues[0][3] = "Si estas preparada, abre la cerradura con esta cosa que te voy a dar.";
        }
        dialogues[0][4] = "*Has recibido un clip.";
        inventory.add(new OBJ_Clip(gp));

        dialogues[1][0] = "Solo te advierto que yo te hubiese matado de una manera mas gentil.";


    }
    public void speak() {
        facePlayer();

        if (!hasGivenItem) {
            setDialogue(gp.player.characterType);
            gp.player.inventory.add(new OBJ_Clip(gp)); // Lo recibe el jugador
            hasGivenItem = true;
        } else {
            // Solo setear los diálogos normales después de recibir el clip
            dialogues[1][0] = "Solo te advierto que yo te hubiese matado de una manera más gentil.";
            dialogueSet = 1;
        }

        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 1;
        }
    }

}
