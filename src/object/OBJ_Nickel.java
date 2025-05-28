package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Nickel extends Entity {

    GamePanel gp;
    public static final String objName = "5 Centimos";

    public OBJ_Nickel(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objName;
        value = 5;
        down1 = setup("/objects/nickel",gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity entity) {

        gp.playSE(32);
        gp.ui.addMessage("+ " + value + " monedas!");
        gp.player.coin += value;
        return true;
    }
}