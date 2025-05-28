package entity.NPC_Rosa;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class NPC_Rosa extends Entity {
//Esta va en el spawn
    public NPC_Rosa(GamePanel gp) {

        super(gp);

        type = type_npc;
        direction = "down";
        speed = 0; // hola
        getImage();
        setDialogue();
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

        up1 =  setup("/npc/Rosa/rosa1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/Rosa/rosa2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/Rosa/rosa1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/Rosa/rosa2",gp.tileSize,gp.tileSize);
        left1 =  setup("/npc/Rosa/rosa1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/Rosa/rosa2",gp.tileSize,gp.tileSize);
        right1 =  setup("/npc/Rosa/rosa1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/Rosa/rosa2",gp.tileSize,gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0][0] = "Soy Rosa. Estoy Aqui Para Que Tu Estancia En La Mazmorra Sea Mas Placentera.\n¡Doy Consejos Super Utiles!";
        dialogues[0][1] = "¡Pulsa Z Para Hablar Con Los NPC!";
        dialogues[0][2] = "Pero Cuidado Con Pulsar Z Repetidamente.\n¡Igual Te Saltas Sin Querer Mis Consejos!";

        dialogues [1][0] = "¿Que Que Es Un NPC?";
        dialogues [1][1] = "Es Un Acronimo. Significa No Pants Club.\nEs Un Grupo De Gente Que No Usa Pantalones.";
        dialogues [1][2] = "Lamentablemente Pertenezo Al Grupo. No Tengo Piernas Y Por Ende Tampoco Pantalones.";
        dialogues [1][3] = "¡Hablando De Pantalones! Mi Nombre Significa Robotic Operations & Signal Assistant.";

        dialogues [2][0] = "¡Sigue Explorando La Mazmorra!\nEncontraras Copias Mias En Tu Estancia.";

    }

    public void speak(){
        facePlayer();
        startDialogue(this,dialogueSet);
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 2;
        }
    }
}
