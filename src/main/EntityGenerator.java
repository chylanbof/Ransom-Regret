package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case OBJ_Bean_Can.objName: obj = new OBJ_Bean_Can(gp); break;
            case OBJ_Boss_Door.objName: obj = new OBJ_Boss_Door(gp); break;
            case OBJ_Boss_Key.objName: obj = new OBJ_Boss_Key(gp); break;
            case OBJ_Burger.objName: obj = new OBJ_Burger(gp); break;
            case OBJ_ButterBean.objName: obj = new OBJ_ButterBean(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_Clip.objName: obj = new OBJ_Clip(gp); break;
            case OBJ_Dime.objName: obj = new OBJ_Dime(gp); break;
            case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
            case OBJ_Fart.objName: obj = new OBJ_Fart(gp); break;
            case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp); break;
            case OBJ_Gusanin.objName: obj = new OBJ_Gusanin(gp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_Locker.objName: obj = new OBJ_Locker(gp); break;
            case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
            case OBJ_Nickel.objName: obj = new OBJ_Nickel(gp); break;
            case OBJ_Noob_Magic.objName: obj = new OBJ_Noob_Magic(gp); break;
            case OBJ_Nota.objName: obj = new OBJ_Nota(gp); break;
            case OBJ_Penny.objName: obj = new OBJ_Penny(gp); break;
            case OBJ_Rock.objName: obj = new OBJ_Rock(gp); break;
            case OBJ_Shield.objName: obj = new OBJ_Shield(gp); break;
            case OBJ_Sword.objName: obj = new OBJ_Sword(gp); break;
            case OBJ_Sword2.objName: obj = new OBJ_Sword2(gp); break;
            case OBJ_Useless_Shield.objName: obj = new OBJ_Useless_Shield(gp); break;
            case OBJ_Xray_Shades.objName: obj = new OBJ_Xray_Shades(gp); break;
            case OBJ_Yucky_Key.objName: obj = new OBJ_Yucky_Key(gp); break;

        }
        return obj;
    }
}
