package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Locker_2 extends Entity {

    GamePanel gp;
    public static final String objName = "Cerradura2";

    public OBJ_Locker_2(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/objects/locker",gp.tileSize,gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "Necesitas un clip para abrir esto.";
    }
    public void interact() {
        startDialogue(this,0);
    }
}