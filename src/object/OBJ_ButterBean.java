package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ButterBean extends Entity {
    public static final String objName = "Butter Bean";

    public OBJ_ButterBean(GamePanel gp) {

        super(gp);
        name = objName;
        down1 = setup("/objects/Butter_Bean",gp.tileSize,gp.tileSize);
    }
}
