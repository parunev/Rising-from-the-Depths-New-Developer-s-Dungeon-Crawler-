package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip; // We use this to open audio files and import
    URL[] soundURL = new URL[30]; // Store file paths of the sound files

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
    }

    // FORMAT TO OPEN AUDIO FILE IN JAVA
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
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
}
