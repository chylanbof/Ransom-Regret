package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Farty extends Entity {

    GamePanel gp;

    public MON_Farty(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Farty";
        defaultSpeed = 2;
        speed = defaultSpeed;
        antiKnockback = false;
        maxLife = 3;
        life = maxLife;
        attack = 3;
        defense = 1;
        exp = 5;
        projectile = new OBJ_Fart(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/farty/farty_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/farty/farty_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/farty/farty_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/farty/farty_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/farty/farty_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/farty/farty_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/farty/farty_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/farty/farty_right_2",gp.tileSize,gp.tileSize);
    }

    public void setAction() {
        getRandomDirection();
        checkShootOrNot(200, 30);
    }
    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop(){

        //ELEGIR UN NUMERO DEL 1 AL 100
        int i = new Random().nextInt(100) + 1;

        //ELEGIR EL DROP DEL MOUNSTRO
        if(i < 20) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
        if(i >= 80) {
            dropItem(new OBJ_Penny(gp));
        }
        if(i >= 60 && i < 80) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 40 && i < 60) {
            dropItem(new OBJ_Nickel(gp));
        }
    }
}
