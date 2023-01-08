package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip; // We use this to open audio files and import
    URL[] soundURL = new URL[30]; // Store file paths of the sound files
    FloatControl fc; // Provides control over a range of floating-point values
    int volumeScale = 3; // Default
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
    }

    // FORMAT TO OPEN AUDIO FILE IN JAVA
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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

        // VOLUME LEVELS -80f to 6f (-30f to -70f sounds all the same)
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
