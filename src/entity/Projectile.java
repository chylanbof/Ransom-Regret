package entity;

import main.GamePanel;

public class Projectile extends Entity{

    Entity user;
    public int spriteCount;
    public int soundEffectIndex;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        gp.playSE(soundEffectIndex);

    }
    public void update(){

    if(gp.player != null) {
        if(user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, attack*(gp.player.attack/2), knockBackPower);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, user.projectile);
                alive = false;
            }
        }
    }


        switch (direction) {
            case "up": worldY -= speed;break;
            case "down": worldY += speed;break;
            case "left": worldX -= speed;break;
            case "right": worldX += speed;break;
        }

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            spriteNum++;
            if (spriteNum > spriteCount){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }
    public void sustractResource(Entity user) {
    }

}
