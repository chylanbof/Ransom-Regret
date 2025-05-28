package entity;

import main.GamePanel;
import object.OBJ_Clip;

import java.awt.*;
import java.util.Random;

public class NPC_Bradney_3 extends Entity {



    public NPC_Bradney_3(GamePanel gp) {

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


        dialogues[0][0] = "Ese ser era un guardian legendario que se despierta una vez\ncada mil años para actuar de guarda en zonas que el considera importantes.";
        dialogues[0][1] = "Estoy quedandome contigo. En verdad no. Es un ser bastante común en Egipto.";
        dialogues[0][2] = "Y me estoy quedando contigo de nuevo. Acabas de extinguir una especie en peligro\nde extinción.";
        if(characterType.equals("chico")) {
            dialogues[0][3] = "Estarás contento me imagino. Y todo porque no querias que te matásemos.";
            dialogues[0][4] = "Disfruta de tu libertad, asesino.";
        } else if (characterType.equals("chica")) {
            dialogues[0][3] = "Estaras contenta me imagino. Y todo porque no querias que te matásemos.";
            dialogues[0][4] = "Disfruta de tu libertad, asesina.";
        }

        dialogues[0][5] = "Conseguiste escapar de la mazmorra, felicidades.\nLargo de aquí.";

        dialogues[1][0] = "... *risita*";


    }
    public void speak() {
        facePlayer();
        setDialogue(gp.player.characterType);
        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 1;
        }
    }

}
