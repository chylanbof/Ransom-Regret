package main;

import entity.Entity;
import entity.NPC_Billson;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Penny;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import static java.awt.Color.getColor;
import static java.awt.Color.white;

public class UI {


    GamePanel gp;
    Graphics2D g2;
    BufferedImage titleImage, GGImage, gameOverImage, gato_riendose, heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public Font Determination;
    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/font/determinationsansweb-webfont.ttf");
        try {
            Determination = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            titleImage = ImageIO.read(getClass().getResourceAsStream("/images/title_image.png"));
            gameOverImage = ImageIO.read(getClass().getResourceAsStream("/images/game_over.png"));
            gato_riendose = ImageIO.read(getClass().getResourceAsStream("/images/gato_riendose.png"));
            GGImage = ImageIO.read(getClass().getResourceAsStream("/images/gg_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Entity heart = new OBJ_Heart(gp);
            heart_full = heart.image;
            heart_half = heart.image2;
            heart_blank = heart.image3;
            Entity crystal = new OBJ_ManaCrystal(gp);
            crystal_full = crystal.image;
            crystal_blank = crystal.image2;
            Entity penny = new OBJ_Penny(gp);
            coin = penny.down1;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(Determination);
        g2.setColor(Color.black);

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }

        //PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            if(gp.ui.npc instanceof NPC_Billson) {
                drawDialogueScreenTrader();
            } else {
                drawDialogueScreen();
            }

        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState) {
           drawPlayerLife();
           drawCharacterScreen();
           drawInventory(gp.player, true);
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState) {
            drawPlayerLife();
            drawOptionsScreen();
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //TRADE STATE
        if(gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        //WIN STATE
        if(gp.gameState == gp.winState) {
            if (!gp.gameEnded) {
                    gp.endTime = Instant.now();
                gp.totalTimePlayed = Duration.between(gp.startTime, gp.endTime).getSeconds();
                gp.gameEnded = true;
                DBManager.guardarPartida((int)gp.totalTimePlayed, gp.player.level, gp.player.coin);

            }
            drawGGScreen();
        }

    }
    public void drawPlayerLife() {

        //gp.player.life = 6;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //DRAW MAX LIFE
        if(gp.player != null) {
            while(i < gp.player.maxLife/2) {
                g2.drawImage(heart_blank, x, y, null);
                i++;
                x += gp.tileSize;
            }
        }


        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        if(gp.player != null) {
            while(i < gp.player.life) {
                g2.drawImage(heart_half, x, y, null);
                i++;
                if(i < gp.player.life) {
                    g2.drawImage(heart_full, x, y, null);
                }
                i++;
                x += gp.tileSize;
            }
        }

        //DRAW MAX MANA
        x = gp.tileSize/2;
        y = gp.tileSize*2;
        i = 0;
        if(gp.player != null) {
            while(i < gp.player.maxMana) {
                g2.drawImage(crystal_blank, x, y, null);
                i++;
                x += 35;
            }
        }

        //DRAW MANA
        x = gp.tileSize/2;
        y = gp.tileSize*2;
        i = 0;
        if(gp.player != null) {
            while(i < gp.player.mana) {
                g2.drawImage(crystal_full, x, y, null);
                i++;
                x += 35;
            }
        }

    }
    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i <message.size(); i++) {

            if(message.get(i) != null) {

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        if(titleScreenState == 0) {

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Ransom & Regret";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            //SOMBRA
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);

            //COLOR PRINCIPAL
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //IMAGEN?????
            x = gp.screenWidth/2 - titleImage.getWidth()/2;
            y += gp.tileSize*5;
            g2.drawImage(titleImage, x, y, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "Nueva partida";
            x = getXforCenteredText(text);
            y += gp.tileSize - 180;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Cargar partida";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Salir del juego";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1) {

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Selecciona tu genero";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Chico";
            g2.setColor(Color.blue);
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Chica";
            g2.setColor(Color.pink);
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            g2.setColor(Color.white);
            text = "Atras";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.setColor(Color.white);
                g2.drawString(">", x-gp.tileSize, y);
            } else {
                g2.setColor(Color.white);
            }

        }

    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize;
        int y = gp.tileSize*9;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            //  currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            int defaultVoiceSE = 46;

            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if(charIndex < characters.length) {

                if (npc.voiceSE != -1) {
                    gp.playSE(npc.voiceSE);
                } else {
                    gp.playSE(defaultVoiceSE);
                }
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if(gp.keyH.enterPressed) {
                gp.keyH.enterPressed = false;

                if(charIndex < characters.length) {
                    combinedText = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
                    currentDialogue = combinedText;
                    charIndex = characters.length;
                } else {
                    charIndex = 0;
                    combinedText = "";
                    if(gp.gameState == gp.dialogueState) {
                        npc.dialogueIndex++;
                    }
                }
            }
        }
        else { // Si no hay texto en el array
            npc.dialogueIndex = 0;

            if(gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }
        int width = gp.screenWidth - (gp.tileSize*2);
        int height = gp.tileSize*3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawDialogueScreenTrader() {

        //WINDOW
        int x = gp.tileSize;
        int y = gp.tileSize*9;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            //  currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            int defaultVoiceSE = 46;

            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if(charIndex < characters.length) {

                if (npc.voiceSE != -1) {
                    gp.playSE(npc.voiceSE);
                } else {
                    gp.playSE(defaultVoiceSE);
                }
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if(gp.keyH.enterPressed) {

                charIndex = 0;
                combinedText = "";

                if(gp.gameState == gp.dialogueState) {

                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else { // Si no hay texto en el array
            npc.dialogueIndex = 0;

            if(gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }
        int width = gp.screenWidth - (gp.tileSize*2);
        int height = gp.tileSize*3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {

        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*7;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;

        // NAMES
        g2.drawString("Nivel", textX, textY);
        textY += lineHeight;
        g2.drawString("Vida", textX, textY);
        textY += lineHeight;
        g2.drawString("Maná", textX, textY);
        textY += lineHeight;
        g2.drawString("Fuerza", textX, textY);
        textY += lineHeight;
        g2.drawString("Destreza", textX, textY);
        textY += lineHeight;
        g2.drawString("Ataque", textX, textY);
        textY += lineHeight;
        g2.drawString("Defensa", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Siguiente nivel", textX, textY);
        textY += lineHeight;
        g2.drawString("Dinero", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Arma", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Escudo", textX, textY);
        textY += lineHeight;

        //VALORES
        int tailX = (frameX + frameWidth);
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strenght);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX-10 - gp.tileSize, textY-29, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX-10 - gp.tileSize, textY-29, null);
        textY += gp.tileSize;
    }
    public void drawInventory(Entity entity, boolean cursor) {

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gp.player) {
            frameX = gp.tileSize * 13;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else{
            frameX = gp.tileSize * 3;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DRAW PLAYER'S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {

            //EQUIP CURSOR
            if(entity.inventory.get(i) == entity.currentWeapon
                    || entity.inventory.get(i) == entity.currentShield
                    || entity.inventory.get(i) == entity.currentLight) {
                gp.player.getImage();
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // DISPLAY AMOUNT
            if(entity == gp.player && entity.inventory.get(i).amount > 1) {

                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = " " + entity.inventory.get(i).amount;
                amountX = getXforAllignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                // SHADOW
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);
                //NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);
            }

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            //DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 5;
            // DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(19F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex >= 0 && itemIndex < entity.inventory.size()) {

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);


                for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
        }
        public void drawGGScreen() {
            if (gp.endTime == null) {
                gp.endTime = Instant.now();
                gp.totalTimePlayed = Duration.between(gp.startTime, gp.endTime).getSeconds();
            }
            g2.setColor(new Color(0,0,0));//si quiero que sea transparente puedo añadir luego del ultimo 0 un 150.
            g2.fillRect(0, 0 , gp.screenWidth, gp.screenHeight);

            int x;
            int y;
            String text;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));


            text = "HAS GANADO";
            //SOMBRA
            g2.setColor(new Color(213,181,110));
            x = getXforCenteredText(text);
            y = gp.tileSize*4;
            g2.drawString(text, x, y);
            //MAIN
            g2.setColor(Color.yellow);
            g2.drawString(text, x-4, y-4);

            //IMAGEN?????
            x = gp.screenWidth/2 - GGImage.getWidth()/2;
            y += gp.tileSize/2;
            g2.drawImage(GGImage, x, y, null);

            g2.setColor(white);

            //Puntuacion
            g2.setFont(g2.getFont().deriveFont(50F));
            text = "Tiempo tardado: " + gp.getTotalTimePlayed() + " segundos";
            x = getXforCenteredText(text);
            y = gp.tileSize*10;
            g2.drawString(text, x, y);


            //VOLVER AL MENU
            text = "Salir";
            x = getXforCenteredText(text);
            y += 55;
            g2.drawString(text, x, y);
            if(commandNum == 0 || commandNum == 1) {
                g2.drawString(">", x-40, y);
                titleScreenState = 0;
            }

        }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0,0,0));//si quiero que sea transparente puedo añadir luego del ultimo 0 un 150.
        g2.fillRect(0, 0 , gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));


        text = "HAS MUERTO";
        //SOMBRA
        g2.setColor(new Color(120,0,0));
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //MAIN
        g2.setColor(Color.red);
        g2.drawString(text, x-4, y-4);

        //IMAGEN?????
        x = gp.screenWidth/2 - gameOverImage.getWidth()/2;
        y += gp.tileSize*2;
        g2.drawImage(gameOverImage, x, y, null);
        //IMAGEN2?????
        x = gp.screenWidth/8 - gato_riendose.getWidth()/2;
        y += gp.tileSize-300;
        g2.drawImage(gato_riendose, x, y, null);

        g2.setColor(white);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Revivir";
        x = getXforCenteredText(text);
        y = gp.tileSize*10;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        //VOLVER AL MENU
        text = "Salir";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
            titleScreenState = 0;
        }

    }
    public void  drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY);break;
            case 3: endGameConfirmation(frameX, frameY);break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        //TITLE
        String text = "Opciones";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Pantalla completa", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                if(!gp.fullScreenOn) {
                    gp.fullScreenOn = true;
                }
                else if (gp.fullScreenOn) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Musica", textX, textY);
        if(commandNum == 01) {
            g2.drawString(">", textX-25, textY);
        }

        //SOUND EFFECTS
        textY += gp.tileSize;
        g2.drawString("SFX", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Controles", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("Salir", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        //Cerrar ventana
        textY += gp.tileSize*2;
        g2.drawString("Atrás", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*6 + 10);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn) {
            g2.fillRect(textX, textY, 24,24);
        }

        //Barra de musica
        textX = frameX + (int)(gp.tileSize*3+10);
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //Barra SFX
        textX = frameX + (int)(gp.tileSize*3+10);
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();

    }
    public void options_fullScreenNotification(int frameX, int frameY) {

        int textX;
        int textY;

        //TITLE
        String text = "Pantalla completa";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*3;




        currentDialogue = "Los cambios tendran\nefecto tras reiniciar\nel juego.\n\n\nPerdon por las molestias.\n-Dylan.";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //ATRAS
        textY = frameY + gp.tileSize*9;
        g2.drawString("Atrás" , textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        //TITLE
        String text = "Controles";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize-10;
        textY += gp.tileSize;
        g2.drawString("Moverse", textX, textY);textY +=gp.tileSize;
        g2.drawString("Aceptar/Atacar", textX, textY);textY +=gp.tileSize;
        g2.drawString("Disparar", textX, textY);textY +=gp.tileSize;
        g2.drawString("Protegerse", textX, textY);textY +=gp.tileSize;
        g2.drawString("Inventario", textX, textY);textY +=gp.tileSize;
        g2.drawString("Pausa", textX, textY);textY +=gp.tileSize;
        g2.drawString("Opciones", textX, textY);textY +=gp.tileSize;
        g2.drawString("Mapa", textX, textY);textY +=gp.tileSize;



        textX = frameX + gp.tileSize*4;
        textY += frameY + gp.tileSize-480;
        g2.setColor(Color.yellow);
        g2.drawString("WASD/Flechas", textX, textY);textY +=gp.tileSize;
        g2.drawString("Enter/Z", textX+60, textY);textY +=gp.tileSize;
        g2.drawString("Shift/X", textX, textY);textY +=gp.tileSize;
        g2.drawString("Espacio", textX, textY);textY +=gp.tileSize;
        g2.drawString("P/C", textX, textY);textY +=gp.tileSize;
        g2.drawString("Backspace", textX, textY);textY +=gp.tileSize;
        g2.drawString("ESC", textX, textY);textY +=gp.tileSize;
        g2.drawString("M", textX, textY);textY +=gp.tileSize;

        //BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize-70;
        g2.setColor(white);
        g2.drawString("Atrás" , textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void endGameConfirmation(int frameX, int frameY) {


        int textX;
        int textY;

        //TITLE
        String text = "Salir del juego";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

      textX = frameX + gp.tileSize;
      textY = frameY + gp.tileSize*3;

      currentDialogue = "¿Salir del juego\ny volver al menu?";

      for(String line: currentDialogue.split("\n")) {
          g2.drawString(line, textX, textY);
          textY += 40;
      }

        //YES
        text = "Si";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                gp.gameState = gp.titleState;
                titleScreenState = 0;
                gp.resetGame(false);
                gp.stopMusic();
                gp.playMusic(6);


            }
        }

        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 4;
            }
        }

    }
    public void drawTradeScreen(){
        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){

        npc.dialogueSet = 0;
        drawDialogueScreenTrader();

        //DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 6-30;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        //DRAW TEXTS
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Comprar" , x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-10, y);
            if(gp.keyH.enterPressed) {
                subState = 1;
            }
        }
        y+= gp.tileSize;
        g2.drawString("Vender" , x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-10, y);
            if(gp.keyH.enterPressed) {
                subState = 2;
            }
        }
        y+= gp.tileSize;
        g2.drawString("Salir" , x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-10, y);
            if(gp.keyH.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 5);
                gp.gameState = gp.dialogueState;
            }
        }
        y+= gp.tileSize;



    }
    public void trade_buy(){

        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        //DRAW NPC INVENTORY
        drawInventory(npc, true);

        //DRAW PLAYER COIN WINDOW
        int x = gp.tileSize*13;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Dinero: " + gp.player.coin, x+24, y+60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size()) {

            x = (int)(gp.tileSize*6.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+10, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = " " + price;
            x = getXforAllignToRightText(text, gp.tileSize*9);
            g2.drawString(text, x, y+32);

            //BUY AN ITEM
            if(gp.keyH.enterPressed) {
                if(npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    npc.startDialogue(npc, 2);
                }
                else {
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                        gp.playSE(31);
                    }
                    else {
                        subState = 0;
                        npc.startDialogue(npc, 3);
                    }
                }
            }
        }
    }
    public void trade_sell(){
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;
        //DRAW PLAYER COIN WINDOW
        x = gp.tileSize*7;
        y = gp.tileSize*5;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Dinero: " + gp.player.coin, x+24, y+60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()) {

            x = (int) (gp.tileSize * 16.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 10, 32, 32, null);

            int sellPrice = gp.player.inventory.get(itemIndex).sellPrice;
            String text = " " + sellPrice;
            x = getXforAllignToRightText(text, gp.tileSize * 18+30);
            g2.drawString(text, x, y + 32);

            //SELL AN ITEM
            if (gp.keyH.enterPressed) {

                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                commandNum = 0;
                subState = 0;
                npc.startDialogue(npc, 4);
                }
                else {
                    if(gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += sellPrice;
                    gp.playSE(31);

                }
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        return slotCol + (slotRow *5);
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text) {
        int lenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
    public int getXforAllignToRightText(String text, int tailX) {
        int lenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return tailX-25 - lenght;
    }
}
