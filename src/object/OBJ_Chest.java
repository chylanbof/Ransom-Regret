package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    public static final String objName = "Cofre";

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;


        type = type_obstacle;
        name = objName;
        image = setup("/objects/Locked_Chest",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/Open_Chest",gp.tileSize,gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }
    public void setDialogue() {

        dialogues[0][0] = "¡Abriste el cofre y enontraste " + loot.name + "\n...pero tienes el inventario lleno...";
        dialogues[1][0] = "¡Abriste el cofre y enontraste " + loot.name + "\n" + loot.name + " fue añadido a tu inventario.";
        dialogues[2][0] = "No hay nada.";
    }
    public void interact() {

        if(!opened) {
            gp.playSE(37);
            gp.playSE(5);

            if(!gp.player.canObtainItem(loot)) {
                startDialogue(this, 0);
            }
            else {
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }
        else {
            startDialogue(this, 2);
        }
    }
}
