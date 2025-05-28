package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Guardarin extends Entity {

    public NPC_Guardarin(GamePanel gp) {

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

    }

    public void getImage() {

        up1 =  setup("/npc/Guardarin/Guardarin1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/Guardarin/Guardarin2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/Guardarin/Guardarin1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/Guardarin/Guardarin2",gp.tileSize,gp.tileSize);
        left1 =  setup("/npc/Guardarin/Guardarin1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/Guardarin/Guardarin2",gp.tileSize,gp.tileSize);
        right1 =  setup("/npc/Guardarin/Guardarin1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/Guardarin/Guardarin2",gp.tileSize,gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0][0] = "¡Ay caray! ¡Ay caray! ¡Soy Guardarin!\n¡Guardo partidas, basura y tus secretos mas turbios!";
        dialogues[0][1] = "*Sientes que tu partida a sido guardada.";
    }

    public void speak(){
        facePlayer();
        startDialogue(this,dialogueSet);
        gp.saveLoad.save();
    }
}
