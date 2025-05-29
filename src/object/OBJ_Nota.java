package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Nota extends Entity {

    GamePanel gp;
    public static final String objName = "Nota";

    public OBJ_Nota(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/nota",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nEs una nota que dejo el bicho ese\nUsala para leer su contenido.";
        stackable = false;
        price = 40000;
        sellPrice = 0;

        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Gusanin devoro tu nota!";

        dialogues[1][0] = "*Prueba a noclipearte en el muro con tu cara.";

    }
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Gusanin");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(34);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            startDialogue(this, 1);
            return false;
        }
    }
}
