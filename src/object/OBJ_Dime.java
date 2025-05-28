package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Dime extends Entity {

    GamePanel gp;
    public static final String objName = "10 Centimos";

    public OBJ_Dime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objName;
        value = 10;
        down1 = setup("/objects/dime",gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity) {

        gp.playSE(33);
        gp.ui.addMessage("+ " + value + " monedas!");
        gp.player.coin += value;
        return true;
    }
}