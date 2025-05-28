package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Llave";

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/Key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\n¡Puedes hacer miles de cosas\ncon una llave!";
        stackable = true;
        price = 5;
        sellPrice = 3;

        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Usaste la llave!";

        dialogues[1][0] = "Intentaste comerte la llave, pero la vomitaste instantaneamente.";

    }
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Puerta");

        if(objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(34);
            gp.obj[gp.currentMap][objIndex] = null;
        }
        else {
            if(entity.inventory.size() < entity.maxInventorySize) {
                gp.playSE(35);
                startDialogue(this, 1);
                gp.player.canObtainItem(new OBJ_Yucky_Key(gp));
                gp.player.canObtainItem(new OBJ_Burger(gp));
            }
            else {
                gp.playSE(35);
                startDialogue(this, 1);
                gp.player.canObtainItem(new OBJ_Yucky_Key(gp));
            }
        }
        return true;
    }

}
