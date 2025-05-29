package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Gusanin extends Entity {

    GamePanel gp;
    public static final String objName = "Gusanin";

    public OBJ_Gusanin(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        down1 = setup("/objects/gusanin",gp.tileSize,gp.tileSize);
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
        dialogues[0][0] = "¡Adoro comer papel!\nSi ese bicho gigante te dio un papel damelo y me ire.";
    }
    public void interact() {
        startDialogue(this,0);
    }
}