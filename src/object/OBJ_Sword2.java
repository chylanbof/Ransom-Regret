package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword2 extends Entity {
    public static final String objName = "Navaja";

    public OBJ_Sword2(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/objects/SwordAlt",gp.tileSize,gp.tileSize);
        attackValue = 0;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nNo es tan ramblastica\ncomo la original.\nBillson las vende. +0 de daño";
        price = 2;
        sellPrice = 1;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
