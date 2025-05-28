package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Boss_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Llave maestra";

    public OBJ_Boss_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/boss_key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nLlave que parece una persona\nsentada.\nAbre la puerta del jefe.";
        stackable = true;
        price = 40000;
        sellPrice = 0;

        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Usaste la llave maestra!";

        dialogues[1][0] = "Intentaste comer la llave, pero como parece una personita no lo hiciste.";

    }
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Puerta Jefe");

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
