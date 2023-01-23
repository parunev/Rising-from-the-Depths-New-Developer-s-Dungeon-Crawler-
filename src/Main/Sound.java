package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[100];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){
        soundURL[0] = getClass().getResource("/Resources/Sounds/theme_music.wav");
        soundURL[1] = getClass().getResource("/Resources/Sounds/coin.wav");
        soundURL[2] = getClass().getResource("/Resources/Sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/Resources/Sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/Resources/Sounds/fanfare.wav");
        soundURL[5] = getClass().getResource("/Resources/Sounds/hitmonster.wav");
        soundURL[6] = getClass().getResource("/Resources/Sounds/receivedamage.wav");
        soundURL[7] = getClass().getResource("/Resources/Sounds/swingsword.wav");
        soundURL[8] = getClass().getResource("/Resources/Sounds/levelup.wav");
        soundURL[9] = getClass().getResource("/Resources/Sounds/cursor.wav");
        soundURL[10] = getClass().getResource("/Resources/Sounds/fireball.wav");
        soundURL[11] = getClass().getResource("/Resources/Sounds/cuttree.wav");
        soundURL[12] = getClass().getResource("/Resources/Sounds/gameover.wav");
        soundURL[13] = getClass().getResource("/Resources/Sounds/stairs.wav");
        soundURL[14] = getClass().getResource("/Resources/Sounds/sleep.wav");
        soundURL[15] = getClass().getResource("/Resources/Sounds/blocked.wav");
        soundURL[16] = getClass().getResource("/Resources/Sounds/parry.wav");
        soundURL[17] = getClass().getResource("/Resources/Sounds/Dungeon.wav");
        soundURL[18] = getClass().getResource("/Resources/Sounds/chipwall.wav");
        soundURL[19] = getClass().getResource("/Resources/Sounds/dooropen.wav");
        soundURL[20] = getClass().getResource("/Resources/Sounds/Battle.wav");
        soundURL[21] = getClass().getResource("/Resources/Sounds/chest_open.wav");
        soundURL[22] = getClass().getResource("/Resources/Sounds/heal.wav");
        soundURL[23] = getClass().getResource("/Resources/Sounds/strength_up.wav");
        soundURL[24] = getClass().getResource("/Resources/Sounds/speed_up.wav");
        soundURL[25] = getClass().getResource("/Resources/Sounds/trade.wav");
        soundURL[26] = getClass().getResource("/Resources/Sounds/denied.wav");
        soundURL[27] = getClass().getResource("/Resources/Sounds/pause.wav");
        soundURL[28] = getClass().getResource("/Resources/Sounds/unpause.wav");
        soundURL[29] = getClass().getResource("/Resources/Sounds/teleport.wav");
        soundURL[30] = getClass().getResource("/Resources/Sounds/save.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch (Exception ignored){}
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void checkVolume(){
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
    }
}
