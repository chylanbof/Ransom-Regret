package entity;


import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {
    public GamePanel gp;
    public BufferedImage up1, up2 , up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public BufferedImage dying1;
    public BufferedImage blinkSprite;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String dialogues[][] = new String [40][40];
    public Entity attacker;

    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int knockBackCounter = 0;
    public int guardCounter = 0;
    public int offBalanceCounter = 0;

    // ATRIBUTOS DEL PERSONAJE
    public String name;
    public int voiceSE = -1;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strenght;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public boolean antiKnockback;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int sellPrice;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_defaultWeapon = 3;
    public final int type_sword = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickUpOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() { return worldX +solidArea.x + solidArea.width; }
    public int getTopY() { return worldY + solidArea.y; }
    public int getBottomY() { return worldY + solidArea.y + solidArea.height; }
    public int getCol() { return (worldX + solidArea.x)/gp.tileSize; }
    public int getRow() { return (worldY + solidArea.y)/gp.tileSize; }
    public int getCenterX() {return worldX + left1.getWidth()/2;}
    public int getCenterY() {return worldY + up1.getHeight()/2;}
    public int getXdistance(Entity target) {
        return Math.abs(getCenterX() - target.getCenterX());
    }
    public int getYdistance(Entity target) {
        return Math.abs(getCenterY() - target.getCenterY());
    }
    public int getTileDistance(Entity target) {
        return (getXdistance(target) + getYdistance(target)/gp.tileSize);
    }
    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x)/gp.tileSize;
    }
    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y)/gp.tileSize;
    }
    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setLoot(Entity loot) {}
    public void setAction(){

    }
    public void damageReaction(){

    }
    public void speak(){



    }
    public void facePlayer() {

        switch(gp.player.direction){
            case "up": direction = "down";break;
            case "down": direction = "up";break;
            case "left": direction = "right";break;
            case "right": direction = "left";break;

        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;

    }
    public void interact() {

    }
    public boolean use(Entity entity) {return false;}
    public void checkDrop() {

    }
    public void dropItem(Entity droppedItem) {
        for(int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0; // pixeles
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0; //velocidad de las particulas
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 0; //cuanto tiempo duraran las particulas
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2,   1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);
        }
    }
    public void update(){


            if (knockBack) {
                checkCollision();

                if (collisionOn) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                } else if (!collisionOn) {
                    switch (knockBackDirection) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }


                knockBackCounter++;
                if(knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
            else if (attacking) {
                attacking();
            }
            else {
                setAction();
                checkCollision();

                //IF COLLISION IS FALSE; PLAYER CAN MOVE

                if (!collisionOn) {

                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 12) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }


        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if(offBalance) {
            offBalanceCounter++;
            if(offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }
    public void checkStartChasingOrNot(Entity target,int distance, int rate) {
        if(getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if(i == 0) {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {

        if(getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if(i == 0) {
                onPath = false;
            }
        }
    }
    public void getRandomDirection() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //eligue un numero del 1 al 100

            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75 && i <= 100) {direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        if (gp.player != null) {
            int xDis = getXdistance(gp.player);
            int yDis = getYdistance(gp.player);

            switch (direction) {
                case "up":
                    if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "down":
                    if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "left":
                    if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "right":
                    if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
            }
        }


        if(targetInRange) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate);
        if(i == 0 && !projectile.alive && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            //    gp.projectileList.add(projectile);

            //CHECK VACANCY
            for(int ii = 0; ii < gp.projectile[1].length; ii++) {
                if(gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public String getOppositeDirection(String direction) {

        String oppositeDirection = "";

        switch (direction) {
            case "up": oppositeDirection = "down";break;
            case "down": oppositeDirection = "up";break;
            case "left": oppositeDirection = "right";break;
            case "right": oppositeDirection = "left";break;
        }
        return oppositeDirection;
    }
    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if(spriteCounter> motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // Save the current worldX, worldY y solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Ajusta el worldX/Y del jugador para el area de ataque
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.height; break;
                case "right": worldX += attackArea.height; break;
            }
            //attack area becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == type_monster) {
                if(gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            }
            else { // Jugador
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }
            //check monster collision with the updated worldX, worldY and solidArea


            //Despues de mirar la colision, vuelve a los datos originales
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }
    public void damagePlayer (int attack) {
        if(!gp.player.invincible){

            int damage = attack - gp.player.defense;

            //Get an opposite direction of this attacker
            String canGuardDirection = getOppositeDirection(direction);

            if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {

                //Parry
                if (gp.player.guardCounter < 5) {
                    damage = 0;
                    gp.playSE(40);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter =- 60;
                } else {
                    //Normal guard
                    damage /= 3;
                    gp.playSE(41);
                }
            }
            else {
                //not guarding
                gp.playSE(8);
                if (damage < 1) {
                    damage = 1;
                }
            }

            if(damage != 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;

    }
    public void draw(Graphics2D g2) {


        BufferedImage image = null;
        if(gp.player != null) {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                int tempScreenX = screenX;
                int tempScreenY = screenY;

                switch (direction) {
                    case "up":
                        if (!attacking) {
                            if (spriteNum == 1) { image = up1; }
                            if (spriteNum == 2) { image = up2; }
                            if (spriteNum == 3) { image = up3; }
                            if (spriteNum == 4) { image = up4; }
                        }
                        if (attacking) {
                            tempScreenY = screenY - up1.getHeight();
                            if (spriteNum == 1) { image = attackUp1; }
                            if (spriteNum == 2) { image = attackUp2; }
                        }
                        break;

                    case "down":
                        if (!attacking) {
                            if (spriteNum == 1) { image = down1; }
                            if (spriteNum == 2) { image = down2; }
                            if (spriteNum == 3) { image = down3; }
                            if (spriteNum == 4) { image = down4; }
                        }
                        if (attacking) {
                            if (spriteNum == 1) { image = attackDown1; }
                            if (spriteNum == 2) { image = attackDown2; }
                        }
                        break;

                    case "left":
                        if (!attacking) {
                            if (spriteNum == 1) { image = left1; }
                            if (spriteNum == 2) { image = left2; }
                            if (spriteNum == 3) { image = left3; }
                            if (spriteNum == 4) { image = left4; }
                        }
                        if (attacking) {
                            tempScreenX = screenX - left1.getWidth();
                            if (spriteNum == 1) { image = attackLeft1; }
                            if (spriteNum == 2) { image = attackLeft2; }
                        }
                        break;

                    case "right":
                        if (!attacking) {
                            if (spriteNum == 1) { image = right1; }
                            if (spriteNum == 2) { image = right2; }
                            if (spriteNum == 3) { image = right3; }
                            if (spriteNum == 4) { image = right4; }
                        }
                        if (attacking) {
                            if (spriteNum == 1) { image = attackRight1; }
                            if (spriteNum == 2) { image = attackRight2; }
                        }
                        break;
                }

                //La barra de vida de los mounstros
                if(type ==2 && hpBarOn) {

                    double oneScale = (double)gp.tileSize/maxLife;
                    double hpBarValue = oneScale*life;

                    g2.setColor(new Color(0, 0, 0));
                    g2.fillRect(screenX-1, screenY-16, gp.tileSize+ 2, 12);

                    g2.setColor(new Color(255, 255, 255));
                    g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                    hpBarCounter++;

                    if(hpBarCounter > 300) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

                if (invincible) {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2,0.4f);
                }
                if (dying) {
                    dyingAnimation(g2);
                } else {
                    g2.drawImage(image, tempScreenX, tempScreenY, null);
                    changeAlpha(g2,1f);
                }
            }
        }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;
        dying1 = setup("/images/death_animation", gp.tileSize, gp.tileSize);

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else if (dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
            g2.drawImage(dying1, screenX, screenY, null);
        } else {
            alive = false;
        }

        // Restaura la opacidad predeterminada al final
        changeAlpha(g2, 1f);
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search()) {

            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
            // Entity?s solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right{
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                //down or right
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }
            // If reaches the goal, stop the search
            //   int nextCol = gp.pFinder.pathList.get(0).col;
            //   int nextRow = gp.pFinder.pathList.get(0).row;
            //   if(nextCol == goalCol && nextRow == goalRow) {
            //       onPath = false;
            //     }
                }
        }
    public int getDetected(Entity user, Entity target[][], String targetName) {

        int index = 999;

        //Check the surrouding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction) {
            case "up": nextWorldY = user.getTopY()-gp.player.speed;break;
            case "down": nextWorldY = user.getBottomY()+gp.player.speed;break;
            case "left": nextWorldX = user.getLeftX()-gp.player.speed;break;
            case "right": nextWorldX = user.getRightX()+gp.player.speed;break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int i =0; i < target[1].length; i++) {
            if(target[gp.currentMap][i] != null) {
                if(target[gp.currentMap][i].getCol() == col &&
                target[gp.currentMap][i].getRow() == row &&
                target[gp.currentMap][i].name.equals(targetName)) {

                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}


