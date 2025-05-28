package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    GamePanel gp;
    public static final String objName = "Roca";

    public OBJ_Rock(GamePanel gp){
        super(gp);
        this.spriteCount = 1;
        this.soundEffectIndex = 16;
        this.gp = gp;

        name = objName;
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 1;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = (setup("/projectile/rock1",gp.tileSize,gp.tileSize));
        down1 = (setup("/projectile/rock1",gp.tileSize,gp.tileSize));
        left1 = (setup("/projectile/rock2",gp.tileSize,gp.tileSize));
        right1 = (setup("/projectile/rock2",gp.tileSize,gp.tileSize));



    }
    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        if(user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void sustractResource(Entity user) {
        user.ammo -= useCost;
    }
    public Color getParticleColor() {
        Color color = new Color(22, 22, 22);
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
