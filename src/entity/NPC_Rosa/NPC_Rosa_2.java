package entity.NPC_Rosa;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bean_Can;

import java.awt.*;

public class NPC_Rosa_2 extends Entity {
    //Esta va al lado de las piedras
    public NPC_Rosa_2(GamePanel gp) {

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

    public void setDialogue(String characterType) {

        dialogues[0][0] = "Ahhhh... Las Famosas Piedras De Lumen Veritas.";
        dialogues[0][1] = "Son Mas Duras Que Un Diamante. Por Algo Tienen Una Dureza De 11 En La Escala De Mohs.";
        if(characterType.equals("chico")) {
            dialogues[0][2] = "Dicen Que Para Romperlas Se Necesita Un Legendario Pedo Cuya Fuerza Deja Al\nFat Man Como A Un Misero Petardo.";
            dialogues[0][3] = "Suena Ridiculo Pero ¿A Quien Se Le Ocurriria Romper Una Piedra A Pedos?";
            dialogues[0][4] = "Eso Explica Que Nunca Se Haya Roto Una.";
            dialogues[0][5] = "*Te da la sensacion de que el creador de este juego no pasa de los 7 años mentales.";
        }
        else if(characterType.equals("chica")) {
            dialogues[0][2] = "Dicen Que Para Romperlas Se Necesita A Una Maga Legendaria.";
            dialogues[0][3] = "Pero De Eso Ya No Hay En Los Tiempos Que Corren.";
            dialogues[0][4] = "No Desde La Gran Purga De Magos Que Ocurrio La Semana Pasada.";
            dialogues[0][5] = "Te Contaria Mas Del Tema Pero No Tiene Nada Que Ver Con Tu Trama.";
        }

        dialogues[1][0] = "La Unica Persona Que Llego A Romper Una De Estas Murio En El Sotano Que Hay Al\nSur De Esta Zona.";

        dialogues[3][0] = "No Te Preguntare De Donde Lo Has Sacado ¡Pero Eso Que Tienes En El Inventario\nPodria Servir Para Romper Estas Piedras!";
    }

    public void speak(){

        if (gp.player.searchItemInInventory("Lata de judías") != 999) {
            dialogueSet = 2; // diálogo especial
        }
        else if(gp.player.searchItemInInventory("Magia de principiantes") != 999) {
            dialogueSet = 2;
        }

        facePlayer();
        setDialogue(gp.player.characterType);
        startDialogue(this,dialogueSet);
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 1;

        }
    }
}
