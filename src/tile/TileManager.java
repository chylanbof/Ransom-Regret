package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];


    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100]; //si quiero añadir mas tiles en un futuro agrandar esto

        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("maps/mapatocho01.txt", 0);
        loadMap("maps/camion.txt", 1);
        loadMap("maps/tienda1.txt", 2);
        loadMap("maps/objeto1.txt", 3);
        loadMap("maps/transicion.txt", 4);
        loadMap("maps/matadero1.txt", 5);
        loadMap("maps/matadero2.txt", 6);
    }

    public void getTileImage(){
        //placeholder
        setup(59, "00", false);
        setup(60, "00", false);
        setup(61, "00", false);




            //relleno
            setup(0, "00", true);

            //suelo
            setup(1, "01", false);
            setup(2, "02", false);
            setup(3, "03", false);
            //muro
            setup(4, "04", true);
            //Barril
            setup(5, "05", true);
            //pinchos
            setup(6, "06", true);
            setup(7, "07", true);
            //telaraña
            setup(8, "08", false);
            //cosas que hagan daño
            setup(9, "09", false);
            setup(10, "10", false);
            //agua
            setup(11, "11", false);
            //agua sprite grande
            setup(12, "12", true);
            setup(13, "13", true);
            setup(14, "14", false);
            setup(15, "15", false);
            //portal
            setup(16, "16", false);
            //camion
            setup(17, "17", false);
            setup(18, "18", false);
            setup(19, "19", false);
            setup(20, "20", false);
            setup(21, "21", false);
            setup(22, "22", false);
            setup(23, "23", false);
            setup(24, "24", false);
            setup(25, "25", false);
            setup(26, "26", false);
            setup(27, "27", false);
            setup(28, "28", true);
            setup(29, "29", true);
        //tienda
            setup(30, "30", true);
            setup(31, "31", true);
            setup(32, "32", true);
            setup(33, "33", true);
            setup(34, "34", true);
            setup(35, "35", true);
            setup(36, "36", true);
            setup(37, "37", true);
            setup(38, "38", true);
            setup(39, "39", true);
            setup(40, "40", true);
            setup(41, "41", true);
            setup(42, "42", true);
            setup(43, "43", true);
            setup(44, "44", true);
            setup(45, "45", true);
            setup(46, "46", true);
            setup(47, "47", true);
            setup(48, "48", true);
            setup(49, "49", true);
            setup(50, "50", true);
            setup(51, "51", true);
            setup(52, "52", true);
            setup(53, "53", true);
            //escaleras
            setup(54, "54", false);
            setup(55, "55", false);
            setup(56, "56", true);
            //portales
            setup(57, "57", false);
            setup(58, "58", false);
            //paredes falsas
            setup(59, "59", false);


    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/" + imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol); {
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch(Exception e) {

        }
    }
    public void draw(Graphics2D g2) {



        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            if (gp.player != null) {
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }




            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
        if(gp.keyH.showDebugText) {
            g2.setColor(new Color(255,0,0,70));

            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {

                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                if (gp.player != null) {
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;

                    g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                }

            }
        }


    }
}
