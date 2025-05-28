package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_BreakableRock extends InteractiveTile {

    GamePanel gp;

    public IT_BreakableRock(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/breakable_rock", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 2;
    }
    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_defaultWeapon) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void playSE() {
        gp.playSE(24);
    }
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_BreakedRock(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(22, 22, 22);
        return color;
    }
    public int getParticleSize() {
        int size = 6; //6 pixeles
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
