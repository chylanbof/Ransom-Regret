package enviroment;

import main.GamePanel;

import java.awt.*;

public class EnviromentManager {

    GamePanel gp;
    Lightning lightning;

    public EnviromentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setup(){
        lightning = new Lightning(gp);
    }
    public void update(){
        if(gp.currentMap == 3) {
            lightning.update();
        }
    }
    public void draw(Graphics2D g2){
        if(gp.currentMap == 3) {
            lightning.draw(g2);
        }
    }
}
