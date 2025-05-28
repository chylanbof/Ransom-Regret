package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fart extends Projectile {

    GamePanel gp;
    public static final String objName = "Pedo";

    public OBJ_Fart(GamePanel gp){
        super(gp);
        this.spriteCount = 4;
        this.soundEffectIndex = 2;
        this.gp = gp;

        name = objName;
        speed = 7;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        knockBackPower = 3;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = (setup("/projectile/fart1",gp.tileSize,gp.tileSize));
        up2 = (setup("/projectile/fart2",gp.tileSize,gp.tileSize));
        up3 = (setup("/projectile/fart3",gp.tileSize,gp.tileSize));
        up4 = (setup("/projectile/fart4",gp.tileSize,gp.tileSize));
        down1 = (setup("/projectile/fart1",gp.tileSize,gp.tileSize));
        down2 = (setup("/projectile/fart2",gp.tileSize,gp.tileSize));
        down3 = (setup("/projectile/fart3",gp.tileSize,gp.tileSize));
        down4 = (setup("/projectile/fart4",gp.tileSize,gp.tileSize));
        left1 = (setup("/projectile/fart1",gp.tileSize,gp.tileSize));
        left2 = (setup("/projectile/fart2",gp.tileSize,gp.tileSize));
        left3 = (setup("/projectile/fart3",gp.tileSize,gp.tileSize));
        left4 = (setup("/projectile/fart4",gp.tileSize,gp.tileSize));
        right1 = (setup("/projectile/fart1",gp.tileSize,gp.tileSize));
        right2 = (setup("/projectile/fart2",gp.tileSize,gp.tileSize));
        right3 = (setup("/projectile/fart3",gp.tileSize,gp.tileSize));
        right4 = (setup("/projectile/fart4",gp.tileSize,gp.tileSize));



    }
    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        if(user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void sustractResource(Entity user) {
        user.mana -= useCost;
    }
    public Color getParticleColor() {
        Color color = new Color(0, 70,20 );
        return color;
    }
    public int getParticleSize() {
        int size = 10;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }
}
