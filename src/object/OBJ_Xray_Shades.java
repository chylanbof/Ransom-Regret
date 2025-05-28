package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Xray_Shades extends Entity {

    public static final String objName = "Gafas de rayos X";

    GamePanel gp;

    public OBJ_Xray_Shades(GamePanel gp) {
        super(gp);

        type = type_light;
        name = objName;
        down1 = setup("/objects/X-Ray_Glasses", gp.tileSize, gp.tileSize);
        description = "[Gafas de rayos X]\nUnas gafas muy utiles para ver en\nla oscuridad.\nTu personaje no es muy listo y no\npuede usarlas y atacar al mismo\ntiempo(Pero si disparar).";
        price = 30;
        sellPrice = 15;
        lightRadius = 250;
    }
}
