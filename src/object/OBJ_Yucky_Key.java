package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Yucky_Key extends Entity {
    public static final String objName = "Llave con pota";

    GamePanel gp;

    public OBJ_Yucky_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/Yucky_Key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nEsta llave apesta, pero se pueden\nhacer miles de cosas con ella.";
        stackable = true;
        price = 5;// NO VENDER
        sellPrice = 1;

        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Usaste la llave con pota!";

        dialogues[1][0] = "El pensamiento de comertela por segunda vez te parecio demasiado depravado\ny psicopata.";
    }
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Puerta");

        if(objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(34);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
            }
        }
    }
