package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Clip extends Entity {

    GamePanel gp;
    public static final String objName = "Clip";

    public OBJ_Clip(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/clip",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nObjeto con un poder maligno inmenso.\nPermite abrir cerraduras y traumas.";
        stackable = true;
        price = 25;
        sellPrice = 6;

        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Usaste el clip!";

        dialogues[1][0] = "Intentaste comerte el clip pero te dio miedo que dañase tus\nfragiles paredes estomacales.";

    }
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Cerradura");
        int objIndex2 = getDetected(entity, gp.obj, "Cerradura2");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(34);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;

        } else if (objIndex2 != 999) {
            startDialogue(this, 0);
            gp.playSE(34);
            gp.stopMusic();
            gp.playMusic(49);
            System.out.println("Cerradura2 usada. Reproduciendo música 49.");
            gp.obj[gp.currentMap][objIndex2] = null; // ← corregido aquí
            return true;

        } else {
            startDialogue(this, 1);
            return false;
        }
    }

}
