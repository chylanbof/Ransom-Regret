package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Burger extends Entity {

    GamePanel gp;
    public static final String objName = "Hamburguesa";
    public int manavalue = 3;


    public OBJ_Burger(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        value = 4;
        down1 = setup("/objects/burger",gp.tileSize, gp.tileSize);
        description = "[Hamburguesa]\nCura " + value + " de vida y " + manavalue + " de mana.";
        stackable = true;
        price = 1;
        sellPrice = 1;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "¡Te comiste la " + name + " y recuperaste salud y mana!";
    }
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        startDialogue(this, 0);
        entity.life += value;
        entity.mana += manavalue;
        gp.playSE(19);
        return true;
    }
}
