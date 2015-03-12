package framework.sound;

import framework.util.exceptions.DogWoodException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author William Gervasio
 */
public class Sound {

    private final AudioInputStream inputStream;
    private final Clip clip;

    public Sound(File audioFile) throws DogWoodException {

        try {

            inputStream = AudioSystem.getAudioInputStream(audioFile);
            final AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(inputStream);
        } catch(IOException e) {
            e.printStackTrace();
            throw new DogWoodException("Failed to load sound");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new DogWoodException("Failed to load sound");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new DogWoodException("Failed to load sound");
        }
    }

    public void play() throws DogWoodException{


        clip.setFramePosition(0);

        if(!clip.isActive()) {
            clip.start();
        }

    }
}
