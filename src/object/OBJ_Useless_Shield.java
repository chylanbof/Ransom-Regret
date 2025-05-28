package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Useless_Shield extends Entity {

    public static final String objName = "Jimmy, el escudo inutil";

    public OBJ_Useless_Shield(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/jimmy_the_useless_shield",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nTe lo encontraste en la basura\nun dia antes de ser secuestrado.\nEs tan mediocre que si te golpean a\ntraves de el es posible que\nrecibas mas daño del habitual.";
        price = 200;
        sellPrice = 0;

    }
}
