package main;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Teclado implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
    //DEBUG
    public boolean showDebugText = false;
    public boolean godModeOn = false;


    public Teclado(GamePanel gp) {
        this.gp = gp;
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (gp.gameState == gp.titleState) {
                        titleState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.playState) {
                        playState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.pauseState) {
                        pauseState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.dialogueState) {
                        dialogueState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.characterState) {
                        characterState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.optionsState) {
                        optionsState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.gameOverState) {
                        gameOverState(KeyEvent.VK_ENTER);
                    } else if (gp.gameState == gp.tradeState) {
                        tradeState(KeyEvent.VK_ENTER);
                    } else if(gp.gameState == gp.winState) {
                        gameOverState(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (gp.gameState == gp.titleState) {
                        titleState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.playState) {
                        playState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.pauseState) {
                        pauseState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.dialogueState) {
                        dialogueState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.characterState) {
                        characterState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.optionsState) {
                        optionsState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.gameOverState) {
                        gameOverState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.tradeState) {
                        tradeState(KeyEvent.VK_X);
                    } else if (gp.gameState == gp.winState) {
                        gameOverState(KeyEvent.VK_X);
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    enterPressed = false;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    shotKeyPressed = false;
                }
            }
        });
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {titleState(code);}

        //PLAYSTATE
        else if (gp.gameState == gp.playState) {playState(code);}

        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {pauseState(code);}

        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {dialogueState(code);}

        //CHARACTER STATE
        else if (gp.gameState == gp.characterState) {characterState(code);}
        //OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {optionsState(code);}
        //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {gameOverState(code);}
        //TRADE STATE
        else if(gp.gameState == gp.tradeState) {tradeState(code);}
        //MAP STATE
        else if (gp.gameState == gp.mapState) {mapState(code);}
        //WIN STATE
        else if (gp.gameState == gp.winState) {gameOverState(code);}
}



        public void titleState (int code) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSE(16);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSE(16);
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.player = new Player(gp, gp.keyH, "chico");
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        gp.stopMusic();
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                    gp.playSE(26);
                }
            } else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSE(16);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSE(16);
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                    if (gp.ui.commandNum == 0) {
                        gp.player = new Player(gp, gp.keyH, "chico");
                        gp.aSetter.setObject("chico");
                        gp.ui.titleScreenState = 1;
                        gp.stopMusic();
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.player = new Player(gp, gp.keyH, "chica");
                        gp.aSetter.setObject("chica");
                        gp.ui.titleScreenState = 1;
                        gp.ui.commandNum = 0;
                        gp.stopMusic();
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.titleScreenState = 0;
                    }
                    gp.playSE(26);
                }
            }
        }
        public void playState (int code){
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    upPressed = true;
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    downPressed = true;
                }
                if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                }
                if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                }
                if (code == KeyEvent.VK_BACK_SPACE) {
                    gp.gameState = gp.pauseState;
                }
                if (code == KeyEvent.VK_C || code == KeyEvent.VK_P) {
                    gp.playSE(26);
                    gp.gameState = gp.characterState;
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                    enterPressed = true;
                }
                if(code == KeyEvent.VK_X || code == KeyEvent.VK_SHIFT) {
                    shotKeyPressed = true;
                }
                if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.optionsState;
                }
                if(code == KeyEvent.VK_M) {
                    gp.gameState = gp.mapState;
                }
                if(code == KeyEvent.VK_L) {
                    if(!gp.map.miniMapOn) {
                        gp.map.miniMapOn = true;
                    }
                    else {
                        gp.map.miniMapOn = false;
                    }
                }
                if(code == KeyEvent.VK_SPACE) {
                    gp.playSE(39);
                    spacePressed = true;
                }

                // DEBUG
                if (code == KeyEvent.VK_T) {
                    showDebugText = !showDebugText;
                }
            if (code == KeyEvent.VK_G) {
                godModeOn = !godModeOn;
            }
            }
        public void pauseState (int code) {
            if (code != KeyEvent.VK_BACK_SPACE) {
                gp.gameState = gp.playState;
            }
        }
        public void dialogueState (int code) {
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_X) {
                enterPressed = true;
                gp.playSE(26);
            }
        }
        public void characterState (int code){
            if (code == KeyEvent.VK_C || code == KeyEvent.VK_P || code == KeyEvent.VK_X) {
                gp.playSE(26);
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                gp.player.selectItem();
                gp.playSE(26);
            }
            playerInventory(code);
        }
        public void optionsState (int code) {
            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_X ) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                enterPressed = true;
                gp.playSE(26);
            }

            int maxCommandNum = 0;
            switch (gp.ui.subState) {
                case 0: maxCommandNum = 5; break;
                case 3: maxCommandNum = 1; break;

            }
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(16);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(16);
                if (gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                        gp.playSE(26);
                    }
                    if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                        gp.se.volumeScale--;
                        gp.playSE(26);
                    }
                }
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                        gp.playSE(26);
                    }
                    if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                        gp.se.volumeScale++;
                        gp.playSE(26);
                    }
                }
            }
        }
        public void gameOverState(int code) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
                gp.playSE(16);
            }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                    gp.playSE(16);
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
                    if(gp.ui.commandNum == 0) {
                        gp.playSE(26);
                        gp.resetGame(false);
                        gp.gameState = gp.playState;
                    }

                    else if(gp.ui.commandNum == 1) {
                        gp.playSE(26);
                        gp.gameState = gp.titleState;
                        gp.ui.commandNum = 0;
                        gp.resetGame(true);
                    }
                }


        }
        public void tradeState(int code) {
        if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_Z) {
            enterPressed = true;
            gp.playSE(26);
        }
        if(gp.ui.subState == 0) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(16);
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(16);
            }
        }
        if(gp.ui.subState == 1) {
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_X) {
                gp.ui.subState = 0;
            }
        }
        if(gp.ui.subState == 2) {
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_X) {
                gp.ui.subState = 0;
            }
        }
    }
    public void mapState(int code) {

        if(code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }
        public void playerInventory(int code) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                if(gp.ui.playerSlotRow != 0) {
                    gp.ui.playerSlotRow--;
                    gp.playSE(16);
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                if (gp.ui.playerSlotRow != 3) {
                    gp.ui.playerSlotRow++;
                    gp.playSE(16);
                }
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                if(gp.ui.playerSlotCol != 0) {
                    gp.ui.playerSlotCol--;
                    gp.playSE(16);
                }
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if (gp.ui.playerSlotCol != 4) {
                    gp.ui.playerSlotCol++;
                    gp.playSE(16);
                }
            }
        }
        public void npcInventory(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(16);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(16);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(16);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(16);
            }
        }
    }



        @Override
        public void keyReleased (KeyEvent e){

            int code = e.getKeyCode();


            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if(code == KeyEvent.VK_X || code == KeyEvent.VK_SHIFT) {
                shotKeyPressed = false;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = false;
            }
            if(code == KeyEvent.VK_SPACE) {
                spacePressed = false;
            }
        }
    }



