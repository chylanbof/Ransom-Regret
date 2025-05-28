package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Penny extends Entity {

    GamePanel gp;
    public static final String objName = "Centimo";

    public OBJ_Penny(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin",gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity) {

        gp.playSE(21);
        gp.ui.addMessage("+ " + value + " moneda!");
        gp.player.coin += value;
        return true;
    }
}