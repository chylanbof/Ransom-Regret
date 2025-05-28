package entity;

import main.GamePanel;
import object.*;




public class NPC_Billson extends Entity {


    public NPC_Billson(GamePanel gp) {

        super(gp);


        type = type_npc;
        direction = "down";
        speed = 0;
        voiceSE = 45;
        getImage();
        if (gp.player != null) {
            setDialogue(gp.player.characterType);
        }
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getImage() {

        down1 = setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        up1 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        up2 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        left1 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        left2 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        right1 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
        right2 =  setup("/npc/Billson/Billson_Shop", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(String characterType) {

        dialogues[0][0] = "Eres la primera persona que entra en mi tienda.\nCon lo feliz que era estando aqui solo... Encerrado tras estos barriles";

        dialogues[0][1] = "En fin, que deseas comprar?";

        dialogues[1][0] = "Tengo posiblemente el trabajo mas estupido de la historia";

        dialogues[2][0] = "Largo de aqui, vagabundo. Vuelve cuando tengas algo de dinero.";

        dialogues[3][0] = "No puedes llevar tantos objetos. Si te sobra alguno me lo puedes\nvender *guiño *guiño*";

        if(characterType.equals("chico")) {
            dialogues[4][0] = "Tu cuando vas a una tienda de ropa no vendes los calzoncillos que llevas\npuesto en ese momento, ¿verdad?\nPues lo mismo aqui, quitate eso antes de querermelo encajar.";
        }
        if(gp.player.characterType.equals("chica")) {
            dialogues[4][0] = "Tu cuando vas a una tienda de ropa no vendes las bragas que lleves\npuesta en ese momento, ¿verdad?\nPues lo mismo aqui, quitate eso antes de querermelo encajar.";
        }
        dialogues[5][0] ="No vuelvas, de verdad te lo pido.\nOdio mi trabajo.";
    }
    public void setItems(String characterType) {
        inventory.clear();

        inventory.add(new OBJ_Burger(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword2(gp));
        inventory.add(new OBJ_Shield(gp));
        inventory.add(new OBJ_Xray_Shades(gp));
    }
    public void speak() {
        facePlayer();
        setDialogue(gp.player.characterType);
        startDialogue(this,dialogueSet);
        gp.gameState = gp.tradeState;
        setItems(gp.player.characterType);
        gp.ui.npc = this;
    }
}