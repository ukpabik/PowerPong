 package audio;
 
 import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class SoundEffects {
	public static void playSound(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		/**
		 * FINDING THE FILE USING RESOURCE AS STREAM INSTEAD OF CLASS PATH WITH FILES
		 * RESOURCE BASED ACCESS WORKS BETTER
		 */
		
		InputStream audio = SoundEffects.class.getResourceAsStream("/media/" + fileName);
		if (audio == null) {
			throw new IOException("Sound file not found: " + fileName);
		}
		InputStream buffAudio = new BufferedInputStream(audio);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffAudio);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		clip.start();
	}
}
