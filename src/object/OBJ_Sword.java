package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity {

    public static final String objName = "Navaja ramblastica";

    public OBJ_Sword(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/objects/Sword",gp.tileSize,gp.tileSize);
        attackValue = 0;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nTe la encontraste en la Rambla\njunto a un cadaver que\ncuriosamente no tenia dinero\nen sus bolsillos.\n + 0 de daño.";
        sellPrice = 5;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
