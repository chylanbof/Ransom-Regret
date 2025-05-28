package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Noob_Magic extends Entity {
    public static final String objName = "Magia de principiantes";

    public OBJ_Noob_Magic(GamePanel gp) {
        super(gp);

        type = type_defaultWeapon;
        name = objName;
        down1 = setup("/objects/magic1",gp.tileSize,gp.tileSize);
        attackValue = 1;
        attackArea.width = 50;
        attackArea.height = 50;
        description = "[" + name + "]\nEs un libro con varios trucos\nde magia que hasta un bebe podria\nhacer.\n+ 1 de daño.";
        price = 5;
        sellPrice = 3;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
