package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Nickel;
import object.OBJ_Penny;

import java.util.Random;

public class MON_Xlime extends Entity {

    GamePanel gp;

    public MON_Xlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Xlime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        antiKnockback = false;
        maxLife = 4;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/xlime/xlime_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/xlime/xlime_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/xlime/xlime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/xlime/xlime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/xlime/xlime_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/xlime/xlime_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/xlime/xlime_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/xlime/xlime_right_2",gp.tileSize,gp.tileSize);
    }

    public void setAction() {

    getRandomDirection();
    }
    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop(){

        //ELEGIR UN NUMERO DEL 1 AL 100
        int i = new Random().nextInt(100) + 1;

        //ELEGIR EL DROP DEL MOUNSTRO
        if(i < 30) {
            dropItem(new OBJ_Penny(gp));
        }
        if(i >= 30 && i <= 60){
            dropItem(new OBJ_Heart(gp));
        }
        if(i == 99){
            dropItem(new OBJ_Nickel(gp));
        }

    }
}
