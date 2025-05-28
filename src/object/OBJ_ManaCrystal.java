package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;
    public static final String objName = "Cristal de Maná";

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/mana_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/mana_empty", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity) {
        gp.playSE(23);
        gp.ui.addMessage("+ " + value + " de maná!");
        entity.mana += value;
        return true;
    }
}
