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
        voiceSE = 52;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        antiKnockback = true;
        maxLife = 100;
        life = maxLife;
        attack = 12;
        defense = 4;
        exp = 50;
        knockBackPower = 5;
        sleep = true;


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
        if (gp.player != null) {
            setDialogue(gp.player.characterType);
        }
    }

    public void getImage() {
        int i = 5;

        if (!inRage) {
            up1 = setup("/monster/boss/walk_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/boss/walk_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/boss/walk_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/boss/walk_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/boss/walk_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/boss/walk_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/boss/walk_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/boss/walk_right_2", gp.tileSize * i, gp.tileSize * i);
        } else {
            up1 = setup("/monster/boss/phase2_walk_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/boss/phase2_walk_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/boss/phase2_walk_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/boss/phase2_walk_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/boss/phase2_walk_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/boss/phase2_walk_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/boss/phase2_walk_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/boss/phase2_walk_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }

    public void getAttackImage() {
        int i = 5;

        if (!inRage) {
            attackUp1 = setup("/monster/boss/attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/boss/attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/boss/attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/boss/attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/boss/attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/boss/attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/boss/attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/boss/attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        } else {
            attackUp1 = setup("/monster/boss/phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/boss/phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/boss/phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/boss/phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/boss/phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/boss/phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/boss/phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/boss/phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
    }
    public void setDialogue(String characterType) {

        dialogues[0][0] = "VAYA VAYA";
        dialogues[0][1] = "CREIAS QUE EL BICHO ANTERIOR ERA EL JEFE FINAL?";
        if(characterType.equals("chico")) {
            dialogues[0][2] = "VOY A HACERTE UN FULL NELSON GORDITO";
        }
        else if (characterType.equals("chica")) {
            dialogues[0][2] = "VOY A HACERTE UN FULL NELSON GORDITA";
        }
    }

    public void setAction() {

        if(!inRage && life < maxLife/2) {
            inRage = true;
            getImage();
            getAttackImage();
            speed += 2;
            attack += 2;
            defense += 1;
            gp.playSE(51);
        }
        if(getTileDistance(gp.player) < 2) {
            moveTowardPlayer(60);
        }
        else {

            getRandomDirection();
        }

        if(!attacking) {
            checkAttackOrNot(60,gp.tileSize*7, gp.tileSize*5);
        }
    }

    public void damageReaction() {

    }

    public void checkDrop(){

        //ELEGIR UN NUMERO DEL 1 AL 100
        int i = new Random().nextInt(100) + 1;

        //ELEGIR EL DROP DEL MOUNSTRO

        dropItem(new OBJ_Nota(gp));

    }

    @Override
    public void attacking() {
        super.attacking();


        if(spriteCounter == 40) {
            gp.playSE(10);
        }
    }
    public void initDialogueFromPlayer() {
        if (gp.player != null && gp.player.characterType != null) {
            setDialogue(gp.player.characterType);
        } else {
            System.out.println("Error: characterType no disponible.");
        }
    }
}
