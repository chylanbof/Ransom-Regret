package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Vigilux extends Entity {

    GamePanel gp;

    public MON_Vigilux(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Vigilux";
        defaultSpeed = 1;
        speed = defaultSpeed;
        antiKnockback = true;
        maxLife = 25;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 30;
        knockBackPower = 5;

        solidArea.x = 3*3;
        solidArea.y = 18*3;
        solidArea.width = 42*3;
        solidArea.height = 30*3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48*3;
        attackArea.height = 48*3;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/monster/vigilux/vigilux_up_1", gp.tileSize * 3, gp.tileSize * 3);
        up2 = setup("/monster/vigilux/vigilux_up_2", gp.tileSize * 3, gp.tileSize * 3);
        down1 = setup("/monster/vigilux/vigilux_down_1", gp.tileSize * 3, gp.tileSize * 3);
        down2 = setup("/monster/vigilux/vigilux_down_2", gp.tileSize * 3, gp.tileSize * 3);
        left1 = setup("/monster/vigilux/vigilux_left_1", gp.tileSize * 3, gp.tileSize * 3);
        left2 = setup("/monster/vigilux/vigilux_left_2", gp.tileSize * 3, gp.tileSize * 3);
        right1 = setup("/monster/vigilux/vigilux_right_1", gp.tileSize * 3, gp.tileSize * 3);
        right2 = setup("/monster/vigilux/vigilux_right_2", gp.tileSize * 3, gp.tileSize * 3);
    }
    public void getAttackImage() {
        attackUp1 = setup("/monster/vigilux/vigilux_attack_up_1", gp.tileSize * 3, gp.tileSize * 6);
        attackUp2 = setup("/monster/vigilux/vigilux_attack_up_2", gp.tileSize * 3, gp.tileSize * 6);
        attackDown1 = setup("/monster/vigilux/vigilux_attack_down_1", gp.tileSize * 3, gp.tileSize * 6);
        attackDown2 = setup("/monster/vigilux/vigilux_attack_down_2", gp.tileSize * 3, gp.tileSize * 6);
        attackLeft1 = setup("/monster/vigilux/vigilux_attack_left_1", gp.tileSize * 6, gp.tileSize * 3);
        attackLeft2 = setup("/monster/vigilux/vigilux_attack_left_2", gp.tileSize * 6, gp.tileSize * 3);
        attackRight1 = setup("/monster/vigilux/vigilux_attack_right_1", gp.tileSize * 6, gp.tileSize * 3);
        attackRight2 = setup("/monster/vigilux/vigilux_attack_right_2", gp.tileSize * 6, gp.tileSize * 3);
    }

    public void setAction() {

        getRandomDirection();

        if (!attacking) {
            checkAttackOrNot(10, gp.tileSize * 4, gp.tileSize * 2);
        }
    }
    public void damageReaction(){

        actionLockCounter = 0;

        if (gp.player != null) {
            direction = gp.player.direction;
        }

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
