package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);

        name = npcName;

        if (gp.player != null) {
            getImage(gp.player.characterType, gp.player.isWearingXRayGlasses);
        }
    }
    public void getImage(String characterType, boolean isWearingXRayGlasses) {
        if (characterType.equals("chico")) {
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
}
