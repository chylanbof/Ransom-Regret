package entity;

import main.GamePanel;
import main.Teclado;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    Teclado keyH;

    public final int screenX;
    public final int screenY;
    public String characterType;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;
    public boolean isWearingXRayGlasses = false;

    public Player(GamePanel gp, Teclado keyH, String characterType) {

        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.characterType = characterType;
        setCharacter(characterType);


        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        //HITBOX
        solidArea.x = 9;
        solidArea.y = 9;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues(characterType);
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        gp.aSetter.setObject(characterType);
    }
    public void setCharacter(String characterType) {
        this.characterType = characterType;
        setDefaultValues(characterType);
        getImage(); // Cargar los sprites correctos
        getAttackImage();
        getGuardImage();
        setItems();
        gp.aSetter.setObject(characterType);
    }
    public void cargarPartida(String characterType) {

        //CAMBIAR DONDE EMPIEZA EL PERSONAJE
        gp.currentMap = 0;
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 25;

        antiKnockback = false;
        //DIRECCION EN LA QUE EMPIEZA
        direction = "down";

        invincible = false;


        currentLight = null;
        if(characterType.equals("chico")) {
            projectile = new OBJ_Fart(gp);
        }
        if(characterType.equals("chica")) {
            projectile = new OBJ_Fireball(gp);
        }
        attack = getAttack(); //el valor del ataque total se decide por la fuerza y el arma
        defense = getDefense(); //lo mismo de arriba pero por el dexterity y el escudo

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();

    }
    public void setDefaultValues(String characterType) {

        //CAMBIAR DONDE EMPIEZA EL PERSONAJE
         gp.currentMap = 0;
         worldX = gp.tileSize * 20;
         worldY = gp.tileSize * 24;
//        gp.currentMap = 6;
//        worldX = gp.tileSize * 28;
//     worldY = gp.tileSize * 30;

        //VELOCIDAD
        defaultSpeed = 5;
        speed = defaultSpeed;
        antiKnockback = false;
        //DIRECCION EN LA QUE EMPIEZA
        direction = "down";
        //PARA NO EMPEZAR CON HITS DE INVINCIBILIDAD
        invincible = false;

        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strenght = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 20;
        currentWeapon = new OBJ_Sword(gp);
        currentShield = new OBJ_Useless_Shield(gp);
        currentLight = null;
        if(characterType.equals("chico")) {
            projectile = new OBJ_Fart(gp);
        }
        if(characterType.equals("chica")) {
            projectile = new OBJ_Fireball(gp);
        }
        attack = getAttack(); //el valor del ataque total se decide por la fuerza y el arma
        defense = getDefense(); //lo mismo de arriba pero por el dexterity y el escudo

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();

    }
    public void setDefaultPositions(){
        gp.currentMap = 0;
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 25;
        direction = "down";
    }
    public void setDialogue() {
        if (characterType.equals("chico")) {
            dialogues[0][0] = "¡Subiste al nivel " + level + "!\n"
                    + "Te sientes mas poderoso...";
        } else if (characterType.equals("chica")) {
            dialogues[0][0] = "¡Subiste al nivel " + level + "!\n"
                    + "Te sientes mas poderosa...";
        }
    }
    public void restoreStatus(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
        speed = defaultSpeed;
    }
    public void setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strenght + currentWeapon.attackValue;
    }
    public int getDefense() {
        return defense = dexterity + currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }
    public void getImage() {
        if (characterType.equals("chico")) {
            blinkSprite = setup("/player/Chylan/Chylan_Map", gp.tileSize, gp.tileSize);
            if (currentLight != null && currentLight.type == type_light) {
                isWearingXRayGlasses = true;
                up1 = setup("/player/Chylan/Chylan_visor_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Chylan/Chylan_visor_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Chylan/Chylan_visor_down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Chylan/Chylan_visor_down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Chylan/Chylan_visor_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Chylan/Chylan_visor_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Chylan/Chylan_visor_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Chylan/Chylan_visor_right_2", gp.tileSize, gp.tileSize);
            } else {
                isWearingXRayGlasses = false;
                up1 = setup("/player/Chylan/Chylan_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Chylan/Chylan_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Chylan/Chylan_Down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Chylan/Chylan_Down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Chylan/Chylan_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Chylan/Chylan_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Chylan/Chylan_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Chylan/Chylan_right_2", gp.tileSize, gp.tileSize);
            }
        } else if (characterType.equals("chica")) {
            blinkSprite = setup("/player/Chylandra/Chylandra_Map", gp.tileSize, gp.tileSize);
            if (currentLight != null && currentLight.type == type_light) {
                isWearingXRayGlasses = true;
                up1 = setup("/player/Chylandra/Chylandra_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Chylandra/Chylandra_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Chylandra/Chylandra_visor_down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Chylandra/Chylandra_visor_down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Chylandra/Chylandra_visor_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Chylandra/Chylandra_visor_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Chylandra/Chylandra_visor_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Chylandra/Chylandra_visor_right_2", gp.tileSize, gp.tileSize);
            } else {
                isWearingXRayGlasses = false;
                up1 = setup("/player/Chylandra/Chylandra_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Chylandra/Chylandra_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Chylandra/Chylandra_Down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Chylandra/Chylandra_Down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Chylandra/Chylandra_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Chylandra/Chylandra_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Chylandra/Chylandra_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Chylandra/Chylandra_right_2", gp.tileSize, gp.tileSize);
            }
        }
    }
    public void getAttackImage() {
        if (currentWeapon.type == type_defaultWeapon){
        if (characterType.equals("chico")) {
            attackUp1 = setup("/player/Chylan/Chylan_attack_up_1", gp.tileSize,gp.tileSize*2);
            attackUp2 = setup("/player/Chylan/Chylan_attack_up_2", gp.tileSize,gp.tileSize*2);
            attackDown1 = setup("/player/Chylan/Chylan_attack_down_1", gp.tileSize,gp.tileSize*2);
            attackDown2 = setup("/player/Chylan/Chylan_attack_down_2", gp.tileSize,gp.tileSize*2);
            attackLeft1 = setup("/player/Chylan/Chylan_attack_left_1", gp.tileSize*2,gp.tileSize);
            attackLeft2 = setup("/player/Chylan/Chylan_attack_left_2", gp.tileSize*2,gp.tileSize);
            attackRight1 = setup("/player/Chylan/Chylan_attack_right_1", gp.tileSize*2,gp.tileSize);
            attackRight2 = setup("/player/Chylan/Chylan_attack_right_2", gp.tileSize*2,gp.tileSize);
        } else if (characterType.equals("chica")) {
            attackUp1 = setup("/player/Chylandra/Chylandra_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/Chylandra/Chylandra_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/Chylandra/Chylandra_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/Chylandra/Chylandra_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/Chylandra/Chylandra_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/Chylandra/Chylandra_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/Chylandra/Chylandra_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/Chylandra/Chylandra_attack_right_2", gp.tileSize * 2, gp.tileSize);
             }
        }
        if (currentWeapon.type == type_sword) {
            if (characterType.equals("chico")) {
                attackUp1 = setup("/player/Chylan/Chylan_sword_up_1", gp.tileSize,gp.tileSize*2);
                attackUp2 = setup("/player/Chylan/Chylan_sword_up_2", gp.tileSize,gp.tileSize*2);
                attackDown1 = setup("/player/Chylan/Chylan_sword_down_1", gp.tileSize,gp.tileSize*2);
                attackDown2 = setup("/player/Chylan/Chylan_sword_down_2", gp.tileSize,gp.tileSize*2);
                attackLeft1 = setup("/player/Chylan/Chylan_sword_left_1", gp.tileSize*2,gp.tileSize);
                attackLeft2 = setup("/player/Chylan/Chylan_sword_left_2", gp.tileSize*2,gp.tileSize);
                attackRight1 = setup("/player/Chylan/Chylan_sword_right_1", gp.tileSize*2,gp.tileSize);
                attackRight2 = setup("/player/Chylan/Chylan_sword_right_2", gp.tileSize*2,gp.tileSize);
            } else if (characterType.equals("chica")) {
                attackUp1 = setup("/player/Chylandra/Chylandra_sword_up_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/player/Chylandra/Chylandra_sword_up_2", gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/player/Chylandra/Chylandra_sword_down_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/Chylandra/Chylandra_sword_down_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/Chylandra/Chylandra_sword_left_1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/player/Chylandra/Chylandra_sword_left_2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/Chylandra/Chylandra_sword_right_1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/Chylandra/Chylandra_sword_right_2", gp.tileSize * 2, gp.tileSize);
            }

        }
    }
    public void getGuardImage() {
        if (characterType.equals("chico")) {
            guardUp = setup("/player/Chylan/Chylan_guard_up", gp.tileSize, gp.tileSize);
            guardDown = setup("/player/Chylan/Chylan_guard_down", gp.tileSize, gp.tileSize);
            guardLeft = setup("/player/Chylan/Chylan_guard_left", gp.tileSize, gp.tileSize);
            guardRight = setup("/player/Chylan/Chylan_guard_right", gp.tileSize, gp.tileSize);
        } else if (characterType.equals("chica")) {
            guardUp = setup("/player/Chylandra/Chylandra_guard_up", gp.tileSize, gp.tileSize);
            guardDown = setup("/player/Chylandra/Chylandra_guard_down", gp.tileSize, gp.tileSize);
            guardLeft = setup("/player/Chylandra/Chylandra_guard_left", gp.tileSize, gp.tileSize);
            guardRight = setup("/player/Chylandra/Chylandra_guard_right", gp.tileSize, gp.tileSize);
        }
    }

    public void update() {
        if (knockBack) {
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);


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
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking) {
            attacking();
        } else if (keyH.spacePressed) {
            System.out.println("asd");
            guarding = true;
            guardCounter++;
        } else if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }


            //TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);


            //CHECK EVENT
            gp.eHandler.checkEvent();


            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {

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


            if (keyH.enterPressed && !attackCanceled) {
                if (isWearingXRayGlasses) {
                    gp.keyH.enterPressed = false;
                    return;

                }
                if (currentWeapon.type == type_defaultWeapon) {
                    if (characterType.equals("chico")) {
                        gp.playSE(2);
                    } else if (characterType.equals("chica")) {
                        gp.playSE(10);
                    }
                }
                if (currentWeapon.type == type_sword) {
                    gp.playSE(18);
                }
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            //SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            //SUSTRACT THE COST (MANA,AMMO,ETC)
            projectile.sustractResource(this);

            //CHECK VACANCY
            for(int i = 0; i < gp.projectile[1].length; i++) {
                if(gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if(life > maxLife) {
            life = maxLife;
        }
        if(mana > maxMana) {
            mana = maxMana;
        }
        if(life<= 0) {
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.gameState = gp.gameOverState;
            gp.playMusic(27);
        }
   }

    public void pickUpObject(int i) {
        if(i != 999) {

            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][i].type == type_pickUpOnly) {

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            //OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if(keyH.enterPressed) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            //INVENTORY ITEMS
            else {
                String text;

                if(canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(3);
                    text = gp.obj[gp.currentMap][i].name + " se guardo en tu inventario";
                }
                else {
                    text = "¡No puedes llevar mas objetos!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }



        }
    }
    public void interactNPC(int i) {

            if(gp.keyH.enterPressed) {
                if(i != 999) {
                    attackCanceled = true;
                    gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMonster(int i) {
        if(i != 999) {
            if(!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSE(8);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage <1) {
                    damage = 1;
                }
            life -= damage;
            invincible = true;
            transparent = true;
        }
     }
}
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if(i != 999) {

        if(!gp.monster[gp.currentMap][i].invincible) {

            gp.playSE(11);

            if(knockBackPower > 0 && !gp.monster[gp.currentMap][i].antiKnockback) {
                setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
            }

            if(gp.monster[gp.currentMap][i].offBalance) {
                attack *=2;
            }

            int damage = attack - gp.monster[gp.currentMap][i].defense;
            if (damage <0) {
                damage = 0;
            }
            gp.monster[gp.currentMap][i].life -= damage;
            gp.ui.addMessage("¡"+ damage + " de daño!");

            gp.monster[gp.currentMap][i].invincible = true;
            gp.monster[gp.currentMap][i].damageReaction();

            if(gp.monster[gp.currentMap][i].life <= 0){
                gp.monster[gp.currentMap][i].dying = true;
                gp.ui.addMessage("¡Mataste un " + gp.monster[gp.currentMap][i].name + "!");
                gp.ui.addMessage("+" + gp.monster[gp.currentMap][i].exp + " de EXP");
                exp += gp.monster[gp.currentMap][i].exp;
                checkLevelUp();
            }

        }
     }
}
    public void damageInteractiveTile(int i){

        if(i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible){
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // el generador de particulas
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }

        }
}
    public void damageProjectile(int i) {

        if(i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    public void checkLevelUp() {

        if(exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strenght++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(15);
            gp.gameState = gp.dialogueState;
            if (characterType.equals("chico")) {
                dialogues[0][0] = "¡Subiste al nivel " + level + "!\n"
                        + "Te sientes mas poderoso...";
            } else if (characterType.equals("chica")) {
                dialogues[0][0] = "¡Subiste al nivel " + level + "!\n"
                        + "Te sientes mas poderosa...";
            }
            setDialogue();
                    startDialogue(this,0);

        }
    }
    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if(itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_defaultWeapon || selectedItem.type == type_sword) {

                currentWeapon = selectedItem;
                attack = getAttack();
                gp.playSE(26);
                getAttackImage();

            }
            if(selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
                gp.playSE(26);
            }
            if(selectedItem.type == type_light) {

                if(currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumable) {
                if(selectedItem.use(this)) {
                    gp.playSE(26);
                    if(selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInInventory(String itenName) {

        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itenName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;

        Entity newItem = gp.eGenerator.getObject(item.name);

        // CHECK If ITEM StACKABLE
        if(newItem.stackable) {

            int index = searchItemInInventory(newItem.name);

            if(index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else { // New item so need to check vacancy
                if(inventory.size() != maxInventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        else { //NOT STACKABLE so check vacancy
            if(inventory.size() != maxInventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if(!attacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = up3;}
                    if (spriteNum == 4) {image = up4;}
                }
                if(attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                if(guarding) {
                    image = guardUp;
                }
                break;
            case "down":
                if(!attacking) {
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                if (spriteNum == 3) {image = down3;}
                if (spriteNum == 4) {image = down4;}
                }
                if(attacking) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                if(guarding) {
                    image = guardDown;
                }
                break;
            case "left":
                if(!attacking) {
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                if (spriteNum == 3) {image = left3;}
                if (spriteNum == 4) {image = left4;}
                }
                if(attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                if(guarding) {
                    image = guardLeft;
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = right3;}
                    if (spriteNum == 4) {image = right4;}
                }
                if(attacking) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                if(guarding) {
                    image = guardRight;
                }
                break;
        }
        if(transparent) {
           g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG

    }
}
