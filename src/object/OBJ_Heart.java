package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Heart extends Entity {
    GamePanel gp;
    public static final String objName = "Heart";


    public OBJ_Heart(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;
        name = objName;
        value = 2;
        down1 = setup("/objects/heart_full",gp.tileSize,gp.tileSize);
        image = setup("/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/damaged_heart",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/empty_heart",gp.tileSize,gp.tileSize);
    }
    public boolean use(Entity entity) {
        gp.playSE(22);
        gp.ui.addMessage("+ " + value + " de vida!");
        entity.life += value;
        return true;
    }
}
