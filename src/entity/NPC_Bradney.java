package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Bradney extends Entity {

    public NPC_Bradney(GamePanel gp) {

        super(gp);
        voiceSE = 44;

        type = type_npc;
        direction = "down";
        speed = 1;
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

            up1 = setup("/npc/Bradney/Bradney_Up_1",gp.tileSize,gp.tileSize);
            up2 = setup("/npc/Bradney/Bradney_Up_2",gp.tileSize,gp.tileSize);
            down1 = setup("/npc/Bradney/Bradney_Down_1",gp.tileSize,gp.tileSize);
            down2 = setup("/npc/Bradney/Bradney_Down_2",gp.tileSize,gp.tileSize);
            left1 = setup("/npc/Bradney/Bradney_Left_1",gp.tileSize,gp.tileSize);
            left2 = setup("/npc/Bradney/Bradney_Left_2",gp.tileSize,gp.tileSize);
            right1 = setup("/npc/Bradney/Bradney_Right_1",gp.tileSize,gp.tileSize);
            right2 = setup("/npc/Bradney/Bradney_Right_2",gp.tileSize,gp.tileSize);
        }

        public void setDialogue(String characterType) {
            System.out.println("DEBUG characterType: " + characterType);

        dialogues[0][0] = "No te asustes, pero te he secuestrado.";
        if(characterType.equals("chico")) {
            dialogues[0][1] = "¡Tranquilo! No es nada personal.";
        }
        else if(characterType.equals("chica")) {
            dialogues[0][1] = "¡Tranquila! No es nada personal.";
        }
        else {
        dialogues[0][1] = "No es nada personal.";
    }

        dialogues[0][2] = "De hecho es muy probable que nos hayamos confundido de persona, jeje";
        dialogues[0][3] = "Igualmente te tendremos que matar. Ordenes del jefe. Nadie puede salir vivo.";
        dialogues[0][4] = "¿Y si exploras un poco la mazmorra antes de morir? Es bastante bonita.";

        dialogues[1][0] = "Por cierto me llamo Bradney, un placer.";
        dialogues[1][1] = "Soy un robot creado por la gran y maravilloestupenda compañia Lumen Veritas.";
        dialogues[1][2] = "Que gran compañia ¿eh? Gracias a nosotros el planeta Tierra tiene energia ilimitada.";
        dialogues[1][3] = "A ver, esto no se lo decimos a nadie pero como vas a morir...";
        dialogues[1][4] = "Resulta que una persona sana asesinada al morir libera unas cosas pequeñas del cuerpo\nllamadas *marlocks*";
        dialogues[1][5] = "Estas cositas al ser introducidas junto a un compuesto quimico llamado adrenocromo\ngenera tal energia que puedes poner a calentar un microondas por la friolera\ncifra de 2 semanas seguidas.";
        dialogues[1][6] = "¿A que es impresionante? Y solo necesitamos matar 20 personas para conseguir eso.";
        dialogues[1][7] = "El problema esta en que no se si te acuerdas pero antes dije que la persona debe estar\nsana para liberar los marlocks...";
            if(characterType.equals("chico")) {
                dialogues[1][8] = "Tu no estas sano.\nEstas fofo.\nEs mas facil saltarte que rodearte.";
            }
            if(characterType.equals("chica")) {
                dialogues[1][8] = "Tu no estas sana.\nEstas fofa.\nEs mas facil saltarte que rodearte.";
            }
        dialogues[1][9] = "Tu secuestro ha sido una perdida de tiempo. No se como me he podido confundir. En fin...";
        dialogues[1][10] = "Bueno al menos piensa esto. De todos modos hubieras muerto de un ataque al corazon\nun dia de estos.";

        dialogues[2][0] = "¿Necesitas algo? Arriba a la izquierda hay una tienda, la cual la lleva Billson.";
        dialogues[2][1] = "Es un gran tio, pero es un poquito repelente.";
        dialogues[2][2] = "Te estaras preguntando ¿Porque hay una tienda en una mazmorra?";
        dialogues[2][3] = "Pues creo que es para los empleados pero nadie nunca en Lumen Veritas ha comprado algo\nde Billson.";
        dialogues[2][4] = "¿Y si vas y le compras algo? Seguro que le ilusiona aunque no lo demuestre.";

        dialogues[3][0] = "Mira me caes muy bien pero no quiero que desarrolles un sindrome estocolmiano\n(o como se diga).Mejor disfruta tus ultimos momentos en esta bonita\nmazmorra y deja de hablarme.";

        if(characterType.equals("chico")) {
        dialogues[5][0] = "Parece ser que tu solito te estas muriendo.\nY no tuve que mover ni un dedo!";
        }
        if(characterType.equals("chica")) {
        dialogues[5][0] = "Parece ser que tu solita te estas muriendo.\nY no tuve que mover ni un dedo!";
        }
        dialogues[5][1] = "Te aconsejo NO ir al manantial que hay al sur de la mazmorra.";
        dialogues[5][2] = "Ni tampoco comerte una hamburguesa que los kilos ya te sobran.";
        dialogues[5][3] = "(espera, ¿porque tenemos un manantial curativo en una mazmorra mortal?";
        }
        public void setAction(){

        if(onPath) {

          //  int goalCol = 4;
          //  int goalRow = 30;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

        }

        else {
            actionLockCounter ++;

            if(actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1; //eligue un numero del 1 al 100

                if (i <= 25){
                    direction = "up";
                }
                if (i > 25 && i <= 50){
                    direction = "down";
                }
                if (i > 50 && i <= 75){
                    direction = "left";
                }
                if (i > 75 && i <= 100){
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }
        public void speak(){
            facePlayer();
            setDialogue(gp.player.characterType);
            startDialogue(this,dialogueSet);

            dialogueSet++;

            if(gp.player.life < gp.player.maxLife/3){
               dialogueSet = 5;
            }

            if (dialogues[dialogueSet][0] == null) {
                dialogueSet = 3;
            }

        }
    }
