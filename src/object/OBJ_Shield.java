package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield extends Entity {

    public static final String objName = "Escudo potente";

    public OBJ_Shield(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield",gp.tileSize,gp.tileSize);
        defenseValue = 3;
        description = "[" + name + "]\nUn escudo claramente superior\na Jimmy. Tiene un simbolo raro.";
        price = 15;
        sellPrice = 7;

    }
}
