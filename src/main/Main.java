package main;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
//PROBANDO EL GITHUB
    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ransom & Regret");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icono = toolkit.getImage("res/icon/icon.png");
        window.setIconImage(icono);
        Image cursorImg = toolkit.getImage("res/cursor/cursor1.png");
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new java.awt.Point(0, 0), "Custom Cursor");
        window.setCursor(customCursor);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
