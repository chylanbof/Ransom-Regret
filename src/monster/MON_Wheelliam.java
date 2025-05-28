package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Dime;
import object.OBJ_Heart;
import object.OBJ_Nickel;
import object.OBJ_Penny;

import java.util.Random;

public class MON_Wheelliam extends Entity {

    GamePanel gp;
    public boolean hasStartedChasing = false;

    public MON_Wheelliam(GamePanel gp) {
        super(gp);

        this.gp = gp;


        type = type_monster;
        name = "Wheelliam";
        defaultSpeed = 3;
        speed = defaultSpeed;
        antiKnockback = true;
        maxLife = 12;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 12;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/wheelliam/wheelliam_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/wheelliam/wheelliam_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/wheelliam/wheelliam_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/wheelliam/wheelliam_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/wheelliam/wheelliam_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/wheelliam/wheelliam_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/wheelliam/wheelliam_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/wheelliam/wheelliam_right_2",gp.tileSize,gp.tileSize);
    }

    public void setAction() {

        getRandomDirection();

        if(onPath) {
            if(!hasStartedChasing) {
                gp.playSE(38);
                hasStartedChasing = true;
            }


            //para que deje de buscar cuando estas lejos del bicho
            if (gp.player != null) {
                checkStopChasingOrNot(gp.player, 15, 100000000);

                //Buscar la direccion a la que ir
                searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            }
            else {
                checkStartChasingOrNot(gp.player, 15, 100000000);
                //ir a una dirección random si no esta en OnPath
            }
        }

    }
    public void damageReaction(){

//        actionLockCounter = 0;
        if (gp.player != null) {
            direction = gp.player.direction;
        }

        defaultSpeed = 6;
        speed = defaultSpeed;
        onPath = true;
    }

    public void checkDrop(){

        int i = new Random().nextInt(100) + 1;

        if(i < 70) {
            dropItem(new OBJ_Nickel(gp));
        }
        if (i >= 70) {
            dropItem(new OBJ_Dime(gp));
        }
    }
}
