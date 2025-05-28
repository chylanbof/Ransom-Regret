package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Boss extends Entity {

    GamePanel gp;
    public static final String monName = "Jefe";

    public MON_Boss(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        antiKnockback = true;
        maxLife = 100;
        life = maxLife;
        attack = 12;
        defense = 3;
        exp = 50;
        knockBackPower = 5;


        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 180;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        int i = 5;

        up1 = setup("/monster/boss/jefe_up_1", gp.tileSize * i, gp.tileSize * i);
        up2 = setup("/monster/boss/jefe_up_2", gp.tileSize * i, gp.tileSize * i);
        down1 = setup("/monster/boss/jefe_down_1", gp.tileSize * i, gp.tileSize * i);
        down2 = setup("/monster/boss/jefe_down_2", gp.tileSize * i, gp.tileSize * i);
        left1 = setup("/monster/boss/jefe_left_1", gp.tileSize * i, gp.tileSize * i);
        left2 = setup("/monster/boss/jefe_left_2", gp.tileSize * i, gp.tileSize * i);
        right1 = setup("/monster/boss/jefe_right_1", gp.tileSize * i, gp.tileSize * i);
        right2 = setup("/monster/boss/jefe_right_2", gp.tileSize * i, gp.tileSize * i);
    }

    public void getAttackImage() {
        int i = 5;

        attackUp1 = setup("/monster/boss/jefe_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
        attackUp2 = setup("/monster/boss/jefe_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
        attackDown1 = setup("/monster/boss/jefe_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
        attackDown2 = setup("/monster/boss/jefe_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
        attackLeft1 = setup("/monster/boss/jefe_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
        attackLeft2 = setup("/monster/boss/jefe_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
        attackRight1 = setup("/monster/boss/jefe_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
        attackRight2 = setup("/monster/boss/jefe_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
    }


    public void setAction() {

        if(onPath) {

        }
        else {

            getRandomDirection();
        }

        if(!attacking) {
            checkAttackOrNot(60,gp.tileSize*10, gp.tileSize*5);
        }
    }

    public void damageReaction() {

    }

    public void checkDrop(){

        //ELEGIR UN NUMERO DEL 1 AL 100
        int i = new Random().nextInt(100) + 1;

        //ELEGIR EL DROP DEL MOUNSTRO

        dropItem(new OBJ_Clip(gp));

    }

    @Override
    public void attacking() {
        super.attacking();


        if(spriteCounter == 40) {
            gp.playSE(10);
        }
    }
}
