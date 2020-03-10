package kbs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {
    private static final String SOUND_FOLDER = "./sounds/";
    private Config config;
    private File soundFile;
    private boolean tapping = false;

    public Sound(Config config, boolean tapping) {
        this.config = config;
        if (!tapping) {
            soundFile = new File(SOUND_FOLDER + config.clickSound);
        } else {
            soundFile = new File(SOUND_FOLDER + config.tapSound);
        }
        this.tapping = tapping;
    }

    public void play() {
        try {
            // Load sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Set volume according to Config
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(20f * (float) Math.log10(getVolume()));

            // Play sound
            clip.setFramePosition(config.framePos);
            clip.start();

            // Closing audio stream
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float getVolume() {
        if (tapping) {
            return config.tapVolume;
        } else {
            return config.clickVolume;
        }
    }

}
