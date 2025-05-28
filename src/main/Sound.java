package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL [50];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    public Sound() {

        //titulo
        soundURL[6] = getClass().getResource("/sound/titleScreen.wav");
        //musica de basement1
        soundURL[0] = getClass().getResource("/sound/main1.wav");
        soundURL[1] = getClass().getResource("/sound/doorOpen.wav");
        soundURL[2] = getClass().getResource("/sound/fart.wav");
        soundURL[3] = getClass().getResource("/sound/KeyPickup.wav");
        soundURL[4] = getClass().getResource("/sound/doorClosed.wav");
        //Cofre abierto
        soundURL[5] = getClass().getResource("/sound/WOW.wav");
        //Sin usar aun pero es el sonido de pony island
        soundURL[7] = getClass().getResource("/sound/select.wav");
        //cuando el jugador recibe daño
        soundURL[8] = getClass().getResource("/sound/ouch.wav");
        soundURL[9] = getClass().getResource("/sound/enemyDies.wav");
        soundURL[10] = getClass().getResource("/sound/chylandraAttack.wav");
        soundURL[11] = getClass().getResource("/sound/enemyHurt.wav");
        //cuando te curas en una zona segura
        soundURL[12] = getClass().getResource("/sound/heal.wav");
        //teletransportes
        soundURL[13] = getClass().getResource("/sound/portal1.wav");
        soundURL[14] = getClass().getResource("/sound/portal2.wav");
        soundURL[15] = getClass().getResource("/sound/levelUP.wav");
        //Moverse en el menu
        soundURL[16] = getClass().getResource("/sound/cursor.wav");
        soundURL[17] = getClass().getResource("/sound/horseSht.wav");
        //ataque espadoso
        soundURL[18] = getClass().getResource("/sound/slash.wav");
        //nom nom nom
        soundURL[19] = getClass().getResource("/sound/yummy.wav");
        soundURL[20] = getClass().getResource("/sound/fireball.wav");
        soundURL[21] = getClass().getResource("/sound/coin.wav");
        soundURL[22] = getClass().getResource("/sound/heart.wav");
        soundURL[23] = getClass().getResource("/sound/mana.wav");
        soundURL[24] = getClass().getResource("/sound/rockdestroyed.wav");
        soundURL[25] = getClass().getResource("/sound/BradneyTheme.wav");
        //sonido de seleccionar
        soundURL[26] = getClass().getResource("/sound/aceptar.wav");
        soundURL[27] = getClass().getResource("/sound/gameOver.wav");
        // Musica de otras zonas
        soundURL[28] = getClass().getResource("/sound/cambiarZona.wav");
        soundURL[29] = getClass().getResource("/sound/safeZone.wav");
        soundURL[30] = getClass().getResource("/sound/BillsonTheme.wav");
        //caching
        soundURL[31] = getClass().getResource("/sound/shoping.wav");
        soundURL[32] = getClass().getResource("/sound/nickel.wav");
        soundURL[33] = getClass().getResource("/sound/dime.wav");
        soundURL[34] = getClass().getResource("/sound/usedKey.wav");
        soundURL[35] = getClass().getResource("/sound/burp.wav");
        //sala con cofre importante
        soundURL[36] = getClass().getResource("/sound/Stargazer.wav");
        soundURL[37] = getClass().getResource("/sound/chest.wav");
        //pathfinding activado
        soundURL[38] = getClass().getResource("/sound/angry.wav");
        //escudo
        soundURL[39] = getClass().getResource("/sound/blocked.wav");
        soundURL[40] = getClass().getResource("/sound/parry.wav");
        soundURL[41] = getClass().getResource("/sound/weak_parry.wav");
        //voces
        soundURL[45] = getClass().getResource("/sound/snd_txtspam.wav");
        soundURL[42] = getClass().getResource("/sound/Billson_voice.wav");
        soundURL[43] = getClass().getResource("/sound/Guardarin_voice.wav");
        soundURL[44] = getClass().getResource("/sound/Bradney_voice.wav");
        soundURL[46] = getClass().getResource("/sound/default_sfx.wav");
        // Matadero
        soundURL[47] = getClass().getResource("/sound/AnotherMedium.wav");
        //Creditos
        soundURL[48] = getClass().getResource("/sound/creditos.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch(Exception e) {
        }
    }
    public void play() {

        clip.start();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        if(clip != null && clip.isRunning())
        {
            clip.stop();
        }
    }

    public void resume(){
        if(clip != null && !clip.isRunning()) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {

        clip.stop();
    }
    public void checkVolume(){
        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -40f;break;
            case 2: volume = -20f;break;
            case 3: volume = -12f;break;
            case 4: volume = -5f;break;
            case 5: volume = 1f;break;
        }
        fc.setValue(volume);
    }

    public void setPosition(long position) {
        if (clip != null) {
            clip.setMicrosecondPosition(position);
        }
    }

    public long getPosition() {
        if (clip != null) {
            long position = clip.getMicrosecondPosition();
            long length = clip.getMicrosecondLength();
            return position % length;
        }
        return 0;
    }
}
