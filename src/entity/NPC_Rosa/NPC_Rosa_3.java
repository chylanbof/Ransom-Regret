package entity.NPC_Rosa;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bean_Can;

import java.awt.*;

public class NPC_Rosa_3 extends Entity {
    //Esta va al lado de la transicion
    private int dialogueProgress = 0;

    public NPC_Rosa_3(GamePanel gp) {

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

        dialogues[0][0] = "¡Ten Cuidado!";
        dialogues[0][1] = "No Lo Digo Por La Roca Con La Que Te Acabas De Tropezar, Si No\nPorque La Siguiente Zona Es Muy Peligrosa.";
        dialogues[0][2] = "Se Le Conoce Como 'El Matadero'";
        dialogues[0][3] = "Nadie Sale Con Vida De Ahi.";

        dialogues[1][0] = "Por Una Parte Quiero Que Mueras Para Que Me Suban El Sueldo.";
        dialogues[1][1] = "Y Por Otra Me Gusta Hablar Con Los Reclusos.";
        dialogues[1][2] = "Que Vida Mas Dura Tengo...";

        dialogues[3][0] = "Eres Muy Debil.\nNo Duraras Ni 5 Minutos En Esa Zona.";

        dialogues[5][0] = "Confio en ti. Parece que tienes el nivel suficiente para escapar.";
    }

    public void speak() {
        facePlayer();

    switch (dialogueProgress) {
        case 0:
            startDialogue(this, 0); // Primer set
            break;
        case 1:
            startDialogue(this, 1); // Segundo set
            break;
        case 2:
            if (gp.player.level < 3) { // Ajusta el nivel como consideres
                startDialogue(this, 3);
            } else {
                startDialogue(this, 5);
            }
            break;
        default:
            // Repetir última parte según el nivel
            if (gp.player.level <= 3) {
                startDialogue(this, 3);
            } else {
                startDialogue(this, 5);
            }
            break;
    }


        dialogueProgress++;
    }
}