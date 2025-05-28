package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bean_Can extends Entity {


    public static final String objName = "Lata de judías";

    public OBJ_Bean_Can(GamePanel gp) {
        super(gp);

        type = type_defaultWeapon;
        name = objName;
        down1 = setup("/objects/bean_can",gp.tileSize,gp.tileSize);
        attackValue = 1;
        attackArea.width = 50;
        attackArea.height = 50;
        description = "[" + name + "]\nUna lata de judias que otorga\nunos pedos destructivos a\nsus consumidores.\n + 1 de daño.";
        price = 5;
        sellPrice = 3;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
